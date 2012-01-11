
package jp.nayutaya.geodelta;

import static jp.nayutaya.geodelta.Assert.assertArrayArrayEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.Random;
import org.junit.Test;

public class GeoDeltaTest
{
    @Test
    public void getDeltaIds()
    {
        assertArrayEquals(new byte[] {0}, GeoDelta.getDeltaIds(+45.0, +0.0, 1));
        assertArrayEquals(new byte[] {1}, GeoDelta.getDeltaIds(+45.0, +90.0, 1));
        assertArrayEquals(new byte[] {2}, GeoDelta.getDeltaIds(+45.0, +180.0, 1));
        assertArrayEquals(new byte[] {3}, GeoDelta.getDeltaIds(+45.0, -90.0, 1));
        assertArrayEquals(new byte[] {2}, GeoDelta.getDeltaIds(+45.0, -180.0, 1));

        assertArrayEquals(new byte[] {4}, GeoDelta.getDeltaIds(-45.0, +0.0, 1));
        assertArrayEquals(new byte[] {5}, GeoDelta.getDeltaIds(-45.0, +90.0, 1));
        assertArrayEquals(new byte[] {6}, GeoDelta.getDeltaIds(-45.0, +180.0, 1));
        assertArrayEquals(new byte[] {7}, GeoDelta.getDeltaIds(-45.0, -90.0, 1));
        assertArrayEquals(new byte[] {6}, GeoDelta.getDeltaIds(-45.0, -180.0, 1));

        assertArrayEquals(new byte[] {0}, GeoDelta.getDeltaIds(+0.0, +0.0, 1));
        assertArrayEquals(new byte[] {0, 1}, GeoDelta.getDeltaIds(+0.0, +0.0, 2));
        assertArrayEquals(new byte[] {0, 1, 1}, GeoDelta.getDeltaIds(+0.0, +0.0, 3));
        assertArrayEquals(new byte[] {0, 1, 1, 1}, GeoDelta.getDeltaIds(+0.0, +0.0, 4));
    }

    @Test
    public void getDeltaCode()
    {
        assertEquals("Z", GeoDelta.getDeltaCode(+45.0, +0.0, 1));
        assertEquals("Y", GeoDelta.getDeltaCode(+45.0, +90.0, 1));
        assertEquals("X", GeoDelta.getDeltaCode(+45.0, +180.0, 1));
        assertEquals("W", GeoDelta.getDeltaCode(+45.0, -90.0, 1));
        assertEquals("X", GeoDelta.getDeltaCode(+45.0, -180.0, 1));

        assertEquals("V", GeoDelta.getDeltaCode(-45.0, +0.0, 1));
        assertEquals("T", GeoDelta.getDeltaCode(-45.0, +90.0, 1));
        assertEquals("S", GeoDelta.getDeltaCode(-45.0, +180.0, 1));
        assertEquals("R", GeoDelta.getDeltaCode(-45.0, -90.0, 1));
        assertEquals("S", GeoDelta.getDeltaCode(-45.0, -180.0, 1));

        assertEquals("Z", GeoDelta.getDeltaCode(+0.0, +0.0, 1));
        assertEquals("ZM", GeoDelta.getDeltaCode(+0.0, +0.0, 2));
        assertEquals("Z7", GeoDelta.getDeltaCode(+0.0, +0.0, 3));
        assertEquals("Z7M", GeoDelta.getDeltaCode(+0.0, +0.0, 4));
    }

    @Test
    public void getCenter_ids()
    {
        assertArrayEquals(new double[] {+71.480, +0.000}, GeoDelta.getCenter(new byte[] {0}), 1E-3);
        assertArrayEquals(new double[] {+46.024, +90.000}, GeoDelta.getCenter(new byte[] {1}), 1E-3);
        assertArrayEquals(new double[] {+71.480, -180.000}, GeoDelta.getCenter(new byte[] {2}), 1E-3);
        assertArrayEquals(new double[] {+46.024, -90.000}, GeoDelta.getCenter(new byte[] {3}), 1E-3);
        assertArrayEquals(new double[] {-71.480, +0.000}, GeoDelta.getCenter(new byte[] {4}), 1E-3);
        assertArrayEquals(new double[] {-46.024, +90.000}, GeoDelta.getCenter(new byte[] {5}), 1E-3);
        assertArrayEquals(new double[] {-71.480, -180.000}, GeoDelta.getCenter(new byte[] {6}), 1E-3);
        assertArrayEquals(new double[] {-46.024, -90.000}, GeoDelta.getCenter(new byte[] {7}), 1E-3);
        assertArrayEquals(new double[] {+71.480, +0.000}, GeoDelta.getCenter(new byte[] {0, 0}), 1E-3);
        assertArrayEquals(new double[] {+71.480, +0.000}, GeoDelta.getCenter(new byte[] {0, 0, 0}), 1E-3);
    }

    @Test
    public void getCenter_code()
    {
        assertArrayEquals(new double[] {+71.480, +0.000}, GeoDelta.getCenter("Z"), 1E-3);
        assertArrayEquals(new double[] {+46.024, +90.000}, GeoDelta.getCenter("Y"), 1E-3);
        assertArrayEquals(new double[] {+71.480, -180.000}, GeoDelta.getCenter("X"), 1E-3);
        assertArrayEquals(new double[] {+46.024, -90.000}, GeoDelta.getCenter("W"), 1E-3);
        assertArrayEquals(new double[] {-71.480, +0.000}, GeoDelta.getCenter("V"), 1E-3);
        assertArrayEquals(new double[] {-46.024, +90.000}, GeoDelta.getCenter("T"), 1E-3);
        assertArrayEquals(new double[] {-71.480, -180.000}, GeoDelta.getCenter("S"), 1E-3);
        assertArrayEquals(new double[] {-46.024, -90.000}, GeoDelta.getCenter("R"), 1E-3);
        assertArrayEquals(new double[] {+71.480, +0.000}, GeoDelta.getCenter("ZK"), 1E-3);
        assertArrayEquals(new double[] {+71.480, +0.000}, GeoDelta.getCenter("Z2"), 1E-3);
    }

    @Test
    public void getCoordinates_ids1()
    {
        {
            // @formatter:off
            final double[][] expecteds = {
                {+71.480,  +0.000},
                { +0.000,  +0.000},
                {+82.467, -90.000},
                {+82.467, +90.000},
            };
            // @formatter:on
            assertArrayArrayEquals(expecteds, GeoDelta.getCoordinates(new byte[] {0}), 1E-3);
        }
        {
            // @formatter:off
            final double[][] expecteds = {
                {-71.480,  +0.000},
                { +0.000,  +0.000},
                {-82.467, +90.000},
                {-82.467, -90.000},
            };
            // @formatter:on
            assertArrayArrayEquals(expecteds, GeoDelta.getCoordinates(new byte[] {4}), 1E-3);
        }
    }

    @Test
    public void getCoordinates_ids2()
    {
        final double[][][] delta = new double[8][][];
        for ( int id = 0; id < 8; id++ )
        {
            delta[id] = GeoDelta.getCoordinates(new byte[] {(byte)id});
        }

        assertArrayEquals(delta[0][1], delta[1][3], 1E-15);
        assertArrayEquals(delta[0][1], delta[3][2], 1E-15);
        assertArrayEquals(delta[0][1], delta[4][1], 1E-15);
        assertArrayEquals(delta[0][1], delta[5][2], 1E-15);
        assertArrayEquals(delta[0][1], delta[7][3], 1E-15);
        assertArrayEquals(delta[0][2], delta[2][3], 1E-15);
        assertArrayEquals(delta[0][2], delta[3][1], 1E-15);
        assertArrayEquals(delta[0][3], delta[1][1], 1E-15);
        assertArrayEquals(delta[0][3], delta[2][2], 1E-15);
        assertArrayEquals(delta[1][2], delta[2][1], 1E-15);
        assertArrayEquals(delta[1][2], delta[3][3], 1E-15);
        assertArrayEquals(delta[1][2], delta[5][3], 1E-15);
        assertArrayEquals(delta[1][2], delta[6][1], 1E-15);
        assertArrayEquals(delta[1][2], delta[7][2], 1E-15);
        assertArrayEquals(delta[4][3], delta[6][2], 1E-15);
        assertArrayEquals(delta[4][3], delta[7][1], 1E-15);
        assertArrayEquals(delta[4][2], delta[5][1], 1E-15);
        assertArrayEquals(delta[4][2], delta[6][3], 1E-15);
    }

    @Test
    public void getCoordinates_ids3()
    {
        final double[][][] delta = new double[4][][];
        for ( int id = 0; id < 4; id++ )
        {
            delta[id] = GeoDelta.getCoordinates(new byte[] {0, (byte)id});
        }

        assertArrayEquals(delta[0][1], delta[2][3], 1E-15);
        assertArrayEquals(delta[0][1], delta[3][2], 1E-15);
        assertArrayEquals(delta[0][2], delta[1][3], 1E-15);
        assertArrayEquals(delta[0][2], delta[3][1], 1E-15);
        assertArrayEquals(delta[0][3], delta[1][2], 1E-15);
        assertArrayEquals(delta[0][3], delta[2][1], 1E-15);
    }

    @Test
    public void getCoordinates_code()
    {
        {
            final double[][] expecteds = GeoDelta.getCoordinates(new byte[] {0});
            assertArrayArrayEquals(expecteds, GeoDelta.getCoordinates("Z"), 1E-15);
        }
        {
            final double[][] expecteds = GeoDelta.getCoordinates(new byte[] {0, 1, 2});
            assertArrayArrayEquals(expecteds, GeoDelta.getCoordinates("Z8"), 1E-15);
        }
    }

    @Test
    public void randomGetDeltaCodeAndGetCenter()
    {
        final double latMax = Projector.nyToLat(+12.0);
        final double lngMax = 180.0;
        final Random r = new Random();

        for ( int i = 0; i < 1000; i++ )
        {
            final double lat1 = r.nextDouble() * latMax * 2 - latMax;
            final double lng1 = r.nextDouble() * lngMax * 2 - lngMax;
            final int level = r.nextInt(30) + 1;
            final String code1 = GeoDelta.getDeltaCode(lat1, lng1, level);
            final double[] latlng2 = GeoDelta.getCenter(code1);
            final String code2 = GeoDelta.getDeltaCode(latlng2[0], latlng2[1], level);
            final double[] latlng3 = GeoDelta.getCenter(code2);
            assertEquals(code1, code2);
            assertArrayEquals(latlng2, latlng3, 1E-15);
        }
    }
}
