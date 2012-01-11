
package jp.nayutaya.geodelta;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UtilityTest
{
    @Test
    public void mod()
    {
        assertEquals(0.0, Utility.mod(+0.0, 1.0), 1E-15);
        assertEquals(0.0, Utility.mod(+1.0, 1.0), 1E-15);
        assertEquals(0.5, Utility.mod(+1.5, 1.0), 1E-15);
        assertEquals(0.0, Utility.mod(+2.0, 1.0), 1E-15);

        assertEquals(0.0, Utility.mod(+0.0, 2.0), 1E-15);
        assertEquals(1.0, Utility.mod(+1.0, 2.0), 1E-15);
        assertEquals(0.0, Utility.mod(+2.0, 2.0), 1E-15);
        assertEquals(1.0, Utility.mod(+3.0, 2.0), 1E-15);
        assertEquals(0.0, Utility.mod(+4.0, 2.0), 1E-15);

        assertEquals(0.0, Utility.mod(-1.0, 1.0), 1E-15);
        assertEquals(0.5, Utility.mod(-1.5, 1.0), 1E-15);
        assertEquals(0.0, Utility.mod(-2.0, 1.0), 1E-15);

        assertEquals(1.0, Utility.mod(-1.0, 2.0), 1E-15);
        assertEquals(0.0, Utility.mod(-2.0, 2.0), 1E-15);
        assertEquals(1.0, Utility.mod(-3.0, 2.0), 1E-15);
        assertEquals(0.0, Utility.mod(-4.0, 2.0), 1E-15);
    }
}
