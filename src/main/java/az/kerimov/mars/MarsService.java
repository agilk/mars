package az.kerimov.mars;

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
    private GamePlayerMatRepository gamePlayerMatRepository;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public List<Card> getAllCardsByDecks(CardDeck deck) {
        return cardRepository.findAllByCardDeck(deck);
    }

    public List<Card> getAllCardsByDecks(List<Integer> decks) {
        List<Card> cards = new ArrayList<>();
        for (Integer i : decks) {
            CardDeck deck = cardDeckRepository.findById(i);
            cards.addAll(getAllCardsByDecks(deck));
        }
        return cards;
    }

    public List<CardDeck> getAllDecks() {
        return cardDeckRepository.findAll();
    }

    public Card getCardById(Integer id) {
        return cardRepository.findById(id);
    }

    public Game getGameById(Integer id) {
        return gameRepository.findById(id);
    }

    public Integer startNewGame(List<Integer> decks) {

        Game game = new Game();
        gameRepository.save(game);


        for (Card card : getAllCardsByDecks(decks)) {
            GameCard gameCard = new GameCard(game, card);
            gameCardRepository.save(gameCard);
        }

        GamePlayerMat gamePlayerMat = new GamePlayerMat(game, 's');
        gamePlayerMatRepository.save(gamePlayerMat);

        return game.getId();
    }

    public List<GameCard> getAvailableCards(Game game) {
        return gameCardRepository.findAllByGameAndGenerationIdAndPlayerId(game, 0, 0);
    }

    public GameCard showRandomCard(Game game, Integer playerId) {

        List<GameCard> cards = getAvailableCards(game);

        Random random = new Random();

        Integer rand = random.nextInt(cards.size());

        GameCard gameCard = cards.get(rand);

        gameCard.setGenerationId(game.getGeneration());
        gameCard.setPlayerId(playerId);
        gameCardRepository.save(gameCard);

        return gameCard;
    }

    public List<Card> showRandomCards(Game game, Integer playerId) {
        Integer countCard = game.getGeneration() == 1 ? 10 : 4;
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < countCard; i++) {
            cards.add(showRandomCard(game, playerId).getCard());
        }
        return cards;
    }

    public Integer sellCard(Integer playerId, Game game, Card card){
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGame(game);
        GamePlayerCard gamePlayerCard = gamePlayerCardRepository.findByGameAndCard(game, card);
        gamePlayerCard.setGenerationId(-1);
        gamePlayerMat.setMoney(gamePlayerMat.getMoney()+1);
        gamePlayerCardRepository.save(gamePlayerCard);
        gamePlayerMatRepository.save(gamePlayerMat);
        return 1;
    }

    public Integer pickUpCards(Integer playerId, Game game, List<Card> cards) {
        int cnt = 0;
        for (Card card : cards) {
            cnt++;
            pickUpCard(playerId, game, card);
        }
        return cnt;
    }

    public Integer pickUpCard(Integer playerId, Game game, Card card) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGame(game);
        if (gamePlayerMat.getMoney() >= 3) {
            gamePlayerMat.setMoney(gamePlayerMat.getMoney() - 3);
            GamePlayerCard gamePlayerCard = new GamePlayerCard();
            gamePlayerCard.setCard(card);
            gamePlayerCard.setGame(game);
            gamePlayerCard.setGenerationId(0);
            gamePlayerMatRepository.save(gamePlayerMat);
            gamePlayerCardRepository.save(gamePlayerCard);
            return card.getId();
        }
        return -1;
    }

    public Integer pickUpFreeCard(Integer playerId, Game game, Card card) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGame(game);
        GamePlayerCard gamePlayerCard = new GamePlayerCard();
        gamePlayerCard.setCard(card);
        gamePlayerCard.setGame(game);
        gamePlayerCard.setGenerationId(0);
        gamePlayerMatRepository.save(gamePlayerMat);
        gamePlayerCardRepository.save(gamePlayerCard);
        return card.getId();
    }

    public Integer raiseOceans(Integer playerId, Game game) {
        if (game.getOceans() >= 9) {
            return 1;
        }
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGame(game);
        game.setOceans(game.getOceans() + 1);
        gameRepository.save(game);
        gamePlayerMat.setRating(gamePlayerMat.getRating() + 1);
        gamePlayerMatRepository.save(gamePlayerMat);
        return 1;
    }

    public Integer raiseOxygene(Integer playerId, Game game) {
        if (game.getOxygene() >= 14) {
            return 1;
        }
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGame(game);
        game.setOxygene(game.getOxygene() + 1);
        gameRepository.save(game);
        gamePlayerMat.setRating(gamePlayerMat.getRating() + 1);
        if (game.getOxygene() == 8) {
            raiseTemperature(playerId, game);
        }
        gamePlayerMatRepository.save(gamePlayerMat);
        return 1;
    }

    public Integer raiseTemperature(Integer playerId, Game game) {
        if (game.getTemperature() >= 8) {
            return 1;
        }
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGame(game);
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
        return 1;
    }

    public Integer raiseTemperatureByHeat(Integer playerId, Game game) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGame(game);
        if (gamePlayerMat.getHeat() >= 8) {
            raiseTemperature(playerId, game);
            gamePlayerMat.setHeat(gamePlayerMat.getHeat() - 8);
            gamePlayerMatRepository.save(gamePlayerMat);
        }
        return 1;
    }

    public Integer addGreenery(Integer playerId, Game game) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGame(game);
        if (gamePlayerMat.getPlant() >= 8) {
            raiseOxygene(playerId, game);
            gamePlayerMat.setPlant(gamePlayerMat.getPlant() - 8);
            gamePlayerMat.setTileGreen(gamePlayerMat.getTileGreen() + 1);
            gamePlayerMatRepository.save(gamePlayerMat);
        }
        return 1;
    }

    public Integer addOcean(Integer playerId, Game game) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGame(game);
        if (gamePlayerMat.getMoney() >= 18) {
            raiseOceans(playerId, game);
            gamePlayerMat.setMoney(gamePlayerMat.getMoney() - 18);
            gamePlayerMatRepository.save(gamePlayerMat);
        }
        return 1;
    }

    public Integer addGreeneryForMoney(Integer playerId, Game game) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGame(game);
        if (gamePlayerMat.getMoney() >= 23) {
            raiseOxygene(playerId, game);
            gamePlayerMat.setMoney(gamePlayerMat.getMoney() - 23);
            gamePlayerMat.setTileGreen(gamePlayerMat.getTileGreen() + 1);
            gamePlayerMatRepository.save(gamePlayerMat);
        }
        return 1;
    }

    public Integer raiseRating(Integer playerId, Game game) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGame(game);
        gamePlayerMat.setRating(gamePlayerMat.getRating() + 1);
        gamePlayerMatRepository.save(gamePlayerMat);
        return 1;
    }

    public Integer raiseMat(Integer playerId, Game game, char res, boolean prod, Integer count) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGame(game);
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
        return 1;
    }

    public Integer checkCardRequirements(Integer playerId, Game game, Card card, boolean useResource, char resource, Integer resourceCount) throws Exception {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGame(game);

        Integer resourceMoney = 0;

        if (useResource) {
            switch (resource) {
                case 's':
                    if (gamePlayerMat.getSteel()<resourceCount) throw new Exception("Not enough steel");
                    if (card.getTagBuilding()==0) throw new Exception("Not building card");
                    resourceMoney += resourceCount * 2;
                    break;
                case 't':
                    if (gamePlayerMat.getTitan()<resourceCount) throw new Exception("Not enough titan");
                    if (card.getTagSpace()==0) throw new Exception("Not space card");
                    resourceMoney += resourceCount * 3;
                    break;
                case 'h':
                    if (gamePlayerMat.getHeat()<resourceCount) throw new Exception("Not enough heat");
                    resourceMoney += resourceCount;
                    break;
            }
        }

        if (gamePlayerMat.getMoney() + resourceMoney < card.getCost()) {
            throw new Exception("Expensive");
        }

        if (gamePlayerMat.getRating() < card.getReqMinRating() || gamePlayerMat.getRating() > card.getReqMaxRating()) {
            throw new Exception("Rating mismatch");
        }

        if (game.getOceans() < card.getReqMinOcean() || game.getOceans() > card.getReqMaxOcean()) {
            throw new Exception("Oceans mismatch");
        }

        if (game.getOxygene() < card.getReqMinOxyg() || game.getOxygene() > card.getReqMaxOxyg()) {
            throw new Exception("Oxygene mismatch");
        }

        if (game.getTemperature() < card.getReqMinTemp() || game.getTemperature() > card.getReqMaxTemp()) {
            throw new Exception("Temperature mismatch");
        }

        if (gamePlayerMat.getTileGreen() < card.getReqTileGreen()) {
            throw new Exception("Greenery mismatch");
        }

        if (gamePlayerMat.getTileCity() < card.getReqTileCity()) {
            throw new Exception("City mismatch");
        }

        return 1;
    }

    public Integer useCard(Integer playerId, Game game, Card cardX, boolean useResource, char resource, Integer resourceCount) {
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGame(game);
        GamePlayerCard gamePlayerCard = gamePlayerCardRepository.findByGameAndCard(game, cardX);
        Card card = gamePlayerCard.getCard();
        try {
            if (gamePlayerCard.getGenerationId() > 0) {
                throw new Exception("Already Used");
            }
            checkCardRequirements(playerId, game, card, useResource, resource, resourceCount);

            for (int i = 0; i < card.getTerrEffOcean(); i++) {
                raiseOceans(playerId, game);
            }

            for (int i = 0; i < card.getTerrEffOxygen(); i++) {
                raiseOxygene(playerId, game);
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
            if (useResource){
                Integer decr = 0;
                switch (resource) {
                    case 's':
                        decr= 2;
                        gamePlayerMat.setSteel(gamePlayerMat.getSteel()-resourceCount);
                        break;
                    case 't':
                        decr= 3;
                        gamePlayerMat.setTitan(gamePlayerMat.getTitan()-resourceCount);
                        break;
                    case 'h':
                        decr=1;
                        gamePlayerMat.setHeat(gamePlayerMat.getHeat()-resourceCount);
                        break;
                }
                for(int i=0; i<resourceCount; i++){
                    cost -= decr;
                }
            }
            gamePlayerMat.setMoney(gamePlayerMat.getMoney() - cost);
            gamePlayerMatRepository.save(gamePlayerMat);
            gamePlayerCard.setGenerationId(game.getGeneration());
            gamePlayerCardRepository.save(gamePlayerCard);


            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public Integer newGeneration(Game game) {
        game.setGeneration(game.getGeneration() + 1);
        GamePlayerMat gamePlayerMat = gamePlayerMatRepository.findByGame(game);
        gamePlayerMat.setMoney(gamePlayerMat.getMoney() + gamePlayerMat.getRating() + gamePlayerMat.getProdMoney());
        gamePlayerMat.setSteel(gamePlayerMat.getSteel() + gamePlayerMat.getProdSteel());
        gamePlayerMat.setTitan(gamePlayerMat.getTitan() + gamePlayerMat.getProdTitan());
        gamePlayerMat.setPlant(gamePlayerMat.getPlant() + gamePlayerMat.getProdPlant());
        gamePlayerMat.setHeat(gamePlayerMat.getHeat() + gamePlayerMat.getProdHeat() + gamePlayerMat.getEnergy());
        gamePlayerMat.setEnergy(gamePlayerMat.getProdEnergy());
        gamePlayerMatRepository.save(gamePlayerMat);
        gameRepository.save(game);
        return 1;
    }
}
