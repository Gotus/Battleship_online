/**
 * Created by stalk on 26.05.2017.
 */
var login, password, checkonserver;

checkonserver = function () {
    location.href = "localhost:8080/gamegate/login?login=" + login + "&password=" + password;
};