
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.util.Map;

public class Histograma {
	private String nombre;
    public Histograma(String nombre){this.nombre=nombre;};

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
            ChartUtilities.saveChartAsPNG(new File("src\\Histogramas\\"+nombre+".png"), J, 600, 300 );
        } catch (Exception e) {
            System.out.println("error en el histograma" + e);
            }
        }
    }
