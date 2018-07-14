package az.kerimov.mars;

import az.kerimov.mars.pojo.*;
import az.kerimov.mars.pojo.Error;
import az.kerimov.mars.services.MarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MarsController {
    @Autowired
    private MarsService marsService;

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


    @PostMapping("/useCard")
    public Response useCard(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGamePlayerMat(marsService.useCard(request.getPlayerId(), request.getGameId(), request.getGameHash(), request.getCardId(), false, ' ', 0));
            response.setData(data);
        } catch (MarsException e) {
            e.printStackTrace();

        }
        return response;
    }

    @PostMapping("/useCardWithSteel")
    public Response useCardWithSteel(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGamePlayerMat(marsService.useCard(request.getPlayerId(), request.getGameId(), request.getGameHash(), request.getCardId(), true, 's', request.getResourceCount()));
            response.setData(data);
        } catch (MarsException e) {
            e.printStackTrace();

        }
        return response;
    }

    @PostMapping("/useCardWithTitan")
    public Response useCardWithTitan(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGamePlayerMat(marsService.useCard(request.getPlayerId(), request.getGameId(), request.getGameHash(), request.getCardId(), true, 't', request.getResourceCount()));
            response.setData(data);
        } catch (MarsException e) {
            e.printStackTrace();

        }
        return response;
    }

    @PostMapping("/newGeneration")
    public Response newGeneration(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGame(marsService.newGeneration(request.getGameId(), request.getGameHash()));
        } catch (MarsException e) {
            e.printStackTrace();

        }
        return response;
    }

    @PostMapping("/raiseTemperatureByHeat")
    public Response raiseTemperatureByHeat(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGame(marsService.raiseTemperatureByHeat(request.getPlayerId(), request.getGameId(), request.getGameHash()));
        } catch (MarsException e) {
            e.printStackTrace();

        }
        return response;
    }

    @PostMapping("/addGreenery")
    public Response addGreenery(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGame(marsService.addGreenery(request.getPlayerId(), request.getGameId(), request.getGameHash()));
        } catch (MarsException e) {
            e.printStackTrace();

        }
        return response;
    }

    @PostMapping("/addOcean")
    public Response addOcean(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGame(marsService.addOcean(request.getPlayerId(), request.getGameId(), request.getGameHash()));
        } catch (MarsException e) {
            e.printStackTrace();

        }
        return response;
    }

    @PostMapping("/addGreeneryForMoney")
    public Response addGreeneryForMoney(@RequestBody Request request) {
        Data data = new Data();
        Response response = new Response();
        try {
            data.setGame(marsService.addGreeneryForMoney(request.getPlayerId(), request.getGameId(), request.getGameHash()));
        } catch (MarsException e) {
            e.printStackTrace();

        }
        return response;
    }
}
