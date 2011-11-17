# encoding: utf-8

require "test/unit"
require_relative "../lib/geodelta/hex_region"

class GeoDeltaHexRegionTest < Test::Unit::TestCase
  def setup
    @mod = GeoDelta::HexRegion
  end

  def test_get_base_delta_ids_in_region__level1
    assert_equal([[0]], @mod.get_base_delta_ids_in_region(+0.0, +8.0, +0.0, +8.0, 1))
    assert_equal([[0]], @mod.get_base_delta_ids_in_region(+6.0, +4.0, +6.0, +4.0, 1))

    assert_equal([[0]], @mod.get_base_delta_ids_in_region(+1.0, +6.0, +5.0, +6.0, 1))
    assert_equal([[0]], @mod.get_base_delta_ids_in_region(+1.0, -6.0, +5.0, -6.0, 1))
    assert_equal([[0]], @mod.get_base_delta_ids_in_region(+0.0, +5.0, +0.0, -5.0, 1))
    assert_equal([[0]], @mod.get_base_delta_ids_in_region(+6.0, +5.0, +6.0, -5.0, 1))

    assert_equal([[0]], @mod.get_base_delta_ids_in_region(+1.0, +6.0, +11.0, +6.0, 1))
    assert_equal([[0]], @mod.get_base_delta_ids_in_region(+1.0, -6.0, +11.0, -6.0, 1))

    assert_equal([[0]], @mod.get_base_delta_ids_in_region(+1.0, +5.0, +5.0, -5.0, 1))
  end

  def test_get_base_delta_ids_in_region__level1_roundtrip
    assert_equal([[0]], @mod.get_base_delta_ids_in_region(-6.0, +6.0, +6.0, +6.0, 1))
    assert_equal([[0]], @mod.get_base_delta_ids_in_region(+12.0, +6.0, -6.0, +6.0, 1))
  end

  def test_get_base_delta_ids_in_region__level2
    assert_equal([[0, 1]], @mod.get_base_delta_ids_in_region(-1.0, +4.0, +1.0, -4.0, 2))
    assert_equal([[0, 1]], @mod.get_base_delta_ids_in_region(-4.0, +1.0, +4.0, -1.0, 2))
    assert_equal([[0, 1]], @mod.get_base_delta_ids_in_region(-3.0, +9.0, +3.0, -9.0, 2))
  end

  def test_get_base_delta_ids_in_region__level3
    assert_equal([[0, 1, 1]], @mod.get_base_delta_ids_in_region(-1.5, +1.5, +1.5, -1.5, 3))
  end
end
