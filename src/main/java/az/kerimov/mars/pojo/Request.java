package az.kerimov.mars.pojo;

import java.util.List;

public class Request {
    private Integer gameId;
    private Integer cardId;
    private Integer generationId;
    private Integer playerId;
    private List<Integer> decks;
    private List<Integer> cards;
    private Integer resourceCount;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public Integer getGenerationId() {
        return generationId;
    }

    public void setGenerationId(Integer generationId) {
        this.generationId = generationId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;

    }

    public List<Integer> getDecks() {
        return decks;
    }

    public void setDecks(List<Integer> decks) {
        this.decks = decks;
    }

    public List<Integer> getCards() {
        return cards;
    }

    public void setCards(List<Integer> cards) {
        this.cards = cards;
    }

    public Integer getResourceCount() {
        return resourceCount;
    }

    public void setResourceCount(Integer resourceCount) {
        this.resourceCount = resourceCount;
    }
}
