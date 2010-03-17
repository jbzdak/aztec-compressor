package cx.ath.jbzdak.aztec.states;

import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 9, 2010
 */
public interface AztecState {

   void addPoint(double point);

   CompressedPoints forceCompressed();

   CompressedPoints getCompressed();

   AztecState getNextState();
   
}
