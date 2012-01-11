
package jp.nayutaya.geodelta;

public class IdUtil
{
    public static byte[][] getAllDeltaIds(final int level)
    {
        final int len = (int)Math.pow(2, (level * 2) + 1);
        final byte[][] ids = new byte[len][];
        for ( int i = 0; i < len; i++ )
        {
            final byte[] id = new byte[level];
            id[0] = (byte)((i >> (2 * (level - 1))) & 7);
            for ( int lv = 2; lv <= level; lv++ )
            {
                id[lv - 1] = (byte)((i >> (2 * (level - lv))) & 3);
            }
            ids[i] = id;
        }
        return ids;
    }
}
