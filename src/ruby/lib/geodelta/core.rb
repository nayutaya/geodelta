# encoding: utf-8

require_relative "projector"
require_relative "delta_geometry"
require_relative "encoder"

module GeoDelta
  def self.get_delta_ids(lat, lng, level)
    nx, ny = GeoDelta::Projector.latlng_to_nxy(lat, lng)
    return GeoDelta::DeltaGeometry.get_delta_ids(nx, ny, level)
  end

  def self.get_delta_code(lat, lng, level)
    ids = self.get_delta_ids(lat, lng, level)
    return GeoDelta::Encoder.encode(ids)
  end

  def self.get_center_from_delta_ids(ids)
    nx, ny = GeoDelta::DeltaGeometry.get_center(ids)
    return GeoDelta::Projector.nxy_to_latlng(nx, ny)
  end

  def self.get_center_from_delta_code(code)
    ids = GeoDelta::Encoder.decode(code)
    return self.get_center_from_delta_ids(ids)
  end

  def self.get_coordinates_from_ids(ids)
    return GeoDelta::DeltaGeometry.get_coordinates(ids).
      map { |nx, ny| GeoDelta::Projector.nxy_to_latlng(nx, ny) }
  end

  def self.get_coordinates_from_code(code)
    ids = GeoDelta::Encoder.decode(code)
    return self.get_coordinates_from_ids(ids)
  end
end
