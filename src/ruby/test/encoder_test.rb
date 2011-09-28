# encoding: utf-8

require "test/unit"
require File.expand_path(File.join(File.dirname(__FILE__), "..", "lib", "geodelta", "encoder"))

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
end
