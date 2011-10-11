# encoding: utf-8

require File.expand_path(File.join(File.dirname(__FILE__), "projector"))
require File.expand_path(File.join(File.dirname(__FILE__), "geometry"))
require File.expand_path(File.join(File.dirname(__FILE__), "encoder"))

module GeoDelta
  def self.get_delta_ids(lat, lng, level)
    mx  = GeoDelta::Projector.lng_to_mx(lng)
    my  = GeoDelta::Projector.lat_to_my(lat)
    nx  = GeoDelta::Projector.mx_to_nx(mx)
    ny  = GeoDelta::Projector.my_to_ny(my)
    ids = GeoDelta::Geometry.get_delta_ids(nx, ny, level)
    return ids
  end

  def self.get_delta_code(lat, lng, level)
    ids  = self.get_delta_ids(lat, lng, level)
    code = GeoDelta::Encoder.encode(ids)
    return code
  end

  def self.get_center_latlng_from_delta_ids(ids)
    nx, ny = GeoDelta::Geometry.get_xy(ids)
    mx  = GeoDelta::Projector.nx_to_mx(nx)
    my  = GeoDelta::Projector.ny_to_my(ny)
    lng = GeoDelta::Projector.mx_to_lng(mx)
    lat = GeoDelta::Projector.my_to_lat(my)
    return [lat, lng]
  end
end
