# encoding: utf-8

module GeoDelta
  module Projector
    DEG2RAD      = Math::PI / 180.0 # 度をラジアンに変換するための係数

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
  end
end
