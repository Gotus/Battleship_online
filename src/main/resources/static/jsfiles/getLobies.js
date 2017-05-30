/**
 * Created by stalk on 29.05.2017.
 */

function getCookie(name) {
    var r = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");
    if (r) return r[2];
    else return "";
}

var mylogin = getCookie("login");
var conditionwait = "ожидается противник"; //opponentConnected = 0
var conditionbattle = "идет бой"; //opponentConnected = 1

$(document).ready(function () {

    $.ajax({
        url: "http://localhost:8080/gamegate/login",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var gamemas = [];
            gamemas = data.dataArray;
            for (var i = 0; i < gamemas.length; i++) {
                $("#games").append(
                    "<tr>" +
                        "<td>" + gamemas[i].battleID + "</td>" +
                        "<td>" + gamemas[i].hostLogin + "</td>" +
                        "<td>" + gamemas[i].opponentConnected ? conditionbattle : conditionwait + "</td>" +
                        "<td>" + gamemas[i].opponentConnected ? "" :
                            "<a href=\"http://localhost:8080/game/lobby/battle/" + gamemas[i].battleID + "/" +
                            mylogin + "\"" + " class=\"btn btn-success\">присоединиться</a>" +
                            "<a href=\"#\" class =\"btn btn-primary\">смотреть</a>" +
                        "</td>" +
                    "</tr>");
            }
        }
    });
});