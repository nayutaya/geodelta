
package jp.nayutaya.geodelta;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Assert
{
    /* package */static void assertArrayArrayEquals(final byte[][] expecteds, final byte[][] actuals)
    {
        assertEquals(expecteds.length, actuals.length);
        for ( int i = 0, len = expecteds.length; i < len; i++ )
        {
            assertArrayEquals(expecteds[i], actuals[i]);
        }
    }

    /* package */static void assertArrayArrayEqualsWithPrint(final byte[][] expecteds, final byte[][] actuals)
    {
        System.out.print("expecteds {");
        for ( int i = 0; i < expecteds.length; i++ )
        {
            System.out.print(" {");
            for ( int j = 0; j < expecteds[i].length; j++ )
            {
                System.out.print(" " + expecteds[i][j]);
            }
            System.out.print(" }");
        }
        System.out.println(" }");

        System.out.print("actuals   {");
        for ( int i = 0; i < actuals.length; i++ )
        {
            System.out.print(" {");
            for ( int j = 0; j < actuals[i].length; j++ )
            {
                System.out.print(" " + actuals[i][j]);
            }
            System.out.print(" }");
        }
        System.out.println(" }");

        assertArrayArrayEquals(expecteds, actuals);
    }

    /* package */static void assertArrayArrayEquals(final double[][] expecteds, final double[][] actuals, final double delta)
    {
        assertEquals(expecteds.length, actuals.length);
        for ( int i = 0, len = expecteds.length; i < len; i++ )
        {
            assertArrayEquals(expecteds[i], actuals[i], delta);
        }
    }
}
