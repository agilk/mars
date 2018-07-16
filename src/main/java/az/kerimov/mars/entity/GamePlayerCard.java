package az.kerimov.mars.entity;

import javax.persistence.*;

@Entity(name = "mars_player_cards")
public class GamePlayerCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private GameCard card;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "played_generation")
    private Integer generationId;

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

    public GameCard getCard() {
        return card;
    }

    public void setCard(GameCard card) {
        this.card = card;
    }

    public Integer getGenerationId() {
        return generationId;
    }

    public void setGenerationId(Integer generationId) {
        this.generationId = generationId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
