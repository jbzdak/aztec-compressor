package cx.ath.jbzdak.aztec.ui;

import javax.swing.*;

import cx.ath.jbzdak.aztec.AztecCompressor;
import cx.ath.jbzdak.aztec.compressor.CompressorDecopressorPolyApprox;
import cx.ath.jbzdak.aztec.compressor.CompressorDepressorApproximation;
import cx.ath.jbzdak.aztec.dataSource.PointSetSource;
import cx.ath.jbzdak.aztec.pointSet.PointSet;
import cx.ath.jbzdak.aztec.pointSet.SinPointSet;


/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 16, 2010
 */
public class ComparePointsFrame extends JFrame{

   PointSet original, compressed;

   public ComparePointsFrame(PointSet compressed, PointSet original) {
      this.compressed = compressed;
      this.original = original;
      add(new ComparePointsPanel(compressed, original));
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   }

   public static void main(String[] args){
//      final DefaultPointSet pointSet
//              = new DefaultPointSet("-0.5;0.5;-0.5;0.5;-0.5;0.5;-1;2;3;4.5;5;5.5;6;7;8;9;0.5;0.5;-0.5;0.5;-0.5;0.5;-1;2;3;4.5;5;6;7;8;9;9.5;7;6;5;4;3;2;1;-0.5;0.5;-0.5;0.5;-0.5;0.5;");
       final SinPointSet pointSet = new SinPointSet(400);
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
