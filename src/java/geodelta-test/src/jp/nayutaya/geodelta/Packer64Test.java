
package jp.nayutaya.geodelta;

import static org.junit.Assert.assertEquals;
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
}
