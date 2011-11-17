
$(function() {
  var map = new google.maps.Map(document.getElementById("map_canvas"), {
    zoom: 10,
    center: new google.maps.LatLng(34.68, 135.18),
    mapTypeId: google.maps.MapTypeId.ROADMAP
  });

  var cache = {};

  google.maps.event.addListener(map, "bounds_changed", function() {
    var bounds = map.getBounds();
    var north_east = bounds.getNorthEast();
    var south_west = bounds.getSouthWest();
    var params = {
      north: north_east.lat(),
      south: south_west.lat(),
      west: south_west.lng(),
      east: north_east.lng(),
      level: 13
    };

    $.get("/api/get_all_hexes", params, function(data) {
      var hexes = data.response.hexes;

      for ( var i = 0, len = hexes.length; i < len; i++ )
      {
        var code = hexes[i].code;

        if ( !cache[code] )
        {
          var coordinates = hexes[i].coordinates;
          var points = [
            new google.maps.LatLng(coordinates[0].lat, coordinates[0].lng),
            new google.maps.LatLng(coordinates[1].lat, coordinates[1].lng),
            new google.maps.LatLng(coordinates[2].lat, coordinates[2].lng),
            new google.maps.LatLng(coordinates[3].lat, coordinates[3].lng),
            new google.maps.LatLng(coordinates[4].lat, coordinates[4].lng),
            new google.maps.LatLng(coordinates[5].lat, coordinates[5].lng),
            new google.maps.LatLng(coordinates[0].lat, coordinates[0].lng),
          ];
          var path = new google.maps.Polyline({
            map: map, 
            path: points,
            strokeColor: "#FF0000",
            strokeOpacity: 0.5,
            strokeWeight: 1
          });
          cache[code] = path;
        }
      }
    });
  });
});
