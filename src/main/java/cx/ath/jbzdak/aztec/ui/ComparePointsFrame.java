package cx.ath.jbzdak.aztec.ui;

import javax.swing.*;

import cx.ath.jbzdak.aztec.AztecCompressor;
import cx.ath.jbzdak.aztec.compressor.CompressorDecopressor;
import cx.ath.jbzdak.aztec.dataSource.PointSetSource;
import cx.ath.jbzdak.aztec.pointSet.DefaultPointSet;
import cx.ath.jbzdak.aztec.pointSet.PointSet;


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
      final DefaultPointSet defaultPointSet
              = new DefaultPointSet("-0.5;0.5;-0.5;0.5;-0.5;0.5;-1;2;3;4.5;5;6;7;8;7;6;5;4;3;2;1;-0.5;0.5;-0.5;0.5;-0.5;0.5;");
      final PointSetSource dataSource = new PointSetSource(defaultPointSet);
      AztecCompressor aztecCompressor = new AztecCompressor(dataSource);
      final CompressorDecopressor decopressor = new CompressorDecopressor();
      aztecCompressor.setCompressor(decopressor);
      dataSource.go();

      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            ComparePointsFrame frame = new ComparePointsFrame(decopressor.getAccumulatedPoints(), defaultPointSet);
            frame.pack();
            frame.setVisible(true);
         }
      });
   }


}
