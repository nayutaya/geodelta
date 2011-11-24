
package jp.nayutaya.geodelta;

public class Utility
{
    private Utility()
    {
        // nop
    }

    /**
     * 剰余を計算する。
     * RubyのFloat#%メソッドと同様の値を返す。
     *
     * @param a 剰余を計算する値
     * @param b 法とする値
     * @return 剰余
     */
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
