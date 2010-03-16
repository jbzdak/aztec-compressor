package cx.ath.jbzdak.aztec;

/**
 * Created by IntelliJ IDEA.
 * User: yaster
 * Date: Mar 16, 2010
 * Time: 6:23:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleSource implements DataSource{

    DataListener dataListener;

    String data = "";

    public SimpleSource(String data) {
        this.data = data;
    }

    public void go(){
        String[] pts = data.split(";");
        for (String pt : pts) {
            dataListener.newPoint(Double.parseDouble(pt));
        }
        dataListener.finished();
    }

    public void addDataListener(DataListener listener) {
        dataListener = listener;
    }

    public boolean removeData(DataListener listener) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
