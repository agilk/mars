package az.kerimov.mars.entity;

import javax.persistence.*;

@Entity(name = "mars_cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "card_type")
    private CardType cardType;

    @ManyToOne
    @JoinColumn(name = "card_deck")
    private CardDeck cardDeck;
    @Column(name = "cost")
    private Integer cost;

    @Column(name = "card_title")
    private String cardTitle;
    @Column(name = "html_text")
    private String textHtml;

    @Column(name = "req_min_temp")
    private Integer reqMinTemp;
    @Column(name = "req_max_temp")
    private Integer reqMaxTemp;
    @Column(name = "req_min_oxyg")
    private Integer reqMinOxyg;
    @Column(name = "req_max_oxyg")
    private Integer reqMaxOxyg;
    @Column(name = "req_min_ocean")
    private Integer reqMinOcean;
    @Column(name = "req_max_ocean")
    private Integer reqMaxOcean;
    @Column(name = "req_min_venus")
    private Integer reqMinVenus;
    @Column(name = "req_max_venus")
    private Integer reqMaxVenus;

    @Column(name = "req_scence")
    private Integer reqScence;
    @Column(name = "req_building")
    private Integer reqBuilding;
    @Column(name = "req_space")
    private Integer reqSpace;
    @Column(name = "req_microbe")
    private Integer reqMicrobe;
    @Column(name = "req_plant")
    private Integer reqPlant;
    @Column(name = "req_animal")
    private Integer reqAnimal;
    @Column(name = "req_city")
    private Integer reqCity;
    @Column(name = "req_earth")
    private Integer reqEarth;
    @Column(name = "req_jovian")
    private Integer reqJovian;
    @Column(name = "req_energy")
    private Integer reqEnergy;
    @Column(name = "req_venus")
    private Integer reqVenus;

    @Column(name = "req_titan_prod")
    private Integer reqTitanProd;
    @Column(name = "req_steel_prod")
    private Integer reqSteelProd;
    @Column(name = "req_plant_prod")
    private Integer reqPlantProd;
    @Column(name = "req_energy_prod")
    private Integer reqEnergyProd;
    @Column(name = "req_heat_prod")
    private Integer reqHeatProd;
    @Column(name = "req_tile_green")
    private Integer reqTileGreen;
    @Column(name = "req_tile_city")
    private Integer reqTileCity;
    @Column(name = "req_min_rating")
    private Integer reqMinRating;
    @Column(name = "req_max_rating")
    private Integer reqMaxRating;
    @Column(name = "req_floaters")
    private Integer reqFloaters;

    @Column(name = "tag_scence")
    private Integer tagScence;
    @Column(name = "tag_building")
    private Integer tagBuilding;
    @Column(name = "tag_space")
    private Integer tagSpace;
    @Column(name = "tag_microbe")
    private Integer tagMicrobe;
    @Column(name = "tag_plant")
    private Integer tagPlant;
    @Column(name = "tag_animal")
    private Integer tagAnimal;
    @Column(name = "tag_city")
    private Integer tagCity;
    @Column(name = "tag_earth")
    private Integer tagEarth;
    @Column(name = "tag_jovian")
    private Integer tagJovian;
    @Column(name = "tag_energy")
    private Integer tagEnergy;
    @Column(name = "tag_venus")
    private Integer tagVenus;
    @Column(name = "tag_event")
    private Integer tagEvent;

    @Column(name = "prod_eff_money")
    private Integer prodEffMoney;
    @Column(name = "prod_eff_money_oth")
    private Integer prodEffMoneyOth;
    @Column(name = "prod_eff_steel")
    private Integer prodEffSteel;
    @Column(name = "prod_eff_steel_oth")
    private Integer prodEffSteelOth;
    @Column(name = "prod_eff_titan")
    private Integer prodEffTitan;
    @Column(name = "prod_eff_titan_oth")
    private Integer prodEffTitanOth;
    @Column(name = "prod_eff_plant")
    private Integer prodEffPlant;
    @Column(name = "prod_eff_plant_oth")
    private Integer prodEffPlantOth;
    @Column(name = "prod_eff_energy")
    private Integer prodEffEnergy;
    @Column(name = "prod_eff_energy_oth")
    private Integer prodEffEnergyOth;
    @Column(name = "prod_eff_heat")
    private Integer prodEffHeat;
    @Column(name = "prod_eff_heat_oth")
    private Integer prodEffHeatOth;

    @Column(name = "res_eff_money")
    private Integer resEffMoney;
    @Column(name = "res_eff_money_oth")
    private Integer resEffMoneyOth;
    @Column(name = "res_eff_steel")
    private Integer resEffSteel;
    @Column(name = "res_eff_steel_oth")
    private Integer resEffSteelOth;
    @Column(name = "res_eff_titan")
    private Integer resEffTitan;
    @Column(name = "res_eff_titan_oth")
    private Integer resEffTitanOth;
    @Column(name = "res_eff_plant")
    private Integer resEffPlant;
    @Column(name = "res_eff_plant_oth")
    private Integer resEffPlantOth;
    @Column(name = "res_eff_energy")
    private Integer resEffEnergy;
    @Column(name = "res_eff_energy_oth")
    private Integer resEffEnergyOth;
    @Column(name = "res_eff_heat")
    private Integer resEffHeat;
    @Column(name = "res_eff_heat_oth")
    private Integer resEffHeatOth;

    @Column(name = "terr_eff_temp")
    private Integer terrEffTemp;
    @Column(name = "terr_eff_oxygen")
    private Integer terrEffOxygen;
    @Column(name = "terr_eff_ocean")
    private Integer terrEffOcean;
    @Column(name = "terr_eff_venus")
    private Integer terrEffVenus;
    @Column(name = "terr_eff_rating")
    private Integer terrEffRating;
    @Column(name = "terr_eff_points")
    private Integer terrEffPoints;

    @Column(name = "rec_action_text")
    private String recActionText;
    @Column(name = "one_time_effect_text")
    private String oneTimeEffectText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public Integer getReqMinTemp() {
        return reqMinTemp;
    }

    public void setReqMinTemp(Integer reqMinTemp) {
        this.reqMinTemp = reqMinTemp;
    }

    public Integer getReqMaxTemp() {
        return reqMaxTemp;
    }

    public void setReqMaxTemp(Integer reqMaxTemp) {
        this.reqMaxTemp = reqMaxTemp;
    }

    public Integer getReqMinOxyg() {
        return reqMinOxyg;
    }

    public void setReqMinOxyg(Integer reqMinOxyg) {
        this.reqMinOxyg = reqMinOxyg;
    }

    public Integer getReqMaxOxyg() {
        return reqMaxOxyg;
    }

    public void setReqMaxOxyg(Integer reqMaxOxyg) {
        this.reqMaxOxyg = reqMaxOxyg;
    }

    public Integer getReqMinOcean() {
        return reqMinOcean;
    }

    public void setReqMinOcean(Integer reqMinOcean) {
        this.reqMinOcean = reqMinOcean;
    }

    public Integer getReqMaxOcean() {
        return reqMaxOcean;
    }

    public void setReqMaxOcean(Integer reqMaxOcean) {
        this.reqMaxOcean = reqMaxOcean;
    }

    public Integer getReqMinVenus() {
        return reqMinVenus;
    }

    public void setReqMinVenus(Integer reqMinVenus) {
        this.reqMinVenus = reqMinVenus;
    }

    public Integer getReqMaxVenus() {
        return reqMaxVenus;
    }

    public void setReqMaxVenus(Integer reqMaxVenus) {
        this.reqMaxVenus = reqMaxVenus;
    }

    public Integer getReqScence() {
        return reqScence;
    }

    public void setReqScence(Integer reqScence) {
        this.reqScence = reqScence;
    }

    public Integer getReqBuilding() {
        return reqBuilding;
    }

    public void setReqBuilding(Integer reqBuilding) {
        this.reqBuilding = reqBuilding;
    }

    public Integer getReqSpace() {
        return reqSpace;
    }

    public void setReqSpace(Integer reqSpace) {
        this.reqSpace = reqSpace;
    }

    public Integer getReqMicrobe() {
        return reqMicrobe;
    }

    public void setReqMicrobe(Integer reqMicrobe) {
        this.reqMicrobe = reqMicrobe;
    }

    public Integer getReqPlant() {
        return reqPlant;
    }

    public void setReqPlant(Integer reqPlant) {
        this.reqPlant = reqPlant;
    }

    public Integer getReqAnimal() {
        return reqAnimal;
    }

    public void setReqAnimal(Integer reqAnimal) {
        this.reqAnimal = reqAnimal;
    }

    public Integer getReqCity() {
        return reqCity;
    }

    public void setReqCity(Integer reqCity) {
        this.reqCity = reqCity;
    }

    public Integer getReqEarth() {
        return reqEarth;
    }

    public void setReqEarth(Integer reqEarth) {
        this.reqEarth = reqEarth;
    }

    public Integer getReqJovian() {
        return reqJovian;
    }

    public void setReqJovian(Integer reqJovian) {
        this.reqJovian = reqJovian;
    }

    public Integer getReqEnergy() {
        return reqEnergy;
    }

    public void setReqEnergy(Integer reqEnergy) {
        this.reqEnergy = reqEnergy;
    }

    public Integer getReqVenus() {
        return reqVenus;
    }

    public void setReqVenus(Integer reqVenus) {
        this.reqVenus = reqVenus;
    }

    public Integer getReqTitanProd() {
        return reqTitanProd;
    }

    public void setReqTitanProd(Integer reqTitanProd) {
        this.reqTitanProd = reqTitanProd;
    }

    public Integer getReqSteelProd() {
        return reqSteelProd;
    }

    public void setReqSteelProd(Integer reqSteelProd) {
        this.reqSteelProd = reqSteelProd;
    }

    public Integer getReqPlantProd() {
        return reqPlantProd;
    }

    public void setReqPlantProd(Integer reqPlantProd) {
        this.reqPlantProd = reqPlantProd;
    }

    public Integer getReqEnergyProd() {
        return reqEnergyProd;
    }

    public void setReqEnergyProd(Integer reqEnergyProd) {
        this.reqEnergyProd = reqEnergyProd;
    }

    public Integer getReqHeatProd() {
        return reqHeatProd;
    }

    public void setReqHeatProd(Integer reqHeatProd) {
        this.reqHeatProd = reqHeatProd;
    }

    public Integer getReqTileGreen() {
        return reqTileGreen;
    }

    public void setReqTileGreen(Integer reqTileGreen) {
        this.reqTileGreen = reqTileGreen;
    }

    public Integer getReqTileCity() {
        return reqTileCity;
    }

    public void setReqTileCity(Integer reqTileCity) {
        this.reqTileCity = reqTileCity;
    }

    public Integer getReqMinRating() {
        return reqMinRating;
    }

    public void setReqMinRating(Integer reqMinRating) {
        this.reqMinRating = reqMinRating;
    }

    public Integer getReqMaxRating() {
        return reqMaxRating;
    }

    public void setReqMaxRating(Integer reqMaxRating) {
        this.reqMaxRating = reqMaxRating;
    }

    public Integer getReqFloaters() {
        return reqFloaters;
    }

    public void setReqFloaters(Integer reqFloaters) {
        this.reqFloaters = reqFloaters;
    }

    public Integer getTagScence() {
        return tagScence;
    }

    public void setTagScence(Integer tagScence) {
        this.tagScence = tagScence;
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

    public Integer getTagCity() {
        return tagCity;
    }

    public void setTagCity(Integer tagCity) {
        this.tagCity = tagCity;
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

    public Integer getTagEnergy() {
        return tagEnergy;
    }

    public void setTagEnergy(Integer tagEnergy) {
        this.tagEnergy = tagEnergy;
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


    public Integer getProdEffMoney() {
        return prodEffMoney;
    }

    public void setProdEffMoney(Integer prodEffMoney) {
        this.prodEffMoney = prodEffMoney;
    }

    public Integer getProdEffMoneyOth() {
        return prodEffMoneyOth;
    }

    public void setProdEffMoneyOth(Integer prodEffMoneyOth) {
        this.prodEffMoneyOth = prodEffMoneyOth;
    }

    public Integer getProdEffSteel() {
        return prodEffSteel;
    }

    public void setProdEffSteel(Integer prodEffSteel) {
        this.prodEffSteel = prodEffSteel;
    }

    public Integer getProdEffSteelOth() {
        return prodEffSteelOth;
    }

    public void setProdEffSteelOth(Integer prodEffSteelOth) {
        this.prodEffSteelOth = prodEffSteelOth;
    }

    public Integer getProdEffTitan() {
        return prodEffTitan;
    }

    public void setProdEffTitan(Integer prodEffTitan) {
        this.prodEffTitan = prodEffTitan;
    }

    public Integer getProdEffTitanOth() {
        return prodEffTitanOth;
    }

    public void setProdEffTitanOth(Integer prodEffTitanOth) {
        this.prodEffTitanOth = prodEffTitanOth;
    }

    public Integer getProdEffPlant() {
        return prodEffPlant;
    }

    public void setProdEffPlant(Integer prodEffPlant) {
        this.prodEffPlant = prodEffPlant;
    }

    public Integer getProdEffPlantOth() {
        return prodEffPlantOth;
    }

    public void setProdEffPlantOth(Integer prodEffPlantOth) {
        this.prodEffPlantOth = prodEffPlantOth;
    }

    public Integer getProdEffEnergy() {
        return prodEffEnergy;
    }

    public void setProdEffEnergy(Integer prodEffEnergy) {
        this.prodEffEnergy = prodEffEnergy;
    }

    public Integer getProdEffEnergyOth() {
        return prodEffEnergyOth;
    }

    public void setProdEffEnergyOth(Integer prodEffEnergyOth) {
        this.prodEffEnergyOth = prodEffEnergyOth;
    }

    public Integer getProdEffHeat() {
        return prodEffHeat;
    }

    public void setProdEffHeat(Integer prodEffHeat) {
        this.prodEffHeat = prodEffHeat;
    }

    public Integer getProdEffHeatOth() {
        return prodEffHeatOth;
    }

    public void setProdEffHeatOth(Integer prodEffHeatOth) {
        this.prodEffHeatOth = prodEffHeatOth;
    }


    public Integer getResEffMoney() {
        return resEffMoney;
    }

    public void setResEffMoney(Integer resEffMoney) {
        this.resEffMoney = resEffMoney;
    }

    public Integer getResEffMoneyOth() {
        return resEffMoneyOth;
    }

    public void setResEffMoneyOth(Integer resEffMoneyOth) {
        this.resEffMoneyOth = resEffMoneyOth;
    }

    public Integer getResEffSteel() {
        return resEffSteel;
    }

    public void setResEffSteel(Integer resEffSteel) {
        this.resEffSteel = resEffSteel;
    }

    public Integer getResEffSteelOth() {
        return resEffSteelOth;
    }

    public void setResEffSteelOth(Integer resEffSteelOth) {
        this.resEffSteelOth = resEffSteelOth;
    }

    public Integer getResEffTitan() {
        return resEffTitan;
    }

    public void setResEffTitan(Integer resEffTitan) {
        this.resEffTitan = resEffTitan;
    }

    public Integer getResEffTitanOth() {
        return resEffTitanOth;
    }

    public void setResEffTitanOth(Integer resEffTitanOth) {
        this.resEffTitanOth = resEffTitanOth;
    }

    public Integer getResEffPlant() {
        return resEffPlant;
    }

    public void setResEffPlant(Integer resEffPlant) {
        this.resEffPlant = resEffPlant;
    }

    public Integer getResEffPlantOth() {
        return resEffPlantOth;
    }

    public void setResEffPlantOth(Integer resEffPlantOth) {
        this.resEffPlantOth = resEffPlantOth;
    }

    public Integer getResEffEnergy() {
        return resEffEnergy;
    }

    public void setResEffEnergy(Integer resEffEnergy) {
        this.resEffEnergy = resEffEnergy;
    }

    public Integer getResEffEnergyOth() {
        return resEffEnergyOth;
    }

    public void setResEffEnergyOth(Integer resEffEnergyOth) {
        this.resEffEnergyOth = resEffEnergyOth;
    }

    public Integer getResEffHeat() {
        return resEffHeat;
    }

    public void setResEffHeat(Integer resEffHeat) {
        this.resEffHeat = resEffHeat;
    }

    public Integer getResEffHeatOth() {
        return resEffHeatOth;
    }

    public void setResEffHeatOth(Integer resEffHeatOth) {
        this.resEffHeatOth = resEffHeatOth;
    }


    public Integer getTerrEffTemp() {
        return terrEffTemp;
    }

    public void setTerrEffTemp(Integer terrEffTemp) {
        this.terrEffTemp = terrEffTemp;
    }

    public Integer getTerrEffOxygen() {
        return terrEffOxygen;
    }

    public void setTerrEffOxygen(Integer terrEffOxygen) {
        this.terrEffOxygen = terrEffOxygen;
    }

    public Integer getTerrEffOcean() {
        return terrEffOcean;
    }

    public void setTerrEffOcean(Integer terrEffOcean) {
        this.terrEffOcean = terrEffOcean;
    }

    public Integer getTerrEffVenus() {
        return terrEffVenus;
    }

    public void setTerrEffVenus(Integer terrEffVenus) {
        this.terrEffVenus = terrEffVenus;
    }

    public Integer getTerrEffRating() {
        return terrEffRating;
    }

    public void setTerrEffRating(Integer terrEffRating) {
        this.terrEffRating = terrEffRating;
    }

    public Integer getTerrEffPoints() {
        return terrEffPoints;
    }

    public void setTerrEffPoIntegers(Integer terrEffPoints) {
        this.terrEffPoints = terrEffPoints;
    }

    public String getRecActionText() {
        return recActionText;
    }

    public void setRecActionText(String recActionText) {
        this.recActionText = recActionText;
    }

    public String getOneTimeEffectText() {
        return oneTimeEffectText;
    }

    public void setOneTimeEffectText(String oneTimeEffectText) {
        this.oneTimeEffectText = oneTimeEffectText;
    }

    public String getTextHtml() {
        return textHtml;
    }

    public void setTextHtml(String textHtml) {
        this.textHtml = textHtml;
    }

    public void setTerrEffPoints(Integer terrEffPoints) {
        this.terrEffPoints = terrEffPoints;
    }
}
