# encoding: utf-8

module GeoDelta
  module Encoder
    WORLD_DELTA_TABLE = [
      [0, "Z"],
      [1, "Y"],
      [2, "X"],
      [3, "W"],
      [4, "V"],
      [5, "T"],
      [6, "S"],
      [7, "R"],
    ].each(&:freeze).freeze
    WORLD_ID_TO_CHAR = WORLD_DELTA_TABLE.inject({}) { |memo, (num, char)| memo[num] = char; memo }.freeze

    def self.encode_world_delta(id)
      return WORLD_ID_TO_CHAR[id] || raise("invalid world delta id -- #{id}")
    end
  end
end
