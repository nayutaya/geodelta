# encoding: utf-8

require "test/unit"
require_relative "../lib/geodelta/projector"

class GeoDeltaProjectorTest < Test::Unit::TestCase
  def setup
    @mod = GeoDelta::Projector
  end

  def test_lat_to_my
    assert_in_delta(+1.0, @mod.lat_to_my(+85.0511), 1.0E-5)
    assert_equal(0.0, @mod.lat_to_my(0.0))
    assert_in_delta(-1.0, @mod.lat_to_my(-85.0511), 1.0E-5)
  end

  def test_lng_to_mx
    assert_equal(+1.0, @mod.lng_to_mx(+180.0))
    assert_equal(+0.5, @mod.lng_to_mx( +90.0))
    assert_equal( 0.0, @mod.lng_to_mx(   0.0))
    assert_equal(-0.5, @mod.lng_to_mx( -90.0))
    assert_equal(-1.0, @mod.lng_to_mx(-180.0))
  end

  def test_my_to_lat
    assert_in_delta(+85.0511, @mod.my_to_lat(+1.0), 1.0E-4)
    assert_equal(0.0, @mod.my_to_lat(0.0))
    assert_in_delta(-85.0511, @mod.my_to_lat(-1.0), 1.0E-4)
  end

  def test_mx_to_lng
    assert_equal( -90.0, @mod.mx_to_lng(+1.5))
    assert_equal(-180.0, @mod.mx_to_lng(+1.0))
    assert_equal( +90.0, @mod.mx_to_lng(+0.5))
    assert_equal(   0.0, @mod.mx_to_lng( 0.0))
    assert_equal( -90.0, @mod.mx_to_lng(-0.5))
    assert_equal(-180.0, @mod.mx_to_lng(-1.0))
    assert_equal( +90.0, @mod.mx_to_lng(-1.5))
  end

  def test_my_to_ny
    max = @mod::DELTA_HEIGHT
    assert_equal(+12.0, @mod.my_to_ny(+max))
    assert_equal(  0.0, @mod.my_to_ny( 0.0))
    assert_equal(-12.0, @mod.my_to_ny(-max))
  end

  def test_mx_to_nx
    assert_equal(+12.0, @mod.mx_to_nx(+1.0))
    assert_equal(  0.0, @mod.mx_to_nx( 0.0))
    assert_equal(-12.0, @mod.mx_to_nx(-1.0))
  end

  def test_ny_to_my
    max = @mod::DELTA_HEIGHT
    assert_equal(+max, @mod.ny_to_my(+12.0))
    assert_equal( 0.0, @mod.ny_to_my(  0.0))
    assert_equal(-max, @mod.ny_to_my(-12.0))
  end

  def test_nx_to_mx
    assert_equal(+1.0, @mod.nx_to_mx(+12.0))
    assert_equal( 0.0, @mod.nx_to_mx(  0.0))
    assert_equal(-1.0, @mod.nx_to_mx(-12.0))
  end

  def test_lat_to_ny
    assert_equal(0.0, @mod.lat_to_ny(0.0))
    assert_equal(
      @mod.my_to_ny(@mod.lat_to_my(+82.4674)),
      @mod.lat_to_ny(+82.4674))
    assert_equal(
      @mod.my_to_ny(@mod.lat_to_my(-82.4674)),
      @mod.lat_to_ny(-82.4674))
  end

  def test_lng_to_nx
    assert_equal(0.0, @mod.lng_to_nx(0.0))
    assert_equal(
      @mod.mx_to_nx(@mod.lng_to_mx(+180.0)),
      @mod.lng_to_nx(+180.0))
    assert_equal(
      @mod.mx_to_nx(@mod.lng_to_mx(-180.0)),
      @mod.lng_to_nx(-180.0))
  end

  def test_ny_to_lat
    assert_equal(0.0, @mod.ny_to_lat(0.0))
    assert_equal(
      @mod.my_to_lat(@mod.ny_to_my(+12.0)),
      @mod.ny_to_lat(+12.0))
    assert_equal(
      @mod.my_to_lat(@mod.ny_to_my(-12.0)),
      @mod.ny_to_lat(-12.0))
  end

  def test_nx_to_lng
    assert_equal(0.0, @mod.nx_to_lng(0.0))
    assert_equal(
      @mod.mx_to_lng(@mod.nx_to_mx(+12.0)),
      @mod.nx_to_lng(+12.0))
    assert_equal(
      @mod.mx_to_lng(@mod.nx_to_mx(-12.0)),
      @mod.nx_to_lng(-12.0))
  end

  def test_latlng_to_nxy
    assert_equal([0.0, 0.0], @mod.latlng_to_nxy(0.0, 0.0))
    assert_equal(
      [@mod.lng_to_nx(+180.0), @mod.lat_to_ny(+82.4674)],
      @mod.latlng_to_nxy(+82.4674, +180.0))
    assert_equal(
      [@mod.lng_to_nx(-180.0), @mod.lat_to_ny(-82.4674)],
      @mod.latlng_to_nxy(-82.4674, -180.0))
  end

  def test_nxy_to_latlng
    assert_equal([0.0, 0.0], @mod.nxy_to_latlng(0.0, 0.0))
    assert_equal(
      [@mod.ny_to_lat(+12.0), @mod.nx_to_lng(+12.0)],
      @mod.nxy_to_latlng(+12.0, +12.0))
    assert_equal(
      [@mod.ny_to_lat(-12.0), @mod.nx_to_lng(-12.0)],
      @mod.nxy_to_latlng(-12.0, -12.0))
  end

  def test_rush__latlng_nxy
    1000.times {
      lat1, lng1 = [(rand * 180.0) - 90.0, (rand * 360.0) - 180.0]
      nx, ny     = @mod.latlng_to_nxy(lat1, lng1)
      lat2, lng2 = @mod.nxy_to_latlng(nx, ny)
      assert_in_delta(lat1, lat2, 1.0E-10)
      assert_in_delta(lng1, lng2, 1.0E-13)
    }
  end
end
