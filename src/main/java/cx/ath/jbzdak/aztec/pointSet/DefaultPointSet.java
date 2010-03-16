package cx.ath.jbzdak.aztec.pointSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 16, 2010
 */
public class DefaultPointSet implements PointSet{

   List<Double> points = new ArrayList<Double>();

   public DefaultPointSet() {
   }

   public DefaultPointSet(String data) {
      String[] pts = data.split(";");
      for (String pt : pts) {
         points.add(Double.parseDouble(pt));
      }
   }

   public DefaultPointSet(List<Double> points) {
      this.points = points;
   }

   public List<Double> getPoints() {
      return points;
   }

   public void setPoints(List<Double> points) {
      this.points = points;
   }

   public int size() {
      return points.size();
   }
}
