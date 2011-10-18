
$(function() {
  var latlng = new google.maps.LatLng(35.0, 135.0);
  var myOptions = {
    zoom: 8,
    center: latlng,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

  google.maps.event.addListener(map, "click", function(e) {
    var params = {
      lat: e.latLng.lat(),
      lng: e.latLng.lng(),
      level: 10
    }
    $.get("/api/encode", params, function(data) {
/*
      var center = new google.maps.LatLng(data.response.coordinates[0].lat, data.response.coordinates[0].lng);
      var marker = new google.maps.Marker({
        map: map, 
        position: center,
        title: "code=" + data.response.code
      });
*/
      var coordinates = data.response.coordinates;
      var points = [
        new google.maps.LatLng(coordinates[1].lat, coordinates[1].lng),
        new google.maps.LatLng(coordinates[2].lat, coordinates[2].lng),
        new google.maps.LatLng(coordinates[3].lat, coordinates[3].lng),
        new google.maps.LatLng(coordinates[1].lat, coordinates[1].lng)
      ];
      var path = new google.maps.Polyline({
        map: map, 
        path: points,
        strokeColor: "#FF0000",
        strokeOpacity: 0.5,
        strokeWeight: 2
      });
    });
  });
});
