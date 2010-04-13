package cx.ath.jbzdak.aztec.compressor;

import cx.ath.jbzdak.aztec.Utils;
import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.PlateauCompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.SlopeCompressed;

import java.io.IOException;
import java.nio.ByteBuffer;

import static cx.ath.jbzdak.aztec.Utils.*;

/**
 * User: Jacek Bzdak jbdak@gmail.com
 * Date: Apr 13, 2010
 */
public class SimpleBinaryCompressor extends AbstractCompressor{


    public void addPoints(CompressedPoints points) throws IOException {
        if (points instanceof PlateauCompressedPoints) {
            PlateauCompressedPoints plateauCompressedPoints = (PlateauCompressedPoints) points;
            outputStream.write(PLATEAU_CODE);
            outputStream.writeInt(plateauCompressedPoints.getPlateauLenght());
            outputStream.writeDouble(plateauCompressedPoints.getyMean());

        }
        if(points instanceof SlopeCompressed){
            SlopeCompressed compressed = (SlopeCompressed) points;
            outputStream.write(SLOPE_CODE);
            outputStream.writeInt(compressed.getSlopeTime());
            outputStream.writeDouble(compressed.getyLast());
        }
    }
}
