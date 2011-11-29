
package jp.nayutaya.geodelta;

/**
 * 64ビット符号付き整数にパックされたデルタID列を扱うクラス。
 */
public class Packer64
{
    /**
     * ワールドデルタIDをパックする。
     *
     * @param id ワールドデルタID
     * @return パックされたID
     */
    /* package */static long packWorldDelta(final byte id)
    {
        return (long)id << 59;
    }

    /**
     * ワールドデルタIDをアンパックする。
     *
     * @param value パックされたデルタID列
     * @return ワールドデルタID
     */
    /* package */static byte unpackWorldDelta(final long value)
    {
        return (byte)((value >> 59) & 0x07);
    }

    /**
     * サブデルタIDをパックする。
     *
     * @param level サブデルタのレベル
     * @param id サブデルタID
     * @return パックされたID
     */
    /* package */static long packSubDelta(final int level, final byte id)
    {
        return (long)id << (57 - ((level - 2) * 2));
    }

    /**
     * サブデルタIDをアンパックする。
     *
     * @param level サブデルタのレベル
     * @param value パックされたデルタID列
     * @return サブデルタID
     */
    /* package */static byte unpackSubDelta(final int level, final long value)
    {
        return (byte)((value >> (57 - ((level - 2) * 2))) & 0x03);
    }

    /**
     * レベルをパックする。
     *
     * @param level レベル
     * @return パックされたID
     */
    /* package */static long packLevel(final int level)
    {
        return level;
    }

    /**
     * レベルをアンパックする。
     *
     * @param value パックされたデルタID列
     * @return レベル
     */
    /* package */static int unpackLevel(final long value)
    {
        return (int)(value & 0x1F);
    }

    /**
     * デルタID列を64ビット整数にパックする。
     *
     * @param ids デルタID列
     * @return パックされたデルタID列
     */
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

    /**
     * 64ビット整数からデルタID列をアンパックする。
     *
     * @param value パックされたデルタID列
     * @return デルタID列
     */
    public static byte[] unpack(final long value)
    {
        final int level = unpackLevel(value);
        final byte[] ids = new byte[level];
        ids[0] = unpackWorldDelta(value);
        for ( int i = 1; i < level; i++ )
        {
            ids[i] = unpackSubDelta(i + 1, value);
        }
        return ids;
    }
}
