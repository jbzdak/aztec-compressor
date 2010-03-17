package cx.ath.jbzdak.aztec.states;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.SlopeCompressed;

import static cx.ath.jbzdak.aztec.Config.CONFIG;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 9, 2010
 */
public class SlopeState implements AztecState{

   private static final Logger LOGGER = LoggerFactory.getLogger(SlopeState.class);

   int slopeLenght = 2;

   final double sign;

   double yMax = Double.MIN_VALUE, yMin = Double.MAX_VALUE;

   int plateauLenght;

   double lastMean;

   AztecState nextState;

   CompressedPoints compressed;

   public SlopeState(int slopeLenght, double sign, double lastMean) {
      this.slopeLenght = slopeLenght;
      this.sign = sign;
      this.lastMean = lastMean;
   }

   public void addPoint(double point) {
      slopeLenght++;
      yMin=point<yMin?point:yMin;
      yMax=point>yMax?point:yMax;

      boolean notAPlateau = (yMax - yMin) > CONFIG.sampleTreshold;

      if(LOGGER.isTraceEnabled()){
           LOGGER.trace("Adding point {}, yMax is {}, yMin is {}, plateauLenght is {}",
                 new Object[]{point, yMax, yMin, slopeLenght});
      }

      if(notAPlateau){
         plateauLenght = 0;
         double mean = (yMax + yMin)/2;
         yMin = Double.MAX_VALUE;
         yMax = Double.MIN_VALUE;
         if((lastMean - mean) * sign <0){
            nextState = new SlopeState(0,-sign, mean);
            compressed = forceCompressed();
            return;
         }
         lastMean = mean;
      }else{
         boolean isPlateau = plateauLenght >= CONFIG.minimalPlateauLenght;
         if(isPlateau){
            nextState = new PlateauState(yMax, yMin, plateauLenght);
            compressed = forceCompressed();
            return;
         }
         plateauLenght++;
      }
   }

   public AztecState getNextState() {
      return nextState;
   }

   public CompressedPoints getCompressed() {
      return compressed;
   }

   public CompressedPoints forceCompressed() {
      return new SlopeCompressed(slopeLenght - plateauLenght, lastMean);
   }
}
