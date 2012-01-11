
package jp.nayutaya.geodelta;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.Random;
import org.junit.Test;

public class EncoderTest
{
    @Test
    public void encodeWorldDelta()
    {
        assertEquals('Z', Encoder.encodeWorldDelta((byte)0));
        assertEquals('Y', Encoder.encodeWorldDelta((byte)1));
        assertEquals('X', Encoder.encodeWorldDelta((byte)2));
        assertEquals('W', Encoder.encodeWorldDelta((byte)3));
        assertEquals('V', Encoder.encodeWorldDelta((byte)4));
        assertEquals('T', Encoder.encodeWorldDelta((byte)5));
        assertEquals('S', Encoder.encodeWorldDelta((byte)6));
        assertEquals('R', Encoder.encodeWorldDelta((byte)7));
    }

    @Test(expected = IllegalArgumentException.class)
    public void encodeWorldDelta__invalidArg1()
    {
        Encoder.encodeWorldDelta((byte)-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void encodeWorldDelta__invalidArg2()
    {
        Encoder.encodeWorldDelta((byte)8);
    }

    @Test
    public void decodeWorldDelta()
    {
        assertEquals(0, Encoder.decodeWorldDelta('Z'));
        assertEquals(1, Encoder.decodeWorldDelta('Y'));
        assertEquals(2, Encoder.decodeWorldDelta('X'));
        assertEquals(3, Encoder.decodeWorldDelta('W'));
        assertEquals(4, Encoder.decodeWorldDelta('V'));
        assertEquals(5, Encoder.decodeWorldDelta('T'));
        assertEquals(6, Encoder.decodeWorldDelta('S'));
        assertEquals(7, Encoder.decodeWorldDelta('R'));
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeWorldDelta__invalidArg1()
    {
        Encoder.decodeWorldDelta('z');
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeWorldDelta__invalidArg2()
    {
        Encoder.decodeWorldDelta('A');
    }

    @Test
    public void allEncodeAndDecodeWorldDelta()
    {
        for ( int id = 0; id <= 7; id++ )
        {
            final char encoded1 = Encoder.encodeWorldDelta((byte)id);
            final byte decoded1 = Encoder.decodeWorldDelta(encoded1);
            final char encoded2 = Encoder.encodeWorldDelta(decoded1);
            assertEquals(encoded1, encoded2);
        }
    }

    @Test
    public void encodeSubDelta__1()
    {
        assertEquals("2", Encoder.encodeSubDelta(new byte[] {0, 0}));
        assertEquals("3", Encoder.encodeSubDelta(new byte[] {0, 1}));
        assertEquals("4", Encoder.encodeSubDelta(new byte[] {0, 2}));
        assertEquals("5", Encoder.encodeSubDelta(new byte[] {0, 3}));
        assertEquals("6", Encoder.encodeSubDelta(new byte[] {1, 0}));
        assertEquals("7", Encoder.encodeSubDelta(new byte[] {1, 1}));
        assertEquals("8", Encoder.encodeSubDelta(new byte[] {1, 2}));
        assertEquals("A", Encoder.encodeSubDelta(new byte[] {1, 3}));
        assertEquals("B", Encoder.encodeSubDelta(new byte[] {2, 0}));
        assertEquals("C", Encoder.encodeSubDelta(new byte[] {2, 1}));
        assertEquals("D", Encoder.encodeSubDelta(new byte[] {2, 2}));
        assertEquals("E", Encoder.encodeSubDelta(new byte[] {2, 3}));
        assertEquals("F", Encoder.encodeSubDelta(new byte[] {3, 0}));
        assertEquals("G", Encoder.encodeSubDelta(new byte[] {3, 1}));
        assertEquals("H", Encoder.encodeSubDelta(new byte[] {3, 2}));
        assertEquals("J", Encoder.encodeSubDelta(new byte[] {3, 3}));
    }

    @Test
    public void encodeSubDelta__2()
    {
        assertEquals("K", Encoder.encodeSubDelta(new byte[] {0}));
        assertEquals("M", Encoder.encodeSubDelta(new byte[] {1}));
        assertEquals("N", Encoder.encodeSubDelta(new byte[] {2}));
        assertEquals("P", Encoder.encodeSubDelta(new byte[] {3}));
    }

    @Test
    public void encodeSubDelta__3()
    {
        assertEquals("2K", Encoder.encodeSubDelta(new byte[] {0, 0, 0}));
        assertEquals("22", Encoder.encodeSubDelta(new byte[] {0, 0, 0, 0}));
        assertEquals("3N", Encoder.encodeSubDelta(new byte[] {0, 1, 2}));
        assertEquals("3E", Encoder.encodeSubDelta(new byte[] {0, 1, 2, 3}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void encodeSubDelta__invalidArg1()
    {
        Encoder.encodeSubDelta(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void encodeSubDelta__invalidArg2()
    {
        Encoder.encodeSubDelta(new byte[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void encodeSubDelta__invalidArg3()
    {
        Encoder.encodeSubDelta(new byte[] {-1});
    }

    @Test(expected = IllegalArgumentException.class)
    public void encodeSubDelta__invalidArg4()
    {
        Encoder.encodeSubDelta(new byte[] {4});
    }

    @Test
    public void decodeSubDelta__1()
    {
        assertArrayEquals(new byte[] {0, 0}, Encoder.decodeSubDelta("2"));
        assertArrayEquals(new byte[] {0, 1}, Encoder.decodeSubDelta("3"));
        assertArrayEquals(new byte[] {0, 2}, Encoder.decodeSubDelta("4"));
        assertArrayEquals(new byte[] {0, 3}, Encoder.decodeSubDelta("5"));
        assertArrayEquals(new byte[] {1, 0}, Encoder.decodeSubDelta("6"));
        assertArrayEquals(new byte[] {1, 1}, Encoder.decodeSubDelta("7"));
        assertArrayEquals(new byte[] {1, 2}, Encoder.decodeSubDelta("8"));
        assertArrayEquals(new byte[] {1, 3}, Encoder.decodeSubDelta("A"));
        assertArrayEquals(new byte[] {2, 0}, Encoder.decodeSubDelta("B"));
        assertArrayEquals(new byte[] {2, 1}, Encoder.decodeSubDelta("C"));
        assertArrayEquals(new byte[] {2, 2}, Encoder.decodeSubDelta("D"));
        assertArrayEquals(new byte[] {2, 3}, Encoder.decodeSubDelta("E"));
        assertArrayEquals(new byte[] {3, 0}, Encoder.decodeSubDelta("F"));
        assertArrayEquals(new byte[] {3, 1}, Encoder.decodeSubDelta("G"));
        assertArrayEquals(new byte[] {3, 2}, Encoder.decodeSubDelta("H"));
        assertArrayEquals(new byte[] {3, 3}, Encoder.decodeSubDelta("J"));
    }

    @Test
    public void decodeSubDelta__2()
    {
        assertArrayEquals(new byte[] {0}, Encoder.decodeSubDelta("K"));
        assertArrayEquals(new byte[] {1}, Encoder.decodeSubDelta("M"));
        assertArrayEquals(new byte[] {2}, Encoder.decodeSubDelta("N"));
        assertArrayEquals(new byte[] {3}, Encoder.decodeSubDelta("P"));
    }

    @Test
    public void decodeSubDelta__3()
    {
        assertArrayEquals(new byte[] {0, 0, 0}, Encoder.decodeSubDelta("2K"));
        assertArrayEquals(new byte[] {0, 0, 0, 0}, Encoder.decodeSubDelta("22"));
        assertArrayEquals(new byte[] {0, 1, 2}, Encoder.decodeSubDelta("3N"));
        assertArrayEquals(new byte[] {0, 1, 2, 3}, Encoder.decodeSubDelta("3E"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeSubDelta__invalidArg1()
    {
        Encoder.decodeSubDelta(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeSubDelta__invalidArg2()
    {
        Encoder.decodeSubDelta("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeSubDelta__invalidArg3()
    {
        Encoder.decodeSubDelta("1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeSubDelta__invalidArg4()
    {
        Encoder.decodeSubDelta("Z");
    }

    @Test
    public void allEncodeAndDecodeSubDelta__level2()
    {
        for ( int id1 = 0; id1 <= 3; id1++ )
        {
            final byte[] ids = {(byte)id1};
            final String encoded1 = Encoder.encodeSubDelta(ids);
            final byte[] decoded1 = Encoder.decodeSubDelta(encoded1);
            final String encoded2 = Encoder.encodeSubDelta(decoded1);
            assertEquals(encoded1, encoded2);
        }
    }

    @Test
    public void allEncodeAndDecodeSubDelta__level3()
    {
        for ( int id1 = 0; id1 <= 3; id1++ )
        {
            for ( int id2 = 0; id2 <= 3; id2++ )
            {
                final byte[] ids = {(byte)id1, (byte)id2};
                final String encoded1 = Encoder.encodeSubDelta(ids);
                final byte[] decoded1 = Encoder.decodeSubDelta(encoded1);
                final String encoded2 = Encoder.encodeSubDelta(decoded1);
                assertEquals(encoded1, encoded2);
            }
        }
    }

    @Test
    public void encode()
    {
        assertEquals("Z", Encoder.encode(new byte[] {0}));
        assertEquals("ZM", Encoder.encode(new byte[] {0, 1}));
        assertEquals("Z8", Encoder.encode(new byte[] {0, 1, 2}));
        assertEquals("Z8P", Encoder.encode(new byte[] {0, 1, 2, 3}));
        assertEquals("R", Encoder.encode(new byte[] {7}));
        assertEquals("RP", Encoder.encode(new byte[] {7, 3}));
        assertEquals("RH", Encoder.encode(new byte[] {7, 3, 2}));
        assertEquals("RHM", Encoder.encode(new byte[] {7, 3, 2, 1}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void encode__invalidArg1()
    {
        Encoder.encode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void encode__invalidArg2()
    {
        Encoder.encode(new byte[0]);
    }

    @Test
    public void decode()
    {
        assertArrayEquals(new byte[] {0}, Encoder.decode("Z"));
        assertArrayEquals(new byte[] {0, 1}, Encoder.decode("ZM"));
        assertArrayEquals(new byte[] {0, 1, 2}, Encoder.decode("Z8"));
        assertArrayEquals(new byte[] {0, 1, 2, 3}, Encoder.decode("Z8P"));
        assertArrayEquals(new byte[] {7}, Encoder.decode("R"));
        assertArrayEquals(new byte[] {7, 3}, Encoder.decode("RP"));
        assertArrayEquals(new byte[] {7, 3, 2}, Encoder.decode("RH"));
        assertArrayEquals(new byte[] {7, 3, 2, 1}, Encoder.decode("RHM"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void decode__invalidArg1()
    {
        Encoder.decode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decode__invalidArg2()
    {
        Encoder.decode("");
    }

    @Test
    public void randomEncodeAndDecode()
    {
        final Random r = new Random();

        for ( int i = 0; i < 1000; i++ )
        {
            final byte[] ids = createRandomDeltaIds(r, 20);
            final String encoded1 = Encoder.encode(ids);
            final byte[] decoded1 = Encoder.decode(encoded1);
            final String encoded2 = Encoder.encode(decoded1);
            assertArrayEquals(ids, decoded1);
            assertEquals(encoded1, encoded2);
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
