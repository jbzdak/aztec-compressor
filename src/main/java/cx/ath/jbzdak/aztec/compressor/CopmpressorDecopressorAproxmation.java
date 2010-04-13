package cx.ath.jbzdak.aztec.compressor;

import java.io.IOException;
import java.io.OutputStream;

import cx.ath.jbzdak.aztec.Compressor;
import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.decompressor.parabolicApproximation;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: 2010-04-13
 */
public class CopmpressorDecopressorAproxmation extends parabolicApproximation implements Compressor {
   public void addPoints(CompressedPoints points) throws IOException {
      addCompressed(points);
   }

   public void start(OutputStream outputStream) throws IOException {
      //To change body of implemented methods use File | Settings | File Templates.
   }

   public void finish() throws IOException {
      //To change body of implemented methods use File | Settings | File Templates.
   }
}
