package cx.ath.jbzdak.aztec.streamDecompressor;

import cx.ath.jbzdak.aztec.StreamDecompressor;
import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.PlateauCompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.SlopeCompressed;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.MalformedInputException;

/**
 * User: Jacek Bzdak jbdak@gmail.com
 * Date: May 4, 2010
 */
public class TextDecompressor extends ReaderDecompressor{

   @Override
   public CompressedPoints getNextPoint() throws IOException {
      int steps;
      float voltage;
      String read = reader.readLine();
      if(read == null){
         return  null;
      }                      
      String[] split = read.split("\\,");
      if(split.length != 2) {
         throw new DecompressionException("Bad file format!");         
      }
      steps = Integer.parseInt(split[0].trim());
      voltage = Float.parseFloat(split[1].trim());
      if(steps < 0){
         return new SlopeCompressed(-steps, voltage);
      }else{
         return new PlateauCompressedPoints(steps, voltage);
      }     
   }
}
