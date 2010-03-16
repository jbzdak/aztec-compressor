package cx.ath.jbzdak.aztec.dataSource;

import java.util.List;

import cx.ath.jbzdak.aztec.pointSet.PointSet;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 16, 2010
 */
public class PointSetSource extends AbstractDataSource{

   final PointSet pointSet;

   public PointSetSource(PointSet pointSet) {
      this.pointSet = pointSet;
   }

   public void go(){
      List<Double> pts = pointSet.getPoints();
      for(Double point : pts){
         firePoint(point);
      }
      fireFinished();
   }
}
