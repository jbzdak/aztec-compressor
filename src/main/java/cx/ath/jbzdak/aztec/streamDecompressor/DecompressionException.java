package cx.ath.jbzdak.aztec.streamDecompressor;

import java.io.IOException;

/**
 * User: Jacek Bzdak jbdak@gmail.com
 * Date: Apr 13, 2010
 */
public class DecompressionException extends IOException{
    
    public DecompressionException() {
    }

    public DecompressionException(String message) {
        super(message);
    }

    public DecompressionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DecompressionException(Throwable cause) {
        super(cause);
    }
}
