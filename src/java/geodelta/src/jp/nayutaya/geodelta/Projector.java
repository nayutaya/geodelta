
package jp.nayutaya.geodelta;

public class Projector
{
    /** 度をラジアンに変換するための係数 */
    public static final double DEG2RAD = Math.PI / 180.0;
    /** ラジアンを度に変換するための係数 */
    public static final double RAD2DEG = 180.0 / Math.PI;
    /** 一辺を1.0とする正三角形の高さ */
    public static final double DELTA_HEIGHT = Math.sqrt(0.75);

    private Projector()
    {
        // nop
    }

    /**
     * 緯度をメルカトルY座標に変換する。
     *
     * @param lat
     * @return
     */
    public static double latToMy(final double lat)
    {
        return atanh(Math.sin(lat * DEG2RAD)) / Math.PI;
    }

    /**
     * 経度をメルカトルX座標に変換する。
     *
     * @param lng
     * @return
     */
    public static double lngToMx(final double lng)
    {
        return lng / 180.0;
    }

    /**
     * メルカトルY座標を緯度に変換する。
     *
     * @param my
     * @return
     */
    public static double myToLat(final double my)
    {
        return Math.asin(Math.tanh(my * Math.PI)) * RAD2DEG;
    }

    /**
     * メルカトルX座標を経度に変換する。
     *
     * @param mx
     * @return
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
     * @param my
     * @return
     */
    public static double myToNy(final double my)
    {
        return my / DELTA_HEIGHT * 12.0;
    }

    /**
     * メルカトルX座標から正規化X座標に変換する。
     *
     * @param mx
     * @return
     */
    public static double mxToNx(final double mx)
    {
        return mx * 12.0;
    }

    /**
     * 正規化Y座標からメルカトルY座標に変換する。
     *
     * @param my
     * @return
     */
    public static double nyToMy(final double my)
    {
        return my / 12.0 * DELTA_HEIGHT;
    }

    /**
     * 正規化X座標からメルカトルX座標に変換する。
     *
     * @param nx
     * @return
     */
    public static double nxToMx(final double nx)
    {
        return nx / 12.0;
    }

    private static double atanh(final double x)
    {
        return Math.log((1 + x) / (1 - x)) / 2;
    }
}
