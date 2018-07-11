package az.kerimov.mars;

import javax.persistence.*;

@Entity(name = "mars_game_cards")
public class GameCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "generation_id")
    private Integer generationId;

    @Column(name = "player_id")
    private Integer playerId;

    public GameCard() {
    }

    public GameCard(Game game, Card card){
        this.game = game;
        this.card = card;
        this.playerId = 0;
        this.generationId = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
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
}
