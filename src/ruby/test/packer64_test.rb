# encoding: utf-8

require "test/unit"
require_relative "../lib/geodelta/packer64"

class GeoDeltaPacker64Test < Test::Unit::TestCase
  def setup
    @packer = GeoDelta::Packer64.new
  end

  def test_pack_world_delta
    ############ 0bXXWwwSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsLllll
    assert_equal(0b0000000000000000000000000000000000000000000000000000000000000000, @packer.pack_world_delta(0))
    assert_equal(0b0000100000000000000000000000000000000000000000000000000000000000, @packer.pack_world_delta(1))
    assert_equal(0b0001000000000000000000000000000000000000000000000000000000000000, @packer.pack_world_delta(2))
    assert_equal(0b0001100000000000000000000000000000000000000000000000000000000000, @packer.pack_world_delta(3))
    assert_equal(0b0010000000000000000000000000000000000000000000000000000000000000, @packer.pack_world_delta(4))
    assert_equal(0b0010100000000000000000000000000000000000000000000000000000000000, @packer.pack_world_delta(5))
    assert_equal(0b0011000000000000000000000000000000000000000000000000000000000000, @packer.pack_world_delta(6))
    assert_equal(0b0011100000000000000000000000000000000000000000000000000000000000, @packer.pack_world_delta(7))
  end

  def test_unpack_world_delta
    ########################################## 0bXXWwwSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsLllll
    assert_equal(0, @packer.unpack_world_delta(0b0000000000000000000000000000000000000000000000000000000000000000))
    assert_equal(1, @packer.unpack_world_delta(0b0000100000000000000000000000000000000000000000000000000000000000))
    assert_equal(2, @packer.unpack_world_delta(0b0001000000000000000000000000000000000000000000000000000000000000))
    assert_equal(3, @packer.unpack_world_delta(0b0001100000000000000000000000000000000000000000000000000000000000))
    assert_equal(4, @packer.unpack_world_delta(0b0010000000000000000000000000000000000000000000000000000000000000))
    assert_equal(5, @packer.unpack_world_delta(0b0010100000000000000000000000000000000000000000000000000000000000))
    assert_equal(6, @packer.unpack_world_delta(0b0011000000000000000000000000000000000000000000000000000000000000))
    assert_equal(7, @packer.unpack_world_delta(0b0011100000000000000000000000000000000000000000000000000000000000))
  end

  def test_pack_sub_delta
    ############ 0bXXWwwSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsLllll
    assert_equal(0b0000000000000000000000000000000000000000000000000000000000000000, @packer.pack_sub_delta( 2, 0))
    assert_equal(0b0000001000000000000000000000000000000000000000000000000000000000, @packer.pack_sub_delta( 2, 1))
    assert_equal(0b0000010000000000000000000000000000000000000000000000000000000000, @packer.pack_sub_delta( 2, 2))
    assert_equal(0b0000011000000000000000000000000000000000000000000000000000000000, @packer.pack_sub_delta( 2, 3))
    assert_equal(0b0000000000000000000000000000000000000000000000000000000000000000, @packer.pack_sub_delta( 3, 0))
    assert_equal(0b0000000010000000000000000000000000000000000000000000000000000000, @packer.pack_sub_delta( 3, 1))
    assert_equal(0b0000000100000000000000000000000000000000000000000000000000000000, @packer.pack_sub_delta( 3, 2))
    assert_equal(0b0000000110000000000000000000000000000000000000000000000000000000, @packer.pack_sub_delta( 3, 3))

    ############ 0bXXWwwSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsLllll
    assert_equal(0b0000000001100000000000000000000000000000000000000000000000000000, @packer.pack_sub_delta( 4, 3))
    assert_equal(0b0000000000011000000000000000000000000000000000000000000000000000, @packer.pack_sub_delta( 5, 3))
    assert_equal(0b0000000000000110000000000000000000000000000000000000000000000000, @packer.pack_sub_delta( 6, 3))
    assert_equal(0b0000000000000001100000000000000000000000000000000000000000000000, @packer.pack_sub_delta( 7, 3))
    assert_equal(0b0000000000000000011000000000000000000000000000000000000000000000, @packer.pack_sub_delta( 8, 3))
    assert_equal(0b0000000000000000000110000000000000000000000000000000000000000000, @packer.pack_sub_delta( 9, 3))
    assert_equal(0b0000000000000000000001100000000000000000000000000000000000000000, @packer.pack_sub_delta(10, 3))
    assert_equal(0b0000000000000000000000011000000000000000000000000000000000000000, @packer.pack_sub_delta(11, 3))
    assert_equal(0b0000000000000000000000000110000000000000000000000000000000000000, @packer.pack_sub_delta(12, 3))
    assert_equal(0b0000000000000000000000000001100000000000000000000000000000000000, @packer.pack_sub_delta(13, 3))
    assert_equal(0b0000000000000000000000000000011000000000000000000000000000000000, @packer.pack_sub_delta(14, 3))
    assert_equal(0b0000000000000000000000000000000110000000000000000000000000000000, @packer.pack_sub_delta(15, 3))
    assert_equal(0b0000000000000000000000000000000001100000000000000000000000000000, @packer.pack_sub_delta(16, 3))
    assert_equal(0b0000000000000000000000000000000000011000000000000000000000000000, @packer.pack_sub_delta(17, 3))
    assert_equal(0b0000000000000000000000000000000000000110000000000000000000000000, @packer.pack_sub_delta(18, 3))
    assert_equal(0b0000000000000000000000000000000000000001100000000000000000000000, @packer.pack_sub_delta(19, 3))
    assert_equal(0b0000000000000000000000000000000000000000011000000000000000000000, @packer.pack_sub_delta(20, 3))
    assert_equal(0b0000000000000000000000000000000000000000000110000000000000000000, @packer.pack_sub_delta(21, 3))
    assert_equal(0b0000000000000000000000000000000000000000000001100000000000000000, @packer.pack_sub_delta(22, 3))
    assert_equal(0b0000000000000000000000000000000000000000000000011000000000000000, @packer.pack_sub_delta(23, 3))
    assert_equal(0b0000000000000000000000000000000000000000000000000110000000000000, @packer.pack_sub_delta(24, 3))
    assert_equal(0b0000000000000000000000000000000000000000000000000001100000000000, @packer.pack_sub_delta(25, 3))
    assert_equal(0b0000000000000000000000000000000000000000000000000000011000000000, @packer.pack_sub_delta(26, 3))
    assert_equal(0b0000000000000000000000000000000000000000000000000000000110000000, @packer.pack_sub_delta(27, 3))
    assert_equal(0b0000000000000000000000000000000000000000000000000000000001100000, @packer.pack_sub_delta(28, 3))
  end

  def test_unpack_sub_delta64
    ############################################ 0bXXWwwSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsLllll
    assert_equal(0, @packer.unpack_sub_delta( 2, 0b0000000000000000000000000000000000000000000000000000000000000000))
    assert_equal(1, @packer.unpack_sub_delta( 2, 0b0000001000000000000000000000000000000000000000000000000000000000))
    assert_equal(2, @packer.unpack_sub_delta( 2, 0b0000010000000000000000000000000000000000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta( 2, 0b0000011000000000000000000000000000000000000000000000000000000000))
    assert_equal(0, @packer.unpack_sub_delta( 3, 0b0000000000000000000000000000000000000000000000000000000000000000))
    assert_equal(1, @packer.unpack_sub_delta( 3, 0b0000000010000000000000000000000000000000000000000000000000000000))
    assert_equal(2, @packer.unpack_sub_delta( 3, 0b0000000100000000000000000000000000000000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta( 3, 0b0000000110000000000000000000000000000000000000000000000000000000))

    ############################################ 0bXXWwwSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsLllll
    assert_equal(3, @packer.unpack_sub_delta( 4, 0b0000000001100000000000000000000000000000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta( 5, 0b0000000000011000000000000000000000000000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta( 6, 0b0000000000000110000000000000000000000000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta( 7, 0b0000000000000001100000000000000000000000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta( 8, 0b0000000000000000011000000000000000000000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta( 9, 0b0000000000000000000110000000000000000000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta(10, 0b0000000000000000000001100000000000000000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta(11, 0b0000000000000000000000011000000000000000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta(12, 0b0000000000000000000000000110000000000000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta(13, 0b0000000000000000000000000001100000000000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta(14, 0b0000000000000000000000000000011000000000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta(15, 0b0000000000000000000000000000000110000000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta(16, 0b0000000000000000000000000000000001100000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta(17, 0b0000000000000000000000000000000000011000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta(18, 0b0000000000000000000000000000000000000110000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta(19, 0b0000000000000000000000000000000000000001100000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta(20, 0b0000000000000000000000000000000000000000011000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta(21, 0b0000000000000000000000000000000000000000000110000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta(22, 0b0000000000000000000000000000000000000000000001100000000000000000))
    assert_equal(3, @packer.unpack_sub_delta(23, 0b0000000000000000000000000000000000000000000000011000000000000000))
    assert_equal(3, @packer.unpack_sub_delta(24, 0b0000000000000000000000000000000000000000000000000110000000000000))
    assert_equal(3, @packer.unpack_sub_delta(25, 0b0000000000000000000000000000000000000000000000000001100000000000))
    assert_equal(3, @packer.unpack_sub_delta(26, 0b0000000000000000000000000000000000000000000000000000011000000000))
    assert_equal(3, @packer.unpack_sub_delta(27, 0b0000000000000000000000000000000000000000000000000000000110000000))
    assert_equal(3, @packer.unpack_sub_delta(28, 0b0000000000000000000000000000000000000000000000000000000001100000))
  end

  def test_pack_level
    ############ 0bXXWwwSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsLllll
    assert_equal(0b0000000000000000000000000000000000000000000000000000000000000001, @packer.pack_level( 1))
    assert_equal(0b0000000000000000000000000000000000000000000000000000000000000010, @packer.pack_level( 2))
    assert_equal(0b0000000000000000000000000000000000000000000000000000000000011011, @packer.pack_level(27))
    assert_equal(0b0000000000000000000000000000000000000000000000000000000000011100, @packer.pack_level(28))
  end

  def test_unpack_level
    ##################################### 0bXXWwwSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsLllll
    assert_equal( 1, @packer.unpack_level(0b0000000000000000000000000000000000000000000000000000000000000001))
    assert_equal( 2, @packer.unpack_level(0b0000000000000000000000000000000000000000000000000000000000000010))
    assert_equal(27, @packer.unpack_level(0b0000000000000000000000000000000000000000000000000000000000011011))
    assert_equal(28, @packer.unpack_level(0b0000000000000000000000000000000000000000000000000000000000011100))
  end

  def test_pack
    ############ 0bXXWwwSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsLllll
    assert_equal(0b0000000000000000000000000000000000000000000000000000000000000001, @packer.pack([0]))
    assert_equal(0b0011100000000000000000000000000000000000000000000000000000000001, @packer.pack([7]))
    assert_equal(0b0000001000000000000000000000000000000000000000000000000000000010, @packer.pack([0, 1]))
    assert_equal(0b0001011000000000000000000000000000000000000000000000000000000010, @packer.pack([2, 3]))
    assert_equal(0b0000000000000000000000000000000000000000000000000000000000011100, @packer.pack([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]))
    assert_equal(0b0000101010101010101010101010101010101010101010101010101010111100, @packer.pack([1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]))
    assert_equal(0b0011111111111111111111111111111111111111111111111111111111111100, @packer.pack([7, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3]))
  end

  def test_unpack
    ############### 0bXXWwwSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsSsLllll
    assert_equal(
      [0],
      @packer.unpack(0b0000000000000000000000000000000000000000000000000000000000000001))
    assert_equal(
      [7],
      @packer.unpack(0b0011100000000000000000000000000000000000000000000000000000000001))
    assert_equal(
      [0, 1],
      @packer.unpack(0b0000001000000000000000000000000000000000000000000000000000000010))
    assert_equal(
      [2, 3],
      @packer.unpack(0b0001011000000000000000000000000000000000000000000000000000000010))
    assert_equal(
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      @packer.unpack(0b0000000000000000000000000000000000000000000000000000000000011100))
    assert_equal(
      [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
      @packer.unpack(0b0000101010101010101010101010101010101010101010101010101010111100))
    assert_equal(
      [7, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3],
      @packer.unpack(0b0011111111111111111111111111111111111111111111111111111111111100))
  end

  def test_pack_and_unpack
    1000.times {
      ids1   = [rand(8)] + ((28 - 1).times.map { rand(4) })[0, rand(28)]
      packed = @packer.pack(ids1)
      ids2   = @packer.unpack(packed)
      assert_equal(ids1, ids2)
    }
  end
end