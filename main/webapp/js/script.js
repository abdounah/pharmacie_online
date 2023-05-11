/* global google */

// set map options
var mylatlng = {lat: 38.3460, lng: -0.4907};
var mapOptions = {
    center: mylatlng,
    zoom: 7,
    mapTypeId: google.maps.MapTypeId.ROADMAP
};

//create Map

var map = new google.maps.Map(document.getElementById("googleMap"), mapOptions);
window.onload = function () {
    map;
};