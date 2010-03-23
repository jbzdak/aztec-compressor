package cx.ath.jbzdak.aztec.pointSet;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yaster
 * Date: Mar 23, 2010
 * Time: 6:17:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class SinPointSet extends MathPointSet{

    public SinPointSet(int size) {
        super(size);
    }

    @Override
    double getPoint(int pointnum) {
        return Math.sin(2 * Math.PI * pointnum/100.0);
    }
}
