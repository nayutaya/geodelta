# encoding: utf-8

require_relative "geometry"

module GeoDelta
  module Hex
    def self.get_hex_position(ids)
      level = ids.size
      unit  = 12.0 / (2 ** (level - 1))
      x, y  = GeoDelta::Geometry.get_center(ids)
      #x, y  = GeoDelta::Geometry.get_coordinates(ids)[1]
      upper = GeoDelta::Geometry.upper_delta?(ids)

      p({:ids => ids, :x => x, :y => y})

      #p [ids, (y / unit).floor]
      k = Math.sqrt((0.75 ** 2) + (0.5 ** 2)) * unit
      i = Math.cos(           Math.atan(6.0 / 9.0)) * x
      j = Math.cos(Math::PI - Math.atan(6.0 / 9.0)) * y
      xx = (x / (unit / 2)).floor
      yy = (y / unit).floor
      ii = (i / k).floor
      jj = (j / k).floor
      #p [ids, (j / k).floor]
      #p [ids, [yy, ii, jj]]
      #p [ids, [y, i, j]]
      #p [[xx, yy, ii, jj], [xx % 2, yy % 2, ii % 2, jj % 2]]
      p [xx, yy]

=begin
      if true
        case [xx % 2, yy % 2]
        when [0, 0, 1] then return 0
        when [1, 0, 1] then return 1
        when [1, 1, 1] then return 2
        when [0, 1, 1] then return 3
        when [1, 1, 1] then return 4
        when [1, 0, 1] then return 5
        end
      end
=end

      case [xx, yy]
      when [ 0,  0] then return 0 # center
      when [ 0,  2] then return 0 # upper
      when [ 0, -2] then return 0 # lower
      when [ 3,  1] then return 0 # upper_right
      when [ 3, -1] then return 0 # lower_right
      when [-3, -1] then return 0 # lower_left
      when [-3,  1] then return 0 # upper_left

      when [ 1,  0] then return 1 # center
      when [ 1,  2] then return 1 # upper
      when [ 1, -2] then return 1 # lower
      when [ 4,  1] then return 1 # upper_right
      when [ 4, -1] then return 1 # lower_right
      when [-2, -1] then return 1 # lower_left
      when [-2,  1] then return 1 # upper_left

      when [ 1, -1] then return 2 # center
      when [ 1,  1] then return 2 # upper
      when [ 1, -3] then return 2 # lower
      when [ 4,  0] then return 2 # upper_right
      when [ 4, -2] then return 2 # lower_right
      when [-2, -2] then return 2 # lower_left
      when [-2,  0] then return 2 # upper_left

      when [ 0, -1] then return 3 # center
      when [ 0,  1] then return 3 # upper
      when [ 0, -3] then return 3 # lower
      when [ 3,  0] then return 3 # upper_right
      when [ 3, -2] then return 3 # lower_right
      when [-3, -2] then return 3 # lower_left
      when [-3,  0] then return 3 # upper_left

      when [-1, -1] then return 4 # center
      when [-1,  1] then return 4 # upper
      when [-1, -3] then return 4 # lower
      when [ 2,  0] then return 4 # upper_right
      when [ 2, -2] then return 4 # lower_right
      when [-4, -2] then return 4 # lower_left
      when [-4,  0] then return 4 # upper_left

      when [-1,  0] then return 5 # center
      when [-1,  2] then return 5 # upper
      when [-1, -2] then return 5 # lower
      when [ 2,  1] then return 5 # upper_right
      when [ 2, -1] then return 5 # lower_right
      when [-4, -1] then return 5 # lower_left
      when [-4,  1] then return 5 # upper_left
      end

=begin
      p [ids, [x, y], upper]
      #p y % unit

      k  = Math.sqrt((unit ** 2) - ((unit / 2) ** 2))
      sx = x
      sy = y * (k / unit)
      si = Math.sin(Math.atan(2)) * sx
      sj = Math.cos(Math.atan(2)) * sy
      #p [sx, sy, si, sj]
      p [sy % k, si % k, sj % k]
      #p [(sy % k) > 1.0, (si % k) > 1.0, (sj % k) > 1.0]
      p [(sy % k) > 1.0, (si % k) > 1.0, (sj % k) > 1.0]
      puts
      
      #p [i, j]
      #p [Math.sin(Math.atan(2)) * 12, Math.cos(Math.atan(2)) * 12]
=end

      case ids
      when [0, 1, 1] then 0
      when [1, 3, 3] then 1
      when [5, 2, 2] then 2
      when [4, 1, 1] then 3
      when [7, 3, 3] then 4
      when [3, 2, 2] then 5
      else -1
      end
    end
  end
end
