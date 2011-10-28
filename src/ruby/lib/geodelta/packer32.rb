# encoding: utf-8

module GeoDelta
  class Packer32
    def pack_world_delta(id)
      return id << 28
    end

    def unpack_world_delta(value)
      return (value >> 28) & 0b111
    end

    def pack_sub_delta(level, id)
      return id << (26 - ((level - 2) * 2))
    end

    def unpack_sub_delta(level, value)
      return (value >> (26 - ((level - 2) * 2))) & 0b11
    end

    def pack_level(level)
      return level
    end

    def unpack_level(value)
      return value & 0b1111
    end

    def pack(ids)
      wid   = self.pack_world_delta(ids[0])
      sids  = ids[1..-1].each_with_index.map { |id, i| self.pack_sub_delta(i + 2, id) }.inject(0, &:+)
      level = self.pack_level(ids.size)
      return wid + sids + level
    end

    def unpack(value)
      level = self.unpack_level(value)
      wid   = self.unpack_world_delta(value)
      sids  = (level - 1).times.map { |i| self.unpack_sub_delta(i + 2, value) }
      return [wid] + sids
    end
  end
end
