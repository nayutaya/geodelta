
package jp.nayutaya.geodelta;

/**
 * 32ビット符号付き整数にパックされたデルタID列を扱うクラス。
 */
public class Packer32
{
    /**
     * ワールドデルタIDをパックする。
     *
     * @param id ワールドデルタID
     * @return パックされたID
     */
    /* package */static int packWorldDelta(final int id)
    {
        return id << 28;
    }

    /**
     * ワールドデルタIDをアンパックする。
     *
     * @param value パックされたデルタID列
     * @return ワールドデルタID
     */
    /* package */static byte unpackWorldDelta(final int value)
    {
        return (byte)((value >> 28) & 0x07);
    }

    /**
     * サブデルタIDをパックする。
     *
     * @param level サブデルタのレベル
     * @param id サブデルタID
     * @return パックされたID
     */
    /* package */static int packSubDelta(final int level, final int id)
    {
        return id << (26 - ((level - 2) * 2));
    }

    /**
     * サブデルタIDをアンパックする。
     *
     * @param level サブデルタのレベル
     * @param value パックされたデルタID列
     * @return サブデルタID
     */
    /* package */static byte unpackSubDelta(final int level, final int value)
    {
        return (byte)((value >> (26 - ((level - 2) * 2))) & 0x03);
    }

    /**
     * レベルをパックする。
     *
     * @param level レベル
     * @return パックされたID
     */
    /* package */static int packLevel(final int level)
    {
        return level;
    }

    /**
     * レベルをアンパックする。
     *
     * @param value パックされたデルタID列
     * @return レベル
     */
    /* package */static int unpackLevel(final int value)
    {
        return value & 0x0F;
    }

    /**
     * デルタID列を32ビット整数にパックする。
     *
     * @param ids デルタID列
     * @return パックされたデルタID列
     */
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

    /**
     * 32ビット整数からデルタID列をアンパックする。
     *
     * @param value パックされたデルタID列
     * @return デルタID列
     */
    public static byte[] unpack(final int value)
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
