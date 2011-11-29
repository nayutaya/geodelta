
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
}
