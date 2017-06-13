/**
 * Created by stalk on 07.06.2017.
 */
function getCookie(name) {
    var r = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");
    if (r) return r[2];
    else return "";
}

if (getCookie("login")!=="") {
    location.href = "/profile.html";
}