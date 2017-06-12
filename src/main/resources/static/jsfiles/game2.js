/**
 * Created by stalk on 30.05.2017.
 */

var eA, eB, eC, eD, eE, eF, eG, eH, eI, eJ; // columns
/*rows in column*/
eA = document.getElementsByName("eyA");
eB = document.getElementsByName("eyB");
eC = document.getElementsByName("eyC");
eD = document.getElementsByName("eyD");
eE = document.getElementsByName("eyE");
eF = document.getElementsByName("eyF");
eG = document.getElementsByName("eyG");
eH = document.getElementsByName("eyH");
eI = document.getElementsByName("eyI");
eJ = document.getElementsByName("eyJ");

var ebuttonsmas = [eA, eB, eC, eD, eE, eF, eG, eH, eI, eJ];

var ebuffermas = [
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //A
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //B
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //C
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //D
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //E
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //F
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //G
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //H
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //I
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]  //J
];
var ebutton;
function allowFire() {
    /*checks what number in battlefield and sets attributes*/
    for (var x = 0; x < 10; x++) {
        for (var y = 0; y < 10; y++) {
            if (ebuffermas[x][y] === 1 || ebuffermas[x][y] === 3) {
                ebutton = ebuttonsmas[x][y];
                ebutton.removeAttribute("onclick");
                if (ebuffermas[x][y] === 1) {
                    ebutton.setAttribute("class", "btn btn-primary disabled");
                }
                if (ebuffermas[x][y] === 3) {
                    ebutton.setAttribute("class", "btn btn-danger disabled");
                }
            }
            if (ebuffermas[x][y] === 0 || ebuffermas[x][y] === 2) {
                ebutton = ebuttonsmas[x][y];
                ebutton.setAttribute("onclick", "fire(event)");
            }
        }
    }
}

function banFire() {
    /*checks what number in battlefield and sets attributes*/
    for (var x = 0; x < 10; x++) {
        for (var y = 0; y < 10; y++) {
            ebuttonsmas[x][y].removeAttribute("onclick");
        }
    }
}

var savedevent, savedeventtarget;
function fire(fireevent) {
    savedevent = fireevent;
    savedeventtarget = fireevent.target;
    banFire();
    for (var exbutton = 0; exbutton < 10; exbutton++) {
        for (var  eybutton = 0; eybutton < 10; eybutton++) {
            if(ebuttonsmas[exbutton][eybutton]===savedeventtarget) {
                $(document).ready(function () {
                    $.ajax({
                        url: "http://localhost:8080/game/fire",
                        type: "POST",
                        data: JSON.stringify({
                            login: mylogin, //to find game
                            xx: exbutton,
                            yy: eybutton
                        }),
                        contentType: 'application/json; charset=utf-8',
                        dataType: "json",
                        success: function (data) {
                            ebuffermas = data; //поле противника
                            console.log(ebuffermas);
                            allowFire();
                            banFire();
                        }
                    });
                });

                return;
            }
        }
    }
}

function getMyField(){
    $(document).ready(function () {
        $.ajax({
            url: "http://localhost:8080/game/getmyfield",
            type: "POST",
            data: JSON.stringify({
                login: mylogin //to find game
            }),
            contentType: 'application/json; charset=utf-8',
            dataType: "json",
            success: function (data2) {
                buffermas = data2;
                showField(buffermas, buttonsmas);
            }
        });
    });
}

var replayResult;
var gotMyField = false;
var timer = setInterval(function () {
    if (GOSTIL) {
        clearInterval(timer);
        var timer2 = setInterval(function () {
            $(document).ready(function () {
                $.ajax({
                    url: "http://localhost:8080/game/isready",
                    type: "POST",
                    data: JSON.stringify({
                        login: mylogin //to find game
                    }),
                    contentType: 'application/json; charset=utf-8',
                    dataType: "text",
                    success: function (data) {
                        if (data === "") {
                            alert("игра закончена из-за бездействия игроков");
                            location.href = "http://localhost:8080/lobies.html";
                        }
                        if (data === "myturn") {
                        allowFire();
                            if (!gotMyField) {
                                getMyField();
                                allowFire();
                                gotMyField = true;
                            }
                            middleleftelem.innerHTML = "Ваш ход";
                            middlerightelem.innerHTML = "";
                        }

                        if (data === "failure") {
                            banFire();
                            getMyField();
                            gotMyField = false;
                            middleleftelem.innerHTML = "";
                            middlerightelem.innerHTML = "Ход противника";
                        }

                        if (data === "gameover1"){
                            banFire();
                            showField(ebuffermas,ebuttonsmas);
                            clearInterval(timer2);
                            replayResult = alert("Вы победили.");
                            location.href = "http://localhost:8080/lobies.html";
                        }
                        if (data === "gameover2"){
                            banFire();
                            showField(ebuffermas,ebuttonsmas);
                            clearInterval(timer2);
                            replayResult = alert("Вы проиграли");
                            location.href = "http://localhost:8080/lobies.html";
                        }
                    }
                });
            });
        }, 1000);
    }

    console.log("gostil proveryaet");

}, 1000);
