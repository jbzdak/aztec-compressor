package cx.ath.jbzdak.aztec.states;

import cx.ath.jbzdak.aztec.Config;
import cx.ath.jbzdak.aztec.Point;
import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;

import static java.lang.Math.*;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 9, 2010
 */
public class CreateNextRegionStateState implements AztecState{

    AztecState nextState;

    CompressedPoints compressed;

    private final double[] resDoubles;
    private final int minLen;

    private int idx = 0;

    public CreateNextRegionStateState() {
        minLen = Config.CONFIG.minimalPlateauLenght -1;
        resDoubles = new double[minLen];
    }

    public void addPoint(double point) {
        if(idx < minLen){
            resDoubles[idx++] = point;
            return;
        }
        double Vmin = min(resDoubles[0], min(resDoubles[1], point));
        double Vmax = max(resDoubles[0], max(resDoubles[1], point));
        double diff = Vmax - Vmin;
        if(diff < Config.CONFIG.sampleTreshold){
            nextState = new PlateauState(Vmax, Vmin, 3);
        }else{
            nextState = new SlopeState(3, Math.signum(resDoubles[0] - point), (Vmax - Vmin)/2);
        }
    }

    public CompressedPoints forceCompressed() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public CompressedPoints getCompressed() {
        return compressed;
    }

    public AztecState getNextState() {
        return nextState;
    }
}
