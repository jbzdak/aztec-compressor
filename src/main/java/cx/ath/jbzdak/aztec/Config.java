package cx.ath.jbzdak.aztec;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 9, 2010
 */
public class Config {

   public static final Config CONFIG = new Config(); 

   private final Properties properties = new Properties();

   public final int samplesMax;

   public final int minimalPlateauLenght;

   public final double sampleTreshold;

   public Config(){
      try {
         properties.load(getClass().getResourceAsStream("compressor.properties"));
      } catch (IOException e) {
         e.printStackTrace();
      }
      samplesMax = Integer.valueOf(properties.getProperty("samplesMax"));
      sampleTreshold = Integer.valueOf(properties.getProperty("sampleTreshold"));
      minimalPlateauLenght = Integer.valueOf(properties.getProperty("minimalPlateauLenght"));
   }
}
