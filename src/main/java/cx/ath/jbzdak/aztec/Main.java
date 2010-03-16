package cx.ath.jbzdak.aztec;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: yaster
 * Date: Mar 16, 2010
 * Time: 6:25:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public  static void main(String[] args) throws IOException{
        SimpleSource source =
                new SimpleSource("" +
                        "-0.5;0.5;-0.5;0.5;-0.5;0.5;-1;2;3;4;5;6;7;8;7;6;5;4;3;2;1;-0.5;0.5;-0.5;0.5;-0.5;0.5;");
        AztecCompressor aztecCompressor = new AztecCompressor(source);
        XmlCompressor compressor = new XmlCompressor();
        compressor.start(System.out);
        aztecCompressor.setCompressor(compressor);
        source.go();
    }
}
