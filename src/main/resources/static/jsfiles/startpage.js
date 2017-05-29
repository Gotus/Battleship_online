var email, login, password, registrationcheck;

$(document).ready( function () {

    $('#registerButton').click(function () {
                login = $("#signuplogin").val();
                password = $("#signuppassword").val();
                email = $("#signupemail").val();
                if (login == "" || password == "" || email == "") {
                    alert("Please fill all fields...!!!!!!");
                } else {
                        alert("pizdwes");
                        $.ajax({
                        url: "http://localhost:8080/gamegate/register",
                        type: "POST",
                        data: JSON.stringify({
                            login: login,
                            password: password,
                            email: email
                        }),
                        contentType: 'application/json; charset=utf-8',
                        dataType: "json",
                        success: function (data) {
                            try {
                                 alert("pizdwespizdews" + data.isSuccess);

                                if(data.isSuccess) {
                                    location.href = "http://localhost:8080/profile.html";
                                } else {
                                    alert("Введённый логин/email уже существует");

                                }
                            } catch (e){

                                alert("exception");
                                }
                        }
                    })
                }
                return false;
        });
});
