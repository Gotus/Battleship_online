/**
 * Created by stalk on 26.05.2017.
 */
var login, password, logincheck;
var email, login2, password2, registrationcheck;

$(document).ready(function () {

    $('#loginButton').click(function () {
        login = $("#loginlogin").val();
        password = $("#loginpassword").val();
        if (login == "" || password == "") {
            alert("Please fill all fields...!!!!!!");
        } else {
            alert("pizdwes");
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
                    var object = data;
                    alert(object);
                    alert(object.isSuccess);
                    if (object.isSuccess) {
                        location.href = "http://localhost:8080/profile.html";
                    } else {
                        alert("Неверный логин/пароль");
                    }
                    /*for (var key in object) {
                     //this if just checks if the key has a value, it is required
                     if (object.hasOwnProperty(key)) {
                     var value = object[key];
                     if (value == null || key == null) {
                     alert("wrong login/password");
                     return;
                     }
                     //then we need to put the key and the value into session storage
                     sessionStorage.setItem(key, value);
                     }
                     alert("redirection");
                     location.href = "http://localhost:8080/profile.html";
                     }*/
                    return false;
                }
            })
        }
    });

    $('#registerButton').click(function () {
        login2 = $("#signuplogin").val();
        password2 = $("#signuppassword").val();
        email = $("#signupemail").val();
        if (login2 == "" || password2 == "" || email == "") {
            alert("Please fill all fields...!!!!!!");
        } else {
            alert("pizdwespizdews");
            $.ajax({
                url: "http://localhost:8080/gamegate/register",
                type: "POST",
                data: JSON.stringify({
                    login: login2,
                    password: password2,
                    email: email
                }),
                contentType: 'application/json; charset=utf-8',
                dataType: "json",
                success: function (data) {
                    var object2 = data;
                    alert(object2);
                    alert(object2.isSuccess);
                    if(object2.isSuccess) {
                        location.href = "http://localhost:8080/profile.html";
                    } else {
                        alert("Введённый логин/email уже существует");
                    }
                }
            })
        }
    })
});