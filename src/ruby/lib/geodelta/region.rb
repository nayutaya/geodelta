# encoding: utf-8

require_relative "geometry"

module GeoDelta
  module Region
    def self.get_delta_ids_in_region(x1, y1, x2, y2, level)
      ids = []
      unit = 12.0 / (2 ** (level - 1))

      ids << (nw = GeoDelta::Geometry.get_delta_ids(x1, y1, level))
      ids << (ne = GeoDelta::Geometry.get_delta_ids(x2, y1, level))
      ids << (sw = GeoDelta::Geometry.get_delta_ids(x1, y2, level))
      ids << (se = GeoDelta::Geometry.get_delta_ids(x2, y2, level))

      # 上辺の一列
      sx, y = GeoDelta::Geometry.get_center(nw)
      ex, _ = GeoDelta::Geometry.get_center(ne)

      y += (unit / 6) * (GeoDelta::Geometry.upper_delta?(nw) ? +1 : -1)
      dx = ex - sx

      (0..((dx / (unit / 2)).floor)).each { |i|
        xx = sx + ((unit / 2) * i)
        ids << GeoDelta::Geometry.get_delta_ids(xx, y, level)
      }

      # 下辺の一列
      sx, y = GeoDelta::Geometry.get_center(sw)
      ex, _ = GeoDelta::Geometry.get_center(se)

      y += (unit / 6) * (GeoDelta::Geometry.upper_delta?(nw) ? +1 : -1)
      dx = ex - sx

      (0..((dx / (unit / 2)).floor)).each { |i|
        xx = sx + ((unit / 2) * i)
        ids << GeoDelta::Geometry.get_delta_ids(xx, y, level)
      }

      yi1 = (y1 / unit).floor
      yi2 = (y2 / unit).ceil + 1
      xi1 = (x1 / (unit / 2)).floor
      xi2 = (x2 / (unit / 2)).ceil
      (yi2..yi1).each { |yi|
        yy = yi * unit - (unit / 2)
        (xi1..xi2).each { |xi|
          xx = xi * (unit / 2)
          ids << GeoDelta::Geometry.get_delta_ids(xx, yy, level)
        }
      }

=begin
      s_ids  = GeoDelta::Geometry.get_delta_ids(x1, y1, level)
      e_ids  = GeoDelta::Geometry.get_delta_ids(x2, y2, level)
      sx, sy = GeoDelta::Geometry.get_center(s_ids)
      ex, ey = GeoDelta::Geometry.get_center(e_ids)

      unit = 12.0 / (2 ** (level - 1))
      sy  += (unit / 6) * (GeoDelta::Geometry.upper_delta?(s_ids) ? +1 : -1)
      ey  += (unit / 6) * (GeoDelta::Geometry.upper_delta?(e_ids) ? +1 : -1)

      ex += 24 if ex < sx
#      sx = (x1 / (unit / 2)).floor * (unit / 2)
#      ex = (x2 / (unit / 2)).ceil  * (unit / 2)

      dx   = ex - sx
      dy   = sy - ey

      ids = []
      ids << s_ids
      ids << e_ids
      ids << GeoDelta::Geometry.get_delta_ids(x2, y1, level)
      ids << GeoDelta::Geometry.get_delta_ids(x1, y2, level)

      (0..((dy / unit).floor)).each { |j|
        yy = sy - (unit * j)
        (0..((dx / (unit / 2)).floor)).each { |i|
          xx = sx + ((unit / 2) * i)
          ids << GeoDelta::Geometry.get_delta_ids(xx, yy, level)
        }
      }
=end

      ids.sort!
      ids.uniq!

      return ids
    end
  end
end
