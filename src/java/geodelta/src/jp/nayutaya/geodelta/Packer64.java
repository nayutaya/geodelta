
package jp.nayutaya.geodelta;

public class Packer64
{
    /* package */static long packWorldDelta(final byte id)
    {
        return (long)id << 59;
    }

    /* package */static byte unpackWorldDelta(final long value)
    {
        return (byte)((value >> 59) & 0x07);
    }

    /* package */static long packSubDelta(final int level, final byte id)
    {
        return (long)id << (57 - ((level - 2) * 2));
    }

    /* package */static byte unpackSubDelta(final int level, final long value)
    {
        return (byte)((value >> (57 - ((level - 2) * 2))) & 0x03);
    }

    // TODO: pack_level
    /*
     * def pack_level(level)
     * return level
     * end
     */

    // TODO: unpack_level
    /*
     * def unpack_level(level)
     * return level & 0b11111
     * end
     */

    // TODO: pack
    /*
     * def pack(ids)
     * wid = self.pack_world_delta(ids[0])
     * sids = ids[1..-1].each_with_index.map { |id, i| self.pack_sub_delta(i + 2, id) }.inject(0, &:+)
     * level = self.pack_level(ids.size)
     * return wid + sids + level
     * end
     */

    // TODO: unpack
    /*
     * def unpack(value)
     * level = self.unpack_level(value)
     * wid = self.unpack_world_delta(value)
     * sids = (level - 1).times.map { |i| self.unpack_sub_delta(i + 2, value) }
     * return [wid] + sids
     * end
     */
}
