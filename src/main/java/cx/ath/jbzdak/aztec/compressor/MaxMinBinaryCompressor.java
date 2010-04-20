package cx.ath.jbzdak.aztec.compressor;

import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;

import java.io.IOException;

/**
 * User: Jacek Bzdak jbdak@gmail.com
 * Date: Apr 13, 2010
 */
public class MaxMinBinaryCompressor extends AbstractCompressor{

    byte byteOffset;

    byte currentByte;

    @Override
    protected void internalStart() {      
        
    }

    public void addPoints(CompressedPoints points) throws IOException {

    }
}
