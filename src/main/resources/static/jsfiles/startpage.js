/**
 * Created by stalk on 26.05.2017.
 */
var login, password, logincheck;
var email, login2, password2, registrationcheck;

/*logincheck = function () {
 location.href = "http://localhost:8080/gamegate/login?login=" + login + "&password=" + password;
 };

 registrationcheck = function () {
 location.href = "http://localhost:8080/gamegate/registrate?mail=" + email + "&login=" + login2 + "&password=" + password2;
 };*/

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
                    //alert(data.password);
                        //data = data + "";
                        alert(data);
                        //object = data;
                        alert(data.isSuccess);
                        if(data.isSuccess) {
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

                    } catch (e) {
                        // here you didn't get JSON back so we can assume it was an error, run your error code in here.  data will still be the error number (3)
                        alert("exception");
                        return false;
                    }
                    //run your code on the json object here.
                }
            });
        }
    });
});
