/**
 * Created by stalk on 27.05.2017.
 */
var ship4, ship3, ship2, ship1;

ship4=1;
ship3=2;
ship2=3;
ship1=4;

document.getElementById("ship4").innerHTML = ship4 + "X";
document.getElementById("ship3").innerHTML = ship3 + "X";
document.getElementById("ship2").innerHTML = ship2 + "X";
document.getElementById("ship1").innerHTML = ship1 + "X";

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
   //need to call java place function
}
