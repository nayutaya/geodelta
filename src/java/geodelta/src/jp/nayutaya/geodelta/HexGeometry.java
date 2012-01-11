
package jp.nayutaya.geodelta;

public class HexGeometry
{
    private static final int[] HEX_POSITION_TABLE;

    static
    {
        HEX_POSITION_TABLE = new int[12];
        HEX_POSITION_TABLE[(0 << 1) | 0] = 0;
        HEX_POSITION_TABLE[(0 << 1) | 1] = 3;
        HEX_POSITION_TABLE[(1 << 1) | 0] = 1;
        HEX_POSITION_TABLE[(1 << 1) | 1] = 2;
        HEX_POSITION_TABLE[(2 << 1) | 0] = 4;
        HEX_POSITION_TABLE[(2 << 1) | 1] = 5;
        HEX_POSITION_TABLE[(3 << 1) | 0] = 3;
        HEX_POSITION_TABLE[(3 << 1) | 1] = 0;
        HEX_POSITION_TABLE[(4 << 1) | 0] = 2;
        HEX_POSITION_TABLE[(4 << 1) | 1] = 1;
        HEX_POSITION_TABLE[(5 << 1) | 0] = 5;
        HEX_POSITION_TABLE[(5 << 1) | 1] = 4;
    }

    public static int getHexPosition(final byte[] ids)
    {
        final double unit = getDeltaUnitSize(ids.length);
        final double[] cxy = Geometry.getCenter(ids);
        final int ix = (int)Utility.mod(Math.floor(cxy[0] / unit * 2.0), 6);
        final int iy = (int)Utility.mod(Math.floor(cxy[1] / unit), 2);
        return HEX_POSITION_TABLE[(ix << 1) | iy];
    }

    public static byte[] getBaseDeltaIds(final byte[] ids)
    {
        final double unit = getDeltaUnitSize(ids.length);
        final int pos = getHexPosition(ids);
        final double[] cxy = Geometry.getCenter(ids);
        final double cx = cxy[0];
        final double cy = cxy[1];

        double sx = 0.0;
        double sy = 0.0;

        switch ( pos )
        {
        case 0:
            sx = 0.0;
            sy = 0.0;
            break;
        case 1:
            sx = -1.0;
            sy = +1.0;
            break;
        case 2:
            sx = -1.0;
            sy = +3.0;
            break;
        case 3:
            sx = 0.0;
            sy = +4.0;
            break;
        case 4:
            sx = +1.0;
            sy = +3.0;
            break;
        case 5:
            sx = +1.0;
            sy = +1.0;
            break;
        default:
            throw new RuntimeException("BUG");
        }

        final double x = cx + (unit * sx / 2.0);
        final double y = cy + (unit * sy / 3.0);

        if ( x > +12.0 )
            return null;
        if ( x < -12.0 + unit )
            return null;
        if ( y > +12.0 )
            return null;
        if ( y < -12.0 + unit )
            return null;

        return Geometry.getDeltaIds(x, y, ids.length);
    }

    public static byte[][] getPartDeltaIds(final byte[] baseIds)
    {
        final int level = baseIds.length;
        final double unit = getDeltaUnitSize(level);
        final double[] xy = Geometry.getCoordinates(baseIds)[1];
        final double x = xy[0];
        final double y = xy[1];

        final double x1 = x - (unit / 2.0);
        final double x2 = x;
        final double x3 = x + (unit / 2.0);

        final double y1 = y + (unit * 2.0 / 3.0);
        final double y2 = y + (unit / 3.0);
        final double y3 = y - (unit / 3.0);
        final double y4 = y - (unit * 2.0 / 3.0);

        final byte[][] result = new byte[6][];
        result[0] = Geometry.getDeltaIds(x2, y1, level);
        result[1] = Geometry.getDeltaIds(x3, y2, level);
        result[2] = Geometry.getDeltaIds(x3, y3, level);
        result[3] = Geometry.getDeltaIds(x2, y4, level);
        result[4] = Geometry.getDeltaIds(x1, y3, level);
        result[5] = Geometry.getDeltaIds(x1, y2, level);

        return result;
    }

    public static double[][] getCoordinates(final byte[] baseIds)
    {
        final int level = baseIds.length;
        final double unit = getDeltaUnitSize(level);
        final double ux1 = unit / 2.0;
        final double ux2 = unit;
        final double uy3 = unit;
        final double[] xy = Geometry.getCoordinates(baseIds)[1];
        final double x = xy[0];
        final double y = xy[1];

        if ( y - uy3 < -12.0 )
        {
            return null;
        }

        // @formatter:off
        return new double[][] {
            {x + ux1, y + uy3},
            {x + ux2, y },
            {x + ux1, y - uy3},
            {x - ux1, y - uy3},
            {x - ux2, y },
            {x - ux1, y + uy3},
        };
        // @formatter:on
    }

    /* package */static double getDeltaUnitSize(final int level)
    {
        return 12.0 / Math.pow(2, level - 1);
    }
}
