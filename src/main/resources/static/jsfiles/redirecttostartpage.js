/**
 * Created by stalk on 29.05.2017.
 */

function getCookie(name) {
    var r = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");
    if (r) return r[2];
    else return "";
}

if (getCookie("login")==="") {
    location.href = "http://localhost:8080/startpage.html";
}