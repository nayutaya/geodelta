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
end
