package cx.ath.jbzdak.aztec.ui;

import javax.swing.*;

import cx.ath.jbzdak.aztec.AztecCompressor;
import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.compressor.CompressorDecopressorPolyApprox;
import cx.ath.jbzdak.aztec.compressor.CompressorDepressorApproximation;
import cx.ath.jbzdak.aztec.dataSource.PointSetSource;
import cx.ath.jbzdak.aztec.decompressor.SimpleDecompressor;
import cx.ath.jbzdak.aztec.decompressor.parabolicApproximation;
import cx.ath.jbzdak.aztec.pointSet.DefaultPointSet;
import cx.ath.jbzdak.aztec.pointSet.PointSet;
import cx.ath.jbzdak.aztec.pointSet.SinPointSet;
import cx.ath.jbzdak.aztec.streamDecompressor.TextDecompressor;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 16, 2010
 */
public class ComparePointsFrame2 extends JFrame{

   PointSet original, compressed;



   public ComparePointsFrame2(PointSet compressed, PointSet original) {
      this.compressed = compressed;
      this.original = original;
      add(new ComparePointsPanel(compressed, original));
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   }

   public static void main(String[] args) throws Exception{
      //List<CompressedPoints> compressedPoints = new ArrayList<CompressedPoints>();
      TextDecompressor textDecompressor = new TextDecompressor();
      textDecompressor.start(ComparePointsFrame2.class.getResourceAsStream("/input.txt"));
      SimpleDecompressor simpleDecompressor = new SimpleDecompressor();
      CompressedPoints pts;
      while((pts = textDecompressor.getNextPoint()) != null){
         simpleDecompressor.addCompressed(pts);
      }
      final PointSet pointSet = simpleDecompressor.getAccumulatedPoints();
      //final SinPointSet pointSet = new SinPointSet(400);
      final PointSetSource dataSource = new PointSetSource(pointSet);
      AztecCompressor aztecCompressor = new AztecCompressor(dataSource);
      //final CompressorDecopressorPolyApprox decompressor = new CompressorDecopressorPolyApprox();
      final CompressorDepressorApproximation decompressor = new CompressorDepressorApproximation();
      //final CompressorDecopressor decompressor = new CompressorDecopressor();

      aztecCompressor.setCompressor(decompressor);
      dataSource.go();

      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            ComparePointsFrame frame = new ComparePointsFrame(decompressor.getAccumulatedPoints(), pointSet);
            frame.pack();
            frame.setVisible(true);
         }
      });
   }


}
