/**
 * Created by stalk on 15.06.2017.
 */

//Get cookie by name
function getCookie(name) {
    var r = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");
    if (r) return r[2];
    else return "";
}

var mylogin = getCookie("login");

var killstreak = $("#killstreakachievements");
var missstreak = $("#missstreakachievements");
var unique = $("#uniqueachievements");
var saveddata = [];
$(document).ready(function () {
    $.ajax({
        url: "/profile/getmyachievements",
        type: "POST",
        data: JSON.stringify({
            login: mylogin
        }),
        contentType: 'application/json; charset=utf-8',
        dataType: "json",
        success: function (data) {
            saveddata = data;
            var column, achievement, img;
            for (var i = 0; i < 1; i++) {
                column = $('<div>').attr("class", "col-xs-4 col-sm-3 col-md-2 col-lg-2 no-padding");
                achievement = $('<div>').attr("class", "achievement");
                img = $('<img>').attr("src", "" + saveddata[i].path);
                if (saveddata[i].gotAchievement){
                    img.attr("class", "img img-responsive img-thumbnail got");
                } else {
                    img.attr("class", "img img-responsive img-thumbnail");
                }

                achievement.append(img);
                column.append(achievement);
                unique.append(column);
            }

            for (var i = 2; i <= 10; i++) {

                column = $('<div>').attr("class", "col-xs-4 col-sm-3 col-md-2 col-lg-2 no-padding");
                achievement = $('<div>').attr("class", "achievement");
                img = $('<img>').attr("src", "" + saveddata[i].path);
                if (saveddata[i].gotAchievement){
                    img.attr("class", "img img-responsive img-thumbnail got");
                } else {
                    img.attr("class", "img img-responsive img-thumbnail");
                }

                achievement.append(img);
                column.append(achievement);
                killstreak.append(column);
            }

            for (var i = 11; i <= 16; i++) {

                column = $('<div>').attr("class", "col-xs-4 col-sm-3 col-md-2 col-lg-2 no-padding");
                achievement = $('<div>').attr("class", "achievement");
                img = $('<img>').attr("src", "" + saveddata[i].path);
                if (saveddata[i].gotAchievement){
                    img.attr("class", "img img-responsive img-thumbnail got");
                } else {
                    img.attr("class", "img img-responsive img-thumbnail");
                }

                achievement.append(img);
                column.append(achievement);
                missstreak.append(column);
            }
        }
    });
});