package cx.ath.jbzdak.aztec;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: Jacek Bzdak jbdak@gmail.com
 * Date: Apr 13, 2010
 */
public interface StreamDecompressor {


    void start(InputStream inputStream);

    /**
     * Null jeśli koniec strumienia
     * @return
     */
    CompressedPoints getNextPoint() throws IOException;

    
}
