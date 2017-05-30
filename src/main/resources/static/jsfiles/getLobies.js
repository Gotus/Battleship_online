/**
 * Created by stalk on 29.05.2017.
 */

//Get cookie by name
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
        url: "http://localhost:8080/game/lobby",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var gamemas = [];
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                console.log(data[i].hostLogin);
                $("#games").append(
                    "<tr>" +
                        "<td>" + data[i].battleID + "</td>" +
                        "<td>" + data[i].hostLogin + "</td>" +
                        "<td>" + data[i].opponentConnected ? conditionbattle : conditionwait + "</td>" +
                        "<td>" + data[i].opponentConnected ? "" :
                            "<a href=\"http://localhost:8080/game/lobby/battle/" + data[i].battleID + "/" +
                            mylogin + "\"" + " class=\"btn btn-success\">присоединиться</a>" +
                            "<a href=\"#\" class =\"btn btn-primary\">смотреть</a>" +
                        "</td>" +
                    "</tr>");
            }
            return false;
        }
    });
});