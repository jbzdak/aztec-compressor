package cx.ath.jbzdak.aztec.streamDecompressor;

import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.PlateauCompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.SlopeCompressed;
import cx.ath.jbzdak.aztec.compressor.SimpleBinaryCompressor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: Jacek Bzdak jbdak@gmail.com
 * Date: Apr 13, 2010
 */
public class SimpleBinaryTesters {

    private static Random RANDOM;

    SimpleBinaryDecompressor decompressor;

    SimpleBinaryCompressor compressor;

    private ByteArrayOutputStream data;

    @BeforeClass
    public static void beforeClasss() throws Exception{
        RANDOM = new SecureRandom();
    }

    @Before
    public void before() throws Exception{
        compressor = new SimpleBinaryCompressor();
        data = new ByteArrayOutputStream();
        compressor.start(data);
    }

    private void finishedCompressing() throws IOException {
        compressor.finish();
        decompressor = new SimpleBinaryDecompressor();
        decompressor.start(new ByteArrayInputStream(data.toByteArray()));
        compressor = null;
        data = null;
    }


    @Test
    public void testSlope() throws Exception{
        SlopeCompressed written = new SlopeCompressed(RANDOM.nextInt(), RANDOM.nextDouble());
        compressor.addPoints(written);
        finishedCompressing();
        SlopeCompressed read = (SlopeCompressed) decompressor.getNextPoint();
        Assert.assertEquals(written, read);

    }

    @Test
    public void testPlateau() throws Exception{
        PlateauCompressedPoints written = new PlateauCompressedPoints(RANDOM.nextInt(), RANDOM.nextDouble());
        compressor.addPoints(written);
        finishedCompressing();
        PlateauCompressedPoints read = (PlateauCompressedPoints) decompressor.getNextPoint();
        Assert.assertEquals(written, read);
    }

    @Test
    public void testRandom() throws IOException {
        List<CompressedPoints> written = new ArrayList<CompressedPoints>();
        for(int ii=0; ii< 100000; ii++){
            CompressedPoints points;
            if(RANDOM.nextBoolean()){
                points = new PlateauCompressedPoints(RANDOM.nextInt(), RANDOM.nextDouble());
            }else{
                points = new SlopeCompressed(RANDOM.nextInt(), RANDOM.nextDouble());
            }
            written.add(points);
            compressor.addPoints(points);
        }
        finishedCompressing();
        List<CompressedPoints> read = new ArrayList<CompressedPoints>();
        CompressedPoints readPoint = null;
        while((readPoint = decompressor.getNextPoint()) != null){
            read.add(readPoint);
        }
        Assert.assertEquals(written, read);
    }
}
