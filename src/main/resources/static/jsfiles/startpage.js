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

$(document).ready(function() {

    $('#loginButton').click(function() {
        if (login === "undefined") {
            alert("login should not be empty");
            return;
        }
        if (password === "undefined") {
            alert("login should not be empty");
            return;
        }

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/gamegate/login?login=" + login + "&password=" + password,
            data: {
                username: login,
                password: password
            },
            success: function(data)
            {
                if (data !== null) {
                    window.location.replace('admin/admin.php'); //lobies url
                }
                else {
                    alert("incorrect login/password");
                }
            }
        });
    });

});