
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

    @Test
    public void unpackWorldDelta()
    {
        assertEquals(0, Packer32.unpackWorldDelta(0x00000000));
        assertEquals(1, Packer32.unpackWorldDelta(0x10000000));
        assertEquals(2, Packer32.unpackWorldDelta(0x20000000));
        assertEquals(3, Packer32.unpackWorldDelta(0x30000000));
        assertEquals(4, Packer32.unpackWorldDelta(0x40000000));
        assertEquals(5, Packer32.unpackWorldDelta(0x50000000));
        assertEquals(6, Packer32.unpackWorldDelta(0x60000000));
        assertEquals(7, Packer32.unpackWorldDelta(0x70000000));
    }

    @Test
    public void packSubDelta()
    {
        assertEquals(0x00000000, Packer32.packSubDelta(2, 0));
        assertEquals(0x04000000, Packer32.packSubDelta(2, 1));
        assertEquals(0x08000000, Packer32.packSubDelta(2, 2));
        assertEquals(0x0C000000, Packer32.packSubDelta(2, 3));
        assertEquals(0x00000000, Packer32.packSubDelta(3, 0));
        assertEquals(0x01000000, Packer32.packSubDelta(3, 1));
        assertEquals(0x02000000, Packer32.packSubDelta(3, 2));
        assertEquals(0x03000000, Packer32.packSubDelta(3, 3));

        assertEquals(0x00C00000, Packer32.packSubDelta(4, 3));
        assertEquals(0x00300000, Packer32.packSubDelta(5, 3));
        assertEquals(0x000C0000, Packer32.packSubDelta(6, 3));
        assertEquals(0x00030000, Packer32.packSubDelta(7, 3));
        assertEquals(0x0000C000, Packer32.packSubDelta(8, 3));
        assertEquals(0x00003000, Packer32.packSubDelta(9, 3));
        assertEquals(0x00000C00, Packer32.packSubDelta(10, 3));
        assertEquals(0x00000300, Packer32.packSubDelta(11, 3));
        assertEquals(0x000000C0, Packer32.packSubDelta(12, 3));
        assertEquals(0x00000030, Packer32.packSubDelta(13, 3));
    }

    @Test
    public void unpackSubDelta()
    {
        assertEquals(0, Packer32.unpackSubDelta(2, 0x00000000));
        assertEquals(1, Packer32.unpackSubDelta(2, 0x04000000));
        assertEquals(2, Packer32.unpackSubDelta(2, 0x08000000));
        assertEquals(3, Packer32.unpackSubDelta(2, 0x0C000000));
        assertEquals(0, Packer32.unpackSubDelta(3, 0x00000000));
        assertEquals(1, Packer32.unpackSubDelta(3, 0x01000000));
        assertEquals(2, Packer32.unpackSubDelta(3, 0x02000000));
        assertEquals(3, Packer32.unpackSubDelta(3, 0x03000000));

        assertEquals(3, Packer32.unpackSubDelta(4, 0x00C00000));
        assertEquals(3, Packer32.unpackSubDelta(5, 0x00300000));
        assertEquals(3, Packer32.unpackSubDelta(6, 0x000C0000));
        assertEquals(3, Packer32.unpackSubDelta(7, 0x00030000));
        assertEquals(3, Packer32.unpackSubDelta(8, 0x0000C000));
        assertEquals(3, Packer32.unpackSubDelta(9, 0x00003000));
        assertEquals(3, Packer32.unpackSubDelta(10, 0x00000C00));
        assertEquals(3, Packer32.unpackSubDelta(11, 0x00000300));
        assertEquals(3, Packer32.unpackSubDelta(12, 0x000000C0));
        assertEquals(3, Packer32.unpackSubDelta(13, 0x00000030));
    }

    @Test
    public void packLevel()
    {
        assertEquals(0x00000001, Packer32.packLevel(1));
        assertEquals(0x00000002, Packer32.packLevel(2));
        assertEquals(0x0000000C, Packer32.packLevel(12));
        assertEquals(0x0000000D, Packer32.packLevel(13));
    }
}
