# encoding: utf-8

module GeoDelta
  module Projector
    DEG2RAD      = Math::PI / 180.0 # 度をラジアンに変換するための係数
    RAD2DEG      = 180.0 / Math::PI # ラジアンを度に変換するための係数
    DELTA_HEIGHT = Math.sqrt(0.75)  # 一辺を1.0とする正三角形の高さ

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
      mx  = (mx % 2.0) - 2.0
      mx += 2.0 if mx < -1.0
      return mx * 180.0
    end

    # メルカトルY座標から正規化Y座標に変換する
    #    -1.0 <= my <=  +1.0
    #   -12.0 <= ny <= +12.0
    def self.my_to_ny(my)
      return my / DELTA_HEIGHT * 12.0
    end

    # メルカトルX座標から正規化X座標に変換する
    #    -1.0 <= my <=  +1.0
    #   -12.0 <= ny <= +12.0
    def self.mx_to_nx(mx)
      return mx * 12.0
    end

    # 正規化Y座標からメルカトルY座標に変換する
    #   -12.0 <= ny <= +12.0
    #    -1.0 <= my <=  +1.0
    def self.ny_to_my(my)
      return my / 12.0 * DELTA_HEIGHT
    end

    # 正規化X座標からメルカトルX座標に変換する
    #   -12.0 <= ny <= +12.0
    #    -1.0 <= my <=  +1.0
    def self.nx_to_mx(ny)
      return ny / 12.0
    end

    def self.lat_to_ny(lat)
      return self.my_to_ny(self.lat_to_my(lat))
    end

    def self.lng_to_nx(lng)
      return self.mx_to_nx(self.lng_to_mx(lng))
    end

    def self.ny_to_lat(ny)
      return self.my_to_lat(self.ny_to_my(ny))
    end

    def self.nx_to_lng(nx)
      return self.mx_to_lng(self.nx_to_mx(nx))
    end

    def self.latlng_to_nxy(lat, lng)
      return [
        self.lng_to_nx(lng),
        self.lat_to_ny(lat),
      ]
    end

    def self.nxy_to_latlng(nx, ny)
      return [
        self.ny_to_lat(ny),
        self.nx_to_lng(nx),
      ]
    end
  end
end
