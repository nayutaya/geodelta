
package jp.nayutaya.geodelta;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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

    // TODO: test_getDeltaIdsInRegion__random
    // def test_getDeltaIdsInRegion__random
    // 10.times {
    // (3..5).each { |level|
    // x1, x2 = [(rand * 24) - 12, (rand * 24) - 12].sort
    // y1, y2 = [(rand * 24) - 12, (rand * 24) - 12].sort.reverse
    // regional_ids = GeoDelta::Region.getDeltaIdsInRegion(x1, y1, x2, y2, level)
    // 10.times {
    // cx, cy = [x1 + (rand * (x2 - x1)), y1 - (rand * (y1 - y2))]
    // test_ids = GeoDelta::DeltaGeometry.get_delta_ids(cx, cy, level)
    // assert_equal(
    // {:rect => [[x1, y1], [x2, y2]], :test_ids => test_ids, :include => true},
    // {:rect => [[x1, y1], [x2, y2]], :test_ids => test_ids, :include => regional_ids.include?(test_ids)})
    // }
    // }
    // }
    // end

    private void assertArrayArrayEquals(final byte[][] expecteds, final byte[][] actuals)
    {
        assertEquals(expecteds.length, actuals.length);
        for ( int i = 0, len = expecteds.length; i < len; i++ )
        {
            assertArrayEquals(expecteds[i], actuals[i]);
        }
    }

    @SuppressWarnings("unused")
    private void assertArrayArrayEqualsWithPrint(final byte[][] expecteds, final byte[][] actuals)
    {
        System.out.print("expecteds {");
        for ( int i = 0; i < expecteds.length; i++ )
        {
            System.out.print(" {");
            for ( int j = 0; j < expecteds[i].length; j++ )
            {
                System.out.print(" " + expecteds[i][j]);
            }
            System.out.print(" }");
        }
        System.out.println(" }");

        System.out.print("actuals   {");
        for ( int i = 0; i < actuals.length; i++ )
        {
            System.out.print(" {");
            for ( int j = 0; j < actuals[i].length; j++ )
            {
                System.out.print(" " + actuals[i][j]);
            }
            System.out.print(" }");
        }
        System.out.println(" }");

        assertArrayArrayEquals(expecteds, actuals);
    }
}
