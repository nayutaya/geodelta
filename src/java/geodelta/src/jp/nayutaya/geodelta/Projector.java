
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
     * メルカトルY座標を正規化Y座標に変換する。
     *
     * @param my メルカトル座標系におけるY
     * @return 正規化座標系におけるY
     */
    public static double myToNy(final double my)
    {
        return my / DELTA_HEIGHT * 12.0;
    }

    /**
     * メルカトルX座標を正規化X座標に変換する。
     *
     * @param mx メルカトル座標系におけるX
     * @return 正規化座標系におけるX
     */
    public static double mxToNx(final double mx)
    {
        return mx * 12.0;
    }

    /**
     * 正規化Y座標をメルカトルY座標に変換する。
     *
     * @param ny 正規化座標系におけるY
     * @return メルカトル座標系におけるY
     */
    public static double nyToMy(final double ny)
    {
        return ny / 12.0 * DELTA_HEIGHT;
    }

    /**
     * 正規化X座標をメルカトルX座標に変換する。
     *
     * @param nx 正規化座標系におけるX
     * @return メルカトル座標系におけるX
     */
    public static double nxToMx(final double nx)
    {
        return nx / 12.0;
    }

    /**
     * 緯度を正規化Y座標系に変換する。
     *
     * @param lat 緯度
     * @return 正規化座標系におけるY
     */
    public static double latToNy(final double lat)
    {
        return Projector.myToNy(Projector.latToMy(lat));
    }

    /**
     * 経度を正規化X座標に変換する。
     *
     * @param lng 経度
     * @return 正規化座標系におけるX
     */
    public static double lngToNx(final double lng)
    {
        return Projector.mxToNx(Projector.lngToMx(lng));
    }

    /**
     * 正規化Y座標を緯度に変換する。
     *
     * @param ny 正規化座標系におけるY
     * @return 緯度
     */
    public static double nyToLat(final double ny)
    {
        return Projector.myToLat(Projector.nyToMy(ny));
    }

    /**
     * 正規化X座標を経度に変換する。
     *
     * @param nx 正規化座標系におけるX
     * @return 経度
     */
    public static double nxToLng(final double nx)
    {
        return Projector.mxToLng(Projector.nxToMx(nx));
    }

    /**
     * 緯度経度を正規化XY座標に変換する。
     *
     * @param lat 緯度
     * @param lng 経度
     * @return 正規化XY座標を含む配列
     */
    public static double[] latLngToNxy(final double lat, final double lng)
    {
        final double nx = Projector.lngToNx(lng);
        final double ny = Projector.latToNy(lat);
        return new double[] {nx, ny};
    }

    /**
     * 正規化XY座標を緯度経度に変換する。
     *
     * @param nx 正規化座標系におけるX
     * @param ny 正規化座標系におけるY
     * @return 緯度経度を含む配列
     */
    public static double[] nxyToLatLng(final double nx, final double ny)
    {
        final double lat = Projector.nyToLat(ny);
        final double lng = Projector.nxToLng(nx);
        return new double[] {lat, lng};
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
