package az.kerimov.mars.entity;

import javax.persistence.*;

@Entity(name ="mars_corporations")
public class Corporation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name ="deck_id")
    private CardDeck cardDeck;

    private String title;

    @Column(name ="init_money")
    private Integer initMoney;

    @Column(name ="init_steel")
    private Integer initSteel;
    @Column(name ="init_titan")
    private Integer initTitan;
    @Column(name ="init_plant")
    private Integer initPlant;
    @Column(name ="init_energy")
    private Integer initEnergy;
    @Column(name ="init_heat")
    private Integer initHeat;
    @Column(name ="init_prod_money")
    private Integer initProdMoney;
    @Column(name ="init_prod_steel")
    private Integer initProdSteel;
    @Column(name ="init_prod_titan")
    private Integer initProdTitan;
    @Column(name ="init_prod_plant")
    private Integer initProdPlant;
    @Column(name ="init_prod_energy")
    private Integer initProdEnergy;
    @Column(name ="init_prod_heat")
    private Integer initProdHeat;
    @Column(name ="text_effect")
    private String textEffect;
    @Column(name ="text_action")
    private String textAction;
    @Column(name ="text_title")
    private String textTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getInitMoney() {
        return initMoney;
    }

    public void setInitMoney(Integer initMoney) {
        this.initMoney = initMoney;
    }

    public Integer getInitSteel() {
        return initSteel;
    }

    public void setInitSteel(Integer initSteel) {
        this.initSteel = initSteel;
    }

    public Integer getInitTitan() {
        return initTitan;
    }

    public void setInitTitan(Integer initTitan) {
        this.initTitan = initTitan;
    }

    public Integer getInitPlant() {
        return initPlant;
    }

    public void setInitPlant(Integer initPlant) {
        this.initPlant = initPlant;
    }

    public Integer getInitEnergy() {
        return initEnergy;
    }

    public void setInitEnergy(Integer initEnergy) {
        this.initEnergy = initEnergy;
    }

    public Integer getInitHeat() {
        return initHeat;
    }

    public void setInitHeat(Integer initHeat) {
        this.initHeat = initHeat;
    }

    public Integer getInitProdMoney() {
        return initProdMoney;
    }

    public void setInitProdMoney(Integer initProdMoney) {
        this.initProdMoney = initProdMoney;
    }

    public Integer getInitProdSteel() {
        return initProdSteel;
    }

    public void setInitProdSteel(Integer initProdSteel) {
        this.initProdSteel = initProdSteel;
    }

    public Integer getInitProdTitan() {
        return initProdTitan;
    }

    public void setInitProdTitan(Integer initProdTitan) {
        this.initProdTitan = initProdTitan;
    }

    public Integer getInitProdPlant() {
        return initProdPlant;
    }

    public void setInitProdPlant(Integer initProdPlant) {
        this.initProdPlant = initProdPlant;
    }

    public Integer getInitProdEnergy() {
        return initProdEnergy;
    }

    public void setInitProdEnergy(Integer initProdEnergy) {
        this.initProdEnergy = initProdEnergy;
    }

    public Integer getInitProdHeat() {
        return initProdHeat;
    }

    public void setInitProdHeat(Integer initProdHeat) {
        this.initProdHeat = initProdHeat;
    }

    public String getTextEffect() {
        return textEffect;
    }

    public void setTextEffect(String textEffect) {
        this.textEffect = textEffect;
    }

    public String getTextAction() {
        return textAction;
    }

    public void setTextAction(String textAction) {
        this.textAction = textAction;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }
}
