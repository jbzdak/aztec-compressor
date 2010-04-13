package cx.ath.jbzdak.aztec.compressedPoints;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 9, 2010
 */
@XmlType()
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class PlateauCompressedPoints implements CompressedPoints{

   int plateauLenght;

   double yMean;

   public PlateauCompressedPoints() {
   }

   public PlateauCompressedPoints(int plateauLenght, double yMean) {
      this.plateauLenght = plateauLenght;
      this.yMean = yMean;
   }

   public int getPlateauLenght() {
      return plateauLenght;
   }

   public double getyMean() {
      return yMean;
   }

    @Override
    public String
    toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PlateauCompressedPoints");
        sb.append("{plateauLenght=").append(plateauLenght);
        sb.append(", yMean=").append(yMean);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlateauCompressedPoints points = (PlateauCompressedPoints) o;

        if (plateauLenght != points.plateauLenght) return false;
        if (Double.compare(points.yMean, yMean) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = plateauLenght;
        temp = yMean != +0.0d ? Double.doubleToLongBits(yMean) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
