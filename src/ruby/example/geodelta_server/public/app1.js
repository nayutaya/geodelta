
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
      var center = new google.maps.LatLng(data.response.center.lat, data.response.center.lng);
      var marker = new google.maps.Marker({
        position: center,
        map: map, 
        title: "code=" + data.response.code
      });
    });
  });
});
