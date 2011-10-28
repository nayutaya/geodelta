# encoding: utf-8

require "test/unit"
require_relative "../lib/geodelta/hex"

class GeoDeltaHexTest < Test::Unit::TestCase
  def setup
    @mod = GeoDelta::Hex
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
    p :center
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
    p :upper
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
    p :upper_right
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
    p :lower_right
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
    p :lower
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
    p :lower_left
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
    p :upper_left
    assert_equal(
      expected,
      expected.map { |pos, ids| [@mod.get_hex_position(ids), ids] })
  end
end
