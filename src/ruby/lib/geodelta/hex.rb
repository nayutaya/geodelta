# encoding: utf-8

require_relative "geometry"

module GeoDelta
  module Hex
    def self.get_hex_position(ids)
      level = ids.size
      unit  = 12.0 / (2 ** (level - 1))
      xu    = unit / 2.0
      yu    = unit

      x, y = GeoDelta::Geometry.get_center(ids)
      i = (x / xu).floor % 6
      j = (y / yu).floor % 2

      case [i, j]
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
  end
end
