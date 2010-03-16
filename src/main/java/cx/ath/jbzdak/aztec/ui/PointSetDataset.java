package cx.ath.jbzdak.aztec.ui;

import org.jfree.data.xy.AbstractXYDataset;

import java.util.ArrayList;
import java.util.List;

import cx.ath.jbzdak.aztec.pointSet.PointSet;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 16, 2010
 */
public class PointSetDataset extends AbstractXYDataset{

   List<Comparable> keys = new ArrayList<Comparable>();

   List<PointSet> pointSets = new ArrayList<PointSet>();

   List<List<Double>> points = new ArrayList<List<Double>>();

   public void addPointSet(String key, PointSet set){
      keys.add(key);
      pointSets.add(set);
      points.add(set.getPoints());
   }

   public int getItemCount(int series) {
      return points.get(series).size();
   }

   public Number getX(int series, int item) {
      return item;
   }

   public Number getY(int series, int item) {
      return points.get(series).get(item);
   }

   @Override
   public int getSeriesCount() {
      return keys.size();
   }

   @Override
   public Comparable getSeriesKey(int series) {
      return keys.get(series);
   }
}
