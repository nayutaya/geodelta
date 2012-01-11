
package jp.nayutaya.geodelta;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.Random;
import org.junit.Test;

public class Packer64Test
{
    @Test
    public void packWorldDelta()
    {
        assertEquals(0x0000000000000000L, Packer64.packWorldDelta((byte)0));
        assertEquals(0x0800000000000000L, Packer64.packWorldDelta((byte)1));
        assertEquals(0x1000000000000000L, Packer64.packWorldDelta((byte)2));
        assertEquals(0x1800000000000000L, Packer64.packWorldDelta((byte)3));
        assertEquals(0x2000000000000000L, Packer64.packWorldDelta((byte)4));
        assertEquals(0x2800000000000000L, Packer64.packWorldDelta((byte)5));
        assertEquals(0x3000000000000000L, Packer64.packWorldDelta((byte)6));
        assertEquals(0x3800000000000000L, Packer64.packWorldDelta((byte)7));
    }

    @Test
    public void unpackWorldDelta()
    {
        assertEquals((byte)0, Packer64.unpackWorldDelta(0x0000000000000000L));
        assertEquals((byte)1, Packer64.unpackWorldDelta(0x0800000000000000L));
        assertEquals((byte)2, Packer64.unpackWorldDelta(0x1000000000000000L));
        assertEquals((byte)3, Packer64.unpackWorldDelta(0x1800000000000000L));
        assertEquals((byte)4, Packer64.unpackWorldDelta(0x2000000000000000L));
        assertEquals((byte)5, Packer64.unpackWorldDelta(0x2800000000000000L));
        assertEquals((byte)6, Packer64.unpackWorldDelta(0x3000000000000000L));
        assertEquals((byte)7, Packer64.unpackWorldDelta(0x3800000000000000L));
    }

    @Test
    public void packSubDelta()
    {
        assertEquals(0x0000000000000000L, Packer64.packSubDelta(2, (byte)0));
        assertEquals(0x0200000000000000L, Packer64.packSubDelta(2, (byte)1));
        assertEquals(0x0400000000000000L, Packer64.packSubDelta(2, (byte)2));
        assertEquals(0x0600000000000000L, Packer64.packSubDelta(2, (byte)3));
        assertEquals(0x0000000000000000L, Packer64.packSubDelta(3, (byte)0));
        assertEquals(0x0080000000000000L, Packer64.packSubDelta(3, (byte)1));
        assertEquals(0x0100000000000000L, Packer64.packSubDelta(3, (byte)2));
        assertEquals(0x0180000000000000L, Packer64.packSubDelta(3, (byte)3));

        assertEquals(0x0060000000000000L, Packer64.packSubDelta(4, (byte)3));
        assertEquals(0x0018000000000000L, Packer64.packSubDelta(5, (byte)3));
        assertEquals(0x0006000000000000L, Packer64.packSubDelta(6, (byte)3));
        assertEquals(0x0001800000000000L, Packer64.packSubDelta(7, (byte)3));
        assertEquals(0x0000600000000000L, Packer64.packSubDelta(8, (byte)3));
        assertEquals(0x0000180000000000L, Packer64.packSubDelta(9, (byte)3));
        assertEquals(0x0000060000000000L, Packer64.packSubDelta(10, (byte)3));
        assertEquals(0x0000018000000000L, Packer64.packSubDelta(11, (byte)3));
        assertEquals(0x0000006000000000L, Packer64.packSubDelta(12, (byte)3));
        assertEquals(0x0000001800000000L, Packer64.packSubDelta(13, (byte)3));
        assertEquals(0x0000000600000000L, Packer64.packSubDelta(14, (byte)3));
        assertEquals(0x0000000180000000L, Packer64.packSubDelta(15, (byte)3));
        assertEquals(0x0000000060000000L, Packer64.packSubDelta(16, (byte)3));
        assertEquals(0x0000000018000000L, Packer64.packSubDelta(17, (byte)3));
        assertEquals(0x0000000006000000L, Packer64.packSubDelta(18, (byte)3));
        assertEquals(0x0000000001800000L, Packer64.packSubDelta(19, (byte)3));
        assertEquals(0x0000000000600000L, Packer64.packSubDelta(20, (byte)3));
        assertEquals(0x0000000000180000L, Packer64.packSubDelta(21, (byte)3));
        assertEquals(0x0000000000060000L, Packer64.packSubDelta(22, (byte)3));
        assertEquals(0x0000000000018000L, Packer64.packSubDelta(23, (byte)3));
        assertEquals(0x0000000000006000L, Packer64.packSubDelta(24, (byte)3));
        assertEquals(0x0000000000001800L, Packer64.packSubDelta(25, (byte)3));
        assertEquals(0x0000000000000600L, Packer64.packSubDelta(26, (byte)3));
        assertEquals(0x0000000000000180L, Packer64.packSubDelta(27, (byte)3));
        assertEquals(0x0000000000000060L, Packer64.packSubDelta(28, (byte)3));
    }

    @Test
    public void unpackSubDelta64()
    {
        assertEquals((byte)0, Packer64.unpackSubDelta(2, 0x0000000000000000L));
        assertEquals((byte)1, Packer64.unpackSubDelta(2, 0x0200000000000000L));
        assertEquals((byte)2, Packer64.unpackSubDelta(2, 0x0400000000000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(2, 0x0600000000000000L));
        assertEquals((byte)0, Packer64.unpackSubDelta(3, 0x0000000000000000L));
        assertEquals((byte)1, Packer64.unpackSubDelta(3, 0x0080000000000000L));
        assertEquals((byte)2, Packer64.unpackSubDelta(3, 0x0100000000000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(3, 0x0180000000000000L));

        assertEquals((byte)3, Packer64.unpackSubDelta(4, 0x0060000000000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(5, 0x0018000000000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(6, 0x0006000000000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(7, 0x0001800000000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(8, 0x0000600000000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(9, 0x0000180000000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(10, 0x0000060000000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(11, 0x0000018000000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(12, 0x0000006000000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(13, 0x0000001800000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(14, 0x0000000600000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(15, 0x0000000180000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(16, 0x0000000060000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(17, 0x0000000018000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(18, 0x0000000006000000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(19, 0x0000000001800000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(20, 0x0000000000600000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(21, 0x0000000000180000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(22, 0x0000000000060000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(23, 0x0000000000018000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(24, 0x0000000000006000L));
        assertEquals((byte)3, Packer64.unpackSubDelta(25, 0x0000000000001800L));
        assertEquals((byte)3, Packer64.unpackSubDelta(26, 0x0000000000000600L));
        assertEquals((byte)3, Packer64.unpackSubDelta(27, 0x0000000000000180L));
        assertEquals((byte)3, Packer64.unpackSubDelta(28, 0x0000000000000060L));
    }

    @Test
    public void packLevel()
    {
        assertEquals(0x0000000000000001L, Packer64.packLevel(1));
        assertEquals(0x0000000000000002L, Packer64.packLevel(2));
        assertEquals(0x000000000000001BL, Packer64.packLevel(27));
        assertEquals(0x000000000000001CL, Packer64.packLevel(28));
    }

    @Test
    public void unpackLevel()
    {
        assertEquals(1, Packer64.unpackLevel(0x0000000000000001L));
        assertEquals(2, Packer64.unpackLevel(0x0000000000000002L));
        assertEquals(27, Packer64.unpackLevel(0x000000000000001BL));
        assertEquals(28, Packer64.unpackLevel(0x000000000000001CL));
    }

    @Test
    public void pack()
    {
        // @formatter:off
        assertEquals(
            0x0000000000000001L,
            Packer64.pack(new byte[] {0}));
        assertEquals(
            0x3800000000000001L,
            Packer64.pack(new byte[] {7}));
        assertEquals(
            0x0200000000000002L,
            Packer64.pack(new byte[] {0, 1}));
        assertEquals(
            0x1600000000000002L,
            Packer64.pack(new byte[] {2, 3}));
        assertEquals(
            0x000000000000001CL,
            Packer64.pack(new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
        assertEquals(
            0x0AAAAAAAAAAAAABCL,
            Packer64.pack(new byte[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
        assertEquals(
            0x3FFFFFFFFFFFFFFCL,
            Packer64.pack(new byte[] {7, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}));
        // @formatter:on
    }

    @Test
    public void unpack()
    {
        // @formatter:off
        assertArrayEquals(
            new byte[] {0},
            Packer64.unpack(0x0000000000000001L));
        assertArrayEquals(
            new byte[] {7},
            Packer64.unpack(0x3800000000000001L));
        assertArrayEquals(
            new byte[] {0, 1},
            Packer64.unpack(0x0200000000000002L));
        assertArrayEquals(
            new byte[] {2, 3},
            Packer64.unpack(0x1600000000000002L));
        assertArrayEquals(
            new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            Packer64.unpack(0x000000000000001CL));
        assertArrayEquals(
            new byte[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            Packer64.unpack(0x0AAAAAAAAAAAAABCL));
        assertArrayEquals(
            new byte[] {7, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            Packer64.unpack(0x3FFFFFFFFFFFFFFCL));
        // @formatter:on
    }

    @Test
    public void randamPackAndUnpack()
    {
        final Random r = new Random();

        for ( int i = 0; i < 1000; i++ )
        {
            final byte[] ids1 = createRandomDeltaIds(r, 28);
            final byte[] ids2 = Packer64.unpack(Packer64.pack(ids1));
            assertArrayEquals(ids1, ids2);
        }
    }

    // FIXME: refactoring
    private byte[] createRandomDeltaIds(final Random random, final int level)
    {
        final byte[] ids = new byte[random.nextInt(level) + 1];
        ids[0] = (byte)random.nextInt(8);
        for ( int i = 1; i < ids.length; i++ )
        {
            ids[i] = (byte)random.nextInt(4);
        }
        return ids;
    }
}
