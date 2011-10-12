
$(function() {
  var latlng = new google.maps.LatLng(35.0, 135.0);
  var myOptions = {
    zoom: 8,
    center: latlng,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

  google.maps.event.addListener(map, "click", function(e) {
    //alert("click lat:" + e.latLng.lat() + " lng:" + e.latLng.lng());
    var params = {
      lat: e.latLng.lat(),
      lng: e.latLng.lng(),
      level: 1
    }
    $.get("/api/encode", params, function(data) {
      alert("ret code:" + data.response.code);
    });
  });
});
