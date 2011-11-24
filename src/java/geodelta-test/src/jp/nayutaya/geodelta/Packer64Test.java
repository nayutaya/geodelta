
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
}
