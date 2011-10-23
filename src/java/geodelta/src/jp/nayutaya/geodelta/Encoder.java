
package jp.nayutaya.geodelta;

import java.util.ArrayList;
import java.util.List;

public class Encoder
{
    public static char encodeWorldDelta(final byte id)
    {
        switch ( id )
        {
        case 0:
            return 'Z';
        case 1:
            return 'Y';
        case 2:
            return 'X';
        case 3:
            return 'W';
        case 4:
            return 'V';
        case 5:
            return 'T';
        case 6:
            return 'S';
        case 7:
            return 'R';
        }
        return '\0';
    }

    public static byte decodeWorldDelta(final char code)
    {
        switch ( code )
        {
        case 'Z':
            return 0;
        case 'Y':
            return 1;
        case 'X':
            return 2;
        case 'W':
            return 3;
        case 'V':
            return 4;
        case 'T':
            return 5;
        case 'S':
            return 6;
        case 'R':
            return 7;
        }
        return 0;
    }

    public static String encodeSubDelta(final byte[] ids)
    {
        return encodeSubDelta(ids, 0);
    }

    private static String encodeSubDelta(final byte[] ids, final int start)
    {
        final int rest = ids.length - start;
        if ( rest < 1 )
        {
            return "";
        }
        else if ( rest == 1 )
        {
            switch ( ids[start] )
            {
            case 0:
                return "K";
            case 1:
                return "M";
            case 2:
                return "N";
            case 3:
                return "P";
            }
        }
        else
        {
            if ( ids[start] == 0 )
            {
                switch ( ids[start + 1] )
                {
                case 0:
                    return "2" + encodeSubDelta(ids, start + 2);
                case 1:
                    return "3" + encodeSubDelta(ids, start + 2);
                case 2:
                    return "4" + encodeSubDelta(ids, start + 2);
                case 3:
                    return "5" + encodeSubDelta(ids, start + 2);
                }
            }
            else if ( ids[start] == 1 )
            {
                switch ( ids[start + 1] )
                {
                case 0:
                    return "6" + encodeSubDelta(ids, start + 2);
                case 1:
                    return "7" + encodeSubDelta(ids, start + 2);
                case 2:
                    return "8" + encodeSubDelta(ids, start + 2);
                case 3:
                    return "A" + encodeSubDelta(ids, start + 2);
                }
            }
            else if ( ids[start] == 2 )
            {
                switch ( ids[start + 1] )
                {
                case 0:
                    return "B" + encodeSubDelta(ids, start + 2);
                case 1:
                    return "C" + encodeSubDelta(ids, start + 2);
                case 2:
                    return "D" + encodeSubDelta(ids, start + 2);
                case 3:
                    return "E" + encodeSubDelta(ids, start + 2);
                }
            }
            else if ( ids[start] == 3 )
            {
                switch ( ids[start + 1] )
                {
                case 0:
                    return "F" + encodeSubDelta(ids, start + 2);
                case 1:
                    return "G" + encodeSubDelta(ids, start + 2);
                case 2:
                    return "H" + encodeSubDelta(ids, start + 2);
                case 3:
                    return "J" + encodeSubDelta(ids, start + 2);
                }
            }
        }
        return null;
    }

    public static byte[] decodeSubDelta(final String code)
    {
        final List<Byte> ids = new ArrayList<Byte>();

        for ( char ch : code.toCharArray() )
        {
            switch ( ch )
            {
            case '2':
                ids.add((byte)0);
                ids.add((byte)0);
                break;
            case '3':
                ids.add((byte)0);
                ids.add((byte)1);
                break;
            case '4':
                ids.add((byte)0);
                ids.add((byte)2);
                break;
            case '5':
                ids.add((byte)0);
                ids.add((byte)3);
                break;
            case '6':
                ids.add((byte)1);
                ids.add((byte)0);
                break;
            case '7':
                ids.add((byte)1);
                ids.add((byte)1);
                break;
            case '8':
                ids.add((byte)1);
                ids.add((byte)2);
                break;
            case 'A':
                ids.add((byte)1);
                ids.add((byte)3);
                break;
            case 'B':
                ids.add((byte)2);
                ids.add((byte)0);
                break;
            case 'C':
                ids.add((byte)2);
                ids.add((byte)1);
                break;
            case 'D':
                ids.add((byte)2);
                ids.add((byte)2);
                break;
            case 'E':
                ids.add((byte)2);
                ids.add((byte)3);
                break;
            case 'F':
                ids.add((byte)3);
                ids.add((byte)0);
                break;
            case 'G':
                ids.add((byte)3);
                ids.add((byte)1);
                break;
            case 'H':
                ids.add((byte)3);
                ids.add((byte)2);
                break;
            case 'J':
                ids.add((byte)3);
                ids.add((byte)3);
                break;
            case 'K':
                ids.add((byte)0);
                break;
            case 'M':
                ids.add((byte)1);
                break;
            case 'N':
                ids.add((byte)2);
                break;
            case 'P':
                ids.add((byte)3);
                break;
            }
        }
        final byte[] ret = new byte[ids.size()];
        for ( int i = 0, len = ids.size(); i < len; i++ )
        {
            ret[i] = ids.get(i);
        }
        return ret;
    }

    public static String encode(final byte[] ids)
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(encodeWorldDelta(ids[0]));
        sb.append(encodeSubDelta(ids, 1));
        return sb.toString();
    }

    public static byte[] decode(final String code)
    {
        final List<Byte> ids = new ArrayList<Byte>();
        ids.add(decodeWorldDelta(code.charAt(0)));
        for ( byte b : decodeSubDelta(code.substring(1)) )
        {
            ids.add(b);
        }
        final byte[] ret = new byte[ids.size()];
        for ( int i = 0, len = ids.size(); i < len; i++ )
        {
            ret[i] = ids.get(i);
        }
        return ret;
    }
}
