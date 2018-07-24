package az.kerimov.mars;

import az.kerimov.mars.pojo.*;
import az.kerimov.mars.pojo.Error;
import az.kerimov.mars.services.MarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class MarsController {
    @Autowired
    private MarsService marsService;

    @CrossOrigin(origins = "*")
    @PostMapping("/test")
    public Response test(@RequestBody Request request){
        Data data = new Data();
        Response response = new Response();
        List<Integer> xx = new ArrayList<>();
        xx.add(1);
        data.setCorporations(marsService.getAllCorporationsByDecks(xx));
        response.setData(data);
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/startNewGame")
    public Response startNewGame(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            az.kerimov.mars.entity.Game game = marsService.startNewGame(request.getDecks(), request.getPlayers());
            data.setGame(game);
            data.setPlayers(marsService.getPlayersOfGame(game));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getCardById")
    public Response getCardById(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setCard(marsService.getCardById(request.getCardId()));
            response.setData(data);
        } catch (Exception e) {
            response.setError(new Error((MarsException) e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/showRandomCard")
    public Response showRandomCard(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGameCard(marsService.showRandomCard(request.getGameId(), request.getGameHash(), request.getPlayerId()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getPlayerMat")
    public Response getPlayerMat(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGamePlayerMat(marsService.getPlayerMat(request.getGameId(), request.getGameHash(), request.getPlayerId()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/showGenerationCards")
    public Response showGenerationCards(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGameCards(marsService.showGenerationCards(request.getGameId(), request.getGameHash(), request.getPlayerId()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/showPlayerUnusedCards")
    public Response showPlayerUnusedCards(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGamePlayerCards(marsService.showPlayerUnusedCards(request.getGameId(), request.getGameHash(), request.getPlayerId()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/showCorporations")
    public Response showCorporations(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGameCorporations(marsService.showCorporationCards(request.getGameId(), request.getGameHash(), request.getPlayerId()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/pickUpCorporation")
    public Response pickUpCorporation(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGamePlayerMat(marsService.pickUpCorporation(request.getPlayerId(), request.getGameId(), request.getGameHash(), request.getCorporationId()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/buyCards")
    public Response buyCards(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setCards(marsService.pickUpCards(request.getPlayerId(), request.getGameId(), request.getGameHash(), request.getCards()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/buyCard")
    public Response buyCard(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setCard(marsService.pickUpCard(request.getPlayerId(), request.getGameId(), request.getGameHash(), request.getCardId()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));
        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/pickUpFreeCard")
    public Response pickUpFreeCard(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setCard(marsService.pickUpFreeCard(request.getPlayerId(), request.getGameId(), request.getGameHash(), request.getCardId()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));
        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/sellCard")
    public Response sellCard(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGamePlayerMat(marsService.sellCard(request.getPlayerId(), request.getGameId(), request.getGameHash(), request.getCardId()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));
        }
        return response;
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/useCard")
    public Response useCard(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGamePlayerMat(marsService.useCard(request.getPlayerId(), request.getGameId(), request.getGameHash(), request.getCardId(), false, ' ', 0));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/useCardWithSteel")
    public Response useCardWithSteel(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGamePlayerMat(marsService.useCard(request.getPlayerId(), request.getGameId(), request.getGameHash(), request.getCardId(), true, 's', request.getResourceCount()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/useCardWithTitan")
    public Response useCardWithTitan(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGamePlayerMat(marsService.useCard(request.getPlayerId(), request.getGameId(), request.getGameHash(), request.getCardId(), true, 't', request.getResourceCount()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/newGeneration")
    public Response newGeneration(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGameCards(marsService.newGeneration(request.getGameId(), request.getGameHash()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/raiseTemperatureByHeat")
    public Response raiseTemperatureByHeat(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGame(marsService.raiseTemperatureByHeat(request.getPlayerId(), request.getGameId(), request.getGameHash()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addGreenery")
    public Response addGreenery(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGame(marsService.addGreenery(request.getPlayerId(), request.getGameId(), request.getGameHash()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addOcean")
    public Response addOcean(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGame(marsService.addOcean(request.getPlayerId(), request.getGameId(), request.getGameHash()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addGreeneryForMoney")
    public Response addGreeneryForMoney(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGame(marsService.addGreeneryForMoney(request.getPlayerId(), request.getGameId(), request.getGameHash()));
            response.setData(data);
        } catch (MarsException e) {
            response.setError(new Error(e));

        }
        return response;
    }
}
