package az.kerimov.mars.entity;

import javax.persistence.*;
import java.util.Random;

@Entity(name = "mars_games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer oceans;

    @Column(name = "oxygene")
    private Integer oxygen;

    private Integer temperature;

    private Integer generation;

    @Column(name = "game_type")
    private char type;

    private String hash;

    private Integer status;

    public Game(){

    }

    public Game(char mode) {
        this.oceans = 0;
        this.oxygen = 0;
        this.temperature = -30;
        this.generation = 1;
        this.hash = Integer.toString((new Random().nextInt(10000000)));
        this.type = mode;
        this.status = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOceans() {
        return oceans;
    }

    public void setOceans(Integer oceans) {
        this.oceans = oceans;
    }

    public Integer getOxygen() {
        return oxygen;
    }

    public void setOxygen(Integer oxygen) {
        this.oxygen = oxygen;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getGeneration() {
        return generation;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;

    }
}
