package teo.info;
import javafx.util.Pair;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		//Ejercicio 3: Codificar imagenes con el metodo de Huffman y reconstruirla
		//Comprimir la imagen original
		Imagen original=new Imagen();
		original.getValoresMat("Will(Original)");
		Imagen policia=new Imagen();
		policia.getValoresMat("Will_ej2");
		Imagen will1=new Imagen();
		will1.getValoresMat("Will_1");
		Distribuciones dis_original=new Distribuciones(original);
		Distribuciones dis_policia=new Distribuciones(policia);
		Huffman codificador=new Huffman();
		System.out.println("tasa de compresion de imagen original: "+codificador.getTasaCompresion(dis_original.get_Distribucion(),original));
		System.out.println("tasa de compresion de imagen policia: "+codificador.getTasaCompresion(dis_original.get_Distribucion(),policia));
		System.out.println("tasa de compresion de imagen Will_1: "+codificador.getTasaCompresion(dis_original.get_Distribucion(),will1));

	}
}





