# encoding: utf-8

require "test/unit"
require_relative "../lib/geodelta/region"

class GeoDeltaRegionTest < Test::Unit::TestCase
  def setup
    @mod = GeoDelta::Region
  end

  def test_get_delta_ids_in_region__level1
    assert_equal([[0]], @mod.get_delta_ids_in_region(+0.0, +8.0, +0.0, +8.0, 1))
    assert_equal([[1]], @mod.get_delta_ids_in_region(+6.0, +4.0, +6.0, +4.0, 1))

    assert_equal([[0], [1]], @mod.get_delta_ids_in_region(+1.0, +6.0, +5.0, +6.0, 1))
    assert_equal([[4], [5]], @mod.get_delta_ids_in_region(+1.0, -6.0, +5.0, -6.0, 1))
    assert_equal([[0], [4]], @mod.get_delta_ids_in_region(+0.0, +5.0, +0.0, -5.0, 1))
    assert_equal([[1], [5]], @mod.get_delta_ids_in_region(+6.0, +5.0, +6.0, -5.0, 1))

    assert_equal([[0], [1], [2]], @mod.get_delta_ids_in_region(+1.0, +6.0, +11.0, +6.0, 1))
    assert_equal([[4], [5], [6]], @mod.get_delta_ids_in_region(+1.0, -6.0, +11.0, -6.0, 1))

    assert_equal(
      [[0], [1], [4], [5]],
      @mod.get_delta_ids_in_region(+1.0, +5.0, +5.0, -5.0, 1))
  end

  def test_get_delta_ids_in_region__level2
    expected = [
      [0, 1],
      [1, 3],
      [3, 2],
      [4, 1],
      [5, 2],
      [7, 3],
    ]
    assert_equal(expected, @mod.get_delta_ids_in_region(-1.0, +4.0, +1.0, -4.0, 2))

    expected = [
      [0, 1],
      [1, 3],
      [3, 2],
      [4, 1],
      [5, 2],
      [7, 3],
    ]
    assert_equal(expected, @mod.get_delta_ids_in_region(-4.0, +1.0, +4.0, -1.0, 2))

    expected = [
      [0, 0],
      [0, 1],
      [0, 2],
      [0, 3],
      [1, 3],
      [3, 2],
      [4, 0],
      [4, 1],
      [4, 2],
      [4, 3],
      [5, 2],
      [7, 3],
    ]
    assert_equal(expected, @mod.get_delta_ids_in_region(-3.0, +9.0, +3.0, -9.0, 2))
  end

  def test_get_delta_ids_in_region__level3
    expected = [
      [0, 1, 1],
      [1, 3, 3],
      [3, 2, 2],
      [4, 1, 1],
      [5, 2, 2],
      [7, 3, 3],
    ]
    assert_equal(expected, @mod.get_delta_ids_in_region(-1.5, +1.5, +1.5, -1.5, 3))
  end
end
