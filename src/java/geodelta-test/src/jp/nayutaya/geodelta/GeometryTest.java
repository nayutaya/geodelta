
package jp.nayutaya.geodelta;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GeometryTest
{
    @Test
    public void getWorldDeltaId()
    {
        assertEquals(2, Geometry.getWorldDeltaId(-6.0, +12.0));
        assertEquals(3, Geometry.getWorldDeltaId(-6.0, +6.0));
        assertEquals(3, Geometry.getWorldDeltaId(-6.0, 0.0));
        assertEquals(3, Geometry.getWorldDeltaId(-3.0, +6.0));
        assertEquals(0, Geometry.getWorldDeltaId(0.0, +12.0));
        assertEquals(0, Geometry.getWorldDeltaId(0.0, +6.0));
        assertEquals(0, Geometry.getWorldDeltaId(0.0, 0.0));
        assertEquals(0, Geometry.getWorldDeltaId(+3.0, +6.0));
        assertEquals(0, Geometry.getWorldDeltaId(+6.0, +12.0));
        assertEquals(1, Geometry.getWorldDeltaId(+6.0, +6.0));
        assertEquals(1, Geometry.getWorldDeltaId(+6.0, 0.0));
        assertEquals(1, Geometry.getWorldDeltaId(+9.0, +6.0));
        assertEquals(1, Geometry.getWorldDeltaId(+12.0, 0.0));
        assertEquals(2, Geometry.getWorldDeltaId(+12.0, +12.0));
        assertEquals(2, Geometry.getWorldDeltaId(+12.0, +6.0));
        assertEquals(2, Geometry.getWorldDeltaId(+15.0, +6.0));
        assertEquals(2, Geometry.getWorldDeltaId(+18.0, +12.0));
        assertEquals(3, Geometry.getWorldDeltaId(+18.0, +6.0));
        assertEquals(3, Geometry.getWorldDeltaId(+18.0, 0.0));
        assertEquals(3, Geometry.getWorldDeltaId(+21.0, +6.0));
        assertEquals(0, Geometry.getWorldDeltaId(+24.0, +12.0));
        assertEquals(0, Geometry.getWorldDeltaId(+24.0, +6.0));
        assertEquals(0, Geometry.getWorldDeltaId(+24.0, 0.0));

        assertEquals(6, Geometry.getWorldDeltaId(-6.0, -12.0));
        assertEquals(7, Geometry.getWorldDeltaId(-6.0, -6.0));
        assertEquals(7, Geometry.getWorldDeltaId(-3.0, -6.0));
        assertEquals(4, Geometry.getWorldDeltaId(0.0, -6.0));
        assertEquals(4, Geometry.getWorldDeltaId(0.0, -12.0));
        assertEquals(4, Geometry.getWorldDeltaId(+3.0, -6.0));
        assertEquals(4, Geometry.getWorldDeltaId(+6.0, -12.0));
        assertEquals(5, Geometry.getWorldDeltaId(+6.0, -6.0));
        assertEquals(5, Geometry.getWorldDeltaId(+9.0, -6.0));
        assertEquals(6, Geometry.getWorldDeltaId(+12.0, -6.0));
        assertEquals(6, Geometry.getWorldDeltaId(+12.0, -12.0));
        assertEquals(6, Geometry.getWorldDeltaId(+15.0, -6.0));
        assertEquals(6, Geometry.getWorldDeltaId(+18.0, -12.0));
        assertEquals(7, Geometry.getWorldDeltaId(+18.0, -6.0));
        assertEquals(7, Geometry.getWorldDeltaId(+21.0, -6.0));
        assertEquals(4, Geometry.getWorldDeltaId(+24.0, -6.0));
        assertEquals(4, Geometry.getWorldDeltaId(+24.0, -12.0));
    }

    @Test
    public void getUpperDeltaId()
    {
        assertEquals(3, Geometry.getUpperDeltaId(0.0, 0.0));
        assertEquals(3, Geometry.getUpperDeltaId(1.5, 3.0));
        assertEquals(3, Geometry.getUpperDeltaId(3.0, 3.0));
        assertEquals(3, Geometry.getUpperDeltaId(3.0, 0.0));
        assertEquals(2, Geometry.getUpperDeltaId(9.0, 3.0));
        assertEquals(2, Geometry.getUpperDeltaId(9.0, 0.0));
        assertEquals(2, Geometry.getUpperDeltaId(10.5, 3.0));
        assertEquals(2, Geometry.getUpperDeltaId(12.0, 0.0));
        assertEquals(1, Geometry.getUpperDeltaId(4.5, 9.0));
        assertEquals(1, Geometry.getUpperDeltaId(6.0, 12.0));
        assertEquals(1, Geometry.getUpperDeltaId(6.0, 9.0));
        assertEquals(1, Geometry.getUpperDeltaId(7.5, 9.0));
        assertEquals(0, Geometry.getUpperDeltaId(3.0, 6.0));
        assertEquals(0, Geometry.getUpperDeltaId(4.5, 3.0));
        assertEquals(0, Geometry.getUpperDeltaId(6.0, 6.0));
        assertEquals(0, Geometry.getUpperDeltaId(6.0, 3.0));
        assertEquals(0, Geometry.getUpperDeltaId(6.0, 0.0));
        assertEquals(0, Geometry.getUpperDeltaId(7.5, 3.0));
        assertEquals(0, Geometry.getUpperDeltaId(9.0, 6.0));
    }

    @Test
    public void getLowerDeltaId()
    {
        assertEquals(3, Geometry.getLowerDeltaId(9.0, 12.0));
        assertEquals(3, Geometry.getLowerDeltaId(9.0, 9.0));
        assertEquals(3, Geometry.getLowerDeltaId(10.5, 9.0));
        assertEquals(3, Geometry.getLowerDeltaId(12.0, 12.0));
        assertEquals(2, Geometry.getLowerDeltaId(0.0, 12.0));
        assertEquals(2, Geometry.getLowerDeltaId(1.5, 9.0));
        assertEquals(2, Geometry.getLowerDeltaId(3.0, 12.0));
        assertEquals(2, Geometry.getLowerDeltaId(3.0, 9.0));
        assertEquals(1, Geometry.getLowerDeltaId(4.5, 3.0));
        assertEquals(1, Geometry.getLowerDeltaId(6.0, 3.0));
        assertEquals(1, Geometry.getLowerDeltaId(6.0, 0.0));
        assertEquals(1, Geometry.getLowerDeltaId(7.5, 3.0));
        assertEquals(0, Geometry.getLowerDeltaId(3.0, 6.0));
        assertEquals(0, Geometry.getLowerDeltaId(4.5, 9.0));
        assertEquals(0, Geometry.getLowerDeltaId(6.0, 12.0));
        assertEquals(0, Geometry.getLowerDeltaId(6.0, 9.0));
        assertEquals(0, Geometry.getLowerDeltaId(6.0, 6.0));
        assertEquals(0, Geometry.getLowerDeltaId(7.5, 9.0));
        assertEquals(0, Geometry.getLowerDeltaId(9.0, 6.0));
    }

    @Test
    public void isUpperWorldDelta()
    {
        assertEquals(false, Geometry.isUpperWorldDelta(0));
        assertEquals(true, Geometry.isUpperWorldDelta(1));
        assertEquals(false, Geometry.isUpperWorldDelta(2));
        assertEquals(true, Geometry.isUpperWorldDelta(3));
        assertEquals(true, Geometry.isUpperWorldDelta(4));
        assertEquals(false, Geometry.isUpperWorldDelta(5));
        assertEquals(true, Geometry.isUpperWorldDelta(6));
        assertEquals(false, Geometry.isUpperWorldDelta(7));
    }

    @Test
    public void isUpperSubDelta()
    {
        assertEquals(false, Geometry.isUpperSubDelta(true, 0));
        assertEquals(true, Geometry.isUpperSubDelta(true, 1));
        assertEquals(true, Geometry.isUpperSubDelta(true, 2));
        assertEquals(true, Geometry.isUpperSubDelta(true, 3));
        assertEquals(true, Geometry.isUpperSubDelta(false, 0));
        assertEquals(false, Geometry.isUpperSubDelta(false, 1));
        assertEquals(false, Geometry.isUpperSubDelta(false, 2));
        assertEquals(false, Geometry.isUpperSubDelta(false, 3));
    }

    @Test
    public void isUpperDelta()
    {
        assertEquals(false, Geometry.isUpperDelta(new byte[] {0}));
        assertEquals(true, Geometry.isUpperDelta(new byte[] {1}));
        assertEquals(true, Geometry.isUpperDelta(new byte[] {4}));
        assertEquals(false, Geometry.isUpperDelta(new byte[] {5}));

        assertEquals(true, Geometry.isUpperDelta(new byte[] {0, 0}));
        assertEquals(false, Geometry.isUpperDelta(new byte[] {0, 1}));
        assertEquals(false, Geometry.isUpperDelta(new byte[] {0, 2}));
        assertEquals(false, Geometry.isUpperDelta(new byte[] {0, 3}));

        assertEquals(false, Geometry.isUpperDelta(new byte[] {4, 0}));
        assertEquals(true, Geometry.isUpperDelta(new byte[] {4, 1}));
        assertEquals(true, Geometry.isUpperDelta(new byte[] {4, 2}));
        assertEquals(true, Geometry.isUpperDelta(new byte[] {4, 3}));

        assertEquals(false, Geometry.isUpperDelta(new byte[] {0, 0, 0}));
        assertEquals(true, Geometry.isUpperDelta(new byte[] {0, 0, 0, 0}));
        assertEquals(false, Geometry.isUpperDelta(new byte[] {0, 0, 0, 0, 0}));
    }

    @Test
    public void transformWorldDelta()
    {
        assertArrayEquals(new double[] {+6.0, +4.0}, Geometry.transformWorldDelta(0, +0.0, +4.0), 1.0E-15);
        assertArrayEquals(new double[] {+6.0, +4.0}, Geometry.transformWorldDelta(1, +6.0, +4.0), 1.0E-15);
        assertArrayEquals(new double[] {+6.0, +4.0}, Geometry.transformWorldDelta(2, +12.0, +4.0), 1.0E-15);
        assertArrayEquals(new double[] {+6.0, +4.0}, Geometry.transformWorldDelta(3, +18.0, +4.0), 1.0E-15);
        assertArrayEquals(new double[] {+6.0, +4.0}, Geometry.transformWorldDelta(4, +0.0, -8.0), 1.0E-15);
        assertArrayEquals(new double[] {+6.0, +4.0}, Geometry.transformWorldDelta(5, +6.0, -8.0), 1.0E-15);
        assertArrayEquals(new double[] {+6.0, +4.0}, Geometry.transformWorldDelta(6, +12.0, -8.0), 1.0E-15);
        assertArrayEquals(new double[] {+6.0, +4.0}, Geometry.transformWorldDelta(7, +18.0, -8.0), 1.0E-15);
    }

    @Test
    public void transformUpperDelta()
    {
        assertArrayEquals(new double[] {+6.0, +8.0}, Geometry.transformUpperDelta(0, +6.0, +4.0), 1.0E-15);
        assertArrayEquals(new double[] {+6.0, +4.0}, Geometry.transformUpperDelta(1, +6.0, +8.0), 1.0E-15);
        assertArrayEquals(new double[] {+6.0, +4.0}, Geometry.transformUpperDelta(2, +9.0, +2.0), 1.0E-15);
        assertArrayEquals(new double[] {+6.0, +4.0}, Geometry.transformUpperDelta(3, +3.0, +2.0), 1.0E-15);
    }

    @Test
    public void transformLowerDelta()
    {
        assertArrayEquals(new double[] {+6.0, +4.0}, Geometry.transformLowerDelta(0, +6.0, +8.0), 1.0E-15);
        assertArrayEquals(new double[] {+6.0, +8.0}, Geometry.transformLowerDelta(1, +6.0, +4.0), 1.0E-15);
        assertArrayEquals(new double[] {+6.0, +8.0}, Geometry.transformLowerDelta(2, +3.0, +10.0), 1.0E-15);
        assertArrayEquals(new double[] {+6.0, +8.0}, Geometry.transformLowerDelta(3, +9.0, +10.0), 1.0E-15);
    }

    @Test
    public void getDeltaIds__level1()
    {
        assertArrayEquals(new byte[] {0}, Geometry.getDeltaIds(0.0, +6.0, 1));
        assertArrayEquals(new byte[] {1}, Geometry.getDeltaIds(6.0, +6.0, 1));
        assertArrayEquals(new byte[] {2}, Geometry.getDeltaIds(12.0, +6.0, 1));
        assertArrayEquals(new byte[] {3}, Geometry.getDeltaIds(18.0, +6.0, 1));
        assertArrayEquals(new byte[] {4}, Geometry.getDeltaIds(0.0, -6.0, 1));
        assertArrayEquals(new byte[] {5}, Geometry.getDeltaIds(6.0, -6.0, 1));
        assertArrayEquals(new byte[] {6}, Geometry.getDeltaIds(12.0, -6.0, 1));
        assertArrayEquals(new byte[] {7}, Geometry.getDeltaIds(18.0, -6.0, 1));
    }

    @Test
    public void getDeltaIds__level2()
    {
        assertArrayEquals(new byte[] {0, 0}, Geometry.getDeltaIds(+0.0, +8.0, 2));
        assertArrayEquals(new byte[] {0, 1}, Geometry.getDeltaIds(+0.0, +4.0, 2));
        assertArrayEquals(new byte[] {0, 2}, Geometry.getDeltaIds(-3.0, +10.0, 2));
        assertArrayEquals(new byte[] {0, 3}, Geometry.getDeltaIds(+3.0, +10.0, 2));
        assertArrayEquals(new byte[] {1, 0}, Geometry.getDeltaIds(+6.0, +4.0, 2));
        assertArrayEquals(new byte[] {1, 1}, Geometry.getDeltaIds(+6.0, +8.0, 2));
        assertArrayEquals(new byte[] {1, 2}, Geometry.getDeltaIds(+9.0, +2.0, 2));
        assertArrayEquals(new byte[] {1, 3}, Geometry.getDeltaIds(+3.0, +2.0, 2));
        assertArrayEquals(new byte[] {2, 2}, Geometry.getDeltaIds(+9.0, +10.0, 2));
        assertArrayEquals(new byte[] {3, 3}, Geometry.getDeltaIds(+15.0, +2.0, 2));

        assertArrayEquals(new byte[] {4, 0}, Geometry.getDeltaIds(+0.0, -8.0, 2));
        assertArrayEquals(new byte[] {4, 1}, Geometry.getDeltaIds(+0.0, -4.0, 2));
        assertArrayEquals(new byte[] {4, 2}, Geometry.getDeltaIds(+3.0, -10.0, 2));
        assertArrayEquals(new byte[] {4, 3}, Geometry.getDeltaIds(-3.0, -10.0, 2));
        assertArrayEquals(new byte[] {5, 0}, Geometry.getDeltaIds(+6.0, -4.0, 2));
        assertArrayEquals(new byte[] {5, 1}, Geometry.getDeltaIds(+6.0, -8.0, 2));
        assertArrayEquals(new byte[] {5, 2}, Geometry.getDeltaIds(+3.0, -2.0, 2));
        assertArrayEquals(new byte[] {5, 3}, Geometry.getDeltaIds(+9.0, -2.0, 2));
        assertArrayEquals(new byte[] {6, 2}, Geometry.getDeltaIds(+15.0, -10.0, 2));
        assertArrayEquals(new byte[] {7, 3}, Geometry.getDeltaIds(+21.0, -2.0, 2));
    }

    @Test
    public void getDeltaIds__level3()
    {
        assertArrayEquals(new byte[] {0, 0, 0}, Geometry.getDeltaIds(+0.0, +8.0, 3));
        assertArrayEquals(new byte[] {1, 0, 0}, Geometry.getDeltaIds(+6.0, +4.0, 3));
    }

    @Test
    public void getDeltaIds__level4()
    {
        assertArrayEquals(new byte[] {0, 0, 0, 0}, Geometry.getDeltaIds(+0.0, +8.0, 4));
        assertArrayEquals(new byte[] {1, 0, 0, 0}, Geometry.getDeltaIds(+6.0, +4.0, 4));
    }

    @Test
    public void getWorldDeltaCenter()
    {
        assertArrayEquals(new double[] {+0.0, +8.0}, Geometry.getWorldDeltaCenter(0), 1.0E-15);
        assertArrayEquals(new double[] {+6.0, +4.0}, Geometry.getWorldDeltaCenter(1), 1.0E-15);
        assertArrayEquals(new double[] {+12.0, +8.0}, Geometry.getWorldDeltaCenter(2), 1.0E-15);
        assertArrayEquals(new double[] {+18.0, +4.0}, Geometry.getWorldDeltaCenter(3), 1.0E-15);
        assertArrayEquals(new double[] {+0.0, -8.0}, Geometry.getWorldDeltaCenter(4), 1.0E-15);
        assertArrayEquals(new double[] {+6.0, -4.0}, Geometry.getWorldDeltaCenter(5), 1.0E-15);
        assertArrayEquals(new double[] {+12.0, -8.0}, Geometry.getWorldDeltaCenter(6), 1.0E-15);
        assertArrayEquals(new double[] {+18.0, -4.0}, Geometry.getWorldDeltaCenter(7), 1.0E-15);
    }

    @Test
    public void getUpperSubDeltaDistance()
    {
        assertArrayEquals(new double[] {+0.0, +0.0}, Geometry.getUpperSubDeltaDistance(0), 1.0E-15);
        assertArrayEquals(new double[] {+0.0, +4.0}, Geometry.getUpperSubDeltaDistance(1), 1.0E-15);
        assertArrayEquals(new double[] {+3.0, -2.0}, Geometry.getUpperSubDeltaDistance(2), 1.0E-15);
        assertArrayEquals(new double[] {-3.0, -2.0}, Geometry.getUpperSubDeltaDistance(3), 1.0E-15);
    }

    @Test
    public void getLowerSubDeltaDistance()
    {
        assertArrayEquals(new double[] {+0.0, +0.0}, Geometry.getLowerSubDeltaDistance(0), 1.0E-15);
        assertArrayEquals(new double[] {+0.0, -4.0}, Geometry.getLowerSubDeltaDistance(1), 1.0E-15);
        assertArrayEquals(new double[] {-3.0, +2.0}, Geometry.getLowerSubDeltaDistance(2), 1.0E-15);
        assertArrayEquals(new double[] {+3.0, +2.0}, Geometry.getLowerSubDeltaDistance(3), 1.0E-15);
    }

    // TODO: test_get_sub_delta_distance
    /*
     * def test_get_sub_delta_distance
     * assert_equal([+0.0, +4.0], @mod.get_sub_delta_distance(true, 1))
     * assert_equal([+3.0, -2.0], @mod.get_sub_delta_distance(true, 2))
     * assert_equal([+0.0, -4.0], @mod.get_sub_delta_distance(false, 1))
     * assert_equal([-3.0, +2.0], @mod.get_sub_delta_distance(false, 2))
     * end
     */

    // TODO: test_get_center__level1
    /*
     * def test_get_center__level1
     * assert_equal([+0.0, +8.0], @mod.get_center([0]))
     * assert_equal([+6.0, +4.0], @mod.get_center([1]))
     * assert_equal([+0.0, -8.0], @mod.get_center([4]))
     * assert_equal([+6.0, -4.0], @mod.get_center([5]))
     * end
     */

    // TODO: test_get_center__level2
    /*
     * def test_get_center__level2
     * assert_equal([ +0.0, +8.0], @mod.get_center([0, 0]))
     * assert_equal([ +0.0, +4.0], @mod.get_center([0, 1]))
     * assert_equal([ -3.0, +10.0], @mod.get_center([0, 2]))
     * assert_equal([ +3.0, +10.0], @mod.get_center([0, 3]))
     * assert_equal([ +6.0, +4.0], @mod.get_center([1, 0]))
     * assert_equal([ +6.0, +8.0], @mod.get_center([1, 1]))
     * assert_equal([ +9.0, +2.0], @mod.get_center([1, 2]))
     * assert_equal([ +3.0, +2.0], @mod.get_center([1, 3]))
     * assert_equal([ +9.0, +10.0], @mod.get_center([2, 2]))
     * assert_equal([ -9.0, +2.0], @mod.get_center([3, 3]))
     *
     * assert_equal([ +0.0, -8.0], @mod.get_center([4, 0]))
     * assert_equal([ +0.0, -4.0], @mod.get_center([4, 1]))
     * assert_equal([ +3.0, -10.0], @mod.get_center([4, 2]))
     * assert_equal([ -3.0, -10.0], @mod.get_center([4, 3]))
     * assert_equal([ +6.0, -4.0], @mod.get_center([5, 0]))
     * assert_equal([ +6.0, -8.0], @mod.get_center([5, 1]))
     * assert_equal([ +3.0, -2.0], @mod.get_center([5, 2]))
     * assert_equal([ +9.0, -2.0], @mod.get_center([5, 3]))
     * assert_equal([ -9.0, -10.0], @mod.get_center([6, 2]))
     * assert_equal([ -3.0, -2.0], @mod.get_center([7, 3]))
     * end
     */

    // TODO: test_get_center__level3
    /*
     * def test_get_center__level3
     * assert_equal([ +0.0, +8.0], @mod.get_center([0, 0, 0]))
     * assert_equal([ +0.0, +10.0], @mod.get_center([0, 0, 1]))
     * assert_equal([ -1.5, +5.0], @mod.get_center([0, 1, 2]))
     * assert_equal([ -1.5, +11.0], @mod.get_center([0, 2, 3]))
     * assert_equal([ +3.0, +10.0], @mod.get_center([0, 3, 0]))
     * end
     */

    // TODO: test_get_coordinates__level1
    /*
     * def test_get_coordinates__level1
     * expected = [
     * [+0.0, +8.0],
     * [+0.0, +0.0], # +0.0, -8.0
     * [-6.0, +12.0], # -6.0, +4.0
     * [+6.0, +12.0], # +6.0, +4.0
     * ]
     * assert_equal(expected, @mod.get_coordinates([0]))
     *
     * expected = [
     * [ +6.0, +4.0],
     * [ +6.0, +12.0], # +0.0, +8.0
     * [+12.0, +0.0], # +6.0, -4.0
     * [ +0.0, +0.0], # -6.0, -4.0
     * ]
     * assert_equal(expected, @mod.get_coordinates([1]))
     *
     * expected = [
     * [+0.0, -8.0],
     * [+0.0, +0.0], # +0.0, +8.0
     * [+6.0, -12.0], # +6.0, -4.0
     * [-6.0, -12.0], # -6.0, -4.0
     * ]
     * assert_equal(expected, @mod.get_coordinates([4]))
     *
     * expected = [
     * [ +6.0, -4.0],
     * [ +6.0, -12.0], # +0.0, -8.0
     * [ +0.0, +0.0], # -6.0, +4.0
     * [+12.0, +0.0], # +6.0, +4.0
     * ]
     * assert_equal(expected, @mod.get_coordinates([5]))
     * end
     */

    // TODO: test_get_coordinates__level2
    /*
     * def test_get_coordinates__level2
     * expected = [
     * [ +0.0, +8.0],
     * [ +0.0, +12.0], # +0.0, +4.0
     * [ +3.0, +6.0], # +3.0, -2.0
     * [ -3.0, +6.0], # -3.0, -2.0
     * ]
     * assert_equal(expected, @mod.get_coordinates([0, 0]))
     *
     * expected = [
     * [ +0.0, +4.0],
     * [ +0.0, +0.0], # +0.0, -4.0
     * [ -3.0, +6.0], # -3.0, +2.0
     * [ +3.0, +6.0], # +3.0, +2.0
     * ]
     * assert_equal(expected, @mod.get_coordinates([0, 1]))
     *
     * expected = [
     * [ -3.0, +10.0],
     * [ -3.0, +6.0], # +0.0, -4.0
     * [ -6.0, +12.0], # -3.0, +2.0
     * [ +0.0, +12.0], # +3.0, +2.0
     * ]
     * assert_equal(expected, @mod.get_coordinates([0, 2]))
     *
     * expected = [
     * [ +3.0, +10.0],
     * [ +3.0, +6.0], # +0.0, -4.0
     * [ +0.0, +12.0], # -3.0, +2.0
     * [ +6.0, +12.0], # +3.0, +2.0
     * ]
     * assert_equal(expected, @mod.get_coordinates([0, 3]))
     *
     * expected = [
     * [ +0.0, -8.0],
     * [ +0.0, -12.0], # +0.0, -4.0
     * [ -3.0, -6.0], # -3.0, +2.0
     * [ +3.0, -6.0], # +3.0, +2.0
     * ]
     * assert_equal(expected, @mod.get_coordinates([4, 0]))
     *
     * expected = [
     * [ +0.0, -4.0],
     * [ +0.0, +0.0], # +0.0, +4.0
     * [ +3.0, -6.0], # +3.0, -2.0
     * [ -3.0, -6.0], # -3.0, -2.0
     * ]
     * assert_equal(expected, @mod.get_coordinates([4, 1]))
     *
     * expected = [
     * [ +3.0, -10.0],
     * [ +3.0, -6.0], # +0.0, +4.0
     * [ +6.0, -12.0], # +3.0, -2.0
     * [ +0.0, -12.0], # -3.0, -2.0
     * ]
     * assert_equal(expected, @mod.get_coordinates([4, 2]))
     *
     * expected = [
     * [ -3.0, -10.0],
     * [ -3.0, -6.0], # +0.0, +4.0
     * [ +0.0, -12.0], # +3.0, -2.0
     * [ -6.0, -12.0], # -3.0, -2.0
     * ]
     * assert_equal(expected, @mod.get_coordinates([4, 3]))
     * end
     */

    // TODO: test_get_coordinates__level3
    /*
     * def test_get_coordinates__level3
     * expected = [
     * [ +0.0, +8.0],
     * [ +0.0, +6.0], # +0.0, -2.0
     * [ -1.5, +9.0], # -1.5, +1.0
     * [ +1.5, +9.0], # +1.5, +1.0
     * ]
     * assert_equal(expected, @mod.get_coordinates([0, 0, 0]))
     *
     * expected = [
     * [ -1.5, +5.0],
     * [ -1.5, +3.0], # +0.0, -2.0
     * [ -3.0, +6.0], # -1.5, +1.0
     * [ +0.0, +6.0], # +1.5, +1.0
     * ]
     * assert_equal(expected, @mod.get_coordinates([0, 1, 2]))
     * end
     */

    // TODO: test_rush__center
    /*
     * def test_rush__center
     * 1000.times {
     * x1 = (rand * 24) - 12
     * y1 = (rand * 24) - 12
     * level = rand(20) + 1
     * ids1 = @mod.get_delta_ids(x1, y1, level)
     * x2, y2 = @mod.get_center(ids1)
     * ids2 = @mod.get_delta_ids(x2, y2, level)
     * x3, y3 = @mod.get_center(ids2)
     * assert_equal(ids1, ids2)
     * assert_equal([x2, y2], [x3, y3])
     * }
     * end
     */
}
