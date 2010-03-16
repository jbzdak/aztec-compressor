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
}
