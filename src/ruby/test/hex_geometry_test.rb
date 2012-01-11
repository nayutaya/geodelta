# encoding: utf-8

require "test/unit"
require_relative "../lib/geodelta/hex_geometry"
require_relative "../lib/geodelta/id_util"

class GeoDeltaHexGeometryTest < Test::Unit::TestCase
  def setup
    @mod = GeoDelta::HexGeometry
  end

  def test_get_hex_position__level3_center
    expected = [
      [0, [0, 1, 1]],
      [1, [1, 3, 3]],
      [2, [5, 2, 2]],
      [3, [4, 1, 1]],
      [4, [7, 3, 3]],
      [5, [3, 2, 2]],
    ]
    assert_equal(
      expected,
      expected.map { |pos, ids| [@mod.get_hex_position(ids), ids] })
  end

  def test_get_hex_position__level3_upper
    expected = [
      [0, [0, 0, 0]],
      [1, [0, 0, 2]],
      [2, [0, 1, 3]],
      [3, [0, 1, 0]],
      [4, [0, 1, 2]],
      [5, [0, 0, 3]],
    ]
    assert_equal(
      expected,
      expected.map { |pos, ids| [@mod.get_hex_position(ids), ids] })
  end

  def test_get_hex_position__level3_upper_right
    expected = [
      [0, [1, 0, 2]],
      [1, [1, 0, 0]],
      [2, [1, 0, 1]],
      [3, [1, 3, 2]],
      [4, [1, 3, 0]],
      [5, [1, 3, 1]],
    ]
    assert_equal(
      expected,
      expected.map { |pos, ids| [@mod.get_hex_position(ids), ids] })
  end

  def test_get_hex_position__level3_lower_right
    expected = [
      [0, [5, 2, 3]],
      [1, [5, 0, 1]],
      [2, [5, 0, 0]],
      [3, [5, 0, 3]],
      [4, [5, 2, 1]],
      [5, [5, 2, 0]],
    ]
    assert_equal(
      expected,
      expected.map { |pos, ids| [@mod.get_hex_position(ids), ids] })
  end

  def test_get_hex_position__level3_lower
    expected = [
      [0, [4, 1, 0]],
      [1, [4, 1, 2]],
      [2, [4, 0, 3]],
      [3, [4, 0, 0]],
      [4, [4, 0, 2]],
      [5, [4, 1, 3]],
    ]
    assert_equal(
      expected,
      expected.map { |pos, ids| [@mod.get_hex_position(ids), ids] })
  end

  def test_get_hex_position__level3_lower_left
    expected = [
      [0, [7, 3, 2]],
      [1, [7, 3, 0]],
      [2, [7, 3, 1]],
      [3, [7, 0, 2]],
      [4, [7, 0, 0]],
      [5, [7, 0, 1]],
    ]
    assert_equal(
      expected,
      expected.map { |pos, ids| [@mod.get_hex_position(ids), ids] })
  end

  def test_get_hex_position__level3_upper_left
    expected = [
      [0, [3, 0, 3]],
      [1, [3, 2, 1]],
      [2, [3, 2, 0]],
      [3, [3, 2, 3]],
      [4, [3, 0, 1]],
      [5, [3, 0, 0]],
    ]
    assert_equal(
      expected,
      expected.map { |pos, ids| [@mod.get_hex_position(ids), ids] })
  end

  def test_get_base_delta_ids__level3_center
    expected = [
      [[0, 1, 1], [0, 1, 1]],
      [[0, 1, 1], [1, 3, 3]],
      [[0, 1, 1], [5, 2, 2]],
      [[0, 1, 1], [4, 1, 1]],
      [[0, 1, 1], [7, 3, 3]],
      [[0, 1, 1], [3, 2, 2]],
    ]
    assert_equal(
      expected,
      expected.map { |pos, ids| [@mod.get_base_delta_ids(ids), ids] })
  end

  def test_get_base_delta_ids__level3_upper
    expected = [
      [[0, 0, 0], [0, 0, 0]],
      [[0, 0, 0], [0, 0, 2]],
      [[0, 0, 0], [0, 1, 3]],
      [[0, 0, 0], [0, 1, 0]],
      [[0, 0, 0], [0, 1, 2]],
      [[0, 0, 0], [0, 0, 3]],
    ]
    assert_equal(
      expected,
      expected.map { |pos, ids| [@mod.get_base_delta_ids(ids), ids] })
  end

  def test_get_base_delta_ids__level3_upper_right
    expected = [
      [[1, 0, 2], [1, 0, 2]],
      [[1, 0, 2], [1, 0, 0]],
      [[1, 0, 2], [1, 0, 1]],
      [[1, 0, 2], [1, 3, 2]],
      [[1, 0, 2], [1, 3, 0]],
      [[1, 0, 2], [1, 3, 1]],
    ]
    assert_equal(
      expected,
      expected.map { |pos, ids| [@mod.get_base_delta_ids(ids), ids] })
  end

  def test_get_base_delta_ids__level3_center_top
    expected = [
      [nil, [0, 2, 3]],
      [nil, [0, 0, 1]],
      [nil, [0, 3, 2]],
    ]
    assert_equal(
      expected,
      expected.map { |pos, ids| [@mod.get_base_delta_ids(ids), ids] })
  end

  def test_get_base_delta_ids__level3_left_top
    expected = [
      [nil, [2, 3, 2]],
      [nil, [2, 3, 0]],
      [nil, [2, 3, 3]],
    ]
    assert_equal(
      expected,
      expected.map { |pos, ids| [@mod.get_base_delta_ids(ids), ids] })
  end

  def test_get_base_delta_ids__level3_right_top
    expected = [
      [nil, [2, 2, 2]],
      [nil, [2, 2, 0]],
      [nil, [2, 2, 3]],
    ]
    assert_equal(
      expected,
      expected.map { |pos, ids| [@mod.get_base_delta_ids(ids), ids] })
  end

  def test_get_base_delta_ids__level3_roundtrip
    assert_equal(nil, @mod.get_base_delta_ids([2, 0, 1]))
    assert_equal(nil, @mod.get_base_delta_ids([2, 0, 0]))
    assert_equal(nil, @mod.get_base_delta_ids([2, 1, 0]))
    assert_equal(nil, @mod.get_base_delta_ids([2, 1, 1]))
    assert_equal(nil, @mod.get_base_delta_ids([6, 1, 1]))
    assert_equal(nil, @mod.get_base_delta_ids([6, 1, 0]))
    assert_equal(nil, @mod.get_base_delta_ids([6, 0, 0]))
    assert_equal(nil, @mod.get_base_delta_ids([6, 0, 0]))
  end

  def test_get_base_delta_ids__level4_center
    expected = [
      [[0, 1, 1, 1], [0, 1, 1, 1]],
      [[0, 1, 1, 1], [1, 3, 3, 3]],
      [[0, 1, 1, 1], [5, 2, 2, 2]],
      [[0, 1, 1, 1], [4, 1, 1, 1]],
      [[0, 1, 1, 1], [7, 3, 3, 3]],
      [[0, 1, 1, 1], [3, 2, 2, 2]],
    ]
    assert_equal(
      expected,
      expected.map { |pos, ids| [@mod.get_base_delta_ids(ids), ids] })
  end

  def test_get_base_delta_ids__all_hexes_level1
    all_ids   = GeoDelta::IdUtil.get_all_delta_ids(1)
    all_hexes = all_ids.map { |ids| @mod.get_base_delta_ids(ids) }.compact.sort.uniq
    assert_equal([[0]], all_hexes)
  end

  def test_get_base_delta_ids__all_hexes_level2
    all_ids   = GeoDelta::IdUtil.get_all_delta_ids(2)
    all_hexes = all_ids.map { |ids| @mod.get_base_delta_ids(ids) }.compact.sort.uniq
    expected = [
      [0, 1],
      [2, 2],
      [5, 3],
    ]
    assert_equal(expected, all_hexes)
  end

  def test_get_base_delta_ids__all_hexes_level3
    all_ids   = GeoDelta::IdUtil.get_all_delta_ids(3)
    all_hexes = all_ids.map { |ids| @mod.get_base_delta_ids(ids) }.compact.sort.uniq
    expected = [
      [0, 0, 0],
      [0, 1, 1],
      [0, 2, 2],
      [0, 3, 3],
      [1, 0, 2],
      [1, 2, 0],
      [2, 2, 1],
      [2, 3, 1],
      [3, 0, 3],
      [3, 3, 0],
      [4, 1, 0],
      [5, 1, 2],
      [5, 2, 3],
      [5, 3, 1],
      [7, 1, 3],
      [7, 2, 1],
      [7, 3, 2],
    ]
    assert_equal(expected, all_hexes)
  end

  def test_get_part_delta_ids__level1
    assert_equal(
      [[0], [1], [5], [4], [7], [3]],
      @mod.get_part_delta_ids([0]))
  end

  def test_get_part_delta_ids__level3
    expected = [
      [0, 1, 1],
      [1, 3, 3],
      [5, 2, 2],
      [4, 1, 1],
      [7, 3, 3],
      [3, 2, 2],
    ]
    assert_equal(expected, @mod.get_part_delta_ids([0, 1, 1]))

    expected = [
      [0, 0, 0],
      [0, 0, 2],
      [0, 1, 3],
      [0, 1, 0],
      [0, 1, 2],
      [0, 0, 3],
    ]
    assert_equal(expected, @mod.get_part_delta_ids([0, 0, 0]))

    expected = [
      [5, 2, 3],
      [5, 0, 1],
      [5, 0, 0],
      [5, 0, 3],
      [5, 2, 1],
      [5, 2, 0],
    ]
    assert_equal(expected, @mod.get_part_delta_ids([5, 2, 3]))
  end

  def test_get_coordinates__level3_1
    expected = [
      [+0.0, +0.0],
      [+1.5, +3.0],
      [+3.0, +0.0],
      [+1.5, -3.0],
      [-1.5, -3.0],
      [-3.0, +0.0],
      [-1.5, +3.0],
    ]
    assert_equal(expected, @mod.get_coordinates([0, 1, 1]))
  end

  def test_get_coordinates__level3_2
    expected = [
      [ -9.0, -6.0],
      [ -7.5, -3.0],
      [ -6.0, -6.0],
      [ -7.5, -9.0],
      [-10.5, -9.0],
      [-12.0, -6.0],
      [-10.5, -3.0],
    ]
    assert_equal(expected, @mod.get_coordinates([7, 2, 1]))
  end

  def test_get_coordinates__level3_3
    assert_equal(nil, @mod.get_coordinates([6, 2, 0]))
    assert_equal(nil, @mod.get_coordinates([4, 0, 1]))
    assert_equal(nil, @mod.get_coordinates([6, 3, 0]))
  end
end
