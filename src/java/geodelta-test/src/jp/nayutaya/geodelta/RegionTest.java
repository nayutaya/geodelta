
package jp.nayutaya.geodelta;

import static jp.nayutaya.geodelta.Assert.assertArrayArrayEquals;
import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.Test;

public class RegionTest
{
    @Test
    public void getDeltaIdsInRegion_level1()
    {
        // @formatter:off
        assertArrayArrayEquals(
            new byte[][] {{0}},
            Region.getDeltaIdsInRegion(+0.0, +8.0, +0.0, +8.0, 1));
        assertArrayArrayEquals(
            new byte[][] {{1}},
            Region.getDeltaIdsInRegion(+6.0, +4.0, +6.0, +4.0, 1));

        assertArrayArrayEquals(
            new byte[][] {{0}, {1}},
            Region.getDeltaIdsInRegion(+1.0, +6.0, +5.0, +6.0, 1));
        assertArrayArrayEquals(
            new byte[][] {{4}, {5}},
            Region.getDeltaIdsInRegion(+1.0, -6.0, +5.0, -6.0, 1));
        assertArrayArrayEquals(
            new byte[][] {{0}, {4}},
            Region.getDeltaIdsInRegion(+0.0, +5.0, +0.0, -5.0, 1));
        assertArrayArrayEquals(
            new byte[][] {{1}, {5}},
            Region.getDeltaIdsInRegion(+6.0, +5.0, +6.0, -5.0, 1));

        assertArrayArrayEquals(
            new byte[][] {{0}, {1}, {2}},
            Region.getDeltaIdsInRegion(+1.0, +6.0, +11.0, +6.0, 1));
        assertArrayArrayEquals(
            new byte[][] {{4}, {5}, {6}},
            Region.getDeltaIdsInRegion(+1.0, -6.0, +11.0, -6.0, 1));

        assertArrayArrayEquals(
            new byte[][] {{0}, {1}, {4}, {5}},
            Region.getDeltaIdsInRegion(+1.0, +5.0, +5.0, -5.0, 1));
        // @formatter:on
    }

    @Test
    public void getDeltaIdsInRegion_level1_roundtrip()
    {
        // @formatter:off
        assertArrayArrayEquals(
            new byte[][] {{0}, {1}, {3}},
            Region.getDeltaIdsInRegion(-6.0, +6.0, +6.0, +6.0, 1));
        assertArrayArrayEquals(
            new byte[][] {{0}, {2}, {3}},
            Region.getDeltaIdsInRegion(+12.0, +6.0, -6.0, +6.0, 1));
        // @formatter:on
    }

    @Test
    public void getDeltaIdsInRegion_level2()
    {
        // @formatter:off
        assertArrayArrayEquals(
            new byte[][] {{0, 1}, {1, 3}, {3, 2}, {4, 1}, {5, 2}, {7, 3}},
            Region.getDeltaIdsInRegion(-1.0, +4.0, +1.0, -4.0, 2));
        assertArrayArrayEquals(
            new byte[][] {{0, 1}, {1, 3}, {3, 2}, {4, 1}, {5, 2}, {7, 3}},
            Region.getDeltaIdsInRegion(-4.0, +1.0, +4.0, -1.0, 2));
        assertArrayArrayEquals(
            new byte[][] {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {1, 3}, {3, 2}, {4, 0}, {4, 1}, {4, 2}, {4, 3}, {5, 2}, {7, 3}},
            Region.getDeltaIdsInRegion(-3.0, +9.0, +3.0, -9.0, 2));
        // @formatter:on
    }

    @Test
    public void getDeltaIdsInRegion_level3()
    {
        {
            // @formatter:off
            final byte[][] expecteds = {
                {0, 1, 1},
                {1, 3, 3},
                {3, 2, 2},
                {4, 1, 1},
                {5, 2, 2},
                {7, 3, 3},
            };
            // @formatter:on
            assertArrayArrayEquals(expecteds, Region.getDeltaIdsInRegion(-1.5, +1.5, +1.5, -1.5, 3));
        }
        {
            // @formatter:off
            final byte[][] expecteds = {
                {2, 1, 1},
                {3, 3, 0},
                {3, 3, 3},
                {6, 1, 1},
                {7, 2, 0},
                {7, 2, 2},
            };
            // @formatter:on
            assertArrayArrayEquals(expecteds, Region.getDeltaIdsInRegion(-11.5, +1.5, -9.5, -1.5, 3));
        }
    }

    @Test
    public void randomGetDeltaIdsInRegion()
    {
        final Random r = new Random();

        for ( int level = 3; level <= 5; level++ )
        {
            for ( int i = 0; i < 10; i++ )
            {
                final double xx1 = (r.nextDouble() * 24.0) - 12.0;
                final double xx2 = (r.nextDouble() * 24.0) - 12.0;
                final double yy1 = (r.nextDouble() * 24.0) - 12.0;
                final double yy2 = (r.nextDouble() * 24.0) - 12.0;
                final double x1 = Math.min(xx1, xx2);
                final double x2 = Math.max(xx1, xx2);
                final double y1 = Math.max(yy1, yy2);
                final double y2 = Math.min(yy1, yy2);

                final byte[][] regionalIdArray = Region.getDeltaIdsInRegion(x1, y1, x2, y2, level);
                final Set<Long> regionalIdSet = new HashSet<Long>();
                for ( byte[] id : regionalIdArray )
                {
                    regionalIdSet.add(Packer64.pack(id));
                }

                for ( int j = 0; j < 10; j++ )
                {
                    final double cx = x1 + (r.nextDouble() * (x2 - x1));
                    final double cy = y1 - (r.nextDouble() * (y1 - y2));
                    final byte[] testId = Geometry.getDeltaIds(cx, cy, level);
                    assertTrue(regionalIdSet.contains(Packer64.pack(testId)));
                }
            }
        }
    }
}
