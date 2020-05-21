
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
<<<<<<< HEAD
import org.jfree.chart.ChartPanel;
=======
import org.jfree.chart.ChartUtilities;
>>>>>>> parent of c081e56... Revert "Cambios para conectar mejor los ejercicios"
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

<<<<<<< HEAD
import javax.print.DocFlavor;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public class Histograma {
    public Histograma() {}
=======
import java.io.File;
import java.util.Map;

public class Histograma {
	private String nombre;
    public Histograma(String nombre){this.nombre=nombre;};
>>>>>>> parent of c081e56... Revert "Cambios para conectar mejor los ejercicios"

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


    public void Get_histograma(Map<Integer, Double> mapa,String nombre) {
        try {
            DefaultCategoryDataset Data = create_Dataset(mapa);
            JFreeChart J = ChartFactory.createBarChart3D(nombre, "Escala de grises", "cantidad", Data, PlotOrientation.VERTICAL, true, true, true);
            ChartFrame F = new ChartFrame("tonos", J);
            F.setSize(6000, 3000);
            F.setLocationRelativeTo(null);
            F.setVisible(true);
            ChartUtilities.saveChartAsPNG(new File("src\\Histogramas\\"+nombre+".png"), J, 600, 300 );
        } catch (Exception e) {
            System.out.println("error en el histograma" + e);
        }
    }
}




