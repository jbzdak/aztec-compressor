package cx.ath.jbzdak.aztec.streamDecompressor;

import cx.ath.jbzdak.aztec.StreamDecompressor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * User: Jacek Bzdak jbdak@gmail.com
 * Date: May 4, 2010
 */
public abstract class ReaderDecompressor implements StreamDecompressor{

   BufferedReader reader;

   @Override
   public void start(InputStream inputStream) {
      reader = new BufferedReader(new InputStreamReader(inputStream));
   }
}
