var email, login, password, registrationcheck;

$(document).ready( function () {

    $('#registerButton').click(function () {
                login = $("#signuplogin").val();
                password = $("#signuppassword").val();
                email = $("#signupemail").val();
                if (login == "" || password == "" || email == "") {
                    alert("Please fill all fields...!!!!!!");
                } else {
                        $.ajax({
                        url: "/gamegate/register",
                        type: "POST",
                        data: JSON.stringify({
                            login: login,
                            password: password,
                            email: email
                        }),
                        contentType: 'application/json; charset=utf-8',
                        dataType: "json",
                        success: function (data) {
                            if(data.isSuccess) {
                                location.href = "/profile.html";
                            } else {
                                alert("Введённый логин/email уже существует");

                            }
                        }
                    })
                }
                return false;
        });
});