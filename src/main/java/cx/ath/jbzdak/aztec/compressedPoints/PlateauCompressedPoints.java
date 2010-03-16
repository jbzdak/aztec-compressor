package cx.ath.jbzdak.aztec.compressedPoints;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 9, 2010
 */
public class PlateauCompressedPoints implements CompressedPoints{

   final int plateauLenght;

   final double yMean;

   public PlateauCompressedPoints(int plateauLenght, double yMean) {
      this.plateauLenght = plateauLenght;
      this.yMean = yMean;
   }
}
