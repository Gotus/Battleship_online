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

            for (var i = 0; i < data.length; i++) {
                var battleID = $('<td>').append(data[i].battleID);
                var hostLogin = $('<td>').append(data[i].hostLogin);
                var opponentConnected = $('<td>').append(data[i].opponentConnected ? conditionbattle : conditionwait);
                var joinbutton = $('<td>')

                .append($('<a>')
                    .attr("href", "#")
                    .attr("class", "btn btn-success")
                    .attr("id", data[i].battleID)
                    .append("Присоединиться")
                )

                .append($('<a>')
                    .attr("href", "#")
                    .attr("class", "btn btn-primary")
                    .append("Смотреть")
                );

                table.append($('<tr>')
                    .append(battleID)
                    .append(hostLogin)
                    .append(opponentConnected)
                    .append(joinbutton)
                );


                $(document).ready(function () {

                    $('#' + data[i].battleID).click(function () {
                        $.ajax({
                            url: "http://localhost:8080/game/lobby/battle/" + data[i].battleID + "/" + mylogin,
                            type: "POST",
                            data: JSON.stringify({
                                login: login
                            }),
                            contentType: 'application/json; charset=utf-8',
                            dataType: "json",
                            success: function (data2) {
                                if (data2.isSuccess) {
                                    location.href = "http://localhost:8080/game.html";
                                } else {
                                    alert("something went wrong");
                                }
                            }
                        });
                        return false;
                    });
                });
            }
            return false;
        }
    });
});