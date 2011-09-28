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

  def test_get_upper_delta_id
    assert_equal(3, @mod.get_upper_delta_id( 0.0,  0.0))
    assert_equal(3, @mod.get_upper_delta_id( 1.5,  3.0))
    assert_equal(3, @mod.get_upper_delta_id( 3.0,  3.0))
    assert_equal(3, @mod.get_upper_delta_id( 3.0,  0.0))
    assert_equal(2, @mod.get_upper_delta_id( 9.0,  3.0))
    assert_equal(2, @mod.get_upper_delta_id( 9.0,  0.0))
    assert_equal(2, @mod.get_upper_delta_id(10.5,  3.0))
    assert_equal(2, @mod.get_upper_delta_id(12.0,  0.0))
    assert_equal(1, @mod.get_upper_delta_id( 4.5,  9.0))
    assert_equal(1, @mod.get_upper_delta_id( 6.0, 12.0))
    assert_equal(1, @mod.get_upper_delta_id( 6.0,  9.0))
    assert_equal(1, @mod.get_upper_delta_id( 7.5,  9.0))
    assert_equal(0, @mod.get_upper_delta_id( 3.0,  6.0))
    assert_equal(0, @mod.get_upper_delta_id( 4.5,  3.0))
    assert_equal(0, @mod.get_upper_delta_id( 6.0,  6.0))
    assert_equal(0, @mod.get_upper_delta_id( 6.0,  3.0))
    assert_equal(0, @mod.get_upper_delta_id( 6.0,  0.0))
    assert_equal(0, @mod.get_upper_delta_id( 7.5,  3.0))
    assert_equal(0, @mod.get_upper_delta_id( 9.0,  6.0))
  end

  def test_get_lower_delta_id
    assert_equal(3, @mod.get_lower_delta_id( 9.0, 12.0))
    assert_equal(3, @mod.get_lower_delta_id( 9.0,  9.0))
    assert_equal(3, @mod.get_lower_delta_id(10.5,  9.0))
    assert_equal(3, @mod.get_lower_delta_id(12.0, 12.0))
    assert_equal(2, @mod.get_lower_delta_id( 0.0, 12.0))
    assert_equal(2, @mod.get_lower_delta_id( 1.5,  9.0))
    assert_equal(2, @mod.get_lower_delta_id( 3.0, 12.0))
    assert_equal(2, @mod.get_lower_delta_id( 3.0,  9.0))
    assert_equal(1, @mod.get_lower_delta_id( 4.5,  3.0))
    assert_equal(1, @mod.get_lower_delta_id( 6.0,  3.0))
    assert_equal(1, @mod.get_lower_delta_id( 6.0,  0.0))
    assert_equal(1, @mod.get_lower_delta_id( 7.5,  3.0))
    assert_equal(0, @mod.get_lower_delta_id( 3.0,  6.0))
    assert_equal(0, @mod.get_lower_delta_id( 4.5,  9.0))
    assert_equal(0, @mod.get_lower_delta_id( 6.0, 12.0))
    assert_equal(0, @mod.get_lower_delta_id( 6.0,  9.0))
    assert_equal(0, @mod.get_lower_delta_id( 6.0,  6.0))
    assert_equal(0, @mod.get_lower_delta_id( 7.5,  9.0))
    assert_equal(0, @mod.get_lower_delta_id( 9.0,  6.0))
  end

  def test_upper_world_delta?
    assert_equal(false, @mod.upper_world_delta?(0))
    assert_equal(true , @mod.upper_world_delta?(1))
    assert_equal(false, @mod.upper_world_delta?(2))
    assert_equal(true , @mod.upper_world_delta?(3))
    assert_equal(true , @mod.upper_world_delta?(4))
    assert_equal(false, @mod.upper_world_delta?(5))
    assert_equal(true , @mod.upper_world_delta?(6))
    assert_equal(false, @mod.upper_world_delta?(7))
  end

  def test_upper_sub_delta?
    assert_equal(false, @mod.upper_sub_delta?(true,  0))
    assert_equal(true,  @mod.upper_sub_delta?(true,  1))
    assert_equal(true,  @mod.upper_sub_delta?(true,  2))
    assert_equal(true,  @mod.upper_sub_delta?(true,  3))
    assert_equal(true,  @mod.upper_sub_delta?(false, 0))
    assert_equal(false, @mod.upper_sub_delta?(false, 1))
    assert_equal(false, @mod.upper_sub_delta?(false, 2))
    assert_equal(false, @mod.upper_sub_delta?(false, 3))
  end

  def test_transform_world_delta
    assert_equal([+6.0, +4.0], @mod.transform_world_delta(0,  +0.0, +4.0))
    assert_equal([+6.0, +4.0], @mod.transform_world_delta(1,  +6.0, +4.0))
    assert_equal([+6.0, +4.0], @mod.transform_world_delta(2, +12.0, +4.0))
    assert_equal([+6.0, +4.0], @mod.transform_world_delta(3, +18.0, +4.0))
    assert_equal([+6.0, +4.0], @mod.transform_world_delta(4,  +0.0, -8.0))
    assert_equal([+6.0, +4.0], @mod.transform_world_delta(5,  +6.0, -8.0))
    assert_equal([+6.0, +4.0], @mod.transform_world_delta(6, +12.0, -8.0))
    assert_equal([+6.0, +4.0], @mod.transform_world_delta(7, +18.0, -8.0))
  end

  def test_transform_upper_delta
    assert_equal([+6.0, +8.0], @mod.transform_upper_delta(0, +6.0, +4.0))
    assert_equal([+6.0, +4.0], @mod.transform_upper_delta(1, +6.0, +8.0))
    assert_equal([+6.0, +4.0], @mod.transform_upper_delta(2, +9.0, +2.0))
    assert_equal([+6.0, +4.0], @mod.transform_upper_delta(3, +3.0, +2.0))
  end

  def test_transform_lower_delta
    assert_equal([+6.0, +4.0], @mod.transform_lower_delta(0, +6.0,  +8.0))
    assert_equal([+6.0, +8.0], @mod.transform_lower_delta(1, +6.0,  +4.0))
    assert_equal([+6.0, +8.0], @mod.transform_lower_delta(2, +3.0, +10.0))
    assert_equal([+6.0, +8.0], @mod.transform_lower_delta(3, +9.0, +10.0))
  end
end
