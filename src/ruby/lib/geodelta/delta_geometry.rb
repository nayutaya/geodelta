# encoding: utf-8

module GeoDelta
  module DeltaGeometry
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

    def self.upper_delta?(ids)
      return ids.inject(nil) { |upper, id|
        if upper.nil?
          self.upper_world_delta?(id)
        else
          self.upper_sub_delta?(upper, id)
        end
      }
    end

    def self.transform_world_delta(id, x, y)
      xx = (x + [+6.0, +0.0, -6.0, -12.0,  +6.0,  +0.0,  -6.0, -12.0][id]) % 12
      yy = (y + [+0.0, +0.0, +0.0,  +0.0, +12.0, +12.0, +12.0, +12.0][id]) % 12
      return [xx, yy]
    end

    def self.transform_upper_delta(id, x, y)
      xx = (x + [-3.0, -3.0, -6.0, -0.0][id]) * 2
      yy = (y + [-0.0, -6.0, -0.0, -0.0][id]) * 2
      return [xx, yy]
    end

    def self.transform_lower_delta(id, x, y)
      xx = (x + [-3.0, -3.0, -0.0, -6.0][id]) * 2
      yy = (y + [-6.0, -0.0, -6.0, -6.0][id]) * 2
      return [xx, yy]
    end

    def self.get_delta_ids(x, y, level)
      ids    = [self.get_world_delta_id(x, y)]
      xx, yy = self.transform_world_delta(ids.last, x, y)
      upper  = self.upper_world_delta?(ids.last)

      (level - 1).times {
        if upper
          ids   << self.get_upper_delta_id(xx, yy)
          xx, yy = self.transform_upper_delta(ids.last, xx, yy)
          upper  = self.upper_sub_delta?(upper, ids.last)
        else
          ids   << self.get_lower_delta_id(xx, yy)
          xx, yy = self.transform_lower_delta(ids.last, xx, yy)
          upper  = self.upper_sub_delta?(upper, ids.last)
        end
      }

      return ids
    end

    def self.get_world_delta_center(id)
      case id
      when 0 then [ +0.0, +8.0]
      when 1 then [ +6.0, +4.0]
      when 2 then [+12.0, +8.0]
      when 3 then [+18.0, +4.0]
      when 4 then [ +0.0, -8.0]
      when 5 then [ +6.0, -4.0]
      when 6 then [+12.0, -8.0]
      when 7 then [+18.0, -4.0]
      end
    end

    def self.get_upper_sub_delta_distance(id)
      case id
      when 0 then [+0.0, +0.0]
      when 1 then [+0.0, +4.0]
      when 2 then [+3.0, -2.0]
      when 3 then [-3.0, -2.0]
      end
    end

    def self.get_lower_sub_delta_distance(id)
      case id
      when 0 then [+0.0, +0.0]
      when 1 then [+0.0, -4.0]
      when 2 then [-3.0, +2.0]
      when 3 then [+3.0, +2.0]
      end
    end

    def self.get_sub_delta_distance(parent_is_upper, id)
      if parent_is_upper
        return self.get_upper_sub_delta_distance(id)
      else
        return self.get_lower_sub_delta_distance(id)
      end
    end

    def self.get_center(ids)
      xs, ys = [], []
      upper  = nil

      ids.each_with_index { |id, index|
        if index == 0
          x, y  = self.get_world_delta_center(id)
          upper = self.upper_world_delta?(id)
          xs << x
          ys << y
        else
          x, y  = self.get_sub_delta_distance(upper, id)
          upper = self.upper_sub_delta?(upper, id)
          xs << (x / (2 ** (index - 1)))
          ys << (y / (2 ** (index - 1)))
        end
      }

      x = xs.sort.inject(0.0, &:+)
      y = ys.sort.inject(0.0, &:+)

      x -= 24.0 if x > 12.0

      return [x, y]
    end

    def self.get_coordinates(ids)
      cx, cy = self.get_center(ids)
      level  = ids.size
      sign   = (self.upper_delta?(ids) ? +1 : -1)
      scale  = 1.0 / (2 ** (level - 1)) * sign

      dx1 = 0.0
      dy1 = 8.0 * scale
      dx2 = 6.0 * scale
      dy2 = 4.0 * scale

      return [
        [cx,       cy      ],
        [cx + dx1, cy + dy1],
        [cx + dx2, cy - dy2],
        [cx - dx2, cy - dy2],
      ]
    end
  end
end
