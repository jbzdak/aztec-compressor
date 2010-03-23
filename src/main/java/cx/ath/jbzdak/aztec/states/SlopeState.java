package cx.ath.jbzdak.aztec.states;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.SlopeCompressed;

import java.security.InvalidParameterException;

import static cx.ath.jbzdak.aztec.Config.CONFIG;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 9, 2010
 */
public class SlopeState implements AztecState{

    private static final Logger LOGGER = LoggerFactory.getLogger(SlopeState.class);

    int slopeLenght = 2;

    final double sign;

    double yMax = Double.MIN_VALUE, yMin = Double.MAX_VALUE;

    double extreme;

    int extremeSlopeLen;

    int plateauLenght;

    double lastMean;

    AztecState nextState;

    CompressedPoints compressed;

    public SlopeState(int slopeLenght, double sign, double lastMean) {
        this.slopeLenght = slopeLenght;
        double setSign;
        setSign = sign;
        if(sign==0){
            setSign=1;
        }
        this.sign = setSign;
        this.lastMean = lastMean;
        if(sign<0){
            extreme=Double.MIN_VALUE;
        }else{
            extreme=Double.MAX_VALUE;
        }
    }

    public void addPoint(double point) {
        slopeLenght++;
        yMin=point<yMin?point:yMin;
        yMax=point>yMax?point:yMax;
        if(sign<0){
            if (point > extreme){
                extreme = point;
                extremeSlopeLen = slopeLenght;
            }
        }else{
            if (point < extreme){
                extreme = point;
                extremeSlopeLen = slopeLenght;
            }
        }
        boolean notAPlateau = (yMax - yMin) > CONFIG.sampleTreshold;
                double mean = (yMax + yMin)/2;
        if(LOGGER.isTraceEnabled()){
            LOGGER.trace("Adding point {}, yMax is {}, yMin is {}, slopeLenght is {}, plateauLength is {}, extreme is {}, lastMean = {}, mean = {}, sign = {}",
                    new Object[]{point, yMax, yMin, slopeLenght, plateauLenght, extreme, lastMean, mean, sign});
        }
        if((lastMean - mean) * sign <0){
            LOGGER.trace("Exiting from slope to next slopeLenght {}, plateauLenght {}", slopeLenght, plateauLenght);
            nextState = new SlopeState(slopeLenght - extremeSlopeLen+1,-sign, extreme);
            compressed = new SlopeCompressed(extremeSlopeLen-1, extreme);
            return;
        }
        if(notAPlateau){
            plateauLenght = 0;
            yMin = Double.MAX_VALUE;
            yMax = Double.MIN_VALUE;
            lastMean = mean;
        }else{
            boolean isPlateau = plateauLenght >= CONFIG.minimalPlateauLenght;
            if(isPlateau){
                nextState = new PlateauState(yMax, yMin, plateauLenght);
                compressed = new SlopeCompressed(slopeLenght - plateauLenght, extreme);
                return;
            }
            plateauLenght++;
        }

    }

    public AztecState getNextState() {
        return nextState;
    }

    public CompressedPoints getCompressed() {
        return compressed;
    }

    public CompressedPoints forceCompressed() {
        return new SlopeCompressed(slopeLenght, lastMean);
    }
}
