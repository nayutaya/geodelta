# encoding: utf-8

require_relative "region"
require_relative "hex_geometry"

module GeoDelta
  module HexRegion
    def self.get_base_delta_ids_in_region(x1, y1, x2, y2, level)
      deltas = GeoDelta::Region.get_delta_ids_in_region(x1, y1, x2, y2, level)
      return deltas.map { |ids| GeoDelta::HexGeometry.get_base_delta_ids(ids) }.compact.sort.uniq
    end
  end
end
