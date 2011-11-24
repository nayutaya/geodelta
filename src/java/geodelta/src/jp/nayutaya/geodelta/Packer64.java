
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

    /* package */static long packLevel(final int level)
    {
        return level;
    }

    /* package */static int unpackLevel(final long value)
    {
        return (int)(value & 0x1F);
    }

    public static long pack(final byte[] ids)
    {
        final long wid = packWorldDelta(ids[0]);
        long sid = 0;
        for ( int i = 1, len = ids.length; i < len; i++ )
        {
            sid |= packSubDelta(i + 1, ids[i]);
        }
        final long level = packLevel(ids.length);
        return wid | sid | level;
    }

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
