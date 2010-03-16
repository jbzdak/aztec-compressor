package cx.ath.jbzdak.aztec.compressedPoints;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by IntelliJ IDEA.
 * User: yaster
 * Date: Mar 16, 2010
 * Time: 6:01:22 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlType()
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SlopeCompressed implements CompressedPoints{

    int slopeTime;

    double yLast;

    public SlopeCompressed() {
    }

    public SlopeCompressed(int slopeTime, double yLast) {
        this.slopeTime = slopeTime;
        this.yLast = yLast;
    }

    public int getSlopeTime() {
        return slopeTime;
    }

    public double getyLast() {
        return yLast;
    }
}
