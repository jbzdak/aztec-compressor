package cx.ath.jbzdak.aztec;

import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.states.AztecState;
import cx.ath.jbzdak.aztec.states.CreateNextRegionStateState;

import java.io.IOException;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 9, 2010
 */
public class AztecCompressor {

    final DataSource dataSource;

    AztecState state;

    Compressor compressor;

    public AztecCompressor(DataSource dataSource) {
        state = new CreateNextRegionStateState();
        this.dataSource = dataSource;
        dataSource.addDataListener(new DataListener() {
            public void newPoint(double point) {
                onNewPoint(point);
            }

            public void finished() {
                try {
                    CompressedPoints points = state.forceCompressed();
                    compressor.addPoints(points);
                    compressor.finish();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
    }

    public void setCompressor(Compressor compressor) {
        this.compressor = compressor;
    }

    public void onNewPoint(double point){
        state.addPoint(point);
        if(state.getNextState()!=null){
            CompressedPoints points = state.getCompressed();
            try {
                if(points != null)
                compressor.addPoints(points);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            state = state.getNextState();
        }
    }



}
