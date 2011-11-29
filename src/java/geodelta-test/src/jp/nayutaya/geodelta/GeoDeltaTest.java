
package jp.nayutaya.geodelta;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
        assertArrayEquals(new double[] {+71.480, +0.000}, GeoDelta.getCenter(new byte[] {0}), 1.0E-3);
        assertArrayEquals(new double[] {+46.024, +90.000}, GeoDelta.getCenter(new byte[] {1}), 1.0E-3);
        assertArrayEquals(new double[] {+71.480, -180.000}, GeoDelta.getCenter(new byte[] {2}), 1.0E-3);
        assertArrayEquals(new double[] {+46.024, -90.000}, GeoDelta.getCenter(new byte[] {3}), 1.0E-3);
        assertArrayEquals(new double[] {-71.480, +0.000}, GeoDelta.getCenter(new byte[] {4}), 1.0E-3);
        assertArrayEquals(new double[] {-46.024, +90.000}, GeoDelta.getCenter(new byte[] {5}), 1.0E-3);
        assertArrayEquals(new double[] {-71.480, -180.000}, GeoDelta.getCenter(new byte[] {6}), 1.0E-3);
        assertArrayEquals(new double[] {-46.024, -90.000}, GeoDelta.getCenter(new byte[] {7}), 1.0E-3);
        assertArrayEquals(new double[] {+71.480, +0.000}, GeoDelta.getCenter(new byte[] {0, 0}), 1.0E-3);
        assertArrayEquals(new double[] {+71.480, +0.000}, GeoDelta.getCenter(new byte[] {0, 0, 0}), 1.0E-3);
    }

    @Test
    public void getCenter_code()
    {
        assertArrayEquals(new double[] {+71.480, +0.000}, GeoDelta.getCenter("Z"), 1.0E-3);
        assertArrayEquals(new double[] {+46.024, +90.000}, GeoDelta.getCenter("Y"), 1.0E-3);
        assertArrayEquals(new double[] {+71.480, -180.000}, GeoDelta.getCenter("X"), 1.0E-3);
        assertArrayEquals(new double[] {+46.024, -90.000}, GeoDelta.getCenter("W"), 1.0E-3);
        assertArrayEquals(new double[] {-71.480, +0.000}, GeoDelta.getCenter("V"), 1.0E-3);
        assertArrayEquals(new double[] {-46.024, +90.000}, GeoDelta.getCenter("T"), 1.0E-3);
        assertArrayEquals(new double[] {-71.480, -180.000}, GeoDelta.getCenter("S"), 1.0E-3);
        assertArrayEquals(new double[] {-46.024, -90.000}, GeoDelta.getCenter("R"), 1.0E-3);
        assertArrayEquals(new double[] {+71.480, +0.000}, GeoDelta.getCenter("ZK"), 1.0E-3);
        assertArrayEquals(new double[] {+71.480, +0.000}, GeoDelta.getCenter("Z2"), 1.0E-3);
    }
}
