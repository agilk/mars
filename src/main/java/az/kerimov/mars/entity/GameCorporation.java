package az.kerimov.mars.entity;

import javax.persistence.*;

@Entity(name = "mars_game_corporations")
public class GameCorporation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "game_id")
    @ManyToOne
    private Game game;

    @JoinColumn(name = "corporation_id")
    @ManyToOne
    private Corporation corporation;

    private Integer playerId;

    private Integer picked;

    public GameCorporation() {

    }

    public GameCorporation(Game game, Corporation corporation) {
        this.game = game;
        this.corporation = corporation;
        this.playerId = 0;
        this.picked = 0;
    }

    public void pick(){
        this.picked = 1;
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

    public Corporation getCorporation() {
        return corporation;
    }

    public void setCorporation(Corporation corporation) {
        this.corporation = corporation;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getPicked() {
        return picked;
    }

    public void setPicked(Integer picked) {
        this.picked = picked;
    }
}
