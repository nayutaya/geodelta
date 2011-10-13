# encoding: utf-8

require "test/unit"
require_relative "../lib/geodelta/encoder"

class GeoDeltaEncoderTest < Test::Unit::TestCase
  def setup
    @mod = GeoDelta::Encoder
  end

  def test_encode_world_delta
    assert_equal("Z", @mod.encode_world_delta(0))
    assert_equal("Y", @mod.encode_world_delta(1))
    assert_equal("X", @mod.encode_world_delta(2))
    assert_equal("W", @mod.encode_world_delta(3))
    assert_equal("V", @mod.encode_world_delta(4))
    assert_equal("T", @mod.encode_world_delta(5))
    assert_equal("S", @mod.encode_world_delta(6))
    assert_equal("R", @mod.encode_world_delta(7))
    assert_raise(RuntimeError) { @mod.encode_world_delta(-1) }
    assert_raise(RuntimeError) { @mod.encode_world_delta(8) }
  end

  def test_decode_world_delta
    assert_equal(0, @mod.decode_world_delta("Z"))
    assert_equal(1, @mod.decode_world_delta("Y"))
    assert_equal(2, @mod.decode_world_delta("X"))
    assert_equal(3, @mod.decode_world_delta("W"))
    assert_equal(4, @mod.decode_world_delta("V"))
    assert_equal(5, @mod.decode_world_delta("T"))
    assert_equal(6, @mod.decode_world_delta("S"))
    assert_equal(7, @mod.decode_world_delta("R"))
    assert_raise(RuntimeError) { @mod.decode_world_delta("z") }
    assert_raise(RuntimeError) { @mod.decode_world_delta("A") }
  end

  def test_encode_and_decode_world_delta
    (0..7).each { |id|
      encoded1 = @mod.encode_world_delta(id)
      decoded1 = @mod.decode_world_delta(encoded1)
      encoded2 = @mod.encode_world_delta(decoded1)
      assert_equal(id, decoded1)
      assert_equal(encoded1, encoded2)
    }
  end

  def test_encode_sub_delta__1
    assert_equal("2", @mod.encode_sub_delta([0, 0]))
    assert_equal("3", @mod.encode_sub_delta([0, 1]))
    assert_equal("4", @mod.encode_sub_delta([0, 2]))
    assert_equal("5", @mod.encode_sub_delta([0, 3]))
    assert_equal("6", @mod.encode_sub_delta([1, 0]))
    assert_equal("7", @mod.encode_sub_delta([1, 1]))
    assert_equal("8", @mod.encode_sub_delta([1, 2]))
    assert_equal("A", @mod.encode_sub_delta([1, 3]))
    assert_equal("B", @mod.encode_sub_delta([2, 0]))
    assert_equal("C", @mod.encode_sub_delta([2, 1]))
    assert_equal("D", @mod.encode_sub_delta([2, 2]))
    assert_equal("E", @mod.encode_sub_delta([2, 3]))
    assert_equal("F", @mod.encode_sub_delta([3, 0]))
    assert_equal("G", @mod.encode_sub_delta([3, 1]))
    assert_equal("H", @mod.encode_sub_delta([3, 2]))
    assert_equal("J", @mod.encode_sub_delta([3, 3]))
  end

  def test_encode_sub_delta__2
    assert_equal("K", @mod.encode_sub_delta([0]))
    assert_equal("M", @mod.encode_sub_delta([1]))
    assert_equal("N", @mod.encode_sub_delta([2]))
    assert_equal("P", @mod.encode_sub_delta([3]))
  end

  def test_encode_sub_delta__3
    assert_equal("2K", @mod.encode_sub_delta([0, 0, 0]))
    assert_equal("22", @mod.encode_sub_delta([0, 0, 0, 0]))
    assert_equal("3N", @mod.encode_sub_delta([0, 1, 2]))
    assert_equal("3E", @mod.encode_sub_delta([0, 1, 2, 3]))
  end

  def test_encode_sub_delta__4
    assert_raise(RuntimeError) { @mod.encode_sub_delta([]) }
    assert_raise(RuntimeError) { @mod.encode_sub_delta([-1]) }
    assert_raise(RuntimeError) { @mod.encode_sub_delta([4]) }
  end

  def test_decode_sub_delta__1
    assert_equal([0, 0], @mod.decode_sub_delta("2"))
    assert_equal([0, 1], @mod.decode_sub_delta("3"))
    assert_equal([0, 2], @mod.decode_sub_delta("4"))
    assert_equal([0, 3], @mod.decode_sub_delta("5"))
    assert_equal([1, 0], @mod.decode_sub_delta("6"))
    assert_equal([1, 1], @mod.decode_sub_delta("7"))
    assert_equal([1, 2], @mod.decode_sub_delta("8"))
    assert_equal([1, 3], @mod.decode_sub_delta("A"))
    assert_equal([2, 0], @mod.decode_sub_delta("B"))
    assert_equal([2, 1], @mod.decode_sub_delta("C"))
    assert_equal([2, 2], @mod.decode_sub_delta("D"))
    assert_equal([2, 3], @mod.decode_sub_delta("E"))
    assert_equal([3, 0], @mod.decode_sub_delta("F"))
    assert_equal([3, 1], @mod.decode_sub_delta("G"))
    assert_equal([3, 2], @mod.decode_sub_delta("H"))
    assert_equal([3, 3], @mod.decode_sub_delta("J"))
  end

  def test_decode_sub_delta__2
    assert_equal([0], @mod.decode_sub_delta("K"))
    assert_equal([1], @mod.decode_sub_delta("M"))
    assert_equal([2], @mod.decode_sub_delta("N"))
    assert_equal([3], @mod.decode_sub_delta("P"))
  end

  def test_decode_sub_delta__3
    assert_equal([0, 0, 0],    @mod.decode_sub_delta("2K"))
    assert_equal([0, 0, 0, 0], @mod.decode_sub_delta("22"))
    assert_equal([0, 1, 2],    @mod.decode_sub_delta("3N"))
    assert_equal([0, 1, 2, 3], @mod.decode_sub_delta("3E"))
  end

  def test_decode_sub_delta__4
    assert_raise(RuntimeError) { @mod.decode_sub_delta("") }
    assert_raise(RuntimeError) { @mod.decode_sub_delta("a") }
    assert_raise(RuntimeError) { @mod.decode_sub_delta("Z") }
  end

  def test_encode_and_decode_sub_delta__1
    (0..3).each { |id1|
      encoded1 = @mod.encode_sub_delta([id1])
      decoded1 = @mod.decode_sub_delta(encoded1)
      encoded2 = @mod.encode_sub_delta(decoded1)
      assert_equal([id1], decoded1)
      assert_equal(encoded1, encoded2)
    }
  end

  def test_encode_and_decode_sub_delta__2
    (0..3).each { |id1|
      (0..3).each { |id2|
        encoded1 = @mod.encode_sub_delta([id1, id2])
        decoded1 = @mod.decode_sub_delta(encoded1)
        encoded2 = @mod.encode_sub_delta(decoded1)
        assert_equal([id1, id2], decoded1)
        assert_equal(encoded1, encoded2)
      }
    }
  end

  def test_encode
    assert_equal("Z",   @mod.encode([0]))
    assert_equal("ZM",  @mod.encode([0, 1]))
    assert_equal("Z8",  @mod.encode([0, 1, 2]))
    assert_equal("Z8P", @mod.encode([0, 1, 2, 3]))
    assert_equal("R",   @mod.encode([7]))
    assert_equal("RP",  @mod.encode([7, 3]))
    assert_equal("RH",  @mod.encode([7, 3, 2]))
    assert_equal("RHM", @mod.encode([7, 3, 2, 1]))
    assert_raise(RuntimeError) { @mod.encode([]) }
  end

  def test_decode
    assert_equal([0],          @mod.decode("Z"))
    assert_equal([0, 1],       @mod.decode("ZM"))
    assert_equal([0, 1, 2],    @mod.decode("Z8"))
    assert_equal([0, 1, 2, 3], @mod.decode("Z8P"))
    assert_equal([7],          @mod.decode("R"))
    assert_equal([7, 3],       @mod.decode("RP"))
    assert_equal([7, 3, 2],    @mod.decode("RH"))
    assert_equal([7, 3, 2, 1], @mod.decode("RHM"))
    assert_raise(RuntimeError) { @mod.encode("") }
  end

  def test_encode_and_decode__rush
    world = (0..7).to_a
    sub   = (0..3).to_a
    1000.times {
      ids = [world[rand(world.size)]] + rand(20).times.map { sub[rand(sub.size)] }
      encoded1 = @mod.encode(ids)
      decoded1 = @mod.decode(encoded1)
      encoded2 = @mod.encode(decoded1)
      assert_equal(ids, decoded1)
      assert_equal(encoded1, encoded2)
    }
  end
end
