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
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/gamegate/login",
                data: {
                    login: login,
                    password: password
                },
                success: function (data) {
                    try {
                        object = $.parseJSON(data);
                        for (var key in object) {
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
                            location.href = "http://localhost:8080/profile.html";
                        }
                    } catch (e) {
                        // here you didn't get JSON back so we can assume it was an error, run your error code in here.  data will still be the error number (3)
                        return false;
                    }
                    //run your code on the json object here.
                }
            });
        }
    });
});
