
package jp.nayutaya.geodelta;

/**
 * 座標系の投影を行うクラス。
 */
public class Projector
{
    /** 度をラジアンに変換するための係数 */
    /* package */static final double DEG2RAD = Math.PI / 180.0;
    /** ラジアンを度に変換するための係数 */
    /* package */static final double RAD2DEG = 180.0 / Math.PI;
    /** 一辺を1.0とする正三角形の高さ */
    /* package */static final double DELTA_HEIGHT = Math.sqrt(0.75);

    private Projector()
    {
        // nop
    }

    /**
     * 緯度をメルカトルY座標に変換する。
     *
     * @param lat 緯度
     * @return メルカトル座標系におけるY
     */
    public static double latToMy(final double lat)
    {
        return atanh(Math.sin(lat * DEG2RAD)) / Math.PI;
    }

    /**
     * 経度をメルカトルX座標に変換する。
     *
     * @param lng 経度
     * @return メルカトル座標系におけるX
     */
    public static double lngToMx(final double lng)
    {
        return lng / 180.0;
    }

    /**
     * メルカトルY座標を緯度に変換する。
     *
     * @param my メルカトル座標系におけるY
     * @return 緯度
     */
    public static double myToLat(final double my)
    {
        return Math.asin(Math.tanh(my * Math.PI)) * RAD2DEG;
    }

    /**
     * メルカトルX座標を経度に変換する。
     *
     * @param mx メルカトル座標系におけるX
     * @return 経度
     */
    public static double mxToLng(final double mx)
    {
        double x = Utility.mod(mx, 2.0) - 2.0;
        if ( x < -1.0 )
        {
            x += 2.0;
        }
        return x * 180.0;
    }

    /**
     * メルカトルY座標から正規化Y座標に変換する。
     *
     * @param my メルカトル座標系におけるY
     * @return 正規化座標系におけるY
     */
    public static double myToNy(final double my)
    {
        return my / DELTA_HEIGHT * 12.0;
    }

    /**
     * メルカトルX座標から正規化X座標に変換する。
     *
     * @param mx メルカトル座標系におけるX
     * @return 正規化座標系におけるX
     */
    public static double mxToNx(final double mx)
    {
        return mx * 12.0;
    }

    /**
     * 正規化Y座標からメルカトルY座標に変換する。
     *
     * @param ny 正規化座標系におけるY
     * @return メルカトル座標系におけるY
     */
    public static double nyToMy(final double ny)
    {
        return ny / 12.0 * DELTA_HEIGHT;
    }

    /**
     * 正規化X座標からメルカトルX座標に変換する。
     *
     * @param nx 正規化座標系におけるX
     * @return メルカトル座標系におけるX
     */
    public static double nxToMx(final double nx)
    {
        return nx / 12.0;
    }

    /**
     * 双曲線逆正接を計算する。
     *
     * @param x 値
     * @return 双曲線逆正接
     */
    private static double atanh(final double x)
    {
        return Math.log((1 + x) / (1 - x)) / 2;
    }
}
