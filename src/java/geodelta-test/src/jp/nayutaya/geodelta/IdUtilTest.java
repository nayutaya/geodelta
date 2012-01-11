
package jp.nayutaya.geodelta;

import static jp.nayutaya.geodelta.Assert.assertArrayArrayEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class IdUtilTest
{
    @Test
    public void getAllDeltaIds__level1()
    {
        // @formatter:off
        assertArrayArrayEquals(
            new byte[][] {{0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}},
            IdUtil.getAllDeltaIds(1));
        // @formatter:on
    }

    @Test
    public void getAllDeltaIds__level2()
    {
        // @formatter:off
        final byte[][] expecteds = {
            {0, 0}, {0, 1}, {0, 2}, {0, 3},
            {1, 0}, {1, 1}, {1, 2}, {1, 3},
            {2, 0}, {2, 1}, {2, 2}, {2, 3},
            {3, 0}, {3, 1}, {3, 2}, {3, 3},
            {4, 0}, {4, 1}, {4, 2}, {4, 3},
            {5, 0}, {5, 1}, {5, 2}, {5, 3},
            {6, 0}, {6, 1}, {6, 2}, {6, 3},
            {7, 0}, {7, 1}, {7, 2}, {7, 3},
        };
        // @formatter:on
        assertArrayArrayEquals(expecteds, IdUtil.getAllDeltaIds(2));
    }

    @Test
    public void getAllDeltaIds__size()
    {
        assertEquals(8, IdUtil.getAllDeltaIds(1).length);
        assertEquals(32, IdUtil.getAllDeltaIds(2).length);
        assertEquals(128, IdUtil.getAllDeltaIds(3).length);
        assertEquals(512, IdUtil.getAllDeltaIds(4).length);
    }

    @Test
    public void getAllDeltaIds__order()
    {
        final byte[][] ids = IdUtil.getAllDeltaIds(3);
        assertArrayEquals(new byte[] {0, 0, 0}, ids[0]);
        assertArrayEquals(new byte[] {0, 0, 1}, ids[1]);
        assertArrayEquals(new byte[] {7, 3, 2}, ids[ids.length - 2]);
        assertArrayEquals(new byte[] {7, 3, 3}, ids[ids.length - 1]);
    }
}
