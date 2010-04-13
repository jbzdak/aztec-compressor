package cx.ath.jbzdak.aztec.streamDecompressor;

import cx.ath.jbzdak.aztec.Utils;
import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.PlateauCompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.SlopeCompressed;

import java.io.EOFException;
import java.io.IOException;

/**
 * User: Jacek Bzdak jbdak@gmail.com
 * Date: Apr 13, 2010
 */
public class SimpleBinaryDecompressor extends AbstractDecompressor{

    public CompressedPoints getNextPoint() throws IOException {
        try {
            switch (inputStream.readByte()){
                case Utils.PLATEAU_CODE:
                    return new PlateauCompressedPoints(inputStream.readInt(), inputStream.readDouble());
                case Utils.SLOPE_CODE:
                    return new SlopeCompressed(inputStream.readInt(), inputStream.readDouble());
                default:
                    throw new DecompressionException("Nie znany bajt definiujący typ odcinka");
            }
        } catch (EOFException e) {
            return null;
        }
    }
}
