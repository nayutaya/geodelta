
package jp.nayutaya.geodelta;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.Random;
import org.junit.Test;

public class ProjectorTest
{
    @Test
    public void latToMy()
    {
        assertEquals(+1.0, Projector.latToMy(+85.0511), 1E-5);
        assertEquals(0.0, Projector.latToMy(0.0), 1E-15);
        assertEquals(-1.0, Projector.latToMy(-85.0511), 1E-5);
    }

    @Test
    public void lngToMx()
    {
        assertEquals(+1.0, Projector.lngToMx(+180.0), 1E-15);
        assertEquals(+0.5, Projector.lngToMx(+90.0), 1E-15);
        assertEquals(0.0, Projector.lngToMx(0.0), 1E-15);
        assertEquals(-0.5, Projector.lngToMx(-90.0), 1E-15);
        assertEquals(-1.0, Projector.lngToMx(-180.0), 1E-15);
    }

    @Test
    public void myToLat()
    {
        assertEquals(+85.0511, Projector.myToLat(+1.0), 1E-4);
        assertEquals(0.0, Projector.myToLat(0.0), 1E-15);
        assertEquals(-85.0511, Projector.myToLat(-1.0), 1E-4);
    }

    @Test
    public void mxToLng()
    {
        assertEquals(-90.0, Projector.mxToLng(+1.5), 1E-15);
        assertEquals(-180.0, Projector.mxToLng(+1.0), 1E-15);
        assertEquals(+90.0, Projector.mxToLng(+0.5), 1E-15);
        assertEquals(0.0, Projector.mxToLng(0.0), 1E-15);
        assertEquals(-90.0, Projector.mxToLng(-0.5), 1E-15);
        assertEquals(-180.0, Projector.mxToLng(-1.0), 1E-15);
        assertEquals(+90.0, Projector.mxToLng(-1.5), 1E-15);
    }

    @Test
    public void myToNy()
    {
        final double max = Projector.DELTA_HEIGHT;
        assertEquals(+12.0, Projector.myToNy(+max), 1E-15);
        assertEquals(0.0, Projector.myToNy(0.0), 1E-15);
        assertEquals(-12.0, Projector.myToNy(-max), 1E-15);
    }

    @Test
    public void mxToNx()
    {
        assertEquals(+12.0, Projector.mxToNx(+1.0), 1E-15);
        assertEquals(0.0, Projector.mxToNx(0.0), 1E-15);
        assertEquals(-12.0, Projector.mxToNx(-1.0), 1E-15);
    }

    @Test
    public void nyToMy()
    {
        final double max = Projector.DELTA_HEIGHT;
        assertEquals(+max, Projector.nyToMy(+12.0), 1E-15);
        assertEquals(0.0, Projector.nyToMy(0.0), 1E-15);
        assertEquals(-max, Projector.nyToMy(-12.0), 1E-15);
    }

    @Test
    public void nxToMx()
    {
        assertEquals(+1.0, Projector.nxToMx(+12.0), 1E-15);
        assertEquals(0.0, Projector.nxToMx(0.0), 1E-15);
        assertEquals(-1.0, Projector.nxToMx(-12.0), 1E-15);
    }

    @Test
    public void latToNy()
    {
        assertEquals(0.0, Projector.latToNy(0.0), 1E-15);
        assertEquals(Projector.myToNy(Projector.latToMy(+82.4674)), Projector.latToNy(+82.4674), 1E-15);
        assertEquals(Projector.myToNy(Projector.latToMy(-82.4674)), Projector.latToNy(-82.4674), 1E-15);
    }

    @Test
    public void lngToNx()
    {
        assertEquals(0.0, Projector.lngToNx(0.0), 1E-15);
        assertEquals(Projector.mxToNx(Projector.lngToMx(+180.0)), Projector.lngToNx(+180.0), 1E-15);
        assertEquals(Projector.mxToNx(Projector.lngToMx(-180.0)), Projector.lngToNx(-180.0), 1E-15);
    }

    @Test
    public void nyToLat()
    {
        assertEquals(0.0, Projector.nyToLat(0.0), 1E-15);
        assertEquals(Projector.myToLat(Projector.nyToMy(+12.0)), Projector.nyToLat(+12.0), 1E-15);
        assertEquals(Projector.myToLat(Projector.nyToMy(-12.0)), Projector.nyToLat(-12.0), 1E-15);
    }

    @Test
    public void nxToLng()
    {
        assertEquals(0.0, Projector.nxToLng(0.0), 1E-15);
        assertEquals(Projector.mxToLng(Projector.nxToMx(+12.0)), Projector.nxToLng(+12.0), 1E-15);
        assertEquals(Projector.mxToLng(Projector.nxToMx(-12.0)), Projector.nxToLng(-12.0), 1E-15);
    }

    @Test
    public void latLngToNxy()
    {
        assertArrayEquals(new double[] {0.0, 0.0}, Projector.latLngToNxy(0.0, 0.0), 1E-15);
        assertArrayEquals(new double[] {Projector.lngToNx(+180.0), Projector.latToNy(+82.4674)}, Projector.latLngToNxy(+82.4674, +180.0), 1E-15);
        assertArrayEquals(new double[] {Projector.lngToNx(-180.0), Projector.latToNy(-82.4674)}, Projector.latLngToNxy(-82.4674, -180.0), 1E-15);
    }

    @Test
    public void nxyToLatLng()
    {
        assertArrayEquals(new double[] {0.0, 0.0}, Projector.nxyToLatLng(0.0, 0.0), 1E-15);
        assertArrayEquals(new double[] {Projector.nyToLat(+12.0), Projector.nxToLng(+12.0)}, Projector.nxyToLatLng(+12.0, +12.0), 1E-15);
        assertArrayEquals(new double[] {Projector.nyToLat(-12.0), Projector.nxToLng(-12.0)}, Projector.nxyToLatLng(-12.0, -12.0), 1E-15);
    }

    @Test
    public void randomLatLngAndNxy()
    {
        final double latMax = Projector.nyToLat(+12.0);
        final double lngMax = 180.0;
        final Random r = new Random();

        for ( int i = 0; i < 1000; i++ )
        {
            final double lat1 = r.nextDouble() * latMax * 2 - latMax;
            final double lng1 = r.nextDouble() * lngMax * 2 - lngMax;
            final double nx = Projector.lngToNx(lng1);
            final double ny = Projector.latToNy(lat1);
            final double lat2 = Projector.nyToLat(ny);
            final double lng2 = Projector.nxToLng(nx);
            assertEquals(lat1, lat2, 1E-13);
            assertEquals(lng1, lng2, 1E-13);
        }
    }
}
