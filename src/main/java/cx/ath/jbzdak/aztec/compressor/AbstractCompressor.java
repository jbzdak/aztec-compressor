package cx.ath.jbzdak.aztec.compressor;

import cx.ath.jbzdak.aztec.Compressor;
import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * User: Jacek Bzdak jbdak@gmail.com
 * Date: Apr 13, 2010
 */
public abstract class AbstractCompressor implements Compressor{

    protected DataOutputStream outputStream;


    public void start(OutputStream outputStream) throws IOException {
        if (!(outputStream instanceof BufferedOutputStream)) {
            outputStream = new BufferedOutputStream(outputStream, 1024*1024);    
        }

        this.outputStream = new DataOutputStream(outputStream);
        internalStart();

    }

    protected void internalStart(){}

    public void finish() throws IOException {
        outputStream.flush();
        outputStream.close();
    }
}
