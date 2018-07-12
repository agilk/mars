package az.kerimov.mars.entity;

import javax.persistence.*;

@Entity(name = "mars_player_mats")
public class GamePlayerMat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private Integer rating;

    @Column(name = "prod_money")
    private Integer prodMoney;

    @Column(name = "prod_steel")
    private Integer prodSteel;

    @Column(name = "prod_titan")
    private Integer prodTitan;

    @Column(name = "prod_plant")
    private Integer prodPlant;

    @Column(name = "prod_energy")
    private Integer prodEnergy;

    @Column(name = "prod_heat")
    private Integer prodHeat;

    private Integer money;

    private Integer steel;

    private Integer titan;

    private Integer plant;

    private Integer energy;

    private Integer heat;

    @Column(name = "tile_green")
    private Integer tileGreen;

    @Column(name = "tile_city")
    private Integer tileCity;

    public GamePlayerMat() {
    }
    public GamePlayerMat(Game game, char mode, Player player) {
        this.game = game;
        this.player = player;
        switch(mode) {
            case 's': {
                this.rating = 14;
                this.prodMoney = 0;
                this.prodSteel = 0;
                this.prodTitan = 0;
                this.prodPlant = 0;
                this.prodEnergy = 0;
                this.prodHeat = 0;
                this.money = 0;
                this.steel = 0;
                this.titan = 0;
                this.plant = 0;
                this.energy = 0;
                this.heat = 0;
                this.tileGreen = 0;
                this.tileCity = 0;
                break;
            }
            case 'm': {
                this.rating = 20;
                this.prodMoney = 0;
                this.prodSteel = 0;
                this.prodTitan = 0;
                this.prodPlant = 0;
                this.prodEnergy = 0;
                this.prodHeat = 0;
                this.money = 0;
                this.steel = 0;
                this.titan = 0;
                this.plant = 0;
                this.energy = 0;
                this.heat = 0;
                this.tileGreen = 0;
                this.tileCity = 0;
                break;
            }
        }
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getProdMoney() {
        return prodMoney;
    }

    public void setProdMoney(Integer prodMoney) {
        this.prodMoney = prodMoney;
    }

    public Integer getProdSteel() {
        return prodSteel;
    }

    public void setProdSteel(Integer prodSteel) {
        this.prodSteel = prodSteel;
    }

    public Integer getProdTitan() {
        return prodTitan;
    }

    public void setProdTitan(Integer prodTitan) {
        this.prodTitan = prodTitan;
    }

    public Integer getProdPlant() {
        return prodPlant;
    }

    public void setProdPlant(Integer prodPlant) {
        this.prodPlant = prodPlant;
    }

    public Integer getProdEnergy() {
        return prodEnergy;
    }

    public void setProdEnergy(Integer prodEnergy) {
        this.prodEnergy = prodEnergy;
    }

    public Integer getProdHeat() {
        return prodHeat;
    }

    public void setProdHeat(Integer prodHeat) {
        this.prodHeat = prodHeat;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getSteel() {
        return steel;
    }

    public void setSteel(Integer steel) {
        this.steel = steel;
    }

    public Integer getTitan() {
        return titan;
    }

    public void setTitan(Integer titan) {
        this.titan = titan;
    }

    public Integer getPlant() {
        return plant;
    }

    public void setPlant(Integer plant) {
        this.plant = plant;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Integer getHeat() {
        return heat;
    }

    public void setHeat(Integer heat) {
        this.heat = heat;
    }

    public Integer getTileGreen() {
        return tileGreen;
    }

    public void setTileGreen(Integer tileGreen) {
        this.tileGreen = tileGreen;
    }

    public Integer getTileCity() {
        return tileCity;
    }

    public void setTileCity(Integer tileCity) {
        this.tileCity = tileCity;
    }
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
