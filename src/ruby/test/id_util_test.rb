# encoding: utf-8

require "test/unit"
require_relative "../lib/geodelta/id_util"

class GeoDeltaIdUtilTest < Test::Unit::TestCase
  def setup
    @mod = GeoDelta::IdUtil
  end

  def test_get_all_delta_ids__level1
    assert_equal(
      [[0], [1], [2], [3], [4], [5], [6], [7]],
      @mod.get_all_delta_ids(1))
  end

  def test_get_all_delta_ids__level2
    expected = [
      [0, 0], [0, 1], [0, 2], [0, 3],
      [1, 0], [1, 1], [1, 2], [1, 3],
      [2, 0], [2, 1], [2, 2], [2, 3],
      [3, 0], [3, 1], [3, 2], [3, 3],
      [4, 0], [4, 1], [4, 2], [4, 3],
      [5, 0], [5, 1], [5, 2], [5, 3],
      [6, 0], [6, 1], [6, 2], [6, 3],
      [7, 0], [7, 1], [7, 2], [7, 3],
    ]
    assert_equal(expected, @mod.get_all_delta_ids(2))
  end

  def test_get_all_delta_ids__size
    assert_equal(  8, @mod.get_all_delta_ids(1).size)
    assert_equal( 32, @mod.get_all_delta_ids(2).size)
    assert_equal(128, @mod.get_all_delta_ids(3).size)
    assert_equal(512, @mod.get_all_delta_ids(4).size)
  end

  def test_get_all_delta_ids__order
    assert_equal(
      @mod.get_all_delta_ids(1).sort.uniq,
      @mod.get_all_delta_ids(1))
    assert_equal(
      @mod.get_all_delta_ids(2).sort.uniq,
      @mod.get_all_delta_ids(2))
    assert_equal(
      @mod.get_all_delta_ids(3).sort.uniq,
      @mod.get_all_delta_ids(3))
  end
end
