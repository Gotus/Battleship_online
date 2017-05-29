/**
 * Created by stalk on 26.05.2017.
 */
var login, password, logincheck;

$(document).ready(function () {

    $('#loginButton').click(function () {
        login = $("#loginlogin").val();
        password = $("#loginpassword").val();
        if (login == "" || password == "") {
            alert("Please fill all fields...!!!!!!");
        } else {
            $.ajax({
                url: "http://localhost:8080/gamegate/login",
                type: "POST",
                data: JSON.stringify({
                    login: login,
                    password: password
                }),
                contentType: 'application/json; charset=utf-8',
                dataType: "json",
                success: function (data) {
                    if (data.isSuccess) {
                        location.href = "http://localhost:8080/profile.html";
                    } else {
                        alert("Неверный логин/пароль");
                    }
                }
            })
        }
        return false;
    });
});