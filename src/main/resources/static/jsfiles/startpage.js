/**
 * Created by stalk on 26.05.2017.
 */
var login, password, logincheck;
var email, login2, password2, registrationcheck;

logincheck = function () {
    location.href = "/gamegate/login?login=" + login + "&password=" + password;
};

registrationcheck = function () {
    location.href = "/gamegate/registrate?mail=" + email + "&login=" + login2 + "&password=" + password2;
}