package az.kerimov.mars.entity;

import javax.persistence.*;

@Entity(name = "mars_players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name ="game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name ="corporation_id")
    private GameCorporation corporation;

    public Player(){

    }

    public Player(Game game, String name){
        this.game = game;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameCorporation getCorporation() {
        return corporation;
    }

    public void setCorporation(GameCorporation corporation) {
        this.corporation = corporation;
    }
}
