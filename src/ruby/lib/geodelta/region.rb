# encoding: utf-8

require_relative "geometry"

module GeoDelta
  module Region
    def self.get_delta_ids_in_region(x1, y1, x2, y2, level)
      ids = []
      unit = 12.0 / (2 ** (level - 1))
      u1   = unit
      u2   = unit / 2

#      ids << (nw = GeoDelta::Geometry.get_delta_ids(x1, y1, level))
#      ids << (ne = GeoDelta::Geometry.get_delta_ids(x2, y1, level))
#      ids << (sw = GeoDelta::Geometry.get_delta_ids(x1, y2, level))
#      ids << (se = GeoDelta::Geometry.get_delta_ids(x2, y2, level))
      (nw = GeoDelta::Geometry.get_delta_ids(x1, y1, level))
      (ne = GeoDelta::Geometry.get_delta_ids(x2, y1, level))
      (sw = GeoDelta::Geometry.get_delta_ids(x1, y2, level))
      (se = GeoDelta::Geometry.get_delta_ids(x2, y2, level))

      # 上辺の一列
      proc {
        sx, y = GeoDelta::Geometry.get_center(nw)
        ex, _ = GeoDelta::Geometry.get_center(ne)

        sxi = (sx / u2).floor
        exi = (ex / u2).ceil

        if !GeoDelta::Geometry.upper_delta?(nw)
          if x1 < sx && nw != sw
            sxi -= 1
          end
        end

        if !GeoDelta::Geometry.upper_delta?(ne)
          if x2 > ex && ne != se
            exi += 1
          end
        end

        (sxi..exi).each { |xi|
          xx = xi * u2
          ids << GeoDelta::Geometry.get_delta_ids(xx, y, level)
        }
      }.call

      # 下辺の一列
      proc {
        sx, y = GeoDelta::Geometry.get_center(sw)
        ex, _ = GeoDelta::Geometry.get_center(se)

        sxi = (sx / u2).floor
        exi = (ex / u2).ceil

        if GeoDelta::Geometry.upper_delta?(sw)
          if x1 < sx && nw != sw
            sxi -= 1
          end
        end

        if GeoDelta::Geometry.upper_delta?(se)
          if x2 > ex && ne != se
            exi += 1
          end
        end

        (sxi..exi).each { |xi|
          xx = xi * u2
          ids << GeoDelta::Geometry.get_delta_ids(xx, y, level)
        }
      }.call

      proc {
        syi = (y1 / u1).floor
        eyi = (y2 / u1).ceil + 1
        sxi = (x1 / u2).floor
        exi = (x2 / u2).ceil
        (eyi..syi).each { |yi|
          yy = yi * unit - u2
          (sxi..exi).each { |xi|
            xx = xi * u2
            ids << GeoDelta::Geometry.get_delta_ids(xx, yy, level)
          }
        }
      }.call

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
