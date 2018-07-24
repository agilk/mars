
var gameId;
var gameHash;
var players = [];
var canPickCard = false;
var cards = [];

function updatePlayerMat(mat) {
    $("#rating").text(mat.rating);
    $("#money").text(mat.money);
    $("#steel").text(mat.steel);
    $("#titan").text(mat.titan);
    $("#plant").text(mat.plant);
    $("#energy").text(mat.energy);
    $("#heat").text(mat.heat);
    $("#moneyProd").text(mat.prodMoney);
    $("#steelProd").text(mat.prodSteel);
    $("#titanProd").text(mat.prodTitan);
    $("#plantProd").text(mat.prodPlant);
    $("#energyProd").text(mat.prodEnergy);
    $("#heatProd").text(mat.prodHeat);
}

function pickUpCorp(playerId, corpId) {

    var sendData = "{\"gameId\": " + gameId + ", \"gameHash\": \"" + gameHash + "\", \"playerId\": " + playerId + ", \"corporationId\":" + corpId + "}";
    $.ajax({
        type: "POST",
        url: "http://localhost:19001/pickUpCorporation",
        dataType: "json",
        headers: {"Content-type": "application/json"},
        crossDomain: true,
        success: function (msg) {

            updatePlayerMat(msg.data.gamePlayerMat);
        },

        data: sendData
    });
    $("#corporations").text("");
    players[0].corporations = [];
    canPickCard = true;
}

function pickUpCard(obj, playerId, cardId) {

    if (canPickCard) {
        var sendData = "{\"gameId\": " + gameId + ", \"gameHash\": \"" + gameHash + "\", \"playerId\": " + playerId + ", \"cardId\":" + cardId + "}";
        $.ajax({
            type: "POST",
            url: "http://localhost:19001/buyCard",
            dataType: "json",
            headers: {"Content-type": "application/json"},
            crossDomain: true,
            success: function (msg) {

                //updatePlayerMat(msg.data.gamePlayerMat);
            },

            data: sendData
        });
        obj.innerText = "";
        obj.style.width = 1;
        obj.style.height = 1;
    }
}

function useCard(obj, playerId, cardId) {

        var sendData = "{\"gameId\": " + gameId + ", \"gameHash\": \"" + gameHash + "\", \"playerId\": " + playerId + ", \"cardId\":" + cardId + "}";
        console.log(sendData);
        $.ajax({
            type: "POST",
            url: "http://localhost:19001/useCard",
            dataType: "json",
            headers: {"Content-type": "application/json"},
            crossDomain: true,
            success: function (msg) {
                console.log(msg.data);
                //updatePlayerMat(msg.data.gamePlayerMat);
            },

            data: sendData
        });
        obj.innerText = "";
        obj.style.width = 1;
        obj.style.height = 1;
        getPlayerMat(playerId);
}

function showPlayerCorps() {
    for (i in corporations) {
        //console.log(corporations[i]);
    }
}

function showCardsN(playerId, cardsN){
    $("#cardsN").html("");
    for (i in cardsN) {
        var litext = "<div onclick='pickUpCard(this, " + playerId + ", " + cardsN[i].id + ")'>"+cardsN[i].textHtml+"</div>";
        $("#cardsN").html($("#cardsN").html() + litext);
    }
}

function showPlayer(player) {
    $("#players").html($("#players").html() + "<p>" + "<span class='player' onclick='showPlayerCorps()'>" + player.name + "</span>");
    for (i in player.corporations) {
        var litext = player.corporations[i].textHtml.replace("</li>","</div>");
        litext = litext.replace("<li", "<div onclick='pickUpCorp(" + player.id + "," + player.corporations[i].id + ")'");
        litext = litext.replace("filterDiv filterDiv-stacked", "xxx");
        $("#corporations").html($("#corporations").html() + litext);

    }
    showCardsN(player.id, player.cardsN);
    playerId = player.id;
}

function showPlayers() {
    console.log(players);
    for (i in players) {
        showPlayer(players[i]);
    }
}

function showCorporations(playerId) {
    var corporations = [];
    //corporations.push({id: 0, title: ""});
    var sendData = "{\"gameId\": " + gameId + ", \"gameHash\": \"" + gameHash + "\", \"playerId\": " + playerId + "}";
    $.ajax({
        type: "POST",
        url: "http://localhost:19001/showCorporations",
        dataType: "json",
        headers: {"Content-type": "application/json"},
        crossDomain: true,
        success: function (msg) {
            for (i in msg.data.gameCorporations) {
                var corporation = msg.data.gameCorporations[i].corporation;
                corporation.id = msg.data.gameCorporations[i].id;
                /*
                {id: 0, title: ""};
                corporation.id = msg.data.gameCorporations[i].id;
                corporation.title = msg.data.gameCorporations[i].corporation.title;
                */
                corporations.push(corporation);
            }
        },

        data: sendData
    });
    return corporations;
}

function getPlayerMat(playerId) {
    var sendData = "{\"gameId\": " + gameId + ", \"gameHash\": \"" + gameHash + "\", \"playerId\": " + playerId + "}";
    $.ajax({
        type: "POST",
        url: "http://localhost:19001/getPlayerMat",
        dataType: "json",
        headers: {"Content-type": "application/json"},
        crossDomain: true,
        success: function (msg) {
            updatePlayerMat(msg.data.gamePlayerMat);
        },

        data: sendData
    });
    return corporations;
}

function updateHand(playerId) {
    //var cards = [];
    var sendData = "{\"gameId\": " + gameId + ", \"gameHash\": \"" + gameHash + "\", \"playerId\": " + playerId + "}";
    $.ajax({
        type: "POST",
        url: "http://localhost:19001/showPlayerUnusedCards",
        dataType: "json",
        headers: {"Content-type": "application/json"},
        crossDomain: true,
        success: function (msg) {
            console.log(msg.data.gamePlayerCards);
            $("#cards").html("");
            for (i in msg.data.gamePlayerCards) {
                //var card = msg.data.gamePlayerCards[i].card.card;
                //card.id = msg.data.gamePlayerCards[i].card.id;
                var litext = "<div onclick='useCard(this, " + playerId + ", " + msg.data.gamePlayerCards[i].card.id + ")'>"+msg.data.gamePlayerCards[i].card.card.textHtml+"</div>";
                $("#cards").html($("#cards").html() + litext);
            }
        },

        data: sendData
    });
    //return cards;
}

function showGenerationCards(playerId) {
    var cards = [];
    var sendData = "{\"gameId\": " + gameId + ", \"gameHash\": \"" + gameHash + "\", \"playerId\": " + playerId + "}";
    $.ajax({
        type: "POST",
        url: "http://localhost:19001/showGenerationCards",
        dataType: "json",
        headers: {"Content-type": "application/json"},
        crossDomain: true,
        success: function (msg) {
            //console.log(msg.data.gameCards);
            for (i in msg.data.gameCards) {
                var card = msg.data.gameCards[i].card;
                card.id = msg.data.gameCards[i].id;
                cards.push(card);
            }
        },

        data: sendData
    });
    return cards;
}

function startNewGame() {
    var sendData = "{\"decks\": [1, 2], \"players\": [\"Agil\"]}";
    $.ajax({
        type: "POST",
        url: "http://localhost:19001/startNewGame",
        dataType: "json",
        headers: {"Content-type": "application/json"},
        crossDomain: true,
        success: function (msg) {
            //console.log(msg.data);
            gameId = msg.data.game.id;
            gameHash = msg.data.game.hash;
            players = [];
            for (i in msg.data.players) {
                var player = {id: 0, name: "", corporations: [], cardsN: []};
                player.id = msg.data.players[i].id;
                player.name = msg.data.players[i].name;
                player.corporations = showCorporations(player.id);
                player.cardsN = showGenerationCards(player.id);
                players.push(player);
            }
            //console.log("players: ");
            //console.log(players);
        },

        data: sendData
    });

}

function newGeneration() {
    var sendData = "{\"gameId\": "+gameId+", \"gameHash\": \""+gameHash+"\"}";
    $.ajax({
        type: "POST",
        url: "http://localhost:19001/newGeneration",
        dataType: "json",
        headers: {"Content-type": "application/json"},
        crossDomain: true,
        success: function (msg) {
            cardsN = [];
            //console.log(msg);
            for (i in msg.data.gameCards) {
                var card = msg.data.gameCards[i].card;
                card.id = msg.data.gameCards[i].id;
                cardsN.push(card);
            }
        },

        data: sendData
    });

}


function toggleCardN(){
    $("#cardsN").toggle();
}

function toggleCard(){
    $("#cards").toggle();
}