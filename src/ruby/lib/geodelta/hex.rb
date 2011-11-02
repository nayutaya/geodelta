# encoding: utf-8

require_relative "geometry"

module GeoDelta
  module Hex
    def self.get_hex_position(ids)
      unit = get_unit(ids)
      x, y = GeoDelta::Geometry.get_center(ids)
      ix   = (x / unit * 2.0).floor % 6
      iy   = (y / unit      ).floor % 2

      case [ix, iy]
      when [0, 0] then return 0
      when [0, 1] then return 3
      when [1, 0] then return 1
      when [1, 1] then return 2
      when [2, 0] then return 4
      when [2, 1] then return 5
      when [3, 0] then return 3
      when [3, 1] then return 0
      when [4, 0] then return 2
      when [4, 1] then return 1
      when [5, 0] then return 5
      when [5, 1] then return 4
      else raise "BUG [#{i}, #{j}]"
      end
    end

    def self.get_base_delta_ids(ids)
      unit = get_unit(ids)
      ux   = unit / 2.0
      uy   = unit / 3.0
      pos  = self.get_hex_position(ids)
      x, y = GeoDelta::Geometry.get_center(ids)

      sx, sy =
        case pos
        when 0 then [0.0, 0.0    ]
        when 1 then [-ux, +uy    ]
        when 2 then [-ux, +uy * 3]
        when 3 then [0.0, +uy * 4]
        when 4 then [+ux, +uy * 3]
        when 5 then [+ux, +uy    ]
        else raise "BUG [#{pos}]"
        end

      return nil if x + sx > +12.0
      return nil if y + sy > +12.0

      return GeoDelta::Geometry.get_delta_ids(x + sx, y + sy, ids.size)
    end

    def self.get_coordinates(base_ids)
      unit = get_unit(base_ids)
      ux1  = unit / 2.0
      ux2  = unit
      uy3  = unit
      x, y = GeoDelta::Geometry.get_coordinates(base_ids)[1]

      return [
        [x + ux1, y + uy3],
        [x + ux2, y      ],
        [x + ux1, y - uy3],
        [x - ux1, y - uy3],
        [x - ux2, y      ],
        [x - ux1, y + uy3],
      ]
    end

    def self.get_unit(ids)
      level = ids.size
      return 12.0 / (2 ** (level - 1))
    end
    private_class_method :get_unit
  end
end
