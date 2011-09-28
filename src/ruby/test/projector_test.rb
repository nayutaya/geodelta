# encoding: utf-8

require "test/unit"
require File.expand_path(File.join(File.dirname(__FILE__), "..", "lib", "geodelta", "projector"))

class GeoDeltaProjectorTest < Test::Unit::TestCase
  def setup
    @mod = GeoDelta::Projector
  end
end
