
package jp.nayutaya.geodelta;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Region
{
    public static byte[][] getDeltaIdsInRegion(final double x1, final double y1, final double x2, final double y2, final int level)
    {
        Set<Long> packedIdsSet = null;
        if ( x1 > x2 )
        {
            packedIdsSet = new HashSet<Long>();
            packedIdsSet.addAll(getDeltaIdsInRegionSet(x1, y1, +12.0, y2, level));
            packedIdsSet.addAll(getDeltaIdsInRegionSet(x2, y1, +0.0, y2, level));
        }
        else
        {
            packedIdsSet = getDeltaIdsInRegionSet(x1, y1, x2, y2, level);
        }

        final Long[] packedIdsArray = packedIdsSet.toArray(new Long[0]);
        Arrays.sort(packedIdsArray);
        final byte[][] result = new byte[packedIdsArray.length][];
        for ( int i = 0, len = packedIdsArray.length; i < len; i++ )
        {
            result[i] = Packer64.unpack(packedIdsArray[i]);
        }

        return result;
    }

    private static Set<Long> getDeltaIdsInRegionSet(final double x1, final double y1, final double x2, final double y2, final int level)
    {
        final Set<Long> packedIdsSet = new HashSet<Long>();

        final double unit = 12.0 / Math.pow(2, level - 1);
        final double u1 = unit;
        final double u2 = unit / 2.0;

        final byte[] nw = Geometry.getDeltaIds(x1, y1, level);
        final byte[] ne = Geometry.getDeltaIds(x2, y1, level);
        final byte[] sw = Geometry.getDeltaIds(x1, y2, level);
        final byte[] se = Geometry.getDeltaIds(x2, y2, level);

        {
            final double[] nwxy = Geometry.getCenter(nw);
            final double[] nexy = Geometry.getCenter(ne);
            double sx = nwxy[0];
            double ex = nexy[0];
            final double y = nwxy[1];

            if ( sx > ex )
            {
                sx -= 24.0;
            }

            int sxi = (int)Math.floor(sx / u2);
            int exi = (int)Math.ceil(ex / u2);

            if ( !Geometry.isUpperDelta(nw) && x1 < sx && !Arrays.equals(nw, sw) )
            {
                sxi -= 1;
            }
            if ( !Geometry.isUpperDelta(ne) && x2 > ex && !Arrays.equals(ne, se) )
            {
                exi += 1;
            }

            for ( int xi = sxi; xi <= exi; xi++ )
            {
                final double xx = xi * u2;
                packedIdsSet.add(getDeltaIdsAsLong(xx, y, level));
            }
        }

        {
            final double[] swxy = Geometry.getCenter(sw);
            final double[] sexy = Geometry.getCenter(se);
            double sx = swxy[0];
            double ex = sexy[0];
            final double y = swxy[1];

            if ( sx > ex )
            {
                sx -= 24.0;
            }

            int sxi = (int)Math.floor(sx / u2);
            int exi = (int)Math.ceil(ex / u2);

            if ( Geometry.isUpperDelta(sw) && x1 < sx && !Arrays.equals(nw, sw) )
            {
                sxi -= 1;
            }
            if ( Geometry.isUpperDelta(se) && x2 > ex && !Arrays.equals(ne, se) )
            {
                exi += 1;
            }

            for ( int xi = sxi; xi <= exi; xi++ )
            {
                final double xx = xi * u2;
                packedIdsSet.add(getDeltaIdsAsLong(xx, y, level));
            }
        }

        {
            final int syi = (int)Math.floor(y1 / u1);
            final int eyi = (int)Math.ceil(y2 / u1) + 1;
            final int sxi = (int)Math.floor(x1 / u2);
            final int exi = (int)Math.ceil(x2 / u2);

            for ( int yi = eyi; yi <= syi; yi++ )
            {
                final double yy = yi * u1 - u2;
                for ( int xi = sxi; xi <= exi; xi++ )
                {
                    final double xx = xi * u2;
                    packedIdsSet.add(getDeltaIdsAsLong(xx, yy, level));
                }
            }
        }

        return packedIdsSet;
    }

    private static long getDeltaIdsAsLong(final double x, final double y, final int level)
    {
        final byte[] ids = Geometry.getDeltaIds(x, y, level);
        return Packer64.pack(ids);
    }
}
