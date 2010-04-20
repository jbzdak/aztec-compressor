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
 *         Date: 2010-04-13
 */
public class parabolicApproximation implements Decompressor {


   double lastPointAdded = 0;

   List<Double> dane = new ArrayList<Double>();

  public void addCompressed(CompressedPoints compressedPoints) {
       List<Double> punkty = new ArrayList<Double>();
        if (compressedPoints instanceof PlateauCompressedPoints) {
            PlateauCompressedPoints plateauCompressedPoints = (PlateauCompressedPoints) compressedPoints;
            punkty = decompressPlateau(plateauCompressedPoints);
        }
        if (compressedPoints instanceof SlopeCompressed) {
            SlopeCompressed slopeCompressed = (SlopeCompressed) compressedPoints;
            punkty = decompressSlope(slopeCompressed);
        }
     if (!punkty.isEmpty())
     {
         lastPointAdded = punkty.get(punkty.size()-1);
         //if (dane.isEmpty()) for (int i=0;i<3;i++) dane.add(punkty.get(0));
         dane.addAll(punkty);
     }
}

   public PointSet getAccumulatedPoints() {
      List<Double> result = new ArrayList<Double>();
      for (int i=0;i<3;i++)
      {
         //dane.add(0,dane.get(0));
         //dane.add(dane.get(dane.size()-1));
         result.add(dane.get(0));
      }
      for (int i=3;i<dane.size()-3;i++) {
         result.add(approximation7points(dane.subList(i-3,i+1+3).toArray(new Double[]{})));
      }
       for (int i=0;i<3;i++)
       {
           result.add(dane.get(dane.size()-1));
       }
      return new DefaultPointSet(result);
   }

   double  approximation7points (Double [] punkty)
   {
      double temp;
       temp  = -2*punkty[0].doubleValue();
       temp += 3*punkty[1].doubleValue();
       temp += 6*punkty[2].doubleValue();
       temp += 7*punkty[3].doubleValue();
       temp += 6*punkty[4].doubleValue();
       temp += 3*punkty[5].doubleValue();
       temp += -2*punkty[6].doubleValue();
       temp /= 21;
      return temp;
   }
      List<Double>  decompressPlateau(PlateauCompressedPoints point){
         List<Double> returnValues = new ArrayList<Double>();
         for (int i=0;i<point.getPlateauLenght();i++)
         {
            returnValues.add(point.getyMean());
         }
         return returnValues;
   }
      List<Double>  decompressSlope(SlopeCompressed point){
         List<Double> returnValues = new ArrayList<Double>();

         double a =   (point.getyLast()-lastPointAdded)/point.getSlopeTime();
         double b =   lastPointAdded;

         for (int i=0;i<point.getSlopeTime();i++)
         {
            returnValues.add(a*i+b);
         }
         return returnValues;
   }
}
