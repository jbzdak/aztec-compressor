package cx.ath.jbzdak.aztec.states;

import java.util.ArrayList;
import java.util.List;

import cx.ath.jbzdak.aztec.Point;
import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.PlateauCompressedPoints;

import static cx.ath.jbzdak.aztec.Config.CONFIG;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 9, 2010
 */
public class PlateauState implements AztecState{

    AztecState nextState;

    CompressedPoints compressed;

    double yMax = Double.MIN_VALUE, yMin = Double.MAX_VALUE;

    int plateauLenght = 0;

    public PlateauState(double yMax, double yMin, int plateauLenght) {
        this.yMax = yMax;
        this.yMin = yMin;
        this.plateauLenght = plateauLenght;
    }

    public void addPoint(double point) {
        plateauLenght++;

        yMin=point<yMin?point:yMin;
        yMax=point>yMax?point:yMax;

        boolean plateauTooWide = (yMax - yMin) > CONFIG.sampleTreshold;
        boolean plateauTooLong = plateauLenght > CONFIG.samplesMax;

        if(plateauTooLong || plateauTooWide){
            compressed = forceCompressed();
            nextState = new CreateNextRegionStateState();
        }
    }

    public CompressedPoints forceCompressed() {
        return new PlateauCompressedPoints(plateauLenght, (yMax + yMin)/2);
    }

    public CompressedPoints getCompressed() {
        return compressed;
    }

    public AztecState getNextState() {
        return nextState;
    }
}
