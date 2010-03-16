package cx.ath.jbzdak.aztec.decompressor;

import java.util.ArrayList;
import java.util.List;

import cx.ath.jbzdak.aztec.Decompressor;
import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.PlateauCompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.SlopeCompressed;
import cx.ath.jbzdak.aztec.pointSet.DefaultPointSet;
import cx.ath.jbzdak.aztec.pointSet.PointSet;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 16, 2010
 */
public class SimpleDecompressor implements Decompressor {
   
   double lastY; 
   
   int lastX; 
   
   List<CompressedPoints> compressedPointses = new ArrayList<CompressedPoints>();

   List<Double> result; 
   
   public void addCompressed(CompressedPoints compressedPoints) {
      compressedPointses.add(compressedPoints);
   }

   public PointSet getAccumulatedPoints() {
      result = new ArrayList<Double>();
      for (CompressedPoints compressedPointse : compressedPointses) {
         if (compressedPointse instanceof PlateauCompressedPoints) {
            PlateauCompressedPoints plateauCompressedPoints = (PlateauCompressedPoints) compressedPointse;
            decompressPlateau(plateauCompressedPoints);
            continue;
         }
         if (compressedPointse instanceof SlopeCompressed) {
            SlopeCompressed slopeCompressed = (SlopeCompressed) compressedPointse;
            decompressSlope(slopeCompressed);
            continue;
         }
      }
      return new DefaultPointSet(result);
   }
   
   public void decompressPlateau(PlateauCompressedPoints points){
      for(int ii = 0; ii < points.getPlateauLenght(); ii++){
         result.add(points.getyMean());
      }
      lastY = points.getyMean();
   }

   public void decompressSlope(SlopeCompressed compressed){
      double a = (compressed.getyLast() - lastY)/compressed.getSlopeTime();
      for(int ii = 0; ii < compressed.getSlopeTime(); ii++){
           result.add(lastY + a*ii);
      }
      lastY = compressed.getyLast();
   }
   
   
   
}
