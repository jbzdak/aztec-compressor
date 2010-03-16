package cx.ath.jbzdak.aztec;

import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.pointSet.PointSet;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 16, 2010
 */
public interface Decompressor {

   PointSet getAccumulatedPoints();

   void addCompressed(CompressedPoints compressedPoints);
   
}
