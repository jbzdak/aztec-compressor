package cx.ath.jbzdak.aztec;

import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: yaster
 * Date: Mar 16, 2010
 * Time: 6:02:39 PM
 */
public interface Compressor {

    void addPoints(CompressedPoints points) throws IOException;

    void start(OutputStream outputStream) throws IOException;

    void finish() throws IOException;

}
