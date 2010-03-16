package cx.ath.jbzdak.aztec.dataSource;

import java.util.ArrayList;
import java.util.List;

import cx.ath.jbzdak.aztec.DataListener;
import cx.ath.jbzdak.aztec.DataSource;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 16, 2010
 */
public class AbstractDataSource implements DataSource{

   List<DataListener> dataListenerList = new ArrayList<DataListener>();

   public void addDataListener(DataListener listener) {
      dataListenerList.add(listener);
   }

   public boolean removeData(DataListener listener) {
      return dataListenerList.remove(listener);
   }

   protected void fireFinished(){
      for (DataListener dataListener : dataListenerList) {
         dataListener.finished();
      }
   }

   protected void firePoint(double point){
      for (DataListener dataListener : dataListenerList) {
         dataListener.newPoint(point);
      }
   }
}
