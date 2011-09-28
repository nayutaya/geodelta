# encoding: utf-8

require "test/unit"
require File.expand_path(File.join(File.dirname(__FILE__), "..", "lib", "geodelta", "geometry"))

class GeoDeltaGeometryTest < Test::Unit::TestCase
  def setup
    @mod = GeoDelta::Geometry
  end
end
