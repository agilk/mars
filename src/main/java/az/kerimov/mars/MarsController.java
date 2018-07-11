package az.kerimov.mars;

import az.kerimov.mars.pojo.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MarsController {
    @Autowired
    private MarsService marsService;

    @GetMapping("/getAllCards")
    public List<Card> getAllCards(){
        return marsService.getAllCards();

    }
    @PostMapping("/getAllCardsByDecks")
    public List<Card> getAllCardsByDecks(@RequestBody Request request){
        return marsService.getAllCardsByDecks(request.getDecks());

    }
    @GetMapping("/getAllDecks")
    public List<CardDeck> getAllDecks(){
        return marsService.getAllDecks();

    }

    @PostMapping("/startNewGame")
    public List<Card> startNewGame(@RequestBody Request request){
        Integer gameId = marsService.startNewGame(request.getDecks());
        return marsService.showRandomCards(marsService.getGameById(gameId), request.getPlayerId());
    }

    @PostMapping("/getCardById")
    public Card getCardById(@RequestBody Request request){
        return marsService.getCardById(request.getCardId());
    }

    @PostMapping("/showRandomCard")
    public Card showRandomCard(@RequestBody Request request){
        Game game = marsService.getGameById(request.getGameId());
        GameCard gameCard = marsService.showRandomCard(game, request.getPlayerId());
        return gameCard.getCard();
    }

    @PostMapping("/showRandomCards")
    public List<Card> showRandomCards(@RequestBody Request request){
        Game game = marsService.getGameById(request.getGameId());
        return marsService.showRandomCards(game, request.getPlayerId());
    }

    @PostMapping("/pickUpCards")
    public Integer pickUpCards(@RequestBody Request request){
        Game game = marsService.getGameById(request.getGameId());
        List<Card> cards = new ArrayList<>();
        for(Integer i: request.getCards()){
            cards.add(marsService.getCardById(i));
        }
        return marsService.pickUpCards(request.getPlayerId(), game, cards);
    }

    @PostMapping("/pickUpCard")
    public Integer pickUpCard(@RequestBody Request request){
        Game game = marsService.getGameById(request.getGameId());
        Card card = marsService.getCardById(request.getCardId());
        return marsService.pickUpCard(request.getPlayerId(), game, card);
    }

    @PostMapping("/pickUpFreeCard")
    public Integer pickUpFreeCard(@RequestBody Request request){
        Game game = marsService.getGameById(request.getGameId());
        Card card = marsService.getCardById(request.getCardId());
        return marsService.pickUpFreeCard(request.getPlayerId(), game, card);
    }

    @PostMapping("/sellCard")
    public Game sellCard(@RequestBody Request request){
        Game game = marsService.getGameById(request.getGameId());
        Card card = marsService.getCardById(request.getCardId());
        marsService.sellCard(request.getPlayerId(), game, card);
        return marsService.getGameById(request.getGameId());
    }


    @PostMapping("/useCard")
    public Game useCard(@RequestBody Request request){
        Game game = marsService.getGameById(request.getGameId());
        Card card = marsService.getCardById(request.getCardId());
        marsService.useCard(request.getPlayerId(), game, card, false, ' ', 0);
        return marsService.getGameById(request.getGameId());
    }

    @PostMapping("/useCardWithSteel")
    public Game useCardWithSteel(@RequestBody Request request){
        Game game = marsService.getGameById(request.getGameId());
        Card card = marsService.getCardById(request.getCardId());
        marsService.useCard(request.getPlayerId(), game, card, true, 's', request.getResourceCount());
        return marsService.getGameById(request.getGameId());
    }

    @PostMapping("/useCardWithTitan")
    public Game useCardWithTitan(@RequestBody Request request){
        Game game = marsService.getGameById(request.getGameId());
        Card card = marsService.getCardById(request.getCardId());
        marsService.useCard(request.getPlayerId(), game, card, true, 't', request.getResourceCount());
        return marsService.getGameById(request.getGameId());
    }

    @PostMapping("/newGeneration")
    public List<Card> newGeneration(@RequestBody Request request){
        Game game = marsService.getGameById(request.getGameId());
        marsService.newGeneration(game);
        return marsService.showRandomCards(game, request.getPlayerId());
    }

    @PostMapping("/raiseTemperatureByHeat")
    public Game raiseTemperatureByHeat(@RequestBody Request request){
        Game game = marsService.getGameById(request.getGameId());
        marsService.raiseTemperatureByHeat(request.getPlayerId(), game);
        return marsService.getGameById(request.getGameId());
    }

    @PostMapping("/addGreenery")
    public Game addGreenery(@RequestBody Request request){
        Game game = marsService.getGameById(request.getGameId());
        marsService.addGreenery(request.getPlayerId(), game);
        return marsService.getGameById(request.getGameId());
    }

    @PostMapping("/addOcean")
    public Game addOcean(@RequestBody Request request){
        Game game = marsService.getGameById(request.getGameId());
        marsService.addOcean(request.getPlayerId(), game);
        return marsService.getGameById(request.getGameId());
    }

    @PostMapping("/addGreeneryForMoney")
    public Game addGreeneryForMoney(@RequestBody Request request){
        Game game = marsService.getGameById(request.getGameId());
        marsService.addGreeneryForMoney(request.getPlayerId(), game);
        return marsService.getGameById(request.getGameId());
    }
}
