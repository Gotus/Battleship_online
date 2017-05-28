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
                           try {
                               alert(data);
                               alert(data.isSuccess);
                               if(data.isSuccess) {
                                   location.href = "http://localhost:8080/profile.html";
                               } else {
                                   alert("Неверный логин/пароль");
                               }
                           } catch (e) {
                               return false;
                           }
                       }
                   })
               }
    })

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

                        alert(data);
                        alert(data.isSuccess);
                        if(data.isSuccess) {
                            location.href = "http://localhost:8080/profile.html";
                        } else {
                            alert("Введённый логин/email уже существует");
                        }
                    }
                })
            }
    })
});
