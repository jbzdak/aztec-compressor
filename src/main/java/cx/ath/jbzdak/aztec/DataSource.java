package cx.ath.jbzdak.aztec;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 9, 2010
 */
public interface DataSource {

   public void addDataListener(DataListener listener);

   public boolean removeData(DataListener listener);
}
