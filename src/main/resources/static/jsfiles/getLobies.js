/**
 * Created by stalk on 29.05.2017.
 */

//Get cookie by name
function getCookie(name) {
    var r = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");
    if (r) return r[2];
    else return "";
}

var conditionwait = "ожидается противник"; //opponentConnected = 0
var conditionbattle = "идет бой"; //opponentConnected = 1

$(document).ready(function () {

    $.ajax({
        url: "http://localhost:8080/game/lobby",
        type: "GET",
        dataType: "json",
        success: function (data) {
            alert(data);
            alert(data.dataArray.length);
            var gamemas = new Array(data.dataArray.length);
            for( var i = 0; i < data.dataArray.length; i++) {

                gamemas[i] = JSON.parse(data.dataArray[i]);
                alert();
            }
            alert(gamemas[0]);
            for (var i = 0; i < gamemas.length; i++) {
                $("#games").append(
                    "<tr>" +
                    "<td>" + gamemas[i].battleID + "</td>" +
                    "<td>" + gamemas[i].hostLogin + "</td>" +
                    "<td>" + gamemas[i].opponentConnected ? conditionbattle : conditionwait + "</td>" +
                        "<td>" + gamemas[i].opponentConnected ? "" :
                        "<a href=\"http://localhost:8080/game/lobby/battle/" + gamemas[i].battleID + "/" +
                        getCookie(login) + "\"" + " class=\"btn btn-success\">присоединиться</a>" +
                        "<a href=\"#\" class =\"btn btn-primary\">смотреть</a>" +
                        "</td>" +
                        "</tr>");
            }
            return false;
        }
    });

});