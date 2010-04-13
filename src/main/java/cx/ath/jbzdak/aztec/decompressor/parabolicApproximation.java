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
         dane.addAll(punkty);
     }
}

   public PointSet getAccumulatedPoints() {
      List<Double> result = new ArrayList<Double>();
      for (int i=0;i<3;i++)
      {
         result.add(0.0); //TODO zmienic zapis 3 pierwszych pkt
      }
      for (int i=3;i<dane.size()-1-3;i++) {
         result.add(dane.get(i));
         //result.add(approximation7points(dane.subList(i-3,i+1+3).toArray(new Double[]{})));
      }
      return new DefaultPointSet(result);
   }

   double  approximation7points (Double [] punkty)
   {
      double temp = 1/21*(-2*punkty[0]+3*punkty[1]+6*punkty[2]+7*punkty[3]+6*punkty[4]+3*punkty[5]-2*punkty[6]);
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
