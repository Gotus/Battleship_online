/**
 * Created by stalk on 26.05.2017.
 */
var login, password, logincheck;
var email, login2, password2, registrationcheck;

logincheck = function () {
    location.href = "localhost:8080/gamegate/login?login=" + login + "&password=" + password;
};

registrationcheck = function () {
    location.href = "localhost:8080/gamegate/registrate?mail=" + email + "&login=" + login2 + "&password=" + password2;
}