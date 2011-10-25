
package jp.nayutaya.geodelta;

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

    // TODO: test_upper_world_delta?
    /*
     * def test_upper_world_delta?
     * assert_equal(false, @mod.upper_world_delta?(0))
     * assert_equal(true , @mod.upper_world_delta?(1))
     * assert_equal(false, @mod.upper_world_delta?(2))
     * assert_equal(true , @mod.upper_world_delta?(3))
     * assert_equal(true , @mod.upper_world_delta?(4))
     * assert_equal(false, @mod.upper_world_delta?(5))
     * assert_equal(true , @mod.upper_world_delta?(6))
     * assert_equal(false, @mod.upper_world_delta?(7))
     * end
     */

    // TODO: test_upper_sub_delta?
    /*
     * def test_upper_sub_delta?
     * assert_equal(false, @mod.upper_sub_delta?(true, 0))
     * assert_equal(true, @mod.upper_sub_delta?(true, 1))
     * assert_equal(true, @mod.upper_sub_delta?(true, 2))
     * assert_equal(true, @mod.upper_sub_delta?(true, 3))
     * assert_equal(true, @mod.upper_sub_delta?(false, 0))
     * assert_equal(false, @mod.upper_sub_delta?(false, 1))
     * assert_equal(false, @mod.upper_sub_delta?(false, 2))
     * assert_equal(false, @mod.upper_sub_delta?(false, 3))
     * end
     */

    // TODO: test_upper_delta?
    /*
     * def test_upper_delta?
     * assert_equal(false, @mod.upper_delta?([0]))
     * assert_equal(true, @mod.upper_delta?([1]))
     * assert_equal(true, @mod.upper_delta?([4]))
     * assert_equal(false, @mod.upper_delta?([5]))
     *
     * assert_equal(true, @mod.upper_delta?([0, 0]))
     * assert_equal(false, @mod.upper_delta?([0, 1]))
     * assert_equal(false, @mod.upper_delta?([0, 2]))
     * assert_equal(false, @mod.upper_delta?([0, 3]))
     *
     * assert_equal(false, @mod.upper_delta?([4, 0]))
     * assert_equal(true, @mod.upper_delta?([4, 1]))
     * assert_equal(true, @mod.upper_delta?([4, 2]))
     * assert_equal(true, @mod.upper_delta?([4, 3]))
     *
     * assert_equal(false, @mod.upper_delta?([0, 0, 0]))
     * assert_equal(true, @mod.upper_delta?([0, 0, 0, 0]))
     * assert_equal(false, @mod.upper_delta?([0, 0, 0, 0, 0]))
     * end
     */

    // TODO: test_transform_world_delta
    /*
     * def test_transform_world_delta
     * assert_equal([+6.0, +4.0], @mod.transform_world_delta(0, +0.0, +4.0))
     * assert_equal([+6.0, +4.0], @mod.transform_world_delta(1, +6.0, +4.0))
     * assert_equal([+6.0, +4.0], @mod.transform_world_delta(2, +12.0, +4.0))
     * assert_equal([+6.0, +4.0], @mod.transform_world_delta(3, +18.0, +4.0))
     * assert_equal([+6.0, +4.0], @mod.transform_world_delta(4, +0.0, -8.0))
     * assert_equal([+6.0, +4.0], @mod.transform_world_delta(5, +6.0, -8.0))
     * assert_equal([+6.0, +4.0], @mod.transform_world_delta(6, +12.0, -8.0))
     * assert_equal([+6.0, +4.0], @mod.transform_world_delta(7, +18.0, -8.0))
     * end
     */

    // TODO: test_transform_upper_delta
    /*
     * def test_transform_upper_delta
     * assert_equal([+6.0, +8.0], @mod.transform_upper_delta(0, +6.0, +4.0))
     * assert_equal([+6.0, +4.0], @mod.transform_upper_delta(1, +6.0, +8.0))
     * assert_equal([+6.0, +4.0], @mod.transform_upper_delta(2, +9.0, +2.0))
     * assert_equal([+6.0, +4.0], @mod.transform_upper_delta(3, +3.0, +2.0))
     * end
     */

    // TODO: test_transform_lower_delta
    /*
     * def test_transform_lower_delta
     * assert_equal([+6.0, +4.0], @mod.transform_lower_delta(0, +6.0, +8.0))
     * assert_equal([+6.0, +8.0], @mod.transform_lower_delta(1, +6.0, +4.0))
     * assert_equal([+6.0, +8.0], @mod.transform_lower_delta(2, +3.0, +10.0))
     * assert_equal([+6.0, +8.0], @mod.transform_lower_delta(3, +9.0, +10.0))
     * end
     */

    // TODO: test_get_delta_ids__level1
    /*
     * def test_get_delta_ids__level1
     * assert_equal([0], @mod.get_delta_ids( 0.0, +6.0, 1))
     * assert_equal([1], @mod.get_delta_ids( 6.0, +6.0, 1))
     * assert_equal([2], @mod.get_delta_ids(12.0, +6.0, 1))
     * assert_equal([3], @mod.get_delta_ids(18.0, +6.0, 1))
     * assert_equal([4], @mod.get_delta_ids( 0.0, -6.0, 1))
     * assert_equal([5], @mod.get_delta_ids( 6.0, -6.0, 1))
     * assert_equal([6], @mod.get_delta_ids(12.0, -6.0, 1))
     * assert_equal([7], @mod.get_delta_ids(18.0, -6.0, 1))
     * end
     */

    // TODO: test_get_delta_ids__level2
    /*
     * def test_get_delta_ids__level2
     * assert_equal([0, 0], @mod.get_delta_ids( +0.0, +8.0, 2))
     * assert_equal([0, 1], @mod.get_delta_ids( +0.0, +4.0, 2))
     * assert_equal([0, 2], @mod.get_delta_ids( -3.0, +10.0, 2))
     * assert_equal([0, 3], @mod.get_delta_ids( +3.0, +10.0, 2))
     * assert_equal([1, 0], @mod.get_delta_ids( +6.0, +4.0, 2))
     * assert_equal([1, 1], @mod.get_delta_ids( +6.0, +8.0, 2))
     * assert_equal([1, 2], @mod.get_delta_ids( +9.0, +2.0, 2))
     * assert_equal([1, 3], @mod.get_delta_ids( +3.0, +2.0, 2))
     * assert_equal([2, 2], @mod.get_delta_ids( +9.0, +10.0, 2))
     * assert_equal([3, 3], @mod.get_delta_ids(+15.0, +2.0, 2))
     *
     * assert_equal([4, 0], @mod.get_delta_ids( +0.0, -8.0, 2))
     * assert_equal([4, 1], @mod.get_delta_ids( +0.0, -4.0, 2))
     * assert_equal([4, 2], @mod.get_delta_ids( +3.0, -10.0, 2))
     * assert_equal([4, 3], @mod.get_delta_ids( -3.0, -10.0, 2))
     * assert_equal([5, 0], @mod.get_delta_ids( +6.0, -4.0, 2))
     * assert_equal([5, 1], @mod.get_delta_ids( +6.0, -8.0, 2))
     * assert_equal([5, 2], @mod.get_delta_ids( +3.0, -2.0, 2))
     * assert_equal([5, 3], @mod.get_delta_ids( +9.0, -2.0, 2))
     * assert_equal([6, 2], @mod.get_delta_ids(+15.0, -10.0, 2))
     * assert_equal([7, 3], @mod.get_delta_ids(+21.0, -2.0, 2))
     * end
     */

    // TODO: test_get_delta_ids__level3
    /*
     * def test_get_delta_ids__level3
     * assert_equal([0, 0, 0], @mod.get_delta_ids(+0.0, +8.0, 3))
     * assert_equal([1, 0, 0], @mod.get_delta_ids(+6.0, +4.0, 3))
     * end
     */

    // TODO: test_get_delta_ids__level4
    /*
     * def test_get_delta_ids__level4
     * assert_equal([0, 0, 0, 0], @mod.get_delta_ids(+0.0, +8.0, 4))
     * assert_equal([1, 0, 0, 0], @mod.get_delta_ids(+6.0, +4.0, 4))
     * end
     */

    // TODO: test_get_world_delta_center
    /*
     * def test_get_world_delta_center
     * assert_equal([ +0.0, +8.0], @mod.get_world_delta_center(0))
     * assert_equal([ +6.0, +4.0], @mod.get_world_delta_center(1))
     * assert_equal([+12.0, +8.0], @mod.get_world_delta_center(2))
     * assert_equal([+18.0, +4.0], @mod.get_world_delta_center(3))
     * assert_equal([ +0.0, -8.0], @mod.get_world_delta_center(4))
     * assert_equal([ +6.0, -4.0], @mod.get_world_delta_center(5))
     * assert_equal([+12.0, -8.0], @mod.get_world_delta_center(6))
     * assert_equal([+18.0, -4.0], @mod.get_world_delta_center(7))
     * end
     */

    // TODO: test_get_upper_sub_delta_distance
    /*
     * def test_get_upper_sub_delta_distance
     * assert_equal([+0.0, +0.0], @mod.get_upper_sub_delta_distance(0))
     * assert_equal([+0.0, +4.0], @mod.get_upper_sub_delta_distance(1))
     * assert_equal([+3.0, -2.0], @mod.get_upper_sub_delta_distance(2))
     * assert_equal([-3.0, -2.0], @mod.get_upper_sub_delta_distance(3))
     * end
     */

    // TODO: test_get_lower_sub_delta_distance
    /*
     * def test_get_lower_sub_delta_distance
     * assert_equal([+0.0, +0.0], @mod.get_lower_sub_delta_distance(0))
     * assert_equal([+0.0, -4.0], @mod.get_lower_sub_delta_distance(1))
     * assert_equal([-3.0, +2.0], @mod.get_lower_sub_delta_distance(2))
     * assert_equal([+3.0, +2.0], @mod.get_lower_sub_delta_distance(3))
     * end
     */

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
