
package jp.nayutaya.geodelta;

import java.util.Arrays;

/**
 * デルタID列とGeoDeltaコードを相互変換するクラス。
 */
public class Encoder
{
    private static final char[] WORLD_DELTA_TABLE = {'Z', 'Y', 'X', 'W', 'V', 'T', 'S', 'R'};
    private static final String[] SUB_DELTA_TABLE1 = {"K", "M", "N", "P"};
    private static final String[][] SUB_DELTA_TABLE2 = { {"2", "3", "4", "5"}, {"6", "7", "8", "A"}, {"B", "C", "D", "E"}, {"F", "G", "H", "J"}};

    private Encoder()
    {
        // nop
    }

    /**
     * ワールドデルタIDをエンコードする。
     *
     * @param id ワールドデルタID
     * @return ワールドデルタコード
     */
    /* package */static char encodeWorldDelta(final byte id)
    {
        if ( id < 0 || id > 7 )
        {
            throw new IllegalArgumentException("id");
        }
        return WORLD_DELTA_TABLE[id];
    }

    /**
     * ワールドデルタコードをデコードする。
     *
     * @param code ワールドデルタコード
     * @return ワールドデルタID
     */
    /* package */static byte decodeWorldDelta(final char code)
    {
        for ( int i = 0; i < WORLD_DELTA_TABLE.length; i++ )
        {
            if ( WORLD_DELTA_TABLE[i] == code )
            {
                return (byte)i;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * サブデルタID列をエンコードする。
     *
     * @param ids サブデルタID列
     * @return サブデルタコード
     */
    /* package */static String encodeSubDelta(final byte[] ids)
    {
        if ( ids == null || ids.length == 0 )
        {
            throw new IllegalArgumentException();
        }
        return encodeSubDelta(ids, 0);
    }

    /**
     * サブデルタID列をエンコードする。
     *
     * @param ids サブデルタID列
     * @param start エンコード開始位置
     * @return サブデルタコード
     */
    private static String encodeSubDelta(final byte[] ids, final int start)
    {
        final StringBuilder sb = new StringBuilder();
        for ( int i = start, len = ids.length; i < len; i += 2 )
        {
            final int rest = len - i;
            try
            {
                if ( rest == 1 )
                {
                    sb.append(SUB_DELTA_TABLE1[ids[i]]);
                }
                else
                {
                    sb.append(SUB_DELTA_TABLE2[ids[i]][ids[i + 1]]);
                }
            }
            catch ( ArrayIndexOutOfBoundsException e )
            {
                throw new IllegalArgumentException();
            }
        }
        return sb.toString();
    }

    /**
     * サブデルタコードをデコードする。
     *
     * @param code サブデルタコード
     * @return サブデルタID列
     */
    /* package */static byte[] decodeSubDelta(final String code)
    {
        if ( code == null || code.isEmpty() )
        {
            throw new IllegalArgumentException();
        }

        final byte[] ids = new byte[code.length() * 2];
        int i = 0;

        for ( final char ch : code.toCharArray() )
        {
            // @formatter:off
            switch ( ch )
            {
            case '2': ids[i++] = 0; ids[i++] = 0; break;
            case '3': ids[i++] = 0; ids[i++] = 1; break;
            case '4': ids[i++] = 0; ids[i++] = 2; break;
            case '5': ids[i++] = 0; ids[i++] = 3; break;
            case '6': ids[i++] = 1; ids[i++] = 0; break;
            case '7': ids[i++] = 1; ids[i++] = 1; break;
            case '8': ids[i++] = 1; ids[i++] = 2; break;
            case 'A': ids[i++] = 1; ids[i++] = 3; break;
            case 'B': ids[i++] = 2; ids[i++] = 0; break;
            case 'C': ids[i++] = 2; ids[i++] = 1; break;
            case 'D': ids[i++] = 2; ids[i++] = 2; break;
            case 'E': ids[i++] = 2; ids[i++] = 3; break;
            case 'F': ids[i++] = 3; ids[i++] = 0; break;
            case 'G': ids[i++] = 3; ids[i++] = 1; break;
            case 'H': ids[i++] = 3; ids[i++] = 2; break;
            case 'J': ids[i++] = 3; ids[i++] = 3; break;
            case 'K': ids[i++] = 0; break;
            case 'M': ids[i++] = 1; break;
            case 'N': ids[i++] = 2; break;
            case 'P': ids[i++] = 3; break;
            default: throw new IllegalArgumentException();
            }
            // @formatter:on
        }

        if ( i % 2 == 0 )
        {
            return ids;
        }
        else
        {
            return Arrays.copyOf(ids, ids.length - 1);
        }
    }

    /**
     * デルタID列をエンコードする。
     *
     * @param ids デルタID列
     * @return GeoDeltaコード
     */
    public static String encode(final byte[] ids)
    {
        if ( ids == null || ids.length == 0 )
        {
            throw new IllegalArgumentException();
        }

        final StringBuilder sb = new StringBuilder();
        sb.append(encodeWorldDelta(ids[0]));
        sb.append(encodeSubDelta(ids, 1));
        return sb.toString();
    }

    /**
     * GeoDeltaコードをデコードする。
     *
     * @param code GeoDeltaコード
     * @return デルタID列
     */
    public static byte[] decode(final String code)
    {
        if ( code == null || code.length() == 0 )
        {
            throw new IllegalArgumentException();
        }
        else if ( code.length() == 1 )
        {
            return new byte[] {decodeWorldDelta(code.charAt(0))};
        }
        else
        {
            final byte[] subIds = decodeSubDelta(code.substring(1));
            final byte[] ids = new byte[subIds.length + 1];
            ids[0] = decodeWorldDelta(code.charAt(0));
            for ( int i = 0, len = subIds.length; i < len; i++ )
            {
                ids[i + 1] = subIds[i];
            }
            return ids;
        }
    }
}
