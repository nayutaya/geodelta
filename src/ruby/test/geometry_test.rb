# encoding: utf-8

require "test/unit"
require File.expand_path(File.join(File.dirname(__FILE__), "..", "lib", "geodelta", "geometry"))

class GeoDeltaGeometryTest < Test::Unit::TestCase
  def setup
    @mod = GeoDelta::Geometry
  end

  def test_get_world_delta_id
    assert_equal(2, @mod.get_world_delta_id( -6.0, +12.0))
    assert_equal(3, @mod.get_world_delta_id( -6.0,  +6.0))
    assert_equal(3, @mod.get_world_delta_id( -6.0,   0.0))
    assert_equal(3, @mod.get_world_delta_id( -3.0,  +6.0))
    assert_equal(0, @mod.get_world_delta_id(  0.0, +12.0))
    assert_equal(0, @mod.get_world_delta_id(  0.0,  +6.0))
    assert_equal(0, @mod.get_world_delta_id(  0.0,   0.0))
    assert_equal(0, @mod.get_world_delta_id( +3.0,  +6.0))
    assert_equal(0, @mod.get_world_delta_id( +6.0, +12.0))
    assert_equal(1, @mod.get_world_delta_id( +6.0,  +6.0))
    assert_equal(1, @mod.get_world_delta_id( +6.0,   0.0))
    assert_equal(1, @mod.get_world_delta_id( +9.0,  +6.0))
    assert_equal(1, @mod.get_world_delta_id(+12.0,   0.0))
    assert_equal(2, @mod.get_world_delta_id(+12.0, +12.0))
    assert_equal(2, @mod.get_world_delta_id(+12.0,  +6.0))
    assert_equal(2, @mod.get_world_delta_id(+15.0,  +6.0))
    assert_equal(2, @mod.get_world_delta_id(+18.0, +12.0))
    assert_equal(3, @mod.get_world_delta_id(+18.0,  +6.0))
    assert_equal(3, @mod.get_world_delta_id(+18.0,   0.0))
    assert_equal(3, @mod.get_world_delta_id(+21.0,  +6.0))
    assert_equal(0, @mod.get_world_delta_id(+24.0, +12.0))
    assert_equal(0, @mod.get_world_delta_id(+24.0,  +6.0))
    assert_equal(0, @mod.get_world_delta_id(+24.0,   0.0))

    assert_equal(6, @mod.get_world_delta_id( -6.0, -12.0))
    assert_equal(7, @mod.get_world_delta_id( -6.0,  -6.0))
    assert_equal(7, @mod.get_world_delta_id( -3.0,  -6.0))
    assert_equal(4, @mod.get_world_delta_id(  0.0,  -6.0))
    assert_equal(4, @mod.get_world_delta_id(  0.0, -12.0))
    assert_equal(4, @mod.get_world_delta_id( +3.0,  -6.0))
    assert_equal(4, @mod.get_world_delta_id( +6.0, -12.0))
    assert_equal(5, @mod.get_world_delta_id( +6.0,  -6.0))
    assert_equal(5, @mod.get_world_delta_id( +9.0,  -6.0))
    assert_equal(6, @mod.get_world_delta_id(+12.0,  -6.0))
    assert_equal(6, @mod.get_world_delta_id(+12.0, -12.0))
    assert_equal(6, @mod.get_world_delta_id(+15.0,  -6.0))
    assert_equal(6, @mod.get_world_delta_id(+18.0, -12.0))
    assert_equal(7, @mod.get_world_delta_id(+18.0,  -6.0))
    assert_equal(7, @mod.get_world_delta_id(+21.0,  -6.0))
    assert_equal(4, @mod.get_world_delta_id(+24.0,  -6.0))
    assert_equal(4, @mod.get_world_delta_id(+24.0, -12.0))
  end
end
