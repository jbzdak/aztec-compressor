package cx.ath.jbzdak.aztec.compressor;

import java.io.IOException;
import java.io.OutputStream;

import cx.ath.jbzdak.aztec.Compressor;
import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.decompressor.SimpleDecompressor;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 16, 2010
 */
public class CompressorDecopressor extends SimpleDecompressor implements Compressor{
   public void addPoints(CompressedPoints points) throws IOException {
      addCompressed(points);
   }

   public void start(OutputStream outputStream) throws IOException {

   }

   public void finish() throws IOException {
   }
}
