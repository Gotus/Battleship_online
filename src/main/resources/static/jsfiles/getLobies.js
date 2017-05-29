/**
 * Created by stalk on 29.05.2017.
 */
var condition1 = "ожидается противник";
var condition2 = "идет бой";

$(document).ready(function () {

    $.ajax({
        url: "http://localhost:8080/gamegate/login",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var gamemas = [];
            gamemas = data.gamelist;
            for (var i = 0; i < gamemas.length; i++) {
                $("#myTable").append(
                    "<tr>" +
                    "<td>gamemas[i].battleID</td>" +
                    "<td>gamemas[i].login</td>" +
                    "<td>gamemas[i].condition ? condition1 : condition2</td>" +
                    "<td>" +
                    "<a href=\"http://localhost:8080/game.html\" class=\"btn btn-success\">присоединиться</a>" +
                    "<a href=\"http://localhost:8080/game.html\" class=\"btn btn-primary\">смотреть</a>" +
                    "</td>" +
                    "</tr>");
            }
        }
    });

});