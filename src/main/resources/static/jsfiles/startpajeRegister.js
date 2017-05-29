var email, login2, password2, registrationcheck;

$(document).ready( function () {

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
        });
});