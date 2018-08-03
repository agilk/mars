

function standardProjectPower(playerId) {
    var sendData = "{\"gameId\": " + gameId + ", \"gameHash\": \"" + gameHash + "\", \"playerId\": " + playerId + "}";
    $.ajax({
        type: "POST",
        url: "http://localhost:19001/raisePowerProduction",
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

function standardProjectTemp(playerId) {
    var sendData = "{\"gameId\": " + gameId + ", \"gameHash\": \"" + gameHash + "\", \"playerId\": " + playerId + "}";
    $.ajax({
        type: "POST",
        url: "http://localhost:19001/raiseTemperatureByMoney",
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

function standardProjectOcean(playerId) {
    var sendData = "{\"gameId\": " + gameId + ", \"gameHash\": \"" + gameHash + "\", \"playerId\": " + playerId + "}";
    $.ajax({
        type: "POST",
        url: "http://localhost:19001/addOcean",
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

function standardProjectGreen(playerId) {
    var sendData = "{\"gameId\": " + gameId + ", \"gameHash\": \"" + gameHash + "\", \"playerId\": " + playerId + "}";
    $.ajax({
        type: "POST",
        url: "http://localhost:19001/addGreeneryForMoney",
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

function standardProjectCity(playerId) {
    var sendData = "{\"gameId\": " + gameId + ", \"gameHash\": \"" + gameHash + "\", \"playerId\": " + playerId + "}";
    $.ajax({
        type: "POST",
        url: "http://localhost:19001/addCity",
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


function addGreenery(playerId) {
    var sendData = "{\"gameId\": " + gameId + ", \"gameHash\": \"" + gameHash + "\", \"playerId\": " + playerId + "}";
    $.ajax({
        type: "POST",
        url: "http://localhost:19001/addGreenery",
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

function raiseTemperatureByHeat(playerId) {
    var sendData = "{\"gameId\": " + gameId + ", \"gameHash\": \"" + gameHash + "\", \"playerId\": " + playerId + "}";
    $.ajax({
        type: "POST",
        url: "http://localhost:19001/raiseTemperatureByHeat",
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
