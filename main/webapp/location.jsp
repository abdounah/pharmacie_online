<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    double altitude = (Double) session.getAttribute("altitude");
    double longitude = (Double) session.getAttribute("longitude");
    String nom = (String) session.getAttribute("nom");
    double altitudeUser = (Double) session.getAttribute("altitudeUser");
    double longitudeUser = (Double) session.getAttribute("longitudeUser");


%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Google Maps API Example</title>

        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCHNAJD71wOwLiPYiWrMi0amZxKD4GAGgk"></script>

        <link rel="stylesheet" href="css/location.css">
        <!--<script src="js/script.js"></script>-->
    </head>
    <body>
        <iframe id="map-frame"
                width="100%"
                height="450"
                frameborder="0" style="border:0"
                src="" allowfullscreen>
        </iframe>

        <div class="btn" style="width: 100%;
             height: calc(100vh - 446px);
             ">
            <button id="drive-btn" style="padding: 10px 10px;
                    border-radius: 7px;
                    margin-right: 32px;
                    margin-bottom: 4px;
                    cursor: pointer;
                    border: none;
                    background: linear-gradient(135deg,#71b7e6,#9b59b6);
                    width: 100px;">Drive</button>
            <button id="walk-btn" style="padding: 10px 10px;
                    border-radius: 7px;
                    margin-right: 32px;
                    margin-bottom: 4px;
                    cursor: pointer;
                    border: none;
                    background: linear-gradient(135deg,#71b7e6,#9b59b6);
                    width: 100px;">Walk</button>
            <button id="bike-btn" style="padding: 10px 10px;
                    border-radius: 7px;
                    margin-right: 32px;
                    margin-bottom: 4px;
                    cursor: pointer;
                    border: none;
                    background: linear-gradient(135deg,#71b7e6,#9b59b6);
                    width: 100px;">Bicycling</button>
            <!--<button id="airplane-btn" style="padding: 10px 10px;
                    border-radius: 7px;
                    margin-right: 32px;
                    margin-bottom: 4px;
                    cursor: pointer;
                    border: none;
                    background: linear-gradient(135deg,#71b7e6,#9b59b6);
                    width: 100px;">Airplane</button>
            -->
            
        </div>

        <script>
            function initMap() {
                // create a map object and get the user's current location
                var map = new google.maps.Map(document.getElementById("map-frame"), {
                    zoom: 14
                });

                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function (position) {
                        // set the current location as the origin for the directions
                        var origin = position.coords.latitude + "," + position.coords.longitude;
                        // set the destination coordinates
                        var destination = "<%=altitude%>," + "<%=longitude%>";
                        // construct the URL with the updated origin and destination
                        //var driving = "https://www.google.com/maps/embed/v1/directions?origin=" + origin + "&destination=" + destination + "&key=AIzaSyCHNAJD71wOwLiPYiWrMi0amZxKD4GAGgk";

                        //var walking = "https://www.google.com/maps/embed/v1/directions?origin=" + origin + "&destination=" + destination + "&mode=walking&key=AIzaSyCHNAJD71wOwLiPYiWrMi0amZxKD4GAGgk";


                        currentLocationMarker = new google.maps.Marker({
                            position: origin,
                            map: "map-frame",
                            title: "Current Location",
                            icon: "https://maps.google.com/mapfiles/ms/icons/green-dot.png"
                        });

                        // set the iFrame's source URL
                        document.getElementById("map-frame").src = getDirectionsURL(origin, destination, 'walking');

                        document.getElementById("drive-btn").addEventListener("click", function () {
                            document.getElementById("map-frame").src = getDirectionsURL(origin, destination, 'driving');
                        });

                        document.getElementById("walk-btn").addEventListener("click", function () {
                            document.getElementById("map-frame").src = getDirectionsURL(origin, destination, 'walking');
                        });

                        document.getElementById("bike-btn").addEventListener("click", function () {
                            document.getElementById("map-frame").src = getDirectionsURL(origin, destination, 'bicycling');
                        });


                        


                        document.getElementById("transit-btn").addEventListener("click", function () {
                            document.getElementById("map-frame").src = getDirectionsURL(origin, destination, 'transit');
                        });

                    });


                }

            }

            function getDirectionsURL(origin, destination, mode) {

                return "https://www.google.com/maps/embed/v1/directions?origin=" + origin + "&destination=" + destination + "&mode=" + mode + "&key=AIzaSyCHNAJD71wOwLiPYiWrMi0amZxKD4GAGgk";

            }

            function getDirectionsURL2(origin, destination, mode) {
                var baseURL = "https://www.google.com/maps/embed/v1/directions?";
                var params = "origin=" + origin + "&destination=" + destination + "&mode=" + mode;

                if (mode === 'airplane') {
                    params += "&dirflg=f"; // add parameter for airplane mode
                }

                var key = "&key=AIzaSyCHNAJD71wOwLiPYiWrMi0amZxKD4GAGgk";

                return baseURL + params + key;
            }

            window.onload = function () {
                initMap();
            };
        </script>


    </body>
</html>
