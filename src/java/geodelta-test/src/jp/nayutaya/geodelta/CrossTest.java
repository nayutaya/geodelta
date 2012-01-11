
package jp.nayutaya.geodelta;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;

public class CrossTest
{
    @Test
    public void center() throws IOException
    {
        final FileReader fr = new FileReader("../../../data/delta_center.txt");
        final BufferedReader br = new BufferedReader(fr);

        String line = null;
        int lineNo = 0;
        while ( (line = br.readLine()) != null )
        {
            lineNo++;
            if ( lineNo == 1 )
            {
                continue;
            }

            final String[] tuple = line.split("\\|");
            final double randomLat = Double.parseDouble(tuple[0].trim());
            final double randomLng = Double.parseDouble(tuple[1].trim());
            final int level = Integer.parseInt(tuple[2].trim());
            final String code = tuple[3].trim();
            final double centerLat = Double.parseDouble(tuple[4].trim());
            final double centerLng = Double.parseDouble(tuple[5].trim());

            final String ref = "line:" + lineNo + " level:" + level;
            final String code2 = GeoDelta.getDeltaCode(randomLat, randomLng, level);
            final double[] center2 = GeoDelta.getCenter(code2);
            assertEquals(ref, code, code2);
            assertEquals(ref, centerLat, center2[0], 1E-13);
            assertEquals(ref, centerLng, center2[1], 1E-15);
        }

        fr.close();
    }
}
