
package jp.nayutaya.geodelta;

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
        final double xx = Utility.mod((x + xs[id]), 12);
        final double yy = Utility.mod((y + ys[id]), 12);
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

    // TODO:
    /*
     * def self.get_sub_delta_distance(parent_is_upper, id)
     * if parent_is_upper
     * return self.get_upper_sub_delta_distance(id)
     * else
     * return self.get_lower_sub_delta_distance(id)
     * end
     * end
     */

    // TODO:
    /*
     * def self.get_center(ids)
     * xs, ys = [], []
     * upper = nil
     *
     * ids.each_with_index { |id, index|
     * if index == 0
     * x, y = self.get_world_delta_center(id)
     * upper = self.upper_world_delta?(id)
     * xs << x
     * ys << y
     * else
     * x, y = self.get_sub_delta_distance(upper, id)
     * upper = self.upper_sub_delta?(upper, id)
     * xs << (x / (2 ** (index - 1)))
     * ys << (y / (2 ** (index - 1)))
     * end
     * }
     *
     * x = xs.sort.inject(0.0, &:+)
     * y = ys.sort.inject(0.0, &:+)
     *
     * x -= 24.0 if x > 12.0
     *
     * return [x, y]
     * end
     */

    // TODO:
    /*
     * def self.get_coordinates(ids)
     * cx, cy = self.get_center(ids)
     * level = ids.size
     * sign = (self.upper_delta?(ids) ? +1 : -1)
     * scale = 1.0 / (2 ** (level - 1)) * sign
     *
     * dx1 = 0.0
     * dy1 = 8.0 * scale
     * dx2 = 6.0 * scale
     * dy2 = 4.0 * scale
     *
     * return [
     * [cx, cy ],
     * [cx + dx1, cy + dy1],
     * [cx + dx2, cy - dy2],
     * [cx - dx2, cy - dy2],
     * ]
     * end
     * end
     */
}
