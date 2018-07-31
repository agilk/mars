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

    @Column(name = "cost_steel")
    private Integer costSteel;
    @Column(name = "cost_titan")
    private Integer costTitan;
    @Column(name = "plant_greenery")
    private Integer plantGreenery;
    @Column(name = "heat_temperature")
    private Integer heatTemperature;
    @Column(name = "cost_sp_power")
    private Integer costStandardProjectPower;
    @Column(name = "cost_sp_temp")
    private Integer costStandardProjectTemperature;
    @Column(name = "cost_sp_ocean")
    private Integer costStandardProjectOcean;
    @Column(name = "cost_sp_green")
    private Integer costStandardProjectGreenery;
    @Column(name = "cost_sp_city")
    private Integer costStandardProjectCity;
    @Column(name = "eff_cost_power_tag")
    private Integer effCostPowerTag;

    @Column(name = "tag_science")
    private Integer tagScience;
    @Column(name = "tag_building")
    private Integer tagBuilding;
    @Column(name = "tag_space")
    private Integer tagSpace;
    @Column(name = "tag_energy")
    private Integer tagEnergy;
    @Column(name = "tag_earth")
    private Integer tagEarth;
    @Column(name = "tag_jovian")
    private Integer tagJovian;
    @Column(name = "tag_microbe")
    private Integer tagMicrobe;
    @Column(name = "tag_plant")
    private Integer tagPlant;
    @Column(name = "tag_animal")
    private Integer tagAnimal;
    @Column(name = "tag_venus")
    private Integer tagVenus;
    @Column(name = "tag_event")
    private Integer tagEvent;

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

    public Integer getCostSteel() {
        return costSteel;
    }

    public void setCostSteel(Integer costSteel) {
        this.costSteel = costSteel;
    }

    public Integer getCostTitan() {
        return costTitan;
    }

    public void setCostTitan(Integer costTitan) {
        this.costTitan = costTitan;
    }

    public Integer getPlantGreenery() {
        return plantGreenery;
    }

    public void setPlantGreenery(Integer plantGreenery) {
        this.plantGreenery = plantGreenery;
    }

    public Integer getHeatTemperature() {
        return heatTemperature;
    }

    public void setHeatTemperature(Integer heatTemperature) {
        this.heatTemperature = heatTemperature;
    }

    public Integer getCostStandardProjectPower() {
        return costStandardProjectPower;
    }

    public void setCostStandardProjectPower(Integer costStandardProjectPower) {
        this.costStandardProjectPower = costStandardProjectPower;
    }

    public Integer getCostStandardProjectTemperature() {
        return costStandardProjectTemperature;
    }

    public void setCostStandardProjectTemperature(Integer costStandardProjectTemperature) {
        this.costStandardProjectTemperature = costStandardProjectTemperature;
    }

    public Integer getCostStandardProjectOcean() {
        return costStandardProjectOcean;
    }

    public void setCostStandardProjectOcean(Integer costStandardProjectOcean) {
        this.costStandardProjectOcean = costStandardProjectOcean;
    }

    public Integer getCostStandardProjectGreenery() {
        return costStandardProjectGreenery;
    }

    public void setCostStandardProjectGreenery(Integer costStandardProjectGreenery) {
        this.costStandardProjectGreenery = costStandardProjectGreenery;
    }

    public Integer getCostStandardProjectCity() {
        return costStandardProjectCity;
    }

    public void setCostStandardProjectCity(Integer costStandardProjectCity) {
        this.costStandardProjectCity = costStandardProjectCity;
    }

    public Integer getEffCostPowerTag() {
        return effCostPowerTag;
    }

    public Integer getTagScience() {
        return tagScience;
    }

    public void setTagScience(Integer tagScience) {
        this.tagScience = tagScience;
    }

    public Integer getTagBuilding() {
        return tagBuilding;
    }

    public void setTagBuilding(Integer tagBuilding) {
        this.tagBuilding = tagBuilding;
    }

    public Integer getTagSpace() {
        return tagSpace;
    }

    public void setTagSpace(Integer tagSpace) {
        this.tagSpace = tagSpace;
    }

    public Integer getTagEnergy() {
        return tagEnergy;
    }

    public void setTagEnergy(Integer tagEnergy) {
        this.tagEnergy = tagEnergy;
    }

    public Integer getTagEarth() {
        return tagEarth;
    }

    public void setTagEarth(Integer tagEarth) {
        this.tagEarth = tagEarth;
    }

    public Integer getTagJovian() {
        return tagJovian;
    }

    public void setTagJovian(Integer tagJovian) {
        this.tagJovian = tagJovian;
    }

    public Integer getTagMicrobe() {
        return tagMicrobe;
    }

    public void setTagMicrobe(Integer tagMicrobe) {
        this.tagMicrobe = tagMicrobe;
    }

    public Integer getTagPlant() {
        return tagPlant;
    }

    public void setTagPlant(Integer tagPlant) {
        this.tagPlant = tagPlant;
    }

    public Integer getTagAnimal() {
        return tagAnimal;
    }

    public void setTagAnimal(Integer tagAnimal) {
        this.tagAnimal = tagAnimal;
    }

    public Integer getTagVenus() {
        return tagVenus;
    }

    public void setTagVenus(Integer tagVenus) {
        this.tagVenus = tagVenus;
    }

    public Integer getTagEvent() {
        return tagEvent;
    }

    public void setTagEvent(Integer tagEvent) {
        this.tagEvent = tagEvent;
    }

    public void setEffCostPowerTag(Integer effCostPowerTag) {
        this.effCostPowerTag = effCostPowerTag;

    }
}
