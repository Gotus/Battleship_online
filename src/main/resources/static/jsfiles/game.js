/**
 * Created by stalk on 27.05.2017.
 */
function getCookie(name) {
    var r = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");
    if (r) return r[2];
    else return "";
}
var mylogin = getCookie("login");
var readytofight = false;
var GOSTIL = false;

var shipselem;
var orient = true;//horizontal
var keyPressFunction;
document.addEventListener("keypress", keyPressFunction = function (keyPressEvent) {
    if (keyPressEvent.key === "q" || keyPressEvent.key === "e" || keyPressEvent.key === "Q" || keyPressEvent.key === "E") {
        orient ? orient = false : orient = true;
        console.log(orient);
    }
    shipselem = document.getElementById("ships");
    if (orient) {
        shipselem.innerHTML = '<img src="images/4ship.png" id="shipfour1" draggable="true" ondragstart="drag(event)"\
            width="111" height="26">\
                <img src="images/3ship.png" id="shipthree1" draggable="true" ondragstart="drag(event)"\
            width="83" height="26">\
                <img src="images/3ship.png" id="shipthree2" draggable="true" ondragstart="drag(event)"\
            width="83" height="26">\
                <img src="images/2ship.png" id="shiptwo1" draggable="true" ondragstart="drag(event)"\
            width="53" height="26">\
                <img src="images/2ship.png" id="shiptwo2" draggable="true" ondragstart="drag(event)"\
            width="53" height="26">\
                <img src="images/2ship.png" id="shiptwo3" draggable="true" ondragstart="drag(event)"\
            width="53" height="26">\
                <img src="images/1ship.png" id="shipone1" draggable="true" ondragstart="drag(event)"\
            width="26" height="26">\
                <img src="images/1ship.png" id="shipone2" draggable="true" ondragstart="drag(event)"\
            width="26" height="26">\
                <img src="images/1ship.png" id="shipone3" draggable="true" ondragstart="drag(event)"\
            width="26" height="26">\
                <img src="images/1ship.png" id="shipone4" draggable="true" ondragstart="drag(event)"\
            width="26" height="26">';
    } else {
        shipselem.innerHTML = '<img src="images/4ship-vertical.png" id="shipfour1" draggable="true" ondragstart="drag(event)"\
            width="26" height="111">\
                <img src="images/3ship-vertical.png" id="shipthree1" draggable="true" ondragstart="drag(event)"\
            width="26" height="83">\
                <img src="images/3ship-vertical.png" id="shipthree2" draggable="true" ondragstart="drag(event)"\
            width="26" height="83">\
                <img src="images/2ship-vertical.png" id="shiptwo1" draggable="true" ondragstart="drag(event)"\
            width="26" height="53">\
                <img src="images/2ship-vertical.png" id="shiptwo2" draggable="true" ondragstart="drag(event)"\
            width="26" height="53">\
                <img src="images/2ship-vertical.png" id="shiptwo3" draggable="true" ondragstart="drag(event)"\
            width="26" height="53">\
                <img src="images/1ship-vertical.png" id="shipone1" draggable="true" ondragstart="drag(event)"\
            width="26" height="26">\
                <img src="images/1ship-vertical.png" id="shipone2" draggable="true" ondragstart="drag(event)"\
            width="26" height="26">\
                <img src="images/1ship-vertical.png" id="shipone3" draggable="true" ondragstart="drag(event)"\
            width="26" height="26">\
                <img src="images/1ship-vertical.png" id="shipone4" draggable="true" ondragstart="drag(event)"\
            width="26" height="26">';
    }
});


var ship = function (id, elementid) {
    {
        this.numberinfleet = id;
        this.imageid = elementid;
    }
};

var shipfour1, shipthree1, shipthree2, shiptwo1, shiptwo2, shiptwo3, shipone1, shipone2, shipone3, shipone4;

shipfour1 = new ship(0, "shipfour1");
shipfour1['prown'] = {
    xx: -1,
    yy: -1
};
shipfour1['stern'] = {
    xx: -1,
    yy: -1
};

shipthree1 = new ship(1, "shipthree1");
shipthree1['prown'] = {
    xx: -1,
    yy: -1
};
shipthree1['stern'] = {
    xx: -1,
    yy: -1
};

shipthree2 = new ship(2, "shipthree2");
shipthree2['prown'] = {
    xx: -1,
    yy: -1
};
shipthree2['stern'] = {
    xx: -1,
    yy: -1
};

shiptwo1 = new ship(3, "shiptwo1");
shiptwo1['prown'] = {
    xx: -1,
    yy: -1
};
shiptwo1['stern'] = {
    xx: -1,
    yy: -1
};

shiptwo2 = new ship(4, "shiptwo2");
shiptwo2['prown'] = {
    xx: -1,
    yy: -1
};
shiptwo2['stern'] = {
    xx: -1,
    yy: -1
};

shiptwo3 = new ship(5, "shiptwo3");
shiptwo3['prown'] = {
    xx: -1,
    yy: -1
};
shiptwo3['stern'] = {
    xx: -1,
    yy: -1
};

shipone1 = new ship(6, "shipone1");
shipone1['prown'] = {
    xx: -1,
    yy: -1
};
shipone1['stern'] = {
    xx: -1,
    yy: -1
};

shipone2 = new ship(7, "shipone2");
shipone2['prown'] = {
    xx: -1,
    yy: -1
};
shipone2['stern'] = {
    xx: -1,
    yy: -1
};

shipone3 = new ship(8, "shipone3");
shipone3['prown'] = {
    xx: -1,
    yy: -1
};
shipone3['stern'] = {
    xx: -1,
    yy: -1
};

shipone4 = new ship(9, "shipone4");
shipone4['prown'] = {
    xx: -1,
    yy: -1
};
shipone4['stern'] = {
    xx: -1,
    yy: -1
};

var fleet = [shipfour1, shipthree1, shipthree2, shiptwo1, shiptwo2, shiptwo3, shipone1, shipone2, shipone3, shipone4];
var selectship = new ship(null, null);

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

var shiptoplace, tobreak;
function refresh() {
    readytofight = true;
    /*places ships*/
    for (var i = 0; i < 10; i++) {
        tobreak = false;
        shiptoplace = fleet[i];
        for (var xx = shiptoplace.prown.xx; xx <= shiptoplace.stern.xx; xx++) {
            for (var yy = shiptoplace.prown.yy; yy <= shiptoplace.stern.yy; yy++) {
                if (xx === -1 || yy === -1) {
                    readytofight = false;
                    tobreak = true;
                    break;
                }
                buffermas[xx][yy] = 2;
            }
            if (tobreak) {
                break
            }
        }
    }

    showField(buffermas,buttonsmas);

    if (readytofight) {
        shipselem.innerHTML = "";
        document.removeEventListener("keypress", keyPressFunction);
        GOSTIL = true;

        $(document).ready(function () {
            $.ajax({
                url: "http://localhost:8080/game/readytofight",
                type: "POST",
                data: JSON.stringify({
                    login: mylogin, //to find game
                    ready: readytofight
                }),
                contentType: 'application/json; charset=utf-8',
                dataType: "json",
                success: function (data) {
                    if (data.isSuccess) {
                        alert("вы расположили корабли.");
                    }
                }
            });
        });
    }

}
function showField(dataforbuttons, buttons) {
    var button;
    /*checks what number in battlefield and sets attributes*/
    for (var x = 0; x < 10; x++) {
        for (var y = 0; y < 10; y++) {
            if (dataforbuttons[x][y] === 1) {
                button = buttons[x][y];
                button.setAttribute("class", "btn btn-primary disabled");
            }
            if (dataforbuttons[x][y] === 0) {
                button = buttons[x][y];
                button.setAttribute("ondragover", "allowDrop(event)");
                button.setAttribute("ondrop", "drop(event)");
            }
            if (dataforbuttons[x][y] === 2) {
                button = buttons[x][y];
                button.removeAttribute("ondragover");
                button.removeAttribute("ondrop");
                button.setAttribute("class", "btn btn-danger");
            }
            if (dataforbuttons[x][y] === 3) {
                button = buttons[x][y];
                button.removeAttribute("ondragover");
                button.removeAttribute("ondrop");
                button.setAttribute("class", "btn btn-danger disabled");
            }
        }
    }
}

refresh();

function allowDrop(dragAndDropEvent) {
    dragAndDropEvent.preventDefault();
}

function drag(dragAndDropEvent) {
    dragAndDropEvent.dataTransfer.setData("text", dragAndDropEvent.target.id); //data contain numberinfleet of image
    dragAndDropEvent.target.style.opacity = "0.4";
    var data = dragAndDropEvent.dataTransfer.getData("text"); //shipfour1 for example
    for (var i = 0; i < 10; i++) {
        selectship = fleet[i];
        if (selectship.imageid === data) {
            break;
        } else {
            selectship = null;
        }
    }
}

function drop(dragAndDropEvent) {
    dragAndDropEvent.preventDefault();
    var selectShipImage = document.getElementById(selectship.imageid);
    selectShipImage.style.opacity = "1.0";
    var dropbutton = dragAndDropEvent.target;
    for (var xbutton = 0; xbutton < 10; xbutton++) {
        for (var ybutton = 0; ybutton < 10; ybutton++) {
            if (buttonsmas[xbutton][ybutton] === dropbutton) {//отправим x и y серверу

                $(document).ready(function () {
                    $.ajax({
                        url: "http://localhost:8080/game/location",
                        type: "POST",
                        data: JSON.stringify({
                            login: mylogin, //to find game
                            numberinfleet: selectship.numberinfleet,
                            xx: xbutton,
                            yy: ybutton,
                            orientation: orient
                        }),
                        contentType: 'application/json; charset=utf-8',
                        dataType: "json",
                        success: function (data) {
                            var serverFleet = [];
                            serverFleet = data;
                            for (var i=0; i< serverFleet.length; i++){
                                fleet[i].prown.xx=serverFleet[i].prown.xx;
                                fleet[i].prown.yy=serverFleet[i].prown.yy;
                                fleet[i].stern.xx=serverFleet[i].stern.xx;
                                fleet[i].stern.xx=serverFleet[i].stern.xx;
                            }
                        }
                    });
                });
                refresh();
                return;
            }
        }
    }
}



