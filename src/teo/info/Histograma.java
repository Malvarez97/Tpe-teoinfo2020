package teo.info;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import javax.print.DocFlavor;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public class Histograma {
    public Histograma() {
    }

    ;

    private DefaultCategoryDataset create_Dataset(Map<Integer, Double> mapa) {
        DefaultCategoryDataset Dataux = new DefaultCategoryDataset();
        for (Integer i = 0; i <= 255; i++) {
            if (mapa.containsKey(i)) {
                Dataux.addValue(mapa.get(i), i, "");
            }
            /**else{ imprimo solo los valores de los tonos de grises que tiene la matriz
             data.addValue(0,i,"");
             }*/
        }
        return Dataux;
    }


    public void Get_histograma(Map<Integer, Double> mapa) {
        try {
            DefaultCategoryDataset Data = create_Dataset(mapa);
            JFreeChart J = ChartFactory.createBarChart3D("TPE", "Escala de grises", "cantidad", Data, PlotOrientation.VERTICAL, true, true, true);
            ChartFrame F = new ChartFrame("tonos", J);
            F.setSize(20000, 10000);
            F.setLocationRelativeTo(null);
            F.setVisible(true);
        } catch (Exception e) {
            System.out.println("error en el histograma" + e);
        }
    }

    private double[] crear_double(Map<Integer, Double> mapa) {
        double[] Dataux =new double[255];
        for (Integer i = 0; i <= 255; i++) {
            if (mapa.containsKey(i)) {
                Dataux[i]=mapa.get(i);
            }
            else{ /**imprimo solo los valores de los tonos de grises que tiene la matriz*/
             Dataux[i]=0;
             }
        }
        return Dataux;
    }

    public  BufferedImage createHistogram(String chartName, Map<Integer,Double>mapa) {
        double[] bufferImagen= this.crear_double(mapa);

        HistogramDataset dataset = new HistogramDataset();
        dataset.setType(HistogramType.FREQUENCY);
        dataset.addSeries("Histograma", bufferImagen, 256);
        String plotTitle = "Histograma de entropias";
        String xaxis = "Color";
        String yaxis = "Apariciones";
        PlotOrientation orientation = PlotOrientation.VERTICAL;
        boolean show = false;
        boolean toolTips = false;
        boolean urls = false;
        JFreeChart chart = ChartFactory.createHistogram(plotTitle, xaxis, yaxis, dataset, orientation, show, toolTips, urls);

        int width = 500;
        int height = 300;

        return chart.createBufferedImage(width, height);
    }
}

