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

  def test_get_delta_ids_in_region__level1_roundtrip
    assert_equal(
      [[0], [1], [3]],
      @mod.get_delta_ids_in_region(-6.0, +6.0, +6.0, +6.0, 1))
    assert_equal(
      [[0], [2], [3]],
      @mod.get_delta_ids_in_region(+12.0, +6.0, -6.0, +6.0, 1))
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

    expected = [
      [2, 1, 1],
      [3, 3, 0],
      [3, 3, 3],
      [6, 1, 1],
      [7, 2, 0],
      [7, 2, 2],
    ]
    assert_equal(expected, @mod.get_delta_ids_in_region(-11.5, +1.5, -9.5, -1.5, 3))
  end

  def test_get_delta_ids_in_region__random
    10.times {
      (3..5).each { |level|
        x1, x2 = [(rand * 24) - 12, (rand * 24) - 12].sort
        y1, y2 = [(rand * 24) - 12, (rand * 24) - 12].sort.reverse
        regional_ids = GeoDelta::Region.get_delta_ids_in_region(x1, y1, x2, y2, level)
        10.times {
          cx, cy = [x1 + (rand * (x2 - x1)), y1 - (rand * (y1 - y2))]
          test_ids = GeoDelta::DeltaGeometry.get_delta_ids(cx, cy, level)
          assert_equal(
            {:rect => [[x1, y1], [x2, y2]], :test_ids => test_ids, :include => true},
            {:rect => [[x1, y1], [x2, y2]], :test_ids => test_ids, :include => regional_ids.include?(test_ids)})
        }
      }
    }
  end
end
