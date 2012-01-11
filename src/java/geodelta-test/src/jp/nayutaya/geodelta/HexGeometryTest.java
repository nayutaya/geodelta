
package jp.nayutaya.geodelta;

import static jp.nayutaya.geodelta.Assert.assertArrayArrayEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class HexGeometryTest
{
    @Test
    public void getHexPosition__level3_center()
    {
        assertEquals(0, HexGeometry.getHexPosition(new byte[] {0, 1, 1}));
        assertEquals(1, HexGeometry.getHexPosition(new byte[] {1, 3, 3}));
        assertEquals(2, HexGeometry.getHexPosition(new byte[] {5, 2, 2}));
        assertEquals(3, HexGeometry.getHexPosition(new byte[] {4, 1, 1}));
        assertEquals(4, HexGeometry.getHexPosition(new byte[] {7, 3, 3}));
        assertEquals(5, HexGeometry.getHexPosition(new byte[] {3, 2, 2}));
    }

    @Test
    public void getHexPosition__level3_upper()
    {
        assertEquals(0, HexGeometry.getHexPosition(new byte[] {0, 0, 0}));
        assertEquals(1, HexGeometry.getHexPosition(new byte[] {0, 0, 2}));
        assertEquals(2, HexGeometry.getHexPosition(new byte[] {0, 1, 3}));
        assertEquals(3, HexGeometry.getHexPosition(new byte[] {0, 1, 0}));
        assertEquals(4, HexGeometry.getHexPosition(new byte[] {0, 1, 2}));
        assertEquals(5, HexGeometry.getHexPosition(new byte[] {0, 0, 3}));
    }

    @Test
    public void getHexPosition__level3_upper_right()
    {
        assertEquals(0, HexGeometry.getHexPosition(new byte[] {1, 0, 2}));
        assertEquals(1, HexGeometry.getHexPosition(new byte[] {1, 0, 0}));
        assertEquals(2, HexGeometry.getHexPosition(new byte[] {1, 0, 1}));
        assertEquals(3, HexGeometry.getHexPosition(new byte[] {1, 3, 2}));
        assertEquals(4, HexGeometry.getHexPosition(new byte[] {1, 3, 0}));
        assertEquals(5, HexGeometry.getHexPosition(new byte[] {1, 3, 1}));
    }

    @Test
    public void getHexPosition__level3_lower_right()
    {
        assertEquals(0, HexGeometry.getHexPosition(new byte[] {5, 2, 3}));
        assertEquals(1, HexGeometry.getHexPosition(new byte[] {5, 0, 1}));
        assertEquals(2, HexGeometry.getHexPosition(new byte[] {5, 0, 0}));
        assertEquals(3, HexGeometry.getHexPosition(new byte[] {5, 0, 3}));
        assertEquals(4, HexGeometry.getHexPosition(new byte[] {5, 2, 1}));
        assertEquals(5, HexGeometry.getHexPosition(new byte[] {5, 2, 0}));
    }

    @Test
    public void getHexPosition__level3_lower()
    {
        assertEquals(0, HexGeometry.getHexPosition(new byte[] {4, 1, 0}));
        assertEquals(1, HexGeometry.getHexPosition(new byte[] {4, 1, 2}));
        assertEquals(2, HexGeometry.getHexPosition(new byte[] {4, 0, 3}));
        assertEquals(3, HexGeometry.getHexPosition(new byte[] {4, 0, 0}));
        assertEquals(4, HexGeometry.getHexPosition(new byte[] {4, 0, 2}));
        assertEquals(5, HexGeometry.getHexPosition(new byte[] {4, 1, 3}));
    }

    @Test
    public void getHexPosition__level3_lower_left()
    {
        assertEquals(0, HexGeometry.getHexPosition(new byte[] {7, 3, 2}));
        assertEquals(1, HexGeometry.getHexPosition(new byte[] {7, 3, 0}));
        assertEquals(2, HexGeometry.getHexPosition(new byte[] {7, 3, 1}));
        assertEquals(3, HexGeometry.getHexPosition(new byte[] {7, 0, 2}));
        assertEquals(4, HexGeometry.getHexPosition(new byte[] {7, 0, 0}));
        assertEquals(5, HexGeometry.getHexPosition(new byte[] {7, 0, 1}));
    }

    @Test
    public void getHexPosition__level3_upper_left()
    {
        assertEquals(0, HexGeometry.getHexPosition(new byte[] {3, 0, 3}));
        assertEquals(1, HexGeometry.getHexPosition(new byte[] {3, 2, 1}));
        assertEquals(2, HexGeometry.getHexPosition(new byte[] {3, 2, 0}));
        assertEquals(3, HexGeometry.getHexPosition(new byte[] {3, 2, 3}));
        assertEquals(4, HexGeometry.getHexPosition(new byte[] {3, 0, 1}));
        assertEquals(5, HexGeometry.getHexPosition(new byte[] {3, 0, 0}));
    }

    @Test
    public void getBaseDeltaIds__level3_center()
    {
        assertArrayEquals(new byte[] {0, 1, 1}, HexGeometry.getBaseDeltaIds(new byte[] {0, 1, 1}));
        assertArrayEquals(new byte[] {0, 1, 1}, HexGeometry.getBaseDeltaIds(new byte[] {1, 3, 3}));
        assertArrayEquals(new byte[] {0, 1, 1}, HexGeometry.getBaseDeltaIds(new byte[] {5, 2, 2}));
        assertArrayEquals(new byte[] {0, 1, 1}, HexGeometry.getBaseDeltaIds(new byte[] {4, 1, 1}));
        assertArrayEquals(new byte[] {0, 1, 1}, HexGeometry.getBaseDeltaIds(new byte[] {7, 3, 3}));
        assertArrayEquals(new byte[] {0, 1, 1}, HexGeometry.getBaseDeltaIds(new byte[] {3, 2, 2}));
    }

    @Test
    public void getBaseDeltaIds__level3_upper()
    {
        assertArrayEquals(new byte[] {0, 0, 0}, HexGeometry.getBaseDeltaIds(new byte[] {0, 0, 0}));
        assertArrayEquals(new byte[] {0, 0, 0}, HexGeometry.getBaseDeltaIds(new byte[] {0, 0, 2}));
        assertArrayEquals(new byte[] {0, 0, 0}, HexGeometry.getBaseDeltaIds(new byte[] {0, 1, 3}));
        assertArrayEquals(new byte[] {0, 0, 0}, HexGeometry.getBaseDeltaIds(new byte[] {0, 1, 0}));
        assertArrayEquals(new byte[] {0, 0, 0}, HexGeometry.getBaseDeltaIds(new byte[] {0, 1, 2}));
        assertArrayEquals(new byte[] {0, 0, 0}, HexGeometry.getBaseDeltaIds(new byte[] {0, 0, 3}));
    }

    @Test
    public void getBaseDeltaIds__level3_upper_right()
    {
        assertArrayEquals(new byte[] {1, 0, 2}, HexGeometry.getBaseDeltaIds(new byte[] {1, 0, 2}));
        assertArrayEquals(new byte[] {1, 0, 2}, HexGeometry.getBaseDeltaIds(new byte[] {1, 0, 0}));
        assertArrayEquals(new byte[] {1, 0, 2}, HexGeometry.getBaseDeltaIds(new byte[] {1, 0, 1}));
        assertArrayEquals(new byte[] {1, 0, 2}, HexGeometry.getBaseDeltaIds(new byte[] {1, 3, 2}));
        assertArrayEquals(new byte[] {1, 0, 2}, HexGeometry.getBaseDeltaIds(new byte[] {1, 3, 0}));
        assertArrayEquals(new byte[] {1, 0, 2}, HexGeometry.getBaseDeltaIds(new byte[] {1, 3, 1}));
    }

    @Test
    public void getBaseDeltaIds__level3_center_top()
    {
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {0, 2, 3}));
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {0, 0, 1}));
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {0, 3, 2}));
    }

    @Test
    public void getBaseDeltaIds__level3_left_top()
    {
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {2, 3, 2}));
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {2, 3, 0}));
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {2, 3, 3}));
    }

    @Test
    public void getBaseDeltaIds__level3_right_top()
    {
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {2, 2, 2}));
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {2, 2, 0}));
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {2, 2, 3}));
    }

    @Test
    public void getBaseDeltaIds__level3_roundtrip()
    {
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {2, 0, 1}));
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {2, 0, 0}));
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {2, 1, 0}));
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {2, 1, 1}));
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {6, 1, 1}));
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {6, 1, 0}));
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {6, 0, 0}));
        assertNull(HexGeometry.getBaseDeltaIds(new byte[] {6, 0, 0}));
    }

    @Test
    public void getBaseDeltaIds__level4_center()
    {
        assertArrayEquals(new byte[] {0, 1, 1, 1}, HexGeometry.getBaseDeltaIds(new byte[] {0, 1, 1, 1}));
        assertArrayEquals(new byte[] {0, 1, 1, 1}, HexGeometry.getBaseDeltaIds(new byte[] {1, 3, 3, 3}));
        assertArrayEquals(new byte[] {0, 1, 1, 1}, HexGeometry.getBaseDeltaIds(new byte[] {5, 2, 2, 2}));
        assertArrayEquals(new byte[] {0, 1, 1, 1}, HexGeometry.getBaseDeltaIds(new byte[] {4, 1, 1, 1}));
        assertArrayEquals(new byte[] {0, 1, 1, 1}, HexGeometry.getBaseDeltaIds(new byte[] {7, 3, 3, 3}));
        assertArrayEquals(new byte[] {0, 1, 1, 1}, HexGeometry.getBaseDeltaIds(new byte[] {3, 2, 2, 2}));
    }

    // TODO: def test_getBaseDeltaIds__all_hexes_level1
    // all_ids = GeoDelta::IdUtil.get_all_delta_ids(1)
    // all_hexes = all_ids.map { |ids| HexGeometry.getBaseDeltaIds(ids) }.compact.sort.uniq
    // assertEquals([[0]], all_hexes)
    // end

    // TODO: def test_getBaseDeltaIds__all_hexes_level2
    // all_ids = GeoDelta::IdUtil.get_all_delta_ids(2)
    // all_hexes = all_ids.map { |ids| HexGeometry.getBaseDeltaIds(ids) }.compact.sort.uniq
    // expected = [
    // [0, 1],
    // [2, 2],
    // [5, 3],
    // ]
    // assertEquals(expected, all_hexes)
    // end

    // TODO: def test_getBaseDeltaIds__all_hexes_level3
    // all_ids = GeoDelta::IdUtil.get_all_delta_ids(3)
    // all_hexes = all_ids.map { |ids| HexGeometry.getBaseDeltaIds(ids) }.compact.sort.uniq
    // expected = [
    // [0, 0, 0],
    // [0, 1, 1],
    // [0, 2, 2],
    // [0, 3, 3],
    // [1, 0, 2],
    // [1, 2, 0],
    // [2, 2, 1],
    // [2, 3, 1],
    // [3, 0, 3],
    // [3, 3, 0],
    // [4, 1, 0],
    // [5, 1, 2],
    // [5, 2, 3],
    // [5, 3, 1],
    // [7, 1, 3],
    // [7, 2, 1],
    // [7, 3, 2],
    // ]
    // assertEquals(expected, all_hexes)
    // end

    @Test
    public void getPartDeltaIds__level1()
    {
        // @formatter:off
        assertArrayArrayEquals(
            new byte[][] {{0}, {1}, {5}, {4}, {7}, {3}},
            HexGeometry.getPartDeltaIds(new byte[] {0}));
        // @formatter:on
    }

    @Test
    public void getPartDeltaIds__level3()
    {
        // @formatter:off
        assertArrayArrayEquals(
            new byte[][] {{0, 1, 1}, {1, 3, 3}, {5, 2, 2}, {4, 1, 1}, {7, 3, 3}, {3, 2, 2}},
            HexGeometry.getPartDeltaIds(new byte[] {0, 1, 1}));
        assertArrayArrayEquals(
            new byte[][] {{0, 0, 0}, {0, 0, 2}, {0, 1, 3}, {0, 1, 0}, {0, 1, 2}, {0, 0, 3}},
            HexGeometry.getPartDeltaIds(new byte[] {0, 0, 0}));
        assertArrayArrayEquals(
            new byte[][] {{5, 2, 3}, {5, 0, 1}, {5, 0, 0}, {5, 0, 3}, {5, 2, 1}, {5, 2, 0}},
            HexGeometry.getPartDeltaIds(new byte[] {5, 2, 3}));
        // @formatter:on
    }

    @Test
    public void getCoordinates__level3_1()
    {
        // @formatter:off
        final double[][] expecteds = {
            {+1.5, +3.0},
            {+3.0, +0.0},
            {+1.5, -3.0},
            {-1.5, -3.0},
            {-3.0, +0.0},
            {-1.5, +3.0},
        };
        // @formatter:on
        assertArrayArrayEquals(expecteds, HexGeometry.getCoordinates(new byte[] {0, 1, 1}), 1E-15);
    }

    @Test
    public void getCoordinates__level3_2()
    {
        // @formatter:off
        final double[][] expecteds = {
            { -7.5, -3.0},
            { -6.0, -6.0},
            { -7.5, -9.0},
            {-10.5, -9.0},
            {-12.0, -6.0},
            {-10.5, -3.0},
        };
        // @formatter:on
        assertArrayArrayEquals(expecteds, HexGeometry.getCoordinates(new byte[] {7, 2, 1}), 1E-15);
    }

    @Test
    public void getCoordinates__level3_3()
    {
        assertNull(HexGeometry.getCoordinates(new byte[] {6, 2, 0}));
        assertNull(HexGeometry.getCoordinates(new byte[] {4, 0, 1}));
        assertNull(HexGeometry.getCoordinates(new byte[] {6, 3, 0}));
    }

    @Test
    public void getDeltaUnitSize()
    {
        assertEquals(12.0, HexGeometry.getDeltaUnitSize(1), 1E-15);
        assertEquals(6.0, HexGeometry.getDeltaUnitSize(2), 1E-15);
        assertEquals(3.0, HexGeometry.getDeltaUnitSize(3), 1E-15);
        assertEquals(1.5, HexGeometry.getDeltaUnitSize(4), 1E-15);
    }
}
