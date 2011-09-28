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
    WORLD_CHAR_TO_ID = WORLD_DELTA_TABLE.inject({}) { |memo, (num, char)| memo[char] = num; memo }.freeze

    SUB_DELTA_TABLE = [
      [[0, 0], "2"],
      [[0, 1], "3"],
      [[0, 2], "4"],
      [[0, 3], "5"],
      [[1, 0], "6"],
      [[1, 1], "7"],
      [[1, 2], "8"],
      [[1, 3], "A"],
      [[2, 0], "B"],
      [[2, 1], "C"],
      [[2, 2], "D"],
      [[2, 3], "E"],
      [[3, 0], "F"],
      [[3, 1], "G"],
      [[3, 2], "H"],
      [[3, 3], "J"],
      [[0]   , "K"],
      [[1]   , "M"],
      [[2]   , "N"],
      [[3]   , "P"],
    ].each(&:freeze).freeze
    SUB_IDS_TO_CHAR = SUB_DELTA_TABLE.inject({}) { |memo, (nums, char)| memo[nums] = char; memo }.freeze
    SUB_CHAR_TO_IDS = SUB_DELTA_TABLE.inject({}) { |memo, (nums, char)| memo[char] = nums; memo }.freeze

    def self.encode_world_delta(id)
      return WORLD_ID_TO_CHAR[id] || raise("invalid world delta id -- #{id}")
    end

    def self.decode_world_delta(code)
      return WORLD_CHAR_TO_ID[code] || raise("invalid world delta code -- #{code}")
    end

    def self.encode_sub_delta(ids)
      raise("sub delta ids is empty") if ids.empty?
      return ids.each_slice(2).map { |part|
        SUB_IDS_TO_CHAR[part] || raise("invalid sub delta ids -- #{part}")
      }.join("")
    end

    def self.decode_sub_delta(codes)
      raise("sub delta codes is empty") if codes.empty?
      return codes.chars.inject([]) { |memo, char|
        memo + (SUB_CHAR_TO_IDS[char] || raise("invalid sub delta code -- #{char}"))
      }
    end

    def self.encode(ids)
      raise("delta ids is empty") if ids.empty?
      result  = self.encode_world_delta(ids[0])
      result += self.encode_sub_delta(ids[1..-1]) if ids.size >= 2
      return result
    end

    def self.decode(codes)
      raise("delta codes is empty") if codes.empty?
      result  = [self.decode_world_delta(codes[0])]
      result += self.decode_sub_delta(codes[1..-1]) if codes.size >= 2
      return result
    end
  end
end
