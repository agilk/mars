package az.kerimov.mars.services;

import az.kerimov.mars.entity.*;
import az.kerimov.mars.pojo.MarsException;
import az.kerimov.mars.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class MarsService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardDeckRepository cardDeckRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameCardRepository gameCardRepository;
    @Autowired
    private GamePlayerCardRepository gamePlayerCardRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GamePlayerMatRepository gamePlayerMatRepository;
    @Autowired
    private ExceptionRepository exceptionRepository;

    private List<Card> getAllCardsByDecks(CardDeck deck) {
        return cardRepository.findAllByCardDeck(deck);
    }

    private List<Card> getAllCardsByDecks(List<Integer> decks) {
        List<Card> cards = new ArrayList<>();
        for (Integer i : decks) {
            CardDeck deck = cardDeckRepository.findById(i);
            cards.addAll(getAllCardsByDecks(deck));
        }
        return cards;
    }


    public Card getCardById(Integer id) {
        return gameCardRepository.findById(id).getCard();
    }

    public Game getGameByIdAndHash(Integer id, String hash) {
        return gameRepository.findByIdAndHash(id, hash);
    }

    public Game startNewGame(List<Integer> decks, List<String> players) throws MarsException {

        char mode = players.size() == 1 ? 's' : 'm';

        Game game = new Game(mode);
        gameRepository.save(game);

        for (String pl : players) {
            Player p = new Player(game, pl);
            playerRepository.save(p);

            GamePlayerMat gamePlayerMat = new GamePlayerMat(game, mode, p);
            gamePlayerMatRepository.save(gamePlayerMat);
        }


        for (Card card : getAllCardsByDecks(decks)) {
            GameCard gameCard = new GameCard(game, card);
            gameCardRepository.save(gameCard);
        }

        return game;
    }

    public List<Player> getPlayersOfGame(Game game) throws MarsException {
        return playerRepository.findAllByGame(game);
    }

    private List<GameCard> getAvailableCards(Game game) {
        return gameCardRepository.findAllByGameAndGenerationIdAndPlayerId(game, 0, 0);
    }

    private GameCard showRandomCard(Game game, Integer playerId) {

        List<GameCard> cards = getAvailableCards(game);

        Random random = new Random();

        Integer rand = random.nextInt(cards.size());

        GameCard gameCard = cards.get(rand);

        gameCard.setGenerationId(game.getGeneration());
        gameCard.setPlayerId(playerId);
        gameCardRepository.save(gameCard);

        return gameCard;
    }

    public GameCard showRandomCard(Integer gameId, String gameHash, Integer playerId) throws MarsException {
        Game game = gameRepository.findByIdAndHash(gameId, gameHash);
        return showRandomCard(game, playerId);

    }

    private List<GameCard> showGenerationCards(Game game, Integer playerId) {
        List<GameCard> cards = new ArrayList<>();
        if (gameCardRepository.findAllByGameAndGenerationIdAndPlayerId(game, game.getGeneration(), playerId).size() == 0) {
            Integer countCard = game.getGeneration() == 1 ? 10 : 4;
            for (int i = 0; i < countCard; i++) {
                cards.add(showRandomCard(game, playerId));
            }
        }
        return cards;
    }

    public List<GameCard> showGenerationCards(Integer gameId, String gameHash, Integer playerId) throws MarsException {
        Game game = gameRepository.findByIdAndHash(gameId, gameHash);
        return showGenerationCards(game, playerId);

    }

    public GamePlayerMat sellCard(Integer playerId, Integer gameId, String gameHash, Integer cardId) throws MarsException {
        Game game = gameRepository.findByIdAndHash(gameId, gameHash);
        Card card = gameCardRepository.findById(cardId).getCard();
        return sellCard(playerId, game, card);
    }

    private GamePlayerMat sellCard(Integer playerId, Game game, Card card) throws MarsException {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGameAndPlayer(game, playerRepository.findById(playerId));
        GamePlayerCard gamePlayerCard = gamePlayerCardRepository.findByGameAndCard(game, card);
        gamePlayerCard.setGenerationId(-1);
        gamePlayerMat.setMoney(gamePlayerMat.getMoney() + 1);
        gamePlayerCardRepository.save(gamePlayerCard);
        gamePlayerMatRepository.save(gamePlayerMat);
        return gamePlayerMat;
    }

    public List<Card> pickUpCards(Integer playerId, Integer gameId, String gameHash, List<Integer> cardIds) throws MarsException {
        List<GameCard> gameCards = new ArrayList<>();
        for (Integer i : cardIds) {
            gameCards.add(gameCardRepository.findById(i));
        }
        Game game = gameRepository.findByIdAndHash(gameId, gameHash);
        return pickUpCards(playerId, game, gameCards);
    }

    private List<Card> pickUpCards(Integer playerId, Game game, List<GameCard> cards) throws MarsException {
        List<Card> res = new ArrayList<>();
        for (GameCard card : cards) {
            res.add(pickUpCard(playerId, game, card));
        }
        return res;
    }

    public Card pickUpCard(Integer playerId, Integer gameId, String gameHash, Integer cardId) throws MarsException {
        Game game = gameRepository.findByIdAndHash(gameId, gameHash);
        GameCard gameCard = gameCardRepository.findById(cardId);
        return pickUpCard(playerId, game, gameCard);
    }

    private Card pickUpCard(Integer playerId, Game game, GameCard card) throws MarsException {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGameAndPlayer(game, playerRepository.findById(playerId));
        GameCard gameCard = gameCardRepository.findByGameAndCard(game, card.getCard());
        if (gamePlayerMat.getMoney() >= 3) {
            gamePlayerMat.setMoney(gamePlayerMat.getMoney() - 3);
            GamePlayerCard gamePlayerCard = new GamePlayerCard();
            gamePlayerCard.setCard(card.getCard());
            gamePlayerCard.setGame(game);
            gamePlayerCard.setGenerationId(0);
            gamePlayerCard.setPlayer(playerRepository.findById(playerId));
            gameCard.setPicked(1);
            gamePlayerMatRepository.save(gamePlayerMat);
            gamePlayerCardRepository.save(gamePlayerCard);
            gameCardRepository.save(gameCard);
            return card.getCard();
        } else {
            throw new MarsException(exceptionRepository.findById(1));
        }
    }

    public Card pickUpFreeCard(Integer playerId, Integer gameId, String gameHash, Integer cardId) throws MarsException {
        Game game = gameRepository.findByIdAndHash(gameId, gameHash);
        Card card = gameCardRepository.findById(cardId).getCard();
        return pickUpFreeCard(playerId, game, card);
    }

    private Card pickUpFreeCard(Integer playerId, Game game, Card card) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGameAndPlayer(game, playerRepository.findById(playerId));
        GamePlayerCard gamePlayerCard = new GamePlayerCard();
        gamePlayerCard.setCard(card);
        gamePlayerCard.setGame(game);
        gamePlayerCard.setGenerationId(0);
        gamePlayerMatRepository.save(gamePlayerMat);
        gamePlayerCardRepository.save(gamePlayerCard);
        return card;
    }

    private void raiseOceans(Integer playerId, Game game) {
        if (game.getOceans() >= 9) {
            return;
        }
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGameAndPlayer(game, playerRepository.findById(playerId));
        game.setOceans(game.getOceans() + 1);
        gameRepository.save(game);
        gamePlayerMat.setRating(gamePlayerMat.getRating() + 1);
        gamePlayerMatRepository.save(gamePlayerMat);
    }

    private void raiseOxygen(Integer playerId, Game game) {
        if (game.getOxygen() >= 14) {
            return;
        }
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGameAndPlayer(game, playerRepository.findById(playerId));
        game.setOxygen(game.getOxygen() + 1);
        gameRepository.save(game);
        gamePlayerMat.setRating(gamePlayerMat.getRating() + 1);
        if (game.getOxygen() == 8) {
            raiseTemperature(playerId, game);
        }
        gamePlayerMatRepository.save(gamePlayerMat);
    }

    private void raiseTemperature(Integer playerId, Game game) {
        if (game.getTemperature() >= 8) {
            return;
        }
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGameAndPlayer(game, playerRepository.findById(playerId));
        game.setTemperature(game.getTemperature() + 2);
        gameRepository.save(game);
        gamePlayerMat.setRating(gamePlayerMat.getRating() + 1);
        if (game.getTemperature() == -24 || game.getTemperature() == -20) {
            gamePlayerMat.setProdHeat(gamePlayerMat.getProdHeat() + 1);
        }
        if (game.getTemperature() == 0) {
            raiseOceans(playerId, game);
        }
        gamePlayerMatRepository.save(gamePlayerMat);
    }

    public Game raiseTemperatureByHeat(Integer playerId, Integer gameId, String gameHash)  throws MarsException{
        Game game = gameRepository.findByIdAndHash(gameId, gameHash);
        raiseTemperatureByHeat(playerId, game);
        return game;
    }

    public void raiseTemperatureByHeat(Integer playerId, Game game) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGameAndPlayer(game, playerRepository.findById(playerId));
        if (gamePlayerMat.getHeat() >= 8) {
            raiseTemperature(playerId, game);
            gamePlayerMat.setHeat(gamePlayerMat.getHeat() - 8);
            gamePlayerMatRepository.save(gamePlayerMat);
        }
    }


    public Game addGreenery(Integer playerId, Integer gameId, String gameHash) throws  MarsException{
        Game game = gameRepository.findByIdAndHash(gameId, gameHash);
        addGreenery(playerId, game);
        return game;
    }

    public void addGreenery(Integer playerId, Game game) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGameAndPlayer(game, playerRepository.findById(playerId));
        if (gamePlayerMat.getPlant() >= 8) {
            raiseOxygen(playerId, game);
            gamePlayerMat.setPlant(gamePlayerMat.getPlant() - 8);
            gamePlayerMat.setTileGreen(gamePlayerMat.getTileGreen() + 1);
            gamePlayerMatRepository.save(gamePlayerMat);
        }
    }



    public Game addOcean(Integer playerId, Integer gameId, String gameHash) throws  MarsException{
        Game game = gameRepository.findByIdAndHash(gameId, gameHash);
        addOcean(playerId, game);
        return game;
    }
    public void addOcean(Integer playerId, Game game) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGameAndPlayer(game, playerRepository.findById(playerId));
        if (gamePlayerMat.getMoney() >= 18) {
            raiseOceans(playerId, game);
            gamePlayerMat.setMoney(gamePlayerMat.getMoney() - 18);
            gamePlayerMatRepository.save(gamePlayerMat);
        }
    }

    public Game addGreeneryForMoney(Integer playerId, Integer gameId, String gameHash) throws  MarsException{
        Game game = gameRepository.findByIdAndHash(gameId, gameHash);
        addGreeneryForMoney(playerId, game);
        return game;
    }

    public void addGreeneryForMoney(Integer playerId, Game game) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGameAndPlayer(game, playerRepository.findById(playerId));
        if (gamePlayerMat.getMoney() >= 23) {
            raiseOxygen(playerId, game);
            gamePlayerMat.setMoney(gamePlayerMat.getMoney() - 23);
            gamePlayerMat.setTileGreen(gamePlayerMat.getTileGreen() + 1);
            gamePlayerMatRepository.save(gamePlayerMat);
        }
    }

    private void raiseRating(Integer playerId, Game game) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGameAndPlayer(game, playerRepository.findById(playerId));
        gamePlayerMat.setRating(gamePlayerMat.getRating() + 1);
        gamePlayerMatRepository.save(gamePlayerMat);
    }

    private void raiseMat(Integer playerId, Game game, char res, boolean prod, Integer count) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGameAndPlayer(game, playerRepository.findById(playerId));
        for (int i = 0; i < count; i++) {
            switch (res) {
                case 'm': {
                    if (prod) {
                        gamePlayerMat.setProdMoney(gamePlayerMat.getProdMoney() + 1);
                    } else {
                        gamePlayerMat.setProdMoney(gamePlayerMat.getProdMoney() + 1);
                    }
                    break;
                }
                case 's': {
                    if (prod) {
                        gamePlayerMat.setProdSteel(gamePlayerMat.getProdSteel() + 1);
                    } else {
                        gamePlayerMat.setSteel(gamePlayerMat.getSteel() + 1);
                    }
                    break;
                }
                case 't': {
                    if (prod) {
                        gamePlayerMat.setProdTitan(gamePlayerMat.getProdTitan() + 1);
                    } else {
                        gamePlayerMat.setTitan(gamePlayerMat.getTitan() + 1);
                    }
                    break;
                }
                case 'p': {
                    if (prod) {
                        gamePlayerMat.setProdPlant(gamePlayerMat.getProdPlant() + 1);
                    } else {
                        gamePlayerMat.setPlant(gamePlayerMat.getPlant() + 1);
                    }
                    break;
                }
                case 'e': {
                    if (prod) {
                        gamePlayerMat.setProdEnergy(gamePlayerMat.getProdEnergy() + 1);
                    } else {
                        gamePlayerMat.setEnergy(gamePlayerMat.getEnergy() + 1);
                    }
                    break;
                }
                case 'h': {
                    if (prod) {
                        gamePlayerMat.setProdHeat(gamePlayerMat.getProdHeat() + 1);
                    } else {
                        gamePlayerMat.setHeat(gamePlayerMat.getHeat() + 1);
                    }
                    break;
                }
            }
        }
        gamePlayerMatRepository.save(gamePlayerMat);
    }

    private void checkCardRequirements(Integer playerId, Game game, Card card, boolean useResource, char resource, Integer resourceCount) throws MarsException {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGameAndPlayer(game, playerRepository.findById(playerId));

        Integer resourceMoney = 0;

        if (useResource) {
            switch (resource) {
                case 's':
                    if (gamePlayerMat.getSteel() < resourceCount)
                        throw new MarsException(exceptionRepository.findById(2));
                    if (card.getTagBuilding() == 0) throw new MarsException(exceptionRepository.findById(3));
                    resourceMoney += resourceCount * 2;
                    break;
                case 't':
                    if (gamePlayerMat.getTitan() < resourceCount)
                        throw new MarsException(exceptionRepository.findById(5));
                    if (card.getTagSpace() == 0) throw new MarsException(exceptionRepository.findById(6));
                    resourceMoney += resourceCount * 3;
                    break;
                case 'h':
                    if (gamePlayerMat.getHeat() < resourceCount)
                        throw new MarsException(exceptionRepository.findById(7));
                    resourceMoney += resourceCount;
                    break;
            }
        }

        if (gamePlayerMat.getMoney() + resourceMoney < card.getCost()) {
            throw new MarsException(exceptionRepository.findById(8));
        }

        if (gamePlayerMat.getRating() < card.getReqMinRating() || gamePlayerMat.getRating() > card.getReqMaxRating()) {
            throw new MarsException(exceptionRepository.findById(9));
        }

        if (game.getOceans() < card.getReqMinOcean() || game.getOceans() > card.getReqMaxOcean()) {
            throw new MarsException(exceptionRepository.findById(10));
        }

        if (game.getOxygen() < card.getReqMinOxyg() || game.getOxygen() > card.getReqMaxOxyg()) {
            throw new MarsException(exceptionRepository.findById(11));
        }

        if (game.getTemperature() < card.getReqMinTemp() || game.getTemperature() > card.getReqMaxTemp()) {
            throw new MarsException(exceptionRepository.findById(12));
        }

        if (gamePlayerMat.getTileGreen() < card.getReqTileGreen()) {
            throw new MarsException(exceptionRepository.findById(13));
        }

        if (gamePlayerMat.getTileCity() < card.getReqTileCity()) {
            throw new MarsException(exceptionRepository.findById(14));
        }

    }

    public GamePlayerMat useCard(Integer playerId, Integer gameId, String gameHash, Integer cardId, boolean useResource, char resource, Integer resourceCount) throws MarsException {

        Game game = gameRepository.findByIdAndHash(gameId, gameHash);
        GameCard gameCard = gameCardRepository.findById(cardId);

        return useCard(playerId, game, gameCard.getCard(), useResource, resource, resourceCount);
    }

    public GamePlayerMat useCard(Integer playerId, Game game, Card cardX, boolean useResource, char resource, Integer resourceCount) throws MarsException {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGameAndPlayer(game, playerRepository.findById(playerId));
        GamePlayerCard gamePlayerCard = gamePlayerCardRepository.findByGameAndCard(game, cardX);
        Card card = gamePlayerCard.getCard();
        if (gamePlayerCard.getGenerationId() > 0) {
            throw new MarsException(exceptionRepository.findById(15));
        }
        checkCardRequirements(playerId, game, card, useResource, resource, resourceCount);

        for (int i = 0; i < card.getTerrEffOcean(); i++) {
            raiseOceans(playerId, game);
        }

        for (int i = 0; i < card.getTerrEffOxygen(); i++) {
            raiseOxygen(playerId, game);
        }

        for (int i = 0; i < card.getTerrEffTemp(); i++) {
            raiseTemperature(playerId, game);
        }

        for (int i = 0; i < card.getTerrEffRating(); i++) {
            raiseRating(playerId, game);
        }

        for (int i = 0; i < card.getProdEffMoney(); i++) {
            raiseRating(playerId, game);
        }

        raiseMat(playerId, game, 'm', true, card.getProdEffMoney());
        raiseMat(playerId, game, 'm', false, card.getResEffMoney());
        raiseMat(playerId, game, 's', true, card.getProdEffSteel());
        raiseMat(playerId, game, 's', false, card.getResEffSteel());
        raiseMat(playerId, game, 't', true, card.getProdEffTitan());
        raiseMat(playerId, game, 't', false, card.getResEffTitan());
        raiseMat(playerId, game, 'p', true, card.getProdEffPlant());
        raiseMat(playerId, game, 'p', false, card.getResEffPlant());
        raiseMat(playerId, game, 'e', true, card.getProdEffEnergy());
        raiseMat(playerId, game, 'e', false, card.getResEffEnergy());
        raiseMat(playerId, game, 'h', true, card.getProdEffHeat());
        raiseMat(playerId, game, 'h', false, card.getResEffHeat());


        Integer cost = card.getCost();
        if (useResource) {
            Integer decr = 0;
            switch (resource) {
                case 's':
                    decr = 2;
                    gamePlayerMat.setSteel(gamePlayerMat.getSteel() - resourceCount);
                    break;
                case 't':
                    decr = 3;
                    gamePlayerMat.setTitan(gamePlayerMat.getTitan() - resourceCount);
                    break;
                case 'h':
                    decr = 1;
                    gamePlayerMat.setHeat(gamePlayerMat.getHeat() - resourceCount);
                    break;
            }
            for (int i = 0; i < resourceCount; i++) {
                cost -= decr;
            }
        }
        gamePlayerMat.setMoney(gamePlayerMat.getMoney() - cost);
        gamePlayerMatRepository.save(gamePlayerMat);
        gamePlayerCard.setGenerationId(game.getGeneration());
        gamePlayerCardRepository.save(gamePlayerCard);

        return gamePlayerMat;

    }

    public Game newGeneration(Integer gameId, String gameHash) throws MarsException {
        Game game = gameRepository.findByIdAndHash(gameId, gameHash);
        newGeneration(game);
        return game;
    }

    private void newGeneration(Game game) {
        game.setGeneration(game.getGeneration() + 1);
        for (Player player : playerRepository.findAllByGame(game)) {
            GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGameAndPlayer(game, player);
            gamePlayerMat.setMoney(gamePlayerMat.getMoney() + gamePlayerMat.getRating() + gamePlayerMat.getProdMoney());
            gamePlayerMat.setSteel(gamePlayerMat.getSteel() + gamePlayerMat.getProdSteel());
            gamePlayerMat.setTitan(gamePlayerMat.getTitan() + gamePlayerMat.getProdTitan());
            gamePlayerMat.setPlant(gamePlayerMat.getPlant() + gamePlayerMat.getProdPlant());
            gamePlayerMat.setHeat(gamePlayerMat.getHeat() + gamePlayerMat.getProdHeat() + gamePlayerMat.getEnergy());
            gamePlayerMat.setEnergy(gamePlayerMat.getProdEnergy());
            gamePlayerMatRepository.save(gamePlayerMat);
        }
        gameRepository.save(game);
    }
}
