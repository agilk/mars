package az.kerimov.mars.pojo;

import az.kerimov.mars.entity.*;

import java.util.List;

public class Data {
    private Game game;
    private Player player;
    private List<Player> players;
    private Card card;
    private List<Card> cards;
    private CardDeck deck;
    private List<CardDeck> decks;
    private CardType type;
    private List<CardType> types;
    private Corporation corporation;
    private List<Corporation> corporations;
    private GameCard gameCard;
    private List<GameCard> gameCards;
    private GamePlayerCard gamePlayerCard;
    private List<GamePlayerCard> gamePlayerCards;
    private GamePlayerMat gamePlayerMat;
    private List<GameCorporation> gameCorporations;
    private GameCorporation gameCorporation;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public CardDeck getDeck() {
        return deck;
    }

    public void setDeck(CardDeck deck) {
        this.deck = deck;
    }

    public List<CardDeck> getDecks() {
        return decks;
    }

    public void setDecks(List<CardDeck> decks) {
        this.decks = decks;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public List<CardType> getTypes() {
        return types;
    }

    public void setTypes(List<CardType> types) {
        this.types = types;
    }

    public Corporation getCorporation() {
        return corporation;
    }

    public void setCorporation(Corporation corporation) {
        this.corporation = corporation;
    }

    public List<Corporation> getCorporations() {
        return corporations;
    }

    public void setCorporations(List<Corporation> corporations) {
        this.corporations = corporations;
    }

    public GameCard getGameCard() {
        return gameCard;
    }

    public void setGameCard(GameCard gameCard) {
        this.gameCard = gameCard;
    }

    public List<GameCard> getGameCards() {
        return gameCards;
    }

    public void setGameCards(List<GameCard> gameCards) {
        this.gameCards = gameCards;
    }

    public GamePlayerCard getGamePlayerCard() {
        return gamePlayerCard;
    }

    public void setGamePlayerCard(GamePlayerCard gamePlayerCard) {
        this.gamePlayerCard = gamePlayerCard;
    }

    public List<GamePlayerCard> getGamePlayerCards() {
        return gamePlayerCards;
    }

    public void setGamePlayerCards(List<GamePlayerCard> gamePlayerCards) {
        this.gamePlayerCards = gamePlayerCards;
    }

    public GamePlayerMat getGamePlayerMat() {
        return gamePlayerMat;
    }

    public void setGamePlayerMat(GamePlayerMat gamePlayerMat) {
        this.gamePlayerMat = gamePlayerMat;
    }

    public List<GameCorporation> getGameCorporations() {
        return gameCorporations;
    }

    public void setGameCorporations(List<GameCorporation> gameCorporations) {
        this.gameCorporations = gameCorporations;
    }

    public GameCorporation getGameCorporation() {
        return gameCorporation;
    }

    public void setGameCorporation(GameCorporation gameCorporation) {
        this.gameCorporation = gameCorporation;
    }
}
