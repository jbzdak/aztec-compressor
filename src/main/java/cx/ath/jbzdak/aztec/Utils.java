package cx.ath.jbzdak.aztec;

import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.SlopeCompressed;

/**
 * User: Jacek Bzdak jbdak@gmail.com
 * Date: Apr 13, 2010
 */
public class Utils {

    public static final byte SLOPE_CODE = 1;

    public static final byte PLATEAU_CODE = 0;

    public static  CompressedPointsType guessType(CompressedPoints compressedPoints){
        if (compressedPoints instanceof SlopeCompressed) {
           return CompressedPointsType.SLOPE;
        }
        return CompressedPointsType.PLATEAU;
    }

    public static  CompressedPointsType guessType(byte type){

        return CompressedPointsType.PLATEAU;
    }
}
