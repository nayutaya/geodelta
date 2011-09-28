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

    # 指定された座標(x,y)に該当する上向きのサブデルタの番号を返す
    # ただし、0.0 <= x <= +12.0、0.0 <= y <= +12.0
    def self.get_upper_delta_id(x, y)
      return 3 if y < -2.0 * (x - 6.0)
      return 2 if y < +2.0 * (x - 6.0)
      return 1 if y > 6.0
      return 0
    end

    # 指定された座標(x,y)に該当する下向きのサブデルタの番号を返す
    # ただし、0.0 <= x <= +12.0、0.0 <= y <= +12.0
    def self.get_lower_delta_id(x, y)
      return 3 if y > -2.0 * (x - 12.0)
      return 2 if y > +2.0 * x
      return 1 if y < 6.0
      return 0
    end

    # 指定されたワールドデルタが上向きかどうかを返す
    def self.upper_world_delta?(id)
      return (id % 2 == (id < 4 ? 1 : 0))
    end

    # 指定されたサブデルタが上向きかどうか返す
    def self.upper_sub_delta?(parent_is_upper, id)
      return (parent_is_upper ? (id != 0) : (id == 0))
    end
  end
end
