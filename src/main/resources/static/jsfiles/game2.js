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

function allowFire() {
    var ebutton;
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

function fire(fireevent) {
    for (var exbutton = 0; exbutton < 10; exbutton++) {
        for (var  eybutton = 0; eybutton < 10; eybutton++) {
            if(ebuttonsmas[exbutton][eybutton]===fireevent.target){

                banFire();

                $(document).ready(function () {
                    $.ajax({
                        url: "http://localhost:8080/game/fire",
                        type: "POST",
                        data: JSON.stringify({
                            login: mylogin, //to find game
                            xx: xbutton,
                            yy: ybutton
                        }),
                        contentType: 'application/json; charset=utf-8',
                        dataType: "json",
                        success: function (data) {
                            ebuffermas = data; //поле противника
                        }
                    });
                });
            }
        }
    }
}

var timer = setInterval(function () {
    if (GOSTIL) {
        var timer2 = setInterval(function () {
            $(document).ready(function () {
                $.ajax({
                    url: "http://localhost:8080/game/readytofight",
                    type: "GET",
                    contentType: 'application/json; charset=utf-8',
                    dataType: "text",
                    success: function (data) {
                        if (data === "success") {
                            allowFire();

                            $(document).ready(function () {
                                $.ajax({
                                    url: "http://localhost:8080/game/getmyfield",
                                    type: "POST",
                                    data: JSON.stringify({
                                        login: mylogin //to find game
                                    }),
                                    contentType: 'application/json; charset=utf-8',
                                    dataType: "json",
                                    success: function (data) {
                                        buffermas = data;//мое поле- по нему попали
                                        showField();
                                    }
                                });
                            });
                        }
                        if (data === "gameover"){
                            banFire();
                            showField(ebuffermas,ebuttonsmas);
                            alert("игра окончена");
                        }
                    }
                });
            });
        }, 1000)
    }
    console.log("0.2 is passed")
}, 200);
