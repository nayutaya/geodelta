
var geodelta = geodelta || {};

// 座標系の投影を行うモジュール
geodelta.projector = geodelta.projector || {};

(function() {
  // 度をラジアンに変換するための係数
  var DEG2RAD = Math.PI / 180.0;
  // ラジアンを度に変換するための係数
  var RAD2DEG = 180.0 / Math.PI;
  // 一辺を1.0とする正三角形の高さ
  var DELTA_HEIGHT = Math.sqrt(0.75);

  // 双曲線正弦を求める
  var sinh = function(x) {
    return (Math.exp(x) - Math.exp(-x)) / 2.0;
  };
  // 双曲線余弦を求める
  var cosh = function(x) {
    return (Math.exp(x) + Math.exp(-x)) / 2.0;
  };
  // 双曲線正接を求める
  var tanh = function(x) {
    return sinh(x) / cosh(x);
  };
  // 双曲線逆正接を求める
  var atanh = function(x) {
    return Math.log((1 + x) / (1 - x)) / 2;
  };
  // 剰余を求める
  var mod = function(a, b) {
    var val = a;
    while ( val >= b )
    {
      val -= b;
    }
    while ( val < 0.0 )
    {
      val += b;
    }
    return val;
  };

  geodelta.projector.DELTA_HEIGHT = function() { return DELTA_HEIGHT };

  // 緯度をメルカトルY座標に変換する
  geodelta.projector.latToMy = function(lat) {
    return atanh(Math.sin(lat * DEG2RAD)) / Math.PI;
  };

  // 経度をメルカトルX座標に変換する
  geodelta.projector.lngToMx = function(lng) {
    return lng / 180.0;
  };

  // メルカトルY座標を緯度に変換する
  geodelta.projector.myToLat = function(my) {
    return Math.asin(tanh(my * Math.PI)) * RAD2DEG;
  };

  // メルカトルX座標を経度に変換する
  geodelta.projector.mxToLng = function(mx) {
    var x = mod(mx, 2.0) - 2.0;
    if ( x < -1.0 )
    {
      x += 2.0;
    }
    return x * 180.0;
  };

  // メルカトルY座標を正規化Y座標に変換する
  geodelta.projector.myToNy = function(my) {
    return my / DELTA_HEIGHT * 12.0;
  };

  // メルカトルX座標を正規化X座標に変換する
  geodelta.projector.mxToNx = function(mx) {
    return mx * 12.0;
  };

  // 正規化Y座標をメルカトルY座標に変換する
  geodelta.projector.nyToMy = function(ny) {
    return ny / 12.0 * DELTA_HEIGHT;
  };

  // 正規化X座標をメルカトルX座標に変換する
  geodelta.projector.nxToMx = function(nx) {
    return nx / 12.0;
  };

  // 緯度を正規化Y座標系に変換する
  geodelta.projector.latToNy = function(lat) {
    return geodelta.projector.myToNy(geodelta.projector.latToMy(lat));
  };

  // 経度を正規化X座標に変換する
  geodelta.projector.lngToNx = function(lng) {
    return geodelta.projector.mxToNx(geodelta.projector.lngToMx(lng));
  };

  // 正規化Y座標を緯度に変換する
  geodelta.projector.nyToLat = function(ny) {
    return geodelta.projector.myToLat(geodelta.projector.nyToMy(ny));
  };

  // 正規化X座標を経度に変換する
  geodelta.projector.nxToLng = function(nx) {
    return geodelta.projector.mxToLng(geodelta.projector.nxToMx(nx));
  };

  // 緯度経度を正規化XY座標に変換する
  geodelta.projector.latLngToNxy = function(lat, lng) {
    return {
      nx: geodelta.projector.lngToNx(lng),
      ny: geodelta.projector.latToNy(lat)
    };
  };

  // 正規化XY座標を緯度経度に変換する
  geodelta.projector.nxyToLatLng = function(nx, ny) {
    return {
      lat: geodelta.projector.nyToLat(ny),
      lng: geodelta.projector.nxToLng(nx)
    };
  };
})();
