# encoding: utf-8

require "test/unit"
require_relative "../lib/geodelta/packer32"

class GeoDeltaPacker32Test < Test::Unit::TestCase
  def setup
    @packer = GeoDelta::Packer32.new
  end

  def test_pack_world_delta
    ############ 0bXWwwSsSsSsSsSsSsSsSsSsSsSsSsLlll
    assert_equal(0b00000000000000000000000000000000, @packer.pack_world_delta(0))
    assert_equal(0b00010000000000000000000000000000, @packer.pack_world_delta(1))
    assert_equal(0b00100000000000000000000000000000, @packer.pack_world_delta(2))
    assert_equal(0b00110000000000000000000000000000, @packer.pack_world_delta(3))
    assert_equal(0b01000000000000000000000000000000, @packer.pack_world_delta(4))
    assert_equal(0b01010000000000000000000000000000, @packer.pack_world_delta(5))
    assert_equal(0b01100000000000000000000000000000, @packer.pack_world_delta(6))
    assert_equal(0b01110000000000000000000000000000, @packer.pack_world_delta(7))
  end

  def test_unpack_world_delta
    ########################################## 0bXWwwSsSsSsSsSsSsSsSsSsSsSsSsLlll
    assert_equal(0, @packer.unpack_world_delta(0b00000000000000000000000000000000))
    assert_equal(1, @packer.unpack_world_delta(0b00010000000000000000000000000000))
    assert_equal(2, @packer.unpack_world_delta(0b00100000000000000000000000000000))
    assert_equal(3, @packer.unpack_world_delta(0b00110000000000000000000000000000))
    assert_equal(4, @packer.unpack_world_delta(0b01000000000000000000000000000000))
    assert_equal(5, @packer.unpack_world_delta(0b01010000000000000000000000000000))
    assert_equal(6, @packer.unpack_world_delta(0b01100000000000000000000000000000))
    assert_equal(7, @packer.unpack_world_delta(0b01110000000000000000000000000000))
  end

  def test_pack_sub_delta
    ############ 0bXWwwSsSsSsSsSsSsSsSsSsSsSsSsLlll
    assert_equal(0b00000000000000000000000000000000, @packer.pack_sub_delta( 2, 0))
    assert_equal(0b00000100000000000000000000000000, @packer.pack_sub_delta( 2, 1))
    assert_equal(0b00001000000000000000000000000000, @packer.pack_sub_delta( 2, 2))
    assert_equal(0b00001100000000000000000000000000, @packer.pack_sub_delta( 2, 3))
    assert_equal(0b00000000000000000000000000000000, @packer.pack_sub_delta( 3, 0))
    assert_equal(0b00000001000000000000000000000000, @packer.pack_sub_delta( 3, 1))
    assert_equal(0b00000010000000000000000000000000, @packer.pack_sub_delta( 3, 2))
    assert_equal(0b00000011000000000000000000000000, @packer.pack_sub_delta( 3, 3))

    ############ 0bXWwwSsSsSsSsSsSsSsSsSsSsSsSsLlll
    assert_equal(0b00000000110000000000000000000000, @packer.pack_sub_delta( 4, 3))
    assert_equal(0b00000000001100000000000000000000, @packer.pack_sub_delta( 5, 3))
    assert_equal(0b00000000000011000000000000000000, @packer.pack_sub_delta( 6, 3))
    assert_equal(0b00000000000000110000000000000000, @packer.pack_sub_delta( 7, 3))
    assert_equal(0b00000000000000001100000000000000, @packer.pack_sub_delta( 8, 3))
    assert_equal(0b00000000000000000011000000000000, @packer.pack_sub_delta( 9, 3))
    assert_equal(0b00000000000000000000110000000000, @packer.pack_sub_delta(10, 3))
    assert_equal(0b00000000000000000000001100000000, @packer.pack_sub_delta(11, 3))
    assert_equal(0b00000000000000000000000011000000, @packer.pack_sub_delta(12, 3))
    assert_equal(0b00000000000000000000000000110000, @packer.pack_sub_delta(13, 3))
  end

  def test_unpack_sub_delta
    ############################################ 0bXWwwSsSsSsSsSsSsSsSsSsSsSsSsLlll
    assert_equal(0, @packer.unpack_sub_delta( 2, 0b00000000000000000000000000000000))
    assert_equal(1, @packer.unpack_sub_delta( 2, 0b00000100000000000000000000000000))
    assert_equal(2, @packer.unpack_sub_delta( 2, 0b00001000000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta( 2, 0b00001100000000000000000000000000))
    assert_equal(0, @packer.unpack_sub_delta( 3, 0b00000000000000000000000000000000))
    assert_equal(1, @packer.unpack_sub_delta( 3, 0b00000001000000000000000000000000))
    assert_equal(2, @packer.unpack_sub_delta( 3, 0b00000010000000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta( 3, 0b00000011000000000000000000000000))

    ############################################ 0bXWwwSsSsSsSsSsSsSsSsSsSsSsSsLlll
    assert_equal(3, @packer.unpack_sub_delta( 4, 0b00000000110000000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta( 5, 0b00000000001100000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta( 6, 0b00000000000011000000000000000000))
    assert_equal(3, @packer.unpack_sub_delta( 7, 0b00000000000000110000000000000000))
    assert_equal(3, @packer.unpack_sub_delta( 8, 0b00000000000000001100000000000000))
    assert_equal(3, @packer.unpack_sub_delta( 9, 0b00000000000000000011000000000000))
    assert_equal(3, @packer.unpack_sub_delta(10, 0b00000000000000000000110000000000))
    assert_equal(3, @packer.unpack_sub_delta(11, 0b00000000000000000000001100000000))
    assert_equal(3, @packer.unpack_sub_delta(12, 0b00000000000000000000000011000000))
    assert_equal(3, @packer.unpack_sub_delta(13, 0b00000000000000000000000000110000))
  end

  def test_pack_level
    ############ 0bXWwwSsSsSsSsSsSsSsSsSsSsSsSsLlll
    assert_equal(0b00000000000000000000000000000001, @packer.pack_level( 1))
    assert_equal(0b00000000000000000000000000000010, @packer.pack_level( 2))
    assert_equal(0b00000000000000000000000000001100, @packer.pack_level(12))
    assert_equal(0b00000000000000000000000000001101, @packer.pack_level(13))
  end

  def test_unpack_level
    ##################################### 0bXWwwSsSsSsSsSsSsSsSsSsSsSsSsLlll
    assert_equal( 1, @packer.unpack_level(0b00000000000000000000000000000001))
    assert_equal( 2, @packer.unpack_level(0b00000000000000000000000000000010))
    assert_equal(12, @packer.unpack_level(0b00000000000000000000000000001100))
    assert_equal(13, @packer.unpack_level(0b00000000000000000000000000001101))
  end

  def test_pack
    ############ 0bXWwwSsSsSsSsSsSsSsSsSsSsSsSsLlll
    assert_equal(0b00000000000000000000000000000001, @packer.pack([0]))
    assert_equal(0b01110000000000000000000000000001, @packer.pack([7]))
    assert_equal(0b00000100000000000000000000000010, @packer.pack([0, 1]))
    assert_equal(0b00101100000000000000000000000010, @packer.pack([2, 3]))
    assert_equal(0b00000000000000000000000000001101, @packer.pack([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]))
    assert_equal(0b00010101010101010101010101011101, @packer.pack([1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]))
    assert_equal(0b01111111111111111111111111111101, @packer.pack([7, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3]))
  end

  def test_unpack
    ################ 0bXWwwSsSsSsSsSsSsSsSsSsSsSsSsLlll
    assert_equal(
      [0],
      @packer.unpack(0b00000000000000000000000000000001))
    assert_equal(
      [7],
      @packer.unpack(0b01110000000000000000000000000001))
    assert_equal(
      [0, 1],
      @packer.unpack(0b00000100000000000000000000000010))
    assert_equal(
      [2, 3],
      @packer.unpack(0b00101100000000000000000000000010))
    assert_equal(
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      @packer.unpack(0b00000000000000000000000000001101))
    assert_equal(
      [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
      @packer.unpack(0b00010101010101010101010101011101))
    assert_equal(
      [7, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3],
      @packer.unpack(0b01111111111111111111111111111101))
  end

  def test_pack_and_unpack
    1000.times {
      ids1   = [rand(8)] + ((13 - 1).times.map { rand(4) })[0, rand(13)]
      packed = @packer.pack(ids1)
      ids2   = @packer.unpack(packed)
      assert_equal(ids1, ids2)
    }
  end
end
