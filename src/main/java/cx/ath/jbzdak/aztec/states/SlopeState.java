package cx.ath.jbzdak.aztec.states;

import cx.ath.jbzdak.aztec.Point;
import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 9, 2010
 */
public class SlopeState implements AztecState{

   final double a, b;

   int slopeLenght = 2;

   public void addPoint(Point point) {
      slopeLenght++;

   }

   public AztecState getNextState() {
      return null;  //TODO implement auto-gen
   }

   public CompressedPoints getCompressed() {
      return null;  //TODO implement auto-gen
   }

   public CompressedPoints forceCompressed() {
      return null;  //TODO implement auto-gen
   }
}
