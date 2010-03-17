package cx.ath.jbzdak.aztec.states;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.PlateauCompressedPoints;

import static cx.ath.jbzdak.aztec.Config.CONFIG;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 9, 2010
 */
public class PlateauState implements AztecState{

   private static final Logger LOGGER = LoggerFactory.getLogger(PlateauState.class);

   AztecState nextState;

   CompressedPoints compressed;

   double yMax = Double.MIN_VALUE, yMin = Double.MAX_VALUE;

   int plateauLenght = 0;

   public PlateauState(double yMax, double yMin, int plateauLenght) {
      this.yMax = yMax;
      this.yMin = yMin;
      this.plateauLenght = plateauLenght;
   }

   public void addPoint(double point) {
      plateauLenght++;

      yMin=point<yMin?point:yMin;
      yMax=point>yMax?point:yMax;

      boolean plateauTooWide = (yMax - yMin) > CONFIG.sampleTreshold;
      boolean plateauTooLong = plateauLenght > CONFIG.samplesMax;
      if(LOGGER.isTraceEnabled()){
         LOGGER.trace("Adding point {}, yMax is {}, yMin is {}, plateauLenght is {}",
                 new Object[]{point, yMax, yMin, plateauLenght});
         LOGGER.trace("PlateuTooWide {}, PlateauLooLong {}", plateauTooWide, plateauTooLong);
      }

      if(plateauTooLong || plateauTooWide){
         compressed = forceCompressed();
         nextState = new CreateNextRegionStateState();
      }
   }

   public CompressedPoints forceCompressed() {
      final double yMean = (yMax + yMin) / 2;
      if(LOGGER.isTraceEnabled()){
         LOGGER.trace("Getting compressed points, yMean is {}", yMean);
      }
      return new PlateauCompressedPoints(plateauLenght, yMean);
   }

   public CompressedPoints getCompressed() {
      return compressed;
   }

   public AztecState getNextState() {
      return nextState;
   }
}
