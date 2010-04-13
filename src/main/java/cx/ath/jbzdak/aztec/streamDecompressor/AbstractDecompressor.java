package cx.ath.jbzdak.aztec.streamDecompressor;

import cx.ath.jbzdak.aztec.StreamDecompressor;
import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

/**
 * User: Jacek Bzdak jbdak@gmail.com
 * Date: Apr 13, 2010
 */
public abstract class AbstractDecompressor implements StreamDecompressor{

    DataInputStream inputStream;

    public void start(InputStream inputStream) {
        if (!(inputStream instanceof BufferedInputStream)) {
            inputStream = new BufferedInputStream(inputStream, 1024);
        }
        this.inputStream = new DataInputStream(inputStream);
        startInternal();
    }

    protected void startInternal() {};

}
