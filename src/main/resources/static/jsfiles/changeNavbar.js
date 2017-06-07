/**
 * Created by stalk on 07.06.2017.
 */

function getCookie(name) {
    var r = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");
    if (r) return r[2];
    else return "";
}
var mylogin = getCookie("login");

var enter = $("<nav>").find('.navbar-right');

if (mylogin!==""){
    enter.innerHTML = '<li> <a href="#" onclick="deleteCookie("login")">Выйти</a></li>';
}

function deleteCookie(name) {
    var date = new Date(); // Берём текущую дату
    date.setTime(date.getTime() - 1); // Возвращаемся в "прошлое"
    document.cookie = name += "=; expires=" + date.toGMTString(); // Устанавливаем cookie пустое значение и срок действия до прошедшего уже времени
    location.href = "http://localhost:8080/startpage.html";
}



