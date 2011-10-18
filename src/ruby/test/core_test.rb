# encoding: utf-8

require "test/unit"
require_relative "../lib/geodelta/core"

class GeoDeltaTest < Test::Unit::TestCase
  def setup
    @mod = GeoDelta
  end

  def test_get_delta_ids
    assert_equal([0], @mod.get_delta_ids(+45.0,   +0.0, 1))
    assert_equal([1], @mod.get_delta_ids(+45.0,  +90.0, 1))
    assert_equal([2], @mod.get_delta_ids(+45.0, +180.0, 1))
    assert_equal([3], @mod.get_delta_ids(+45.0,  -90.0, 1))
    assert_equal([2], @mod.get_delta_ids(+45.0, -180.0, 1))

    assert_equal([4], @mod.get_delta_ids(-45.0,   +0.0, 1))
    assert_equal([5], @mod.get_delta_ids(-45.0,  +90.0, 1))
    assert_equal([6], @mod.get_delta_ids(-45.0, +180.0, 1))
    assert_equal([7], @mod.get_delta_ids(-45.0,  -90.0, 1))
    assert_equal([6], @mod.get_delta_ids(-45.0, -180.0, 1))

    assert_equal([0],          @mod.get_delta_ids(+0.0, +0.0, 1))
    assert_equal([0, 1],       @mod.get_delta_ids(+0.0, +0.0, 2))
    assert_equal([0, 1, 1],    @mod.get_delta_ids(+0.0, +0.0, 3))
    assert_equal([0, 1, 1, 1], @mod.get_delta_ids(+0.0, +0.0, 4))
  end

  def test_get_delta_code
    assert_equal("Z", @mod.get_delta_code(+45.0,   +0.0, 1))
    assert_equal("Y", @mod.get_delta_code(+45.0,  +90.0, 1))
    assert_equal("X", @mod.get_delta_code(+45.0, +180.0, 1))
    assert_equal("W", @mod.get_delta_code(+45.0,  -90.0, 1))
    assert_equal("X", @mod.get_delta_code(+45.0, -180.0, 1))

    assert_equal("V", @mod.get_delta_code(-45.0,   +0.0, 1))
    assert_equal("T", @mod.get_delta_code(-45.0,  +90.0, 1))
    assert_equal("S", @mod.get_delta_code(-45.0, +180.0, 1))
    assert_equal("R", @mod.get_delta_code(-45.0,  -90.0, 1))
    assert_equal("S", @mod.get_delta_code(-45.0, -180.0, 1))

    assert_equal("Z",   @mod.get_delta_code(+0.0, +0.0, 1))
    assert_equal("ZM",  @mod.get_delta_code(+0.0, +0.0, 2))
    assert_equal("Z7",  @mod.get_delta_code(+0.0, +0.0, 3))
    assert_equal("Z7M", @mod.get_delta_code(+0.0, +0.0, 4))
  end

  def test_get_center_from_delta_ids
    lat, lng = @mod.get_center_from_delta_ids([0])
    assert_in_delta( +71.480, lat, 1.0E-3)
    assert_in_delta(  +0.000, lng, 1.0E-3)
    lat, lng = @mod.get_center_from_delta_ids([1])
    assert_in_delta( +46.024, lat, 1.0E-3)
    assert_in_delta( +90.000, lng, 1.0E-3)
    lat, lng = @mod.get_center_from_delta_ids([2])
    assert_in_delta( +71.480, lat, 1.0E-3)
    assert_in_delta(-180.000, lng, 1.0E-3)
    lat, lng = @mod.get_center_from_delta_ids([3])
    assert_in_delta( +46.024, lat, 1.0E-3)
    assert_in_delta( -90.000, lng, 1.0E-3)

    lat, lng = @mod.get_center_from_delta_ids([4])
    assert_in_delta( -71.480, lat, 1.0E-3)
    assert_in_delta(  +0.000, lng, 1.0E-3)
    lat, lng = @mod.get_center_from_delta_ids([5])
    assert_in_delta( -46.024, lat, 1.0E-3)
    assert_in_delta( +90.000, lng, 1.0E-3)
    lat, lng = @mod.get_center_from_delta_ids([6])
    assert_in_delta( -71.480, lat, 1.0E-3)
    assert_in_delta(-180.000, lng, 1.0E-3)
    lat, lng = @mod.get_center_from_delta_ids([7])
    assert_in_delta( -46.024, lat, 1.0E-3)
    assert_in_delta( -90.000, lng, 1.0E-3)

    lat, lng = @mod.get_center_from_delta_ids([0, 0])
    assert_in_delta(+71.480, lat, 1.0E-3)
    assert_in_delta( +0.000, lng, 1.0E-3)
    lat, lng = @mod.get_center_from_delta_ids([0, 0, 0])
    assert_in_delta(+71.480, lat, 1.0E-3)
    assert_in_delta( +0.000, lng, 1.0E-3)
  end

  def test_get_center_from_delta_code
    lat, lng = @mod.get_center_from_delta_code("Z")
    assert_in_delta( +71.480, lat, 1.0E-3)
    assert_in_delta(  +0.000, lng, 1.0E-3)
    lat, lng = @mod.get_center_from_delta_code("Y")
    assert_in_delta( +46.024, lat, 1.0E-3)
    assert_in_delta( +90.000, lng, 1.0E-3)
    lat, lng = @mod.get_center_from_delta_code("X")
    assert_in_delta( +71.480, lat, 1.0E-3)
    assert_in_delta(-180.000, lng, 1.0E-3)
    lat, lng = @mod.get_center_from_delta_code("W")
    assert_in_delta( +46.024, lat, 1.0E-3)
    assert_in_delta( -90.000, lng, 1.0E-3)

    lat, lng = @mod.get_center_from_delta_code("V")
    assert_in_delta( -71.480, lat, 1.0E-3)
    assert_in_delta(  +0.000, lng, 1.0E-3)
    lat, lng = @mod.get_center_from_delta_code("T")
    assert_in_delta( -46.024, lat, 1.0E-3)
    assert_in_delta( +90.000, lng, 1.0E-3)
    lat, lng = @mod.get_center_from_delta_code("S")
    assert_in_delta( -71.480, lat, 1.0E-3)
    assert_in_delta(-180.000, lng, 1.0E-3)
    lat, lng = @mod.get_center_from_delta_code("R")
    assert_in_delta( -46.024, lat, 1.0E-3)
    assert_in_delta( -90.000, lng, 1.0E-3)

    lat, lng = @mod.get_center_from_delta_code("ZK")
    assert_in_delta(+71.480, lat, 1.0E-3)
    assert_in_delta( +0.000, lng, 1.0E-3)
    lat, lng = @mod.get_center_from_delta_code("Z2")
    assert_in_delta(+71.480, lat, 1.0E-3)
    assert_in_delta( +0.000, lng, 1.0E-3)
  end

  def test_get_coordinates_from_ids__1
    delta0 = @mod.get_coordinates_from_ids([0])
    assert_equal(4, delta0.size)
    assert_equal(2, delta0[0].size)
    assert_equal(2, delta0[1].size)
    assert_equal(2, delta0[2].size)
    assert_equal(2, delta0[3].size)
    assert_in_delta( +71.480, delta0[0][0], 1.0E-3)
    assert_in_delta(  +0.000, delta0[0][1], 1.0E-3)
    assert_in_delta(  +0.000, delta0[1][0], 1.0E-3)
    assert_in_delta(  +0.000, delta0[1][1], 1.0E-3)
    assert_in_delta( +82.467, delta0[2][0], 1.0E-3)
    assert_in_delta( -90.000, delta0[2][1], 1.0E-3)
    assert_in_delta( +82.467, delta0[3][0], 1.0E-3)
    assert_in_delta( +90.000, delta0[3][1], 1.0E-3)

    delta4 = @mod.get_coordinates_from_ids([4])
    assert_in_delta( -71.480, delta4[0][0], 1.0E-3)
    assert_in_delta(  +0.000, delta4[0][1], 1.0E-3)
    assert_in_delta(  +0.000, delta4[1][0], 1.0E-3)
    assert_in_delta(  +0.000, delta4[1][1], 1.0E-3)
    assert_in_delta( -82.467, delta4[2][0], 1.0E-3)
    assert_in_delta( +90.000, delta4[2][1], 1.0E-3)
    assert_in_delta( -82.467, delta4[3][0], 1.0E-3)
    assert_in_delta( -90.000, delta4[3][1], 1.0E-3)
  end

  def test_get_coordinates_from_ids__2
    delta = (0..7).map { |id| @mod.get_coordinates_from_ids([id]) }
    assert_equal(delta[0][1], delta[1][3])
    assert_equal(delta[0][1], delta[3][2])
    assert_equal(delta[0][1], delta[4][1])
    assert_equal(delta[0][1], delta[5][2])
    assert_equal(delta[0][1], delta[7][3])
    assert_equal(delta[0][2], delta[2][3])
    assert_equal(delta[0][2], delta[3][1])
    assert_equal(delta[0][3], delta[1][1])
    assert_equal(delta[0][3], delta[2][2])
    assert_equal(delta[1][2], delta[2][1])
    assert_equal(delta[1][2], delta[3][3])
    assert_equal(delta[1][2], delta[5][3])
    assert_equal(delta[1][2], delta[6][1])
    assert_equal(delta[1][2], delta[7][2])
    assert_equal(delta[4][3], delta[6][2])
    assert_equal(delta[4][3], delta[7][1])
    assert_equal(delta[4][2], delta[5][1])
    assert_equal(delta[4][2], delta[6][3])
  end

  def test_get_coordinates_from_ids__3
    delta = (0..3).map { |id| @mod.get_coordinates_from_ids([0, id]) }
    assert_equal(delta[0][1], delta[2][3])
    assert_equal(delta[0][1], delta[3][2])
    assert_equal(delta[0][2], delta[1][3])
    assert_equal(delta[0][2], delta[3][1])
    assert_equal(delta[0][3], delta[1][2])
    assert_equal(delta[0][3], delta[2][1])
  end

  def test_get_coordinates_from_code
    assert_equal(
      @mod.get_coordinates_from_ids([0]),
      @mod.get_coordinates_from_code("Z"))
    assert_equal(
      @mod.get_coordinates_from_ids([0, 1, 2]),
      @mod.get_coordinates_from_code("Z8"))
  end

  def test_rush
    1000.times {
      lat1  = rand * 180.0 -  90.0
      lng1  = rand * 360.0 - 180.0
      level = rand(30) + 1
      code1 = @mod.get_delta_code(lat1, lng1, level)
      lat2,
      lng2  = @mod.get_center_from_delta_code(code1)
      code2 = @mod.get_delta_code(lat2, lng2, level)
      lat3,
      lng3  = @mod.get_center_from_delta_code(code2)
      assert_equal(code1, code2)
      assert_equal([lat2, lng2], [lat3, lng3])
    }
  end
end
