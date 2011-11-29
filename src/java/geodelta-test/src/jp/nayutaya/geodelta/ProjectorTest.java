
package jp.nayutaya.geodelta;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ProjectorTest
{
    @Test
    public void latToMy()
    {
        assertEquals(+1.0, Projector.latToMy(+85.0511), 1.0E-5);
        assertEquals(0.0, Projector.latToMy(0.0), 1.0E-15);
        assertEquals(-1.0, Projector.latToMy(-85.0511), 1.0E-5);
    }

    @Test
    public void lngToMx()
    {
        assertEquals(+1.0, Projector.lngToMx(+180.0), 1.0E-15);
        assertEquals(+0.5, Projector.lngToMx(+90.0), 1.0E-15);
        assertEquals(0.0, Projector.lngToMx(0.0), 1.0E-15);
        assertEquals(-0.5, Projector.lngToMx(-90.0), 1.0E-15);
        assertEquals(-1.0, Projector.lngToMx(-180.0), 1.0E-15);
    }

    @Test
    public void myToLat()
    {
        assertEquals(+85.0511, Projector.myToLat(+1.0), 1.0E-4);
        assertEquals(0.0, Projector.myToLat(0.0), 1.0E-15);
        assertEquals(-85.0511, Projector.myToLat(-1.0), 1.0E-4);
    }

    @Test
    public void mxToLng()
    {
        assertEquals(-90.0, Projector.mxToLng(+1.5), 1.0E-15);
        assertEquals(-180.0, Projector.mxToLng(+1.0), 1.0E-15);
        assertEquals(+90.0, Projector.mxToLng(+0.5), 1.0E-15);
        assertEquals(0.0, Projector.mxToLng(0.0), 1.0E-15);
        assertEquals(-90.0, Projector.mxToLng(-0.5), 1.0E-15);
        assertEquals(-180.0, Projector.mxToLng(-1.0), 1.0E-15);
        assertEquals(+90.0, Projector.mxToLng(-1.5), 1.0E-15);
    }

    @Test
    public void myToNy()
    {
        final double max = Projector.DELTA_HEIGHT;
        assertEquals(+12.0, Projector.myToNy(+max), 1.0E-15);
        assertEquals(0.0, Projector.myToNy(0.0), 1.0E-15);
        assertEquals(-12.0, Projector.myToNy(-max), 1.0E-15);
    }

    @Test
    public void mxToNx()
    {
        assertEquals(+12.0, Projector.mxToNx(+1.0), 1.0E-15);
        assertEquals(0.0, Projector.mxToNx(0.0), 1.0E-15);
        assertEquals(-12.0, Projector.mxToNx(-1.0), 1.0E-15);
    }

    @Test
    public void nyToMy()
    {
        final double max = Projector.DELTA_HEIGHT;
        assertEquals(+max, Projector.nyToMy(+12.0), 1.0E-15);
        assertEquals(0.0, Projector.nyToMy(0.0), 1.0E-15);
        assertEquals(-max, Projector.nyToMy(-12.0), 1.0E-15);
    }

    @Test
    public void nxToMx()
    {
        assertEquals(+1.0, Projector.nxToMx(+12.0), 1.0E-15);
        assertEquals(0.0, Projector.nxToMx(0.0), 1.0E-15);
        assertEquals(-1.0, Projector.nxToMx(-12.0), 1.0E-15);
    }

    @Test
    public void latToNy()
    {
        assertEquals(0.0, Projector.latToNy(0.0), 1.0E-15);
        assertEquals(Projector.myToNy(Projector.latToMy(+82.4674)), Projector.latToNy(+82.4674), 1.0E-15);
        assertEquals(Projector.myToNy(Projector.latToMy(-82.4674)), Projector.latToNy(-82.4674), 1.0E-15);
    }

    @Test
    public void lngToNx()
    {
        assertEquals(0.0, Projector.lngToNx(0.0), 1.0E-15);
        assertEquals(Projector.mxToNx(Projector.lngToMx(+180.0)), Projector.lngToNx(+180.0), 1.0E-15);
        assertEquals(Projector.mxToNx(Projector.lngToMx(-180.0)), Projector.lngToNx(-180.0), 1.0E-15);
    }

    @Test
    public void nyToLat()
    {
        assertEquals(0.0, Projector.nyToLat(0.0), 1.0E-15);
        assertEquals(Projector.myToLat(Projector.nyToMy(+12.0)), Projector.nyToLat(+12.0), 1.0E-15);
        assertEquals(Projector.myToLat(Projector.nyToMy(-12.0)), Projector.nyToLat(-12.0), 1.0E-15);
    }

    @Test
    public void nxToLng()
    {
        assertEquals(0.0, Projector.nxToLng(0.0), 1.0E-15);
        assertEquals(Projector.mxToLng(Projector.nxToMx(+12.0)), Projector.nxToLng(+12.0), 1.0E-15);
        assertEquals(Projector.mxToLng(Projector.nxToMx(-12.0)), Projector.nxToLng(-12.0), 1.0E-15);
    }

    @Test
    public void latLngToNxy()
    {
        assertArrayEquals(new double[] {0.0, 0.0}, Projector.latLngToNxy(0.0, 0.0), 1.0E-15);
        assertArrayEquals(new double[] {Projector.lngToNx(+180.0), Projector.latToNy(+82.4674)}, Projector.latLngToNxy(+82.4674, +180.0), 1.0E-15);
        assertArrayEquals(new double[] {Projector.lngToNx(-180.0), Projector.latToNy(-82.4674)}, Projector.latLngToNxy(-82.4674, -180.0), 1.0E-15);
    }

    @Test
    public void nxyToLatLng()
    {
        assertArrayEquals(new double[] {0.0, 0.0}, Projector.nxyToLatLng(0.0, 0.0), 1.0E-15);
        assertArrayEquals(new double[] {Projector.nyToLat(+12.0), Projector.nxToLng(+12.0)}, Projector.nxyToLatLng(+12.0, +12.0), 1.0E-15);
        assertArrayEquals(new double[] {Projector.nyToLat(-12.0), Projector.nxToLng(-12.0)}, Projector.nxyToLatLng(-12.0, -12.0), 1.0E-15);
    }

    // TODO: test_rush__lat
    /*
     * def test_rush__lat
     * 1000.times {
     * lat1 = rand * 180.0 - 90.0
     * my1 = @mod.lat_to_my(lat1)
     * ny1 = @mod.my_to_ny(my1)
     * my2 = @mod.ny_to_my(ny1)
     * lat2 = @mod.my_to_lat(my2)
     * assert_in_delta(lat1, lat2, 1.0E-10)
     * }
     * end
     */

    // TODO: test_rush__lng
    /*
     * def test_rush__lng
     * 1000.times {
     * lng1 = rand * 360.0 - 180.0
     * mx1 = @mod.lng_to_mx(lng1)
     * nx1 = @mod.mx_to_nx(mx1)
     * mx2 = @mod.nx_to_mx(nx1)
     * lng2 = @mod.mx_to_lng(mx2)
     * assert_in_delta(lng1, lng2, 1.0E-13)
     * }
     * end
     */
}
