/**
 * Created by stalk on 12.06.2017.
 */

var A, B, C, D, E, F, G, H, I, J; // columns
/*rows in column*/
A = document.getElementsByName("yA");
B = document.getElementsByName("yB");
C = document.getElementsByName("yC");
D = document.getElementsByName("yD");
E = document.getElementsByName("yE");
F = document.getElementsByName("yF");
G = document.getElementsByName("yG");
H = document.getElementsByName("yH");
I = document.getElementsByName("yI");
J = document.getElementsByName("yJ");

var buttonsmas = [A, B, C, D, E, F, G, H, I, J];
var buffermas = [
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

var battleID = localStorage.getItem('battleID');

var saveddata;
var timer = setInterval(function () {
    $(document).ready(function () {
        $.ajax({
            url: "/game/witness",
            type: "POST",
            data: JSON.stringify({
                battleID: battleID //to find game
            }),
            contentType: 'application/json; charset=utf-8',
            dataType: "json",
            success: function (data) {
                saveddata = data;
                console.log(data);
                console.log(saveddata);
                if (saveddata.isSuccess) {
                    buffermas = saveddata.hostBattleField;
                    ebuffermas = saveddata.opponentBattleField;
                    showField(buffermas, buttonsmas);
                    showField(ebuffermas, ebuttonsmas);
                } else {

                    alert("Игра окончена");
                    location.href = "/lobies.html";
                }
            }
        });
    });
    return;
}, 1000);

function showField(dataforbuttons, buttons) {
    var button;
    /*checks what number in battlefield and sets attributes*/
    for (var x = 0; x < 10; x++) {
        for (var y = 0; y < 10; y++) {
            if (dataforbuttons[x][y] === 1) {
                button = buttons[x][y];
                button.setAttribute("class", "btn btn-primary disabled");
            }
            if (dataforbuttons[x][y] === 0 || dataforbuttons[x][y] === 2) {
                button = buttons[x][y];
                button.setAttribute("class", "btn btn-primary");
            }
            if (dataforbuttons[x][y] === 3) {
                button = buttons[x][y];
                button.setAttribute("class", "btn btn-danger disabled");
            }
        }
    }
}