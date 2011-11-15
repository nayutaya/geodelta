
$(function() {
  var map = new google.maps.Map(document.getElementById("map_canvas"), {
    zoom: 8,
    center: new google.maps.LatLng(35.0, 135.0),
    mapTypeId: google.maps.MapTypeId.ROADMAP
  });

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
      console.debug(data);
    });
  });
});
