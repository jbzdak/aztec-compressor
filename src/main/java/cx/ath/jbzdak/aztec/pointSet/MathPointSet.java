package cx.ath.jbzdak.aztec.pointSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yaster
 * Date: Mar 23, 2010
 * Time: 6:19:09 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class MathPointSet implements PointSet{

    final int size;

    final List<Double> points;

    protected MathPointSet(int size) {
        this.size = size;
        points = new ArrayList<Double>(size);
        for(int ii = 0; ii < size; ii++){
            points.add(getPoint(ii));                        
        }
    }

    abstract double getPoint(int pointnum);

    public int size() {
        return size;
    }

    public List<Double> getPoints() {
        return points;
    }
}
