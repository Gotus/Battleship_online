/**
 * Created by stalk on 29.05.2017.
 */

function getCookie(name) {
    var r = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");
    if (r) return r[2];
    else return "";
}

var conditionwait = "ожидается противник";
var conditionbattle = "идет бой";

$(document).ready(function () {

    $.ajax({
        url: "http://localhost:8080/gamegate/login",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var gamemas = [];
            gamemas = data.dataArray;
            for (var i = 0; i < gamemas.length; i++) {
                $("#myTable").append(
                    "<tr>" +
                    "<td>" + gamemas[i].battleID + "</td>" +
                    "<td>" + gamemas[i].hostLogin + "</td>" +
                    "<td>" + gamemas[i].opponentConnected ? conditionwait : conditionbattle + "</td>" +
                        "<td>" +
                        "<a href=\"http://localhost:8080/game/lobby/battle/" + gamemas[i].battleID + "/" +
                        getCookie(login) + "\"" + " class=\"btn btn-success\">присоединиться</a>" +
                        "<a href=\"#\" class =\"btn btn-primary\">смотреть</a>" +
                        "</td>" +
                        "</tr>");
            }
        }
    });

});