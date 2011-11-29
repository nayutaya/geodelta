
package jp.nayutaya.geodelta;

/**
 * 主要な機能を提供するクラス。
 */
public class GeoDelta
{
    /**
     * 緯度経度からデルタID列を取得する。
     *
     * @param lat 緯度
     * @param lng 経度
     * @param level レベル（1～）
     * @return デルタID列
     */
    public static byte[] getDeltaIds(final double lat, final double lng, final int level)
    {
        final double[] nxy = Projector.latLngToNxy(lat, lng);
        return Geometry.getDeltaIds(nxy[0], nxy[1], level);
    }

    /**
     * 緯度経度からGeoDeltaコードを取得する。
     *
     * @param lat 緯度
     * @param lng 経度
     * @param level レベル（1～）
     * @return GeoDeltaコード
     */
    public static String getDeltaCode(final double lat, final double lng, final int level)
    {
        final byte[] ids = GeoDelta.getDeltaIds(lat, lng, level);
        return Encoder.encode(ids);
    }

    /**
     * デルタID列からデルタの中心座標を取得する。
     *
     * @param ids デルタID列
     * @return 中心座標を含む配列
     */
    public static double[] getCenter(final byte[] ids)
    {
        final double[] nxy = Geometry.getCenter(ids);
        return Projector.nxyToLatLng(nxy[0], nxy[1]);
    }

    /**
     * GeoDeltaコードから中心座標を取得する。
     *
     * @param code GeoDeltaコード
     * @return 中心座標を含む配列
     */
    public static double[] getCenter(final String code)
    {
        final byte[] ids = Encoder.decode(code);
        return GeoDelta.getCenter(ids);
    }

    /**
     * デルタID列から中心座標と各頂点の座標を取得する。
     *
     * @param ids デルタID列
     * @return 中心座標と各頂点の座標を含む配列
     */
    public static double[][] getCoordinates(final byte[] ids)
    {
        final double[][] coordinates = Geometry.getCoordinates(ids);
        final double[][] result = new double[4][];
        for ( int i = 0; i < 4; i++ )
        {
            result[i] = Projector.nxyToLatLng(coordinates[i][0], coordinates[i][1]);
        }
        return result;
    }

    /**
     * GeoDeltaコードから中心座標と各頂点の座標を取得する。
     *
     * @param code GeoDeltaコード
     * @return 中心座標と各頂点の座標を含む配列
     */
    public static double[][] getCoordinates(final String code)
    {
        final byte[] ids = Encoder.decode(code);
        return GeoDelta.getCoordinates(ids);
    }
}
