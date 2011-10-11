# encoding: utf-8

require "test/unit"
require File.expand_path(File.join(File.dirname(__FILE__), "..", "lib", "geodelta", "core"))

class GeoDeltaTest < Test::Unit::TestCase
  def setup
    @mod = GeoDelta
  end
end
