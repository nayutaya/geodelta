
$(function() {
  var map = new google.maps.Map(document.getElementById("map_canvas"), {
    zoom: 8,
    center: new google.maps.LatLng(35.0, 135.0),
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
      level: 4
    };

    $.get("/api/get_all_deltas", params, function(data) {
      var deltas = data.response.deltas;

      for ( var i = 0, len = deltas.length; i < len; i++ )
      {
        var code = deltas[i].code;

        if ( !cache[code] )
        {
          console.debug("code:" + code + " cache miss");
          var coordinates = deltas[i].coordinates;
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
          cache[code] = path;
        }
      }
    });
  });
});
