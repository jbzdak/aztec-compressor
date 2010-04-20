package cx.ath.jbzdak.aztec.decompressor;

import org.apache.commons.collections.primitives.ArrayDoubleList;
import org.apache.commons.collections.primitives.DoubleList;

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
 *         Date: 2010-04-20
 */
public class PolynomialApproximation implements Decompressor {

   double lastX = 0;

   DoubleList daneX = new ArrayDoubleList();
   DoubleList daneY = new ArrayDoubleList();

   public PointSet getAccumulatedPoints() {

      List<Double> result = new ArrayList<Double>();
      for (int i=0;i<lastX;i++)
      {
         result.add(evaluateY(i));
      }
      return new DefaultPointSet(result);
   }
   double evaluateY(int x)
   {
      int N = daneY.size();
      double result=0;
      double t1=0,t2=0,upT=0,downT=0;
      for (int i=0;i<N;i++) t1 += daneY.get(i);
      for (int i=0;i<N;i++) t2 += daneX.get(i)*daneX.get(i);
      downT = t2  *N;
      upT = t1*t2;
      t1=0;t2=0;
      for (int i=0;i<N;i++) t1 += daneY.get(i)*daneX.get(i);
      for (int i=0;i<N;i++) t2 += daneX.get(i);
      upT -= t1*t2;
      downT -= t2*t2;
      result = upT/downT;

      upT = t1*N;
      t1=0;
      for (int i=0;i<N;i++) t1 += daneY.get(i);
      upT -= t1*t2;
      result += x*upT/downT;

      return result;
   }

   public void addCompressed(CompressedPoints compressedPoints) {
        if (compressedPoints instanceof PlateauCompressedPoints) {
            PlateauCompressedPoints plateauCompressedPoints = (PlateauCompressedPoints) compressedPoints;
            decompressPlateau(plateauCompressedPoints);
        }
        if (compressedPoints instanceof SlopeCompressed) {
            SlopeCompressed slopeCompressed = (SlopeCompressed) compressedPoints;
            decompressSlope(slopeCompressed);
        }
      //To change body of implemented methods use File | Settings | File Templates.
   }

   void  decompressPlateau(PlateauCompressedPoints point){
         lastX+=point.getPlateauLenght();
         daneX.add(lastX);
         daneY.add(point.getyMean());
   }
      void   decompressSlope(SlopeCompressed point){
         lastX+=point.getSlopeTime();
         daneX.add(lastX);
         daneY.add(point.getyLast());
      }
}
