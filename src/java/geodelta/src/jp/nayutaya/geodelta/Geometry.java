
package jp.nayutaya.geodelta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 幾何的演算を行うクラス。
 */
public class Geometry
{
    private Geometry()
    {
        // nop
    }

    /**
     * 指定された座標(x, y)に該当するワールドデルタIDを返す。
     *
     * @param x 正規化座標系におけるX
     * @param y 正規化座標系におけるY
     * @return ワールドデルタID
     */
    /* package */static int getWorldDeltaId(final double x, final double y)
    {
        final double xx = Utility.mod(x, 24);
        final double yy = Math.abs(y);
        final int base = (y >= 0.0 ? 0 : 4);
        if ( yy >= +2.0 * (xx - 0.0) )
        {
            return base + 0;
        }
        else if ( yy <= -2.0 * (xx - 12.0) )
        {
            return base + 1;
        }
        else if ( yy >= +2.0 * (xx - 12.0) )
        {
            return base + 2;
        }
        else if ( yy <= -2.0 * (xx - 24.0) )
        {
            return base + 3;
        }
        return base;
    }

    /**
     * 指定された座標(x, y)に該当する上向きのサブデルタIDを返す。
     *
     * @param x 正規化座標系におけるX
     * @param y 正規化座標系におけるY
     * @return サブデルタID
     */
    /* package */static int getUpperDeltaId(final double x, final double y)
    {
        if ( y < -2.0 * (x - 6.0) )
        {
            return 3;
        }
        else if ( y < +2.0 * (x - 6.0) )
        {
            return 2;
        }
        else if ( y > 6.0 )
        {
            return 1;
        }
        return 0;
    }

    /**
     * 指定された座標(x, y)に該当する下向きのサブデルタの番号を返す。
     *
     * @param x 正規化座標系におけるX
     * @param y 正規化座標系におけるY
     * @return サブデルタID
     */
    /* package */static int getLowerDeltaId(final double x, final double y)
    {
        if ( y > -2.0 * (x - 12.0) )
        {
            return 3;
        }
        else if ( y > +2.0 * x )
        {
            return 2;
        }
        else if ( y < 6.0 )
        {
            return 1;
        }
        return 0;
    }

    /**
     * 指定されたワールドデルタが上向きかどうか判定する。
     *
     * @param id ワールドデルタID
     * @return 上向きであればtrueを、下向きであればfalseを返す
     */
    /* package */static boolean isUpperWorldDelta(final int id)
    {
        return (id % 2 == (id < 4 ? 1 : 0));
    }

    /**
     * 指定されたサブデルタが上向きかどうか判定する。
     *
     * @param parentIsUpper 上位デルタが上向きか否か
     * @param id サブデルタID
     * @return 上向きであればtrueを、下向きであればfalseを返す
     */
    /* package */static boolean isUpperSubDelta(final boolean parentIsUpper, final int id)
    {
        return (parentIsUpper ? (id != 0) : (id == 0));
    }

    /**
     * 指定されたデルタが上向きかどうか判定する。
     *
     * @param ids デルタID列
     * @return 上向きであればtrueを、下向きであればfalseを返す
     */
    public static boolean isUpperDelta(final byte[] ids)
    {
        boolean upper = false;
        for ( int i = 0, len = ids.length; i < len; i++ )
        {
            if ( i == 0 )
            {
                upper = isUpperWorldDelta(ids[i]);
            }
            else
            {
                upper = isUpperSubDelta(upper, ids[i]);
            }
        }
        return upper;
    }

    /**
     * 指定された座標(x, y)を指定されたワールドデルタID内における正規化座標系に平行移動する。
     *
     * @param id ワールドデルタID
     * @param x 正規化座標系におけるX
     * @param y 正規化座標系におけるY
     * @return 移動後の座標(x, y)を含む配列
     */
    /* package */static double[] transformWorldDelta(final int id, final double x, final double y)
    {
        final double[] xs = {+6.0, +0.0, -6.0, -12.0, +6.0, +0.0, -6.0, -12.0};
        final double[] ys = {+0.0, +0.0, +0.0, +0.0, +12.0, +12.0, +12.0, +12.0};
        final double xx = Utility.mod((x + xs[id]), 12.0);
        final double yy = Utility.mod((y + ys[id]), 12.0);
        return new double[] {xx, yy};
    }

    /**
     * 指定された座標(x, y)を指定されたサブデルタID内における正規化座標系に平行移動する。
     *
     * @param id サブデルタID
     * @param x 正規化座標系におけるX
     * @param y 正規化座標系におけるY
     * @return 移動後の座標(x, y)を含む配列
     */
    /* package */static double[] transformUpperDelta(final int id, final double x, final double y)
    {
        final double[] xs = {-3.0, -3.0, -6.0, -0.0};
        final double[] ys = {-0.0, -6.0, -0.0, -0.0};
        final double xx = (x + xs[id]) * 2;
        final double yy = (y + ys[id]) * 2;
        return new double[] {xx, yy};
    }

    /**
     * 指定された座標(x, y)を指定されたサブデルタID内における正規化座標系に平行移動する。
     *
     * @param id サブデルタID
     * @param x 正規化座標系におけるX
     * @param y 正規化座標系におけるY
     * @return 移動後の座標(x, y)を含む配列
     */
    /* package */static double[] transformLowerDelta(final int id, final double x, final double y)
    {
        final double[] xs = {-3.0, -3.0, -0.0, -6.0};
        final double[] ys = {-6.0, -0.0, -6.0, -6.0};
        final double xx = (x + xs[id]) * 2;
        final double yy = (y + ys[id]) * 2;
        return new double[] {xx, yy};
    }

    /**
     * 指定された座標(x, y)に該当するデルタのデルタID列を取得する。
     *
     * @param x 正規化座標系におけるX
     * @param y 正規化座標系におけるY
     * @param level デルタのレベル（1～）
     * @return デルタID列
     */
    public static byte[] getDeltaIds(final double x, final double y, final int level)
    {
        final byte[] ids = new byte[level];

        ids[0] = (byte)getWorldDeltaId(x, y);
        double[] xxyy = transformWorldDelta(ids[0], x, y);
        boolean upper = isUpperWorldDelta(ids[0]);

        for ( int i = 1; i < level; i++ )
        {
            ids[i] = (byte)(upper ? getUpperDeltaId(xxyy[0], xxyy[1]) : getLowerDeltaId(xxyy[0], xxyy[1]));
            xxyy = (upper ? transformUpperDelta(ids[i], xxyy[0], xxyy[1]) : transformLowerDelta(ids[i], xxyy[0], xxyy[1]));
            upper = isUpperSubDelta(upper, ids[i]);
        }

        return ids;
    }

    /**
     * 指定されたワールドデルタIDの中心座標を取得する。
     *
     * @param id ワールドデルタID
     * @return 中心座標(x, y)を含む配列
     */
    /* package */static double[] getWorldDeltaCenter(final int id)
    {
        final double[] xs = {+0.0, +6.0, +12.0, +18.0, +0.0, +6.0, +12.0, +18.0};
        final double[] ys = {+8.0, +4.0, +8.0, +4.0, -8.0, -4.0, -8.0, -4.0};
        final double x = xs[id];
        final double y = ys[id];
        return new double[] {x, y};
    }

    /**
     * 指定されたサブデルタIDの上向き上位デルタからの距離を取得する。
     *
     * @param id サブデルタID
     * @return 距離(dx, dy)を含む配列
     */
    /* package */static double[] getUpperSubDeltaDistance(final int id)
    {
        final double[] xs = {+0.0, +0.0, +3.0, -3.0};
        final double[] ys = {+0.0, +4.0, -2.0, -2.0};
        final double dx = xs[id];
        final double dy = ys[id];
        return new double[] {dx, dy};
    }

    /**
     * 指定されたサブデルタIDの下向き上位デルタからの距離を取得する。
     *
     * @param id サブデルタID
     * @return 距離(dx, dy)を含む配列
     */
    /* package */static double[] getLowerSubDeltaDistance(final int id)
    {
        final double[] xs = {+0.0, +0.0, -3.0, +3.0};
        final double[] ys = {+0.0, -4.0, +2.0, +2.0};
        final double dx = xs[id];
        final double dy = ys[id];
        return new double[] {dx, dy};
    }

    /**
     * 指定されたサブデルタIDの上位デルタからの距離を取得する。
     *
     * @param parentIsUpper 上位デルタが上向きか否か
     * @param id サブデルタID
     * @return 距離(dx, dy)を含む配列
     */
    /* package */static double[] getSubDeltaDistance(final boolean parentIsUpper, final int id)
    {
        return (parentIsUpper ? getUpperSubDeltaDistance(id) : getLowerSubDeltaDistance(id));
    }

    /**
     * デルタID列から中心座標を取得する。
     *
     * @param ids デルタID列
     * @return 中心座標(x, y)を含む配列
     */
    public static double[] getCenter(final byte[] ids)
    {
        final List<Double> xs = new ArrayList<Double>();
        final List<Double> ys = new ArrayList<Double>();

        boolean upper = false;

        for ( int i = 0, len = ids.length; i < len; i++ )
        {
            if ( i == 0 )
            {
                final double[] xy = getWorldDeltaCenter(ids[0]);
                upper = isUpperWorldDelta(ids[0]);
                xs.add(xy[0]);
                ys.add(xy[1]);
            }
            else
            {
                final double[] xy = getSubDeltaDistance(upper, ids[i]);
                upper = isUpperSubDelta(upper, ids[i]);
                xs.add(xy[0] / Math.pow(2, (i - 1)));
                ys.add(xy[1] / Math.pow(2, (i - 1)));
            }
        }

        Collections.sort(xs);
        Collections.sort(ys);

        final double x = sum(xs);
        final double y = sum(ys);

        return new double[] {(x > 12.0 ? x - 24.0 : x), y};
    }

    /**
     * デルタID列からデルタの中心座標、頂点座標を取得する。
     *
     * @param ids デルタID列
     * @return 4点の座標(x, y)を含む配列
     */
    public static double[][] getCoordinates(final byte[] ids)
    {
        final double[] cxy = getCenter(ids);
        final int level = ids.length;
        final int sign = (isUpperDelta(ids) ? +1 : -1);
        final double scale = 1.0 / Math.pow(2, level - 1) * sign;

        final double dx1 = 0.0;
        final double dy1 = 8.0 * scale;
        final double dx2 = 6.0 * scale;
        final double dy2 = 4.0 * scale;

        // @formatter:off
        return new double[][] {
            {cxy[0], cxy[1]},
            {cxy[0] + dx1, cxy[1] + dy1},
            {cxy[0] + dx2, cxy[1] - dy2},
            {cxy[0] - dx2, cxy[1] - dy2},
        };
        // @formatter:on
    }

    private static double sum(final List<Double> list)
    {
        double total = 0.0;
        for ( final double value : list )
        {
            total += value;
        }
        return total;
    }
}
