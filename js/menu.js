/* Set the width of the side navigation to 250px */
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}

/* Set the width of the side navigation to 0 */
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.body.style.backgroundColor = "white";
}

/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function firstLevel() {
    $("#drop1").show()
    //document.getElementById("drop1").classList.toggle("show");

}

function secondLevel() {
    $("#drop2").show()
 // document.getElementById("drop2").classList.toggle("show");
}

function thirdLevel() {
    $("#drop3").show()
 // document.getElementById("drop3").classList.toggle("show");
}

