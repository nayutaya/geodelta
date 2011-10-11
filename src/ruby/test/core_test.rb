# encoding: utf-8

require "test/unit"
require File.expand_path(File.join(File.dirname(__FILE__), "..", "lib", "geodelta", "core"))

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
end
