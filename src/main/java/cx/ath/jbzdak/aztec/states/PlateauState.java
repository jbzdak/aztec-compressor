package cx.ath.jbzdak.aztec.states;

import java.util.ArrayList;
import java.util.List;

import cx.ath.jbzdak.aztec.Point;
import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.PlateauCompressedPoints;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 9, 2010
 */
public class PlateauState extends AbstractState{

   final List<Point> savedPoints = new ArrayList<Point>();



   boolean plateauEstablished;


   AztecState newState;

   CompressedPoints compressedPoints;

   PlateauState(){

   }

   

   public void addPoint(Point point) {
      plateauLenght++;
      controlLogic(point.getY());

   }

   public CompressedPoints forceCompressed() {
      return new PlateauCompressedPoints(plateauLenght, (yMax + yMin)/2);
   }

   public CompressedPoints getCompressed() {
      return compressedPoints;
   }

   public AztecState getNextState() {
      return newState;
   }
}
