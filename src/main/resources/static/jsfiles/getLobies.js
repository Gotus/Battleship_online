/**
 * Created by stalk on 29.05.2017.
 */

//Get cookie by name
function getCookie(name) {
    var r = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");
    if (r) return r[2];
    else return "";
}

localStorage.removeItem("battleID");

var mylogin = getCookie("login");
var battleIDMas = [];

var conditionwait = "ожидается противник"; //opponentConnected = 0
var conditionbattle = "идет бой"; //opponentConnected = 1
var watchbuttonelement, watchbuttonelements;
$(document).ready(function () {
    var table = $("#games").find('tbody');
    var savedButtonID, savedBattleID;
    $.ajax({
        url: "http://localhost:8080/game/lobby",
        type: "GET",
        dataType: "json",
        success: function (data) {

            for (var i = 0; i < data.length; i++) {
                var battleID = $('<td>').append(data[i].battleID);
                var hostLogin = $('<td>').append(data[i].hostLogin);
                var opponentConnected = $('<td>').append(data[i].opponentConnected ? conditionbattle : conditionwait);
                var joinbutton = $('<td>');

                if (data[i].opponentConnected) {
                    joinbutton.append($('<a>')
                        .attr("href", "#")
                        .attr("class", "btn btn-success")
                        .attr("id", "" + data[i].battleID)
                        .append("Присоединиться")
                    )
                }
                ;


                joinbutton.append($('<a>')
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

                savedButtonID = '#' + data[i].battleID;
                savedBattleID = data[i].battleID;

                $(document).ready(function () {

                    $(savedButtonID).click(function () {
                        $.ajax({
                            url: "http://localhost:8080/game/join/",
                            type: "POST",
                            data: JSON.stringify({
                                login: mylogin,
                                battleID: savedBattleID
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

                battleIDMas[i] = savedBattleID;
            }
            return false;
        }
    });
});

watchbuttonelements = document.getElementsByClassName('btn-primary');
for (var i = 0; i < watchbuttonelements.length; i++) {
    watchbuttonelement = watchbuttonelements[i];
    watchbuttonelement.setAttribute("onclick", "movetowitness(moveevent)");
}


var savedevent, savedeventtarget;
function movetowitness(moveevent) {
    savedevent = moveevent;
    savedeventtarget = moveevent.target;
    for (var ibutton = 0; ibutton < watchbuttonelements.length; ibutton++) {
        if (watchbuttonelements[ibutton] === savedeventtarget) {
            localStorage.setItem('battleID', battleIDMas[ibutton]);
            location.href = "http://localhost:8080/witness.html";

            return;
        }
    }
}