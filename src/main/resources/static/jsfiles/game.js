/**
 * Created by stalk on 27.05.2017.
 */
var ship = function (id, elementid) {
    {
        this.id = id;
        this.elementid = elementid;
        this.image = document.getElementById(elementid);
    }
};
var shipfour1, shipthree1, shipthree2, shiptwo1, shiptwo2, shiptwo3, shipone1, shipone2, shipone3, shipone4;

shipfour1 = new ship(0, "shipfour1");
shipthree1 = new ship(1, "shipthree1");
shipthree2 = new ship(2, "shipthree2");
shiptwo1 = new ship(3, "shiptwo1");
shiptwo2 = new ship(4, "shiptwo2");
shiptwo3 = new ship(5, "shiptwo3");
shipone1 = new ship(6, "shipone1");
shipone2 = new ship(7, "shipone2");
shipone3 = new ship(8, "shipone3");
shipone4 = new ship(9, "shipone4");

var fleet = [shipfour1, shipthree1, shipthree2, shiptwo1, shiptwo2, shiptwo3, shipone1, shipone2, shipone3, shipone4];
var selectship = new ship(null, null);

var A = document.getElementsByName("yA");
var B = document.getElementsByName("yB");
var C = document.getElementsByName("yC");
var D = document.getElementsByName("yD");
var E = document.getElementsByName("yE");
var F = document.getElementsByName("yF");
var G = document.getElementsByName("yG");
var H = document.getElementsByName("yH");
var I = document.getElementsByName("yI");
var J = document.getElementsByName("yJ");

var buttonsmas = [A, B, C, D, E, F, G, H, I, J];
var buffermas = [
    [1,0,0,0,0,0,0,0,0,0], //A
    [0,0,0,0,0,0,0,0,0,0], //B
    [0,0,0,0,0,0,0,0,0,0], //C
    [0,0,0,0,0,0,0,0,0,0], //D
    [0,0,0,0,0,0,0,0,0,0], //E
    [0,0,0,0,0,0,0,0,0,0], //F
    [0,0,0,0,0,0,0,0,0,0], //G
    [0,0,0,0,0,0,0,0,0,0], //H
    [0,0,0,0,0,0,0,0,0,0], //I
    [0,0,0,0,0,0,0,0,0,0]  //J
];
var button;
for (var x = 0; x < 10; x++){
    for (var y = 0; y < 10; y++){
        if (buffermas[x][y] === 1) {
            button = buttonsmas[x][y];
            button.className += " disabled";
        }
    }
}

function allowDrop(dragAndDropEvent) {
    ev.preventDefault();
}

function drag(dragAndDropEvent) {
    dragAndDropEvent.dataTransfer.setData("text", dragAndDropEvent.target.id); //data contain id of image
    var data = dragAndDropEvent.dataTransfer.getData("text");
    console.log(data);
    for (var i = 0; i <10; i++) {
        selectship = fleet[i];
        if (selectship.elementid === data) {
            break;
        } else {
            selectship = null;
        }
    }
    console.log(selectship.id);
    //выбор корабля
}

function drop(dragAndDropEvent) {
    ev.preventDefault();
    //need to call java place function
}
