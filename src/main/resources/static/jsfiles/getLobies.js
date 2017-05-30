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
    var table = $("#games").find('tbody');
    $.ajax({
        url: "http://localhost:8080/game/lobby",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var gamemas = [];
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                var battleID = $('<td>').append(data[i].battleID);
                var hostLogin = $('<td>').append(data[i].hostLogin);
                var opponentConnected = $('<td>').append(data[i].opponentConnected ? conditionbattle : conditionwait);
                var joinbutton = $('<td>').append($('<a>')
                    .attr("href", "http://localhost:8080/game/lobby/battle/" + data[i].battleID + "/" + mylogin)
                    .attr("class", "btn btn-success"))
                    .attr("value", "присоединиться");
                var watchbutton = $('<td>').append($('<a>')
                    .attr("href", "#")
                    .attr("class", "btn btn-primary"))
                    .attr("value", "смотреть");
                table.append($('<tr>')
                    .append(battleID)
                    .append(hostLogin)
                    .append(opponentConnected)
                    .append(joinbutton)
                    .append(watchbutton));
            }
            return false;
        }
    });
});