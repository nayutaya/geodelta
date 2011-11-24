
package jp.nayutaya.geodelta;

public class Packer64
{
    /* package */static long packWorldDelta(final byte id)
    {
        return (long)id << 59;
    }
}
