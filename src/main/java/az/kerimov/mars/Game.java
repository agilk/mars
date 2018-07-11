package az.kerimov.mars;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "mars_games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer oceans;

    private Integer oxygene;

    private Integer temperature;

    private Integer generation;

    public Game() {
        this.oceans = 0;
        this.oxygene = 0;
        this.temperature = -30;
        this.generation = 1;
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

    public Integer getOxygene() {
        return oxygene;
    }

    public void setOxygene(Integer oxygene) {
        this.oxygene = oxygene;
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

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }
}
