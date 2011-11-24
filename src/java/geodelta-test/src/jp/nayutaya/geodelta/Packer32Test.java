
package jp.nayutaya.geodelta;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Packer32Test
{
    @Test
    public void packWorldDelta()
    {
        assertEquals(0x00000000, Packer32.packWorldDelta(0));
        assertEquals(0x10000000, Packer32.packWorldDelta(1));
        assertEquals(0x20000000, Packer32.packWorldDelta(2));
        assertEquals(0x30000000, Packer32.packWorldDelta(3));
        assertEquals(0x40000000, Packer32.packWorldDelta(4));
        assertEquals(0x50000000, Packer32.packWorldDelta(5));
        assertEquals(0x60000000, Packer32.packWorldDelta(6));
        assertEquals(0x70000000, Packer32.packWorldDelta(7));
    }

}
