
function test_namespace() {
  assertNotUndefined(geodelta);
  assertNotUndefined(geodelta.encoder);
}

function test_encodeWorldDelta()
{
  assertEquals("Z", geodelta.encoder.encodeWorldDelta(0));
  assertEquals("Y", geodelta.encoder.encodeWorldDelta(1));
  assertEquals("X", geodelta.encoder.encodeWorldDelta(2));
  assertEquals("W", geodelta.encoder.encodeWorldDelta(3));
  assertEquals("V", geodelta.encoder.encodeWorldDelta(4));
  assertEquals("T", geodelta.encoder.encodeWorldDelta(5));
  assertEquals("S", geodelta.encoder.encodeWorldDelta(6));
  assertEquals("R", geodelta.encoder.encodeWorldDelta(7));
}

function test_encodeWorldDelta__invalidArg1()
{
  try
  {
    geodelta.encoder.encodeWorldDelta(-1);
  }
  catch ( e )
  {
    return;
  }
  fail("exception not raised");
}

function test_encodeWorldDelta__invalidArg2()
{
  try
  {
    geodelta.encoder.encodeWorldDelta(8);
  }
  catch ( e )
  {
    return;
  }
  fail("exception not raised");
}

function test_decodeWorldDelta()
{
  assertEquals(0, geodelta.encoder.decodeWorldDelta("Z"));
  assertEquals(1, geodelta.encoder.decodeWorldDelta("Y"));
  assertEquals(2, geodelta.encoder.decodeWorldDelta("X"));
  assertEquals(3, geodelta.encoder.decodeWorldDelta("W"));
  assertEquals(4, geodelta.encoder.decodeWorldDelta("V"));
  assertEquals(5, geodelta.encoder.decodeWorldDelta("T"));
  assertEquals(6, geodelta.encoder.decodeWorldDelta("S"));
  assertEquals(7, geodelta.encoder.decodeWorldDelta("R"));
}

/*
    @Test(expected = IllegalArgumentException.class)
    public void decodeWorldDelta__invalidArg1()
    {
        geodelta.encoder.decodeWorldDelta("z");
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeWorldDelta__invalidArg2()
    {
        geodelta.encoder.decodeWorldDelta("A");
    }

    @Test
    public void allEncodeAndDecodeWorldDelta()
    {
        for ( int id = 0; id <= 7; id++ )
        {
            final char encoded1 = geodelta.encoder.encodeWorldDelta((byte)id);
            final byte decoded1 = geodelta.encoder.decodeWorldDelta(encoded1);
            final char encoded2 = geodelta.encoder.encodeWorldDelta(decoded1);
            assertEquals(encoded1, encoded2);
        }
    }
*/

function test_encodeSubDelta__1()
{
  assertEquals("2", geodelta.encoder.encodeSubDelta([0, 0]));
  assertEquals("3", geodelta.encoder.encodeSubDelta([0, 1]));
  assertEquals("4", geodelta.encoder.encodeSubDelta([0, 2]));
  assertEquals("5", geodelta.encoder.encodeSubDelta([0, 3]));
  assertEquals("6", geodelta.encoder.encodeSubDelta([1, 0]));
  assertEquals("7", geodelta.encoder.encodeSubDelta([1, 1]));
  assertEquals("8", geodelta.encoder.encodeSubDelta([1, 2]));
  assertEquals("A", geodelta.encoder.encodeSubDelta([1, 3]));
  assertEquals("B", geodelta.encoder.encodeSubDelta([2, 0]));
  assertEquals("C", geodelta.encoder.encodeSubDelta([2, 1]));
  assertEquals("D", geodelta.encoder.encodeSubDelta([2, 2]));
  assertEquals("E", geodelta.encoder.encodeSubDelta([2, 3]));
  assertEquals("F", geodelta.encoder.encodeSubDelta([3, 0]));
  assertEquals("G", geodelta.encoder.encodeSubDelta([3, 1]));
  assertEquals("H", geodelta.encoder.encodeSubDelta([3, 2]));
  assertEquals("J", geodelta.encoder.encodeSubDelta([3, 3]));
}

function test_encodeSubDelta__2()
{
  assertEquals("K", geodelta.encoder.encodeSubDelta([0]));
  assertEquals("M", geodelta.encoder.encodeSubDelta([1]));
  assertEquals("N", geodelta.encoder.encodeSubDelta([2]));
  assertEquals("P", geodelta.encoder.encodeSubDelta([3]));
}

function test_encodeSubDelta__3()
{
  assertEquals("2K", geodelta.encoder.encodeSubDelta([0, 0, 0]));
  assertEquals("22", geodelta.encoder.encodeSubDelta([0, 0, 0, 0]));
  assertEquals("3N", geodelta.encoder.encodeSubDelta([0, 1, 2]));
  assertEquals("3E", geodelta.encoder.encodeSubDelta([0, 1, 2, 3]));
}

/*
    @Test(expected = IllegalArgumentException.class)
    public void encodeSubDelta__invalidArg1()
    {
        geodelta.encoder.encodeSubDelta(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void encodeSubDelta__invalidArg2()
    {
        geodelta.encoder.encodeSubDelta(new byte[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void encodeSubDelta__invalidArg3()
    {
        geodelta.encoder.encodeSubDelta(new byte[] {-1});
    }

    @Test(expected = IllegalArgumentException.class)
    public void encodeSubDelta__invalidArg4()
    {
        geodelta.encoder.encodeSubDelta(new byte[] {4});
    }
*/

function test_decodeSubDelta__1()
{
  assertArrayEquals([0, 0], geodelta.encoder.decodeSubDelta("2"));
  assertArrayEquals([0, 1], geodelta.encoder.decodeSubDelta("3"));
  assertArrayEquals([0, 2], geodelta.encoder.decodeSubDelta("4"));
  assertArrayEquals([0, 3], geodelta.encoder.decodeSubDelta("5"));
  assertArrayEquals([1, 0], geodelta.encoder.decodeSubDelta("6"));
  assertArrayEquals([1, 1], geodelta.encoder.decodeSubDelta("7"));
  assertArrayEquals([1, 2], geodelta.encoder.decodeSubDelta("8"));
  assertArrayEquals([1, 3], geodelta.encoder.decodeSubDelta("A"));
  assertArrayEquals([2, 0], geodelta.encoder.decodeSubDelta("B"));
  assertArrayEquals([2, 1], geodelta.encoder.decodeSubDelta("C"));
  assertArrayEquals([2, 2], geodelta.encoder.decodeSubDelta("D"));
  assertArrayEquals([2, 3], geodelta.encoder.decodeSubDelta("E"));
  assertArrayEquals([3, 0], geodelta.encoder.decodeSubDelta("F"));
  assertArrayEquals([3, 1], geodelta.encoder.decodeSubDelta("G"));
  assertArrayEquals([3, 2], geodelta.encoder.decodeSubDelta("H"));
  assertArrayEquals([3, 3], geodelta.encoder.decodeSubDelta("J"));
}

function test_decodeSubDelta__2()
{
  assertArrayEquals([0], geodelta.encoder.decodeSubDelta("K"));
  assertArrayEquals([1], geodelta.encoder.decodeSubDelta("M"));
  assertArrayEquals([2], geodelta.encoder.decodeSubDelta("N"));
  assertArrayEquals([3], geodelta.encoder.decodeSubDelta("P"));
}

function test_decodeSubDelta__3()
{
  assertArrayEquals([0, 0, 0], geodelta.encoder.decodeSubDelta("2K"));
  assertArrayEquals([0, 0, 0, 0], geodelta.encoder.decodeSubDelta("22"));
  assertArrayEquals([0, 1, 2], geodelta.encoder.decodeSubDelta("3N"));
  assertArrayEquals([0, 1, 2, 3], geodelta.encoder.decodeSubDelta("3E"));
}

/*
    @Test(expected = IllegalArgumentException.class)
    public void decodeSubDelta__invalidArg1()
    {
        geodelta.encoder.decodeSubDelta(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeSubDelta__invalidArg2()
    {
        geodelta.encoder.decodeSubDelta("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeSubDelta__invalidArg3()
    {
        geodelta.encoder.decodeSubDelta("1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeSubDelta__invalidArg4()
    {
        geodelta.encoder.decodeSubDelta("Z");
    }

    @Test
    public void allEncodeAndDecodeSubDelta__level2()
    {
        for ( int id1 = 0; id1 <= 3; id1++ )
        {
            final byte[] ids = {(byte)id1};
            final String encoded1 = geodelta.encoder.encodeSubDelta(ids);
            final byte[] decoded1 = geodelta.encoder.decodeSubDelta(encoded1);
            final String encoded2 = geodelta.encoder.encodeSubDelta(decoded1);
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
                final String encoded1 = geodelta.encoder.encodeSubDelta(ids);
                final byte[] decoded1 = geodelta.encoder.decodeSubDelta(encoded1);
                final String encoded2 = geodelta.encoder.encodeSubDelta(decoded1);
                assertEquals(encoded1, encoded2);
            }
        }
    }
*/

function test_encode()
{
  assertEquals("Z",   geodelta.encoder.encode([0]));
  assertEquals("ZM",  geodelta.encoder.encode([0, 1]));
  assertEquals("Z8",  geodelta.encoder.encode([0, 1, 2]));
  assertEquals("Z8P", geodelta.encoder.encode([0, 1, 2, 3]));
  assertEquals("R",   geodelta.encoder.encode([7]));
  assertEquals("RP",  geodelta.encoder.encode([7, 3]));
  assertEquals("RH",  geodelta.encoder.encode([7, 3, 2]));
  assertEquals("RHM", geodelta.encoder.encode([7, 3, 2, 1]));
}

/*
    @Test(expected = IllegalArgumentException.class)
    public void encode__invalidArg1()
    {
        geodelta.encoder.encode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void encode__invalidArg2()
    {
        geodelta.encoder.encode(new byte[0]);
    }
*/

function test_decode()
{
  assertArrayEquals([0],          geodelta.encoder.decode("Z"));
  assertArrayEquals([0, 1],       geodelta.encoder.decode("ZM"));
  assertArrayEquals([0, 1, 2],    geodelta.encoder.decode("Z8"));
  assertArrayEquals([0, 1, 2, 3], geodelta.encoder.decode("Z8P"));
  assertArrayEquals([7],          geodelta.encoder.decode("R"));
  assertArrayEquals([7, 3],       geodelta.encoder.decode("RP"));
  assertArrayEquals([7, 3, 2],    geodelta.encoder.decode("RH"));
  assertArrayEquals([7, 3, 2, 1], geodelta.encoder.decode("RHM"));
}

/*
    @Test(expected = IllegalArgumentException.class)
    public void decode__invalidArg1()
    {
        geodelta.encoder.decode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decode__invalidArg2()
    {
        geodelta.encoder.decode("");
    }

    @Test
    public void randomEncodeAndDecode()
    {
        final Random r = new Random();

        for ( int i = 0; i < 1000; i++ )
        {
            final byte[] ids = createRandomDeltaIds(r, 20);
            final String encoded1 = geodelta.encoder.encode(ids);
            final byte[] decoded1 = geodelta.encoder.decode(encoded1);
            final String encoded2 = geodelta.encoder.encode(decoded1);
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
*/
