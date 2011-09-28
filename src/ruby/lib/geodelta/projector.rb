# encoding: utf-8

module GeoDelta
  module Projector
    DEG2RAD = Math::PI / 180.0 # 度をラジアンに変換するための係数
    RAD2DEG = 180.0 / Math::PI # ラジアンを度に変換するための係数

    # 緯度をメルカトルY座標に変換する
    #   -90.0 <= lat <= +90.0
    #    -1.0 <= my  <=  +1.0
    def self.lat_to_my(lat)
      return Math.atanh(Math.sin(lat * DEG2RAD)) / Math::PI
    end

    # 経度をメルカトルX座標に変換する
    #   -180.0 <= lng <= +180.0
    #     -1.0 <= mx  <=   +1.0
    def self.lng_to_mx(lng)
      return lng / 180.0
    end

    # メルカトルY座標を緯度に変換する
    #    -1.0 <= my  <=  +1.0
    #   -90.0 <= lat <= +90.0
    def self.my_to_lat(my)
      return Math.asin(Math.tanh(my * Math::PI)) * RAD2DEG
    end

    # メルカトルX座標を経度に変換する
    #     -1.0 <= mx  <=   +1.0
    #   -180.0 <= lng <= +180.0
    def self.mx_to_lng(mx)
      return mx * 180.0
    end
  end
end
