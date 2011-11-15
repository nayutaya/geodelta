
$(function() {
  var map = new google.maps.Map(document.getElementById("map_canvas"), {
    zoom: 8,
    center: new google.maps.LatLng(35.0, 135.0),
    mapTypeId: google.maps.MapTypeId.ROADMAP
  });

  google.maps.event.addListener(map, "bounds_changed", function() {
    console.debug("bounds_changed");
  });
});
