package cx.ath.jbzdak.aztec.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import javax.swing.*;

import java.awt.BorderLayout;

import cx.ath.jbzdak.aztec.pointSet.PointSet;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 16, 2010
 */
public class ComparePointsPanel extends JPanel{

   ChartPanel chartPanel;

   PointSet original, compressed;

   public ComparePointsPanel(PointSet compressed, PointSet original) {
      this.compressed = compressed;
      this.original = original;
      setLayout(new BorderLayout());
      PointSetDataset dataset = new PointSetDataset();
      dataset.addPointSet("original", original);
      dataset.addPointSet("compressed", compressed);
      JFreeChart chart = ChartFactory.createXYLineChart("Porównanie", "Czas", "Napięcie", dataset, PlotOrientation.VERTICAL, true, false, false);
      chartPanel = new ChartPanel(chart);
      add(chartPanel, BorderLayout.CENTER);
   }
}

