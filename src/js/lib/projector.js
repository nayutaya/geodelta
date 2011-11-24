
var geodelta = geodelta || {};
geodelta.projector = geodelta.projector || {};

(function() {
  // 度をラジアンに変換するための係数
  var DEG2RAD = Math.PI / 180.0;

  // 双曲線逆正接を計算する
  var atanh = function(x) {
    return Math.log((1 + x) / (1 - x)) / 2;
  };

  // 緯度をメルカトルY座標に変換する
  geodelta.projector.latToMy = function(lat) {
    return atanh(Math.sin(lat * DEG2RAD)) / Math.PI;
  };

  // 経度をメルカトルX座標に変換する
  geodelta.projector.lngToMx = function(lng) {
    return lng / 180.0;
  };
})();
