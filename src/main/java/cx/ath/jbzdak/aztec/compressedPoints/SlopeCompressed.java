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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlopeCompressed that = (SlopeCompressed) o;

        if (slopeTime != that.slopeTime) return false;
        if (Double.compare(that.yLast, yLast) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = slopeTime;
        temp = yLast != +0.0d ? Double.doubleToLongBits(yLast) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SlopeCompressed");
        sb.append("{slopeTime=").append(slopeTime);
        sb.append(", yLast=").append(yLast);
        sb.append('}');
        return sb.toString();
    }
}
