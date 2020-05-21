package teo.info;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.Map;

public class Histograma {
    public Histograma(){};

    public void Get_histograma (Map<Integer,Double> mapa){
        try {
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        for (Integer i=0;i<=255;i++){
            if (mapa.containsKey(i)) {
                data.addValue(mapa.get(i),i,"");
            }
            /**else{ imprimo solo los valores de los tonos de grises que tiene la matriz
                data.addValue(0,i,"");
                }*/
            }
            JFreeChart J = ChartFactory.createBarChart3D("TPE", "Escala de grises", "cantidad", data, PlotOrientation.VERTICAL, true, true, true);
            ChartFrame F = new ChartFrame("tonos", J);
            F.setSize(20000, 10000);
            F.setLocationRelativeTo(null);
            F.setVisible(true);
        } catch (Exception e) {
            System.out.println("error en el histograma" + e);
            }
        }
    }
