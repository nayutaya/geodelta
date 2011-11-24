
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
}
