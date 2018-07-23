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
    private GameCorporationRepository gameCorporationRepository;
    @Autowired
    private CorporationRepository corporationRepository;
    @Autowired
    private ExceptionRepository exceptionRepository;

    private Game saveGame(Game game){
        gameRepository.save(game);
        return game;
    }

    private Player savePlayer(Player player){
        playerRepository.save(player);
        return player;
    }

    private GameCard saveGameCard(GameCard gameCard){
        gameCardRepository.save(gameCard);
        return gameCard;
    }


    private GameCorporation saveGameCorporation(GameCorporation gameCorporation){
        gameCorporationRepository.save(gameCorporation);
        return gameCorporation;
    }

    private GamePlayerCard saveGamePlayerCard(GamePlayerCard gamePlayerCard){
        gamePlayerCardRepository.save(gamePlayerCard);
        return gamePlayerCard;
    }

    private GamePlayerMat saveGamePlayerMat(GamePlayerMat gamePlayerMat){
        gamePlayerMatRepository.save(gamePlayerMat);
        return gamePlayerMat;
    }

    private MException getExceptionById(Integer id){
        return exceptionRepository.findById(id);
    }

    private MException getExceptionByCode(String code){
        return exceptionRepository.findByCode(code);
    }

    private Player getPlayerById(Integer id){
        return playerRepository.findById(id);
    }

    private GamePlayerMat getPlayerMatByGameAndPlayer(Game game, Player player){
        return gamePlayerMatRepository.findByGameAndPlayer(game, player);
    }


    private GamePlayerMat getPlayerMatByGameAndPlayerId(Game game, Integer playerId){
        return gamePlayerMatRepository.findByGameAndPlayer(game, getPlayerById(playerId));
    }

    private GameCard getGameCardById(Integer id){
        return gameCardRepository.findById(id);
    }

    private GameCard getGameCardByGameAndCard(Game game, Card card){
        return gameCardRepository.findByGameAndCard(game, card);
    }

    private GamePlayerCard getPlayerCardByGameAndCard(Game game, GameCard card){
        return gamePlayerCardRepository.findByGameAndCard(game, card);
    }

    private GameCorporation getCorporationByGameAndCorporation(Game game, Corporation corporation){
        return gameCorporationRepository.findByGameAndCorporation(game, corporation);
    }

    private GameCorporation getCorporationById(Integer id){
        return gameCorporationRepository.findById(id);
    }

    private List<Card> getAllCardsByDecks(CardDeck deck) {
        return cardRepository.findAllByCardDeck(deck);
    }

    private List<Corporation> getAllCorporationsByDecks(CardDeck deck) {
        return corporationRepository.findAllByCardDeck(deck);
    }

    public List<Player> getPlayersOfGame(Game game) throws MarsException {
        return playerRepository.findAllByGame(game);
    }

    private List<GameCard> getAvailableCards(Game game) {
        return gameCardRepository.findAllByGameAndGenerationIdAndPlayerId(game, 0, 0);
    }

    private List<GameCorporation> getAvailableCorporations(Game game) {
        return gameCorporationRepository.findAllByGameAndPicked(game, 0);
    }

    private List<Card> getAllCardsByDecks(List<Integer> decks) {
        List<Card> cards = new ArrayList<>();
        for (Integer i : decks) {
            CardDeck deck = cardDeckRepository.findById(i);
            cards.addAll(getAllCardsByDecks(deck));
        }
        return cards;
    }

    public List<Corporation> getAllCorporationsByDecks(List<Integer> decks) {
        List<Corporation> corporations = new ArrayList<>();
        for (Integer i : decks) {
            CardDeck deck = cardDeckRepository.findById(i);
            corporations.addAll(getAllCorporationsByDecks(deck));
        }
        return corporations;
    }


    public Card getCardById(Integer id) {
        return getGameCardById(id).getCard();
    }

    private Game getGameByIdAndHash(Integer id, String hash) throws MarsException{
        Game game = gameRepository.findByIdAndHash(id, hash);
        if (game == null){
            throw new MarsException(getExceptionByCode("GAME_NOT_FOUND"));
        }
        return game;
    }

    public Game startNewGame(List<Integer> decks, List<String> players) throws MarsException {

        char mode = players.size() == 1 ? 's' : 'm';

        Game game = new Game(mode);
        saveGame(game);


        for (Card card : getAllCardsByDecks(decks)) {
            GameCard gameCard = new GameCard(game, card);
            saveGameCard(gameCard);
        }

        for (Corporation corporation : getAllCorporationsByDecks(decks)){
            GameCorporation gameCorporation = new GameCorporation(game, corporation);
            saveGameCorporation(gameCorporation);
        }

        for (String pl : players) {
            Player p = new Player(game, pl);
            savePlayer(p);

            showGenerationCards(game, p.getId());
            showCorporations(game, p.getId());

            GamePlayerMat gamePlayerMat = new GamePlayerMat(game, mode, p);
            saveGamePlayerMat(gamePlayerMat);
        }

        return game;
    }

    private GameCard showRandomCard(Game game, Integer playerId) {

        List<GameCard> cards = getAvailableCards(game);

        Random random = new Random();

        Integer rand = random.nextInt(cards.size());

        GameCard gameCard = cards.get(rand);

        gameCard.setGenerationId(game.getGeneration());
        gameCard.setPlayerId(playerId);
        saveGameCard(gameCard);

        return gameCard;
    }

    private GameCorporation showRandomCorporation(Game game, Integer playerId) {

        List<GameCorporation> corporations = getAvailableCorporations(game);

        Random random = new Random();

        Integer rand = random.nextInt(corporations.size());

        GameCorporation gameCorporation = corporations.get(rand);

        gameCorporation.setPlayerId(playerId);
        saveGameCorporation(gameCorporation);

        return gameCorporation;
    }

    public GameCard showRandomCard(Integer gameId, String gameHash, Integer playerId) throws MarsException {
        Game game = getGameByIdAndHash(gameId, gameHash);
        return showRandomCard(game, playerId);

    }

    public List<GameCorporation> showCorporationCards(Integer gameId, String gameHash, Integer playerId) throws MarsException {
        return showCorporationCards(getGameByIdAndHash(gameId, gameHash), playerId);
    }

    private List<GameCorporation> showCorporationCards(Game game, Integer playerId){
         return gameCorporationRepository.findAllByGameAndPlayerIdAndPicked(game, playerId, 0);

    }

    private List<GameCorporation> showCorporations(Game game, Integer playerId){
        List<GameCorporation> corporations = new ArrayList<>();
        for (int i = 0; i<2; i++){
            corporations.add(showRandomCorporation(game, playerId));
        }
        return corporations;
    }


    public List<GameCorporation> showCorporations(Integer gameId, String gameHash, Integer playerId) throws MarsException {
        return showCorporations(getGameByIdAndHash(gameId, gameHash), playerId);
    }

    private List<GameCard> showGenerationCards(Game game, Integer playerId) {
        List<GameCard> cards = new ArrayList<>();
        List<GameCard> gameCards = gameCardRepository.findAllByGameAndGenerationIdAndPlayerId(game, game.getGeneration(), playerId);
        if (gameCards.size() == 0) {
            Integer countCard = game.getGeneration() == 1 ? 10 : 4;
            for (int i = 0; i < countCard; i++) {
                cards.add(showRandomCard(game, playerId));
            }
        }else{
            cards = gameCards;
        }
        return cards;
    }

    public List<GameCard> showGenerationCards(Integer gameId, String gameHash, Integer playerId) throws MarsException {
        Game game = getGameByIdAndHash(gameId, gameHash);
        return showGenerationCards(game, playerId);

    }

    private List<GamePlayerCard> showPlayerUnusedCards(Game game, Player player){
        return gamePlayerCardRepository.findAllByGameAndPlayerAndGenerationId(game, player, 0);
    }

    public List<GamePlayerCard> showPlayerUnusedCards(Integer gameId, String gameHash, Integer playerId) throws MarsException {
        return showPlayerUnusedCards(getGameByIdAndHash(gameId, gameHash), getPlayerById(playerId));
    }

    public GamePlayerMat sellCard(Integer playerId, Integer gameId, String gameHash, Integer cardId) throws MarsException {
        Game game = getGameByIdAndHash(gameId, gameHash);
        GameCard card = getGameCardById(cardId);
        return sellCard(playerId, game, card);
    }

    private GamePlayerMat sellCard(Integer playerId, Game game, GameCard card) throws MarsException {
        GamePlayerMat gamePlayerMat = getPlayerMatByGameAndPlayerId(game, playerId);
        GamePlayerCard gamePlayerCard = getPlayerCardByGameAndCard(game, card);
        gamePlayerCard.setGenerationId(-1);
        gamePlayerMat.setMoney(gamePlayerMat.getMoney() + 1);
        saveGamePlayerCard(gamePlayerCard);
        saveGamePlayerMat(gamePlayerMat);
        return gamePlayerMat;
    }

    public List<Card> pickUpCards(Integer playerId, Integer gameId, String gameHash, List<Integer> cardIds) throws MarsException {
        List<GameCard> gameCards = new ArrayList<>();
        for (Integer i : cardIds) {
            gameCards.add(getGameCardById(i));
        }
        Game game = getGameByIdAndHash(gameId, gameHash);
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
        Game game = getGameByIdAndHash(gameId, gameHash);
        GameCard gameCard = getGameCardById(cardId);
        return pickUpCard(playerId, game, gameCard);
    }

    private GamePlayerMat setCorporationToPlayer(GamePlayerMat gamePlayerMat, GameCorporation gameCorporation){
        GamePlayerMat playerMat = gamePlayerMat;
        playerMat.setMoney(gameCorporation.getCorporation().getInitMoney());
        playerMat.setSteel(gameCorporation.getCorporation().getInitSteel());
        playerMat.setTitan(gameCorporation.getCorporation().getInitTitan());
        playerMat.setPlant(gameCorporation.getCorporation().getInitPlant());
        playerMat.setEnergy(gameCorporation.getCorporation().getInitEnergy());
        playerMat.setHeat(gameCorporation.getCorporation().getInitHeat());
        playerMat.setProdMoney(gameCorporation.getCorporation().getInitProdMoney());
        playerMat.setProdSteel(gameCorporation.getCorporation().getInitProdSteel());
        playerMat.setProdTitan(gameCorporation.getCorporation().getInitProdTitan());
        playerMat.setProdPlant(gameCorporation.getCorporation().getInitProdPlant());
        playerMat.setProdEnergy(gameCorporation.getCorporation().getInitProdEnergy());
        playerMat.setProdHeat(gameCorporation.getCorporation().getInitProdHeat());
        saveGamePlayerMat(playerMat);
        return playerMat;
    }

    private GamePlayerMat pickUpCorporation(Integer playerId, Game game, GameCorporation corporation){
        Player player = getPlayerById(playerId);
        GameCorporation gameCorporation = getCorporationByGameAndCorporation(game, corporation.getCorporation());
        gameCorporation.pick();
        saveGameCorporation(gameCorporation);
        player.setCorporation(gameCorporation);
        savePlayer(player);
        GamePlayerMat gamePlayerMat = setCorporationToPlayer(getPlayerMatByGameAndPlayerId(game, playerId), gameCorporation );
        return gamePlayerMat;
    }

    public GamePlayerMat pickUpCorporation (Integer playerId, Integer gameId, String gameHash, Integer corporationId) throws MarsException{
        return pickUpCorporation(playerId, getGameByIdAndHash(gameId, gameHash), getCorporationById(corporationId));
    }

    private Card pickUpCard(Integer playerId, Game game, GameCard card) throws MarsException {
        GamePlayerMat gamePlayerMat = getPlayerMatByGameAndPlayerId(game, playerId);
        GameCard gameCard = getGameCardByGameAndCard(game, card.getCard());
        if (gamePlayerMat.getMoney() >= 3) {
            gamePlayerMat.setMoney(gamePlayerMat.getMoney() - 3);
            GamePlayerCard gamePlayerCard = new GamePlayerCard();
            gamePlayerCard.setCard(card);
            gamePlayerCard.setGame(game);
            gamePlayerCard.setGenerationId(0);
            gamePlayerCard.setPlayer(getPlayerById(playerId));
            gameCard.setPicked(1);
            saveGamePlayerCard(gamePlayerCard);
            saveGamePlayerMat(gamePlayerMat);
            saveGameCard(gameCard);
            return card.getCard();
        } else {
            throw new MarsException(getExceptionById(1));
        }
    }

    public Card pickUpFreeCard(Integer playerId, Integer gameId, String gameHash, Integer cardId) throws MarsException {
        Game game = getGameByIdAndHash(gameId, gameHash);
        Card card = getGameCardById(cardId).getCard();
        return pickUpFreeCard(playerId, game, card);
    }

    private Card pickUpFreeCard(Integer playerId, Game game, Card card) {
        GamePlayerMat gamePlayerMat = getPlayerMatByGameAndPlayerId(game, playerId);
        GamePlayerCard gamePlayerCard = new GamePlayerCard();
        gamePlayerCard.setCard(getGameCardByGameAndCard(game, card));
        gamePlayerCard.setGame(game);
        gamePlayerCard.setGenerationId(0);
        saveGamePlayerCard(gamePlayerCard);
        saveGamePlayerMat(gamePlayerMat);
        return card;
    }

    private void raiseOceans(Integer playerId, Game game) {
        if (game.getOceans() >= 9) {
            return;
        }
        GamePlayerMat gamePlayerMat = getPlayerMatByGameAndPlayerId(game, playerId);
        game.setOceans(game.getOceans() + 1);
        saveGame(game);
        gamePlayerMat.setRating(gamePlayerMat.getRating() + 1);
        saveGamePlayerMat(gamePlayerMat);
    }

    private void raiseOxygen(Integer playerId, Game game) {
        if (game.getOxygen() >= 14) {
            return;
        }
        GamePlayerMat gamePlayerMat = getPlayerMatByGameAndPlayerId(game, playerId);
        game.setOxygen(game.getOxygen() + 1);
        saveGame(game);
        gamePlayerMat.setRating(gamePlayerMat.getRating() + 1);
        if (game.getOxygen() == 8) {
            raiseTemperature(playerId, game);
        }
        saveGamePlayerMat(gamePlayerMat);
    }

    private void raiseTemperature(Integer playerId, Game game) {
        if (game.getTemperature() >= 8) {
            return;
        }
        GamePlayerMat gamePlayerMat = getPlayerMatByGameAndPlayerId(game, playerId);
        game.setTemperature(game.getTemperature() + 2);
        saveGame(game);
        gamePlayerMat.setRating(gamePlayerMat.getRating() + 1);
        if (game.getTemperature() == -24 || game.getTemperature() == -20) {
            gamePlayerMat.setProdHeat(gamePlayerMat.getProdHeat() + 1);
        }
        if (game.getTemperature() == 0) {
            raiseOceans(playerId, game);
        }
        saveGamePlayerMat(gamePlayerMat);
    }

    public Game raiseTemperatureByHeat(Integer playerId, Integer gameId, String gameHash)  throws MarsException{
        Game game = getGameByIdAndHash(gameId, gameHash);
        raiseTemperatureByHeat(playerId, game);
        return game;
    }

    private void raiseTemperatureByHeat(Integer playerId, Game game) throws  MarsException{
        GamePlayerMat gamePlayerMat = getPlayerMatByGameAndPlayerId(game, playerId);
        if (gamePlayerMat.getHeat() >= 8) {
            raiseTemperature(playerId, game);
            gamePlayerMat.setHeat(gamePlayerMat.getHeat() - 8);
            saveGamePlayerMat(gamePlayerMat);
        }else{
            throw new MarsException(getExceptionById(7));
        }
    }


    public Game addGreenery(Integer playerId, Integer gameId, String gameHash) throws  MarsException{
        Game game = getGameByIdAndHash(gameId, gameHash);
        addGreenery(playerId, game);
        return game;
    }

    private void addGreenery(Integer playerId, Game game) throws MarsException {
        GamePlayerMat gamePlayerMat = getPlayerMatByGameAndPlayerId(game, playerId);
        if (gamePlayerMat.getPlant() >= 8) {
            raiseOxygen(playerId, game);
            gamePlayerMat.setPlant(gamePlayerMat.getPlant() - 8);
            gamePlayerMat.setTileGreen(gamePlayerMat.getTileGreen() + 1);
            saveGamePlayerMat(gamePlayerMat);
        }else{
            throw new MarsException(getExceptionById(4));
        }
    }



    public Game addOcean(Integer playerId, Integer gameId, String gameHash) throws  MarsException{
        Game game = getGameByIdAndHash(gameId, gameHash);
        addOcean(playerId, game);
        return game;
    }
    private void addOcean(Integer playerId, Game game) throws MarsException {
        GamePlayerMat gamePlayerMat = getPlayerMatByGameAndPlayerId(game, playerId);
        if (gamePlayerMat.getMoney() >= 18) {
            raiseOceans(playerId, game);
            gamePlayerMat.setMoney(gamePlayerMat.getMoney() - 18);
            saveGamePlayerMat(gamePlayerMat);
        }else{
            throw new MarsException(getExceptionById(1));
        }
    }

    public Game addGreeneryForMoney(Integer playerId, Integer gameId, String gameHash) throws  MarsException{
        Game game = getGameByIdAndHash(gameId, gameHash);
        addGreeneryForMoney(playerId, game);
        return game;
    }

    private void addGreeneryForMoney(Integer playerId, Game game) throws MarsException {
        GamePlayerMat gamePlayerMat = getPlayerMatByGameAndPlayerId(game, playerId);
        if (gamePlayerMat.getMoney() >= 23) {
            raiseOxygen(playerId, game);
            gamePlayerMat.setMoney(gamePlayerMat.getMoney() - 23);
            gamePlayerMat.setTileGreen(gamePlayerMat.getTileGreen() + 1);
            saveGamePlayerMat(gamePlayerMat);
        }else{
            throw new MarsException(getExceptionById(1));
        }
    }

    private void raiseRating(Integer playerId, Game game) {
        GamePlayerMat gamePlayerMat = getPlayerMatByGameAndPlayerId(game, playerId);
        gamePlayerMat.setRating(gamePlayerMat.getRating() + 1);
        saveGamePlayerMat(gamePlayerMat);
    }

    private void raiseMat(Integer playerId, Game game, char res, boolean prod, Integer count) {
        GamePlayerMat gamePlayerMat = getPlayerMatByGameAndPlayerId(game, playerId);
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
        saveGamePlayerMat(gamePlayerMat);
    }

    private void checkCardRequirements(Integer playerId, Game game, Card card, boolean useResource, char resource, Integer resourceCount) throws MarsException {
        GamePlayerMat gamePlayerMat = getPlayerMatByGameAndPlayerId(game, playerId);

        Integer resourceMoney = 0;

        if (useResource) {
            switch (resource) {
                case 's':
                    if (gamePlayerMat.getSteel() < resourceCount)
                        throw new MarsException(getExceptionById(2));
                    if (card.getTagBuilding() == 0) throw new MarsException(getExceptionById(3));
                    resourceMoney += resourceCount * 2;
                    break;
                case 't':
                    if (gamePlayerMat.getTitan() < resourceCount)
                        throw new MarsException(getExceptionById(5));
                    if (card.getTagSpace() == 0) throw new MarsException(getExceptionById(6));
                    resourceMoney += resourceCount * 3;
                    break;
                case 'h':
                    if (gamePlayerMat.getHeat() < resourceCount)
                        throw new MarsException(getExceptionById(7));
                    resourceMoney += resourceCount;
                    break;
            }
        }

        if (gamePlayerMat.getMoney() + resourceMoney < card.getCost()) {
            throw new MarsException(getExceptionById(8));
        }

        if (gamePlayerMat.getRating() < card.getReqMinRating() || gamePlayerMat.getRating() > card.getReqMaxRating()) {
            throw new MarsException(getExceptionById(9));
        }

        if (game.getOceans() < card.getReqMinOcean() || game.getOceans() > card.getReqMaxOcean()) {
            throw new MarsException(getExceptionById(10));
        }

        if (game.getOxygen() < card.getReqMinOxyg() || game.getOxygen() > card.getReqMaxOxyg()) {
            throw new MarsException(getExceptionById(11));
        }

        if (game.getTemperature() < card.getReqMinTemp() || game.getTemperature() > card.getReqMaxTemp()) {
            throw new MarsException(getExceptionById(12));
        }

        if (gamePlayerMat.getTileGreen() < card.getReqTileGreen()) {
            throw new MarsException(getExceptionById(13));
        }

        if (gamePlayerMat.getTileCity() < card.getReqTileCity()) {
            throw new MarsException(getExceptionById(14));
        }

    }

    public GamePlayerMat useCard(Integer playerId,
                                 Integer gameId,
                                 String gameHash,
                                 Integer cardId,
                                 boolean useResource,
                                 char resource,
                                 Integer resourceCount) throws MarsException {

        Game game = getGameByIdAndHash(gameId, gameHash);
        GameCard gameCard = getGameCardById(cardId);

        return useCard(playerId, game, gameCard.getCard(), useResource, resource, resourceCount);
    }

    private GamePlayerMat useCard(Integer playerId, Game game, Card cardX, boolean useResource, char resource, Integer resourceCount) throws MarsException {
        GamePlayerMat gamePlayerMat = getPlayerMatByGameAndPlayerId(game, playerId);
        GamePlayerCard gamePlayerCard = getPlayerCardByGameAndCard(game, getGameCardByGameAndCard(game, cardX));
        Card card = gamePlayerCard.getCard().getCard();
        if (gamePlayerCard.getGenerationId() > 0) {
            throw new MarsException(getExceptionById(15));
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
        saveGamePlayerMat(gamePlayerMat);
        gamePlayerCard.setGenerationId(game.getGeneration());
        saveGamePlayerCard(gamePlayerCard);

        return gamePlayerMat;

    }

    public List<GameCard> newGeneration(Integer gameId, String gameHash) throws MarsException {
        return newGeneration(getGameByIdAndHash(gameId, gameHash));
    }

    private List<GameCard> newGeneration(Game game) throws MarsException {
        List<GameCard> res = new ArrayList<>();
        game.setGeneration(game.getGeneration() + 1);
        for (Player player : getPlayersOfGame(game)) {
            GamePlayerMat gamePlayerMat = getPlayerMatByGameAndPlayer(game, player);
            gamePlayerMat.setMoney(gamePlayerMat.getMoney() + gamePlayerMat.getRating() + gamePlayerMat.getProdMoney());
            gamePlayerMat.setSteel(gamePlayerMat.getSteel() + gamePlayerMat.getProdSteel());
            gamePlayerMat.setTitan(gamePlayerMat.getTitan() + gamePlayerMat.getProdTitan());
            gamePlayerMat.setPlant(gamePlayerMat.getPlant() + gamePlayerMat.getProdPlant());
            gamePlayerMat.setHeat(gamePlayerMat.getHeat() + gamePlayerMat.getProdHeat() + gamePlayerMat.getEnergy());
            gamePlayerMat.setEnergy(gamePlayerMat.getProdEnergy());
            res.addAll(showGenerationCards(game.getId(), game.getHash(), player.getId()));
            saveGamePlayerMat(gamePlayerMat);
        }
        saveGame(game);
        return res;
    }
}
