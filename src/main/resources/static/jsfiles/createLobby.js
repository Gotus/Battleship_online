/**
 * Created by stalk on 29.05.2017.
 */
var login;

function getCookie(name) {
    var r = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");
    if (r) return r[2];
    else return "";
}

$(document).ready(function () {

    $('#createLobby').click(function () {
        login = getCookie("login");

            $.ajax({
                url: "/game/battle/login",
                type: "POST",
                data: JSON.stringify({
                    login: login
                }),
                contentType: 'application/json; charset=utf-8',
                dataType: "json",
                success: function (data) {
                    if (data.isSuccess) {
                        location.href = "/game.html";
                    } else {
                        alert("something went wrong");
                    }
                }
            });
        return false;
    });
});