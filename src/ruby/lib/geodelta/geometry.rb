# encoding: utf-8

module GeoDelta
  module Geometry
    # 指定された座標(x,y)に該当するワールドデルタの番号を返す
    # ただし、-12.0 <= x <= +12.0、-12.0 <= y <= +12.0
    def self.get_world_delta_id(x, y)
      xx = x % 24
      yy = y.abs
      return (y >= 0.0 ? 0 : 4) +
        case
        when yy >= +2.0 * (xx -  0.0) then 0
        when yy <= -2.0 * (xx - 12.0) then 1
        when yy >= +2.0 * (xx - 12.0) then 2
        when yy <= -2.0 * (xx - 24.0) then 3
        else                               0
        end
    end
  end
end
