package cx.ath.jbzdak.aztec.states;

import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;

import static cx.ath.jbzdak.aztec.Config.CONFIG;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 9, 2010
 */
public abstract class AbstractState implements AztecState{

   AztecState nextState;

   CompressedPoints compressed;

   double yMax = Double.MIN_VALUE, yMin = Double.MAX_VALUE;

   int plateauLenght = 0;

   protected void controlLogic(double y){
      if(y < yMin){
         yMin = y;
      }else if(y > yMax){
         yMax = y;
      }

      boolean plateauTooWide = (yMax - yMin) > CONFIG.sampleTreshold;
      if(plateauLenght < CONFIG.minimalPlateauLenght){
         //savedPoints.add(point);
         if(plateauTooWide){
            //KONIEC budujemy slopea
         }
      }
      boolean plateauTooLong = plateauLenght > CONFIG.samplesMax;

      if(plateauTooLong || plateauTooWide){
         compressed = forceCompressed();
         nextState = new PlateauState();
      }
   }

   public CompressedPoints getCompressed() {
      return compressed;
   }

   public AztecState getNextState() {
      return nextState;
   }
}
