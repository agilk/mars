package az.kerimov.mars;

import az.kerimov.mars.entity.Card;
import az.kerimov.mars.entity.Game;
import az.kerimov.mars.entity.GameCard;
import az.kerimov.mars.pojo.*;
import az.kerimov.mars.pojo.Error;
import az.kerimov.mars.services.MarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MarsController {
    @Autowired
    private MarsService marsService;

    @PostMapping("/startNewGame")
    public Response startNewGame(@RequestBody Request request){
        Data data = new Data();
        Response response = new Response();
        try{
            Game game = marsService.startNewGame(request.getDecks(), request.getPlayers());
            data.setGame(game);
            data.setPlayers(marsService.getPlayersOfGame(game));
            response.setData(data);
        }catch (Exception e){
            Error error = new Error(-1, e.getMessage());
            response.setError(error);

        }
        return response;
    }

    @PostMapping("/getCardById")
    public Response getCardById(@RequestBody Request request){
        Data data = new Data();
        Response response = new Response();
        try{
            data.setCard(marsService.getCardById(request.getCardId()));
            response.setData(data);
        }catch (Exception e){
            Error error = new Error(-1, e.getMessage());
            response.setError(error);

        }
        return response;
    }

    @PostMapping("/showRandomCard")
    public Response showRandomCard(@RequestBody Request request){
        Data data = new Data();
        Response response = new Response();
        try{
            data.setGameCard(marsService.showRandomCard(request.getGameId(), request.getGameHash(), request.getPlayerId()));
            response.setData(data);
        }catch (Exception e){
            Error error = new Error(-1, e.getMessage());
            response.setError(error);

        }
        return response;
    }

    @PostMapping("/showGenerationCards")
    public Response showGenerationCards(@RequestBody Request request){
        Data data = new Data();
        Response response = new Response();
        try{
            data.setGameCards(marsService.showGenerationCards(request.getGameId(), request.getGameHash(), request.getPlayerId()));
            response.setData(data);
        }catch (Exception e){
            Error error = new Error(-1, e.getMessage());
            response.setError(error);

        }
        return response;
    }

    @PostMapping("/buyCards")
    public Response buyCards(@RequestBody Request request){
        Data data = new Data();
        Response response = new Response();
        try{
            data.setCards(marsService.pickUpCards(request.getPlayerId(), request.getGameId(), request.getGameHash(), request.getCards()));
            response.setData(data);
        }catch (Exception e){
            Error error = new Error(-1, e.getMessage());
            response.setError(error);

        }
        return response;
    }

    @PostMapping("/buyCard")
    public Response buyCard(@RequestBody Request request){
        Data data = new Data();
        Response response = new Response();
        try{
            data.setCard(marsService.pickUpCard(request.getPlayerId(), request.getGameId(), request.getGameHash(), request.getCardId()));
            response.setData(data);
        }catch (Exception e){
            Error error = new Error(-1, e.getMessage());
            response.setError(error);
            e.printStackTrace();

        }
        return response;
    }

    @PostMapping("/pickUpFreeCard")
    public Integer pickUpFreeCard(@RequestBody Request request){
        Game game = marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
        Card card = marsService.getCardById(request.getCardId());
        return marsService.pickUpFreeCard(request.getPlayerId(), game, card);
    }

    @PostMapping("/sellCard")
    public Game sellCard(@RequestBody Request request){
        Game game = marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
        Card card = marsService.getCardById(request.getCardId());
        marsService.sellCard(request.getPlayerId(), game, card);
        return marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
    }


    @PostMapping("/useCard")
    public Game useCard(@RequestBody Request request){
        Game game = marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
        Card card = marsService.getCardById(request.getCardId());
        marsService.useCard(request.getPlayerId(), game, card, false, ' ', 0);
        return marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
    }

    @PostMapping("/useCardWithSteel")
    public Game useCardWithSteel(@RequestBody Request request){
        Game game = marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
        Card card = marsService.getCardById(request.getCardId());
        marsService.useCard(request.getPlayerId(), game, card, true, 's', request.getResourceCount());
        return marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
    }

    @PostMapping("/useCardWithTitan")
    public Game useCardWithTitan(@RequestBody Request request){
        Game game = marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
        Card card = marsService.getCardById(request.getCardId());
        marsService.useCard(request.getPlayerId(), game, card, true, 't', request.getResourceCount());
        return marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
    }

    @PostMapping("/newGeneration")
    public List<GameCard> newGeneration(@RequestBody Request request){
        Game game = marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
        marsService.newGeneration(game);
        return marsService.showGenerationCards(request.getGameId(), request.getGameHash(), request.getPlayerId());
    }

    @PostMapping("/raiseTemperatureByHeat")
    public Game raiseTemperatureByHeat(@RequestBody Request request){
        Game game = marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
        marsService.raiseTemperatureByHeat(request.getPlayerId(), game);
        return marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
    }

    @PostMapping("/addGreenery")
    public Game addGreenery(@RequestBody Request request){
        Game game = marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
        marsService.addGreenery(request.getPlayerId(), game);
        return marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
    }

    @PostMapping("/addOcean")
    public Game addOcean(@RequestBody Request request){
        Game game = marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
        marsService.addOcean(request.getPlayerId(), game);
        return marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
    }

    @PostMapping("/addGreeneryForMoney")
    public Game addGreeneryForMoney(@RequestBody Request request){
        Game game = marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
        marsService.addGreeneryForMoney(request.getPlayerId(), game);
        return marsService.getGameByIdAndHash(request.getGameId(), request.getGameHash());
    }
}
