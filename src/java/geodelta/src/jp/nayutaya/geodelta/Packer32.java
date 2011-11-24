
package jp.nayutaya.geodelta;

public class Packer32
{
    /* package */static int packWorldDelta(final int id)
    {
        return id << 28;
    }

    /* package */static byte unpackWorldDelta(final int value)
    {
        return (byte)((value >> 28) & 0x07);
    }

    /* package */static int packSubDelta(final int level, final int id)
    {
        return id << (26 - ((level - 2) * 2));
    }

    /* package */static byte unpackSubDelta(final int level, final int value)
    {
        return (byte)((value >> (26 - ((level - 2) * 2))) & 0x03);
    }

    /* package */static int packLevel(final int level)
    {
        return level;
    }

    /* package */static int unpackLevel(final int value)
    {
        return value & 0x0F;
    }

    public static int pack(final byte[] ids)
    {
        final int wid = packWorldDelta(ids[0]);
        int sid = 0;
        for ( int i = 1, len = ids.length; i < len; i++ )
        {
            sid |= packSubDelta(i + 1, ids[i]);
        }
        final int level = packLevel(ids.length);
        return wid | sid | level;
    }
}
