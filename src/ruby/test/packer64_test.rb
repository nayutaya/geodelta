# encoding: utf-8

require "test/unit"
require_relative "../lib/geodelta/packer64"

class GeoDeltaPacker64Test < Test::Unit::TestCase
  def setup
    @packer = GeoDelta::Packer64.new
  end
end
