
package jp.nayutaya.geodelta;

public class Utility
{
    private Utility()
    {
        // nop
    }

    public static double mod(final double a, final double b)
    {
        double x = a;
        while ( x >= b )
        {
            x -= b;
        }
        while ( x < 0.0 )
        {
            x += b;
        }
        return x;
    }
}
