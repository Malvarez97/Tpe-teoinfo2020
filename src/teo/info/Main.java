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
		Imagen original=new Imagen();
		int[][] matriz_original =original.getValoresMat("Will(Original)");

		/**  Main 1
        ArrayList<NodoImagen> lista=new Calculador().ordenarImagenes(original);
        for(NodoImagen n:lista) {
            System.out.print("la correlacion cruzada entre imagen original e imagen ");
            System.out.print(n.getImagen().getNombre());
            System.out.print(" es: ");
            System.out.println(n.getCorrelacion());
         }
         /* Main 2
         ej8p2 ej= new ej8p2();
         int[][] B= {{6,6,3},{4,9,3},{2,1,2}};
         int[][] A={{6,6},{9,3}};
         System.out.println(ej.calcularMayorCoincidencia(A, B, 2, 3).toString());
         
    	/** Main 3 , prueba de el ejercicio 2*/
		/**int [][] matriz = {{6,6,3},{4,9,3},{2,1,2}};
		Distribuciones dis_orig=new Distribuciones(matriz_original);
		Map map_orig = dis_orig.get();
		Iterator it_orig = map_orig.keySet().iterator();
		System.out.println("Distribucion de la imagen original: ");
		while(it_orig.hasNext()){
			Integer key_orig = (Integer) it_orig.next();
			System.out.println("Digito " + key_orig + " -> Valor: " + (Double)(map_orig.get(key_orig)));
		}
		Histograma h_orig= new Histograma(original.getNombre());
		h_orig.Get_histograma(map_orig);
		System.out.println(dis_orig.cantidad_digitos);
		 System.out.println("la media es "+dis_orig.get_Media());
		System.out.println("el desvio es de "+dis_orig.get_DesvioEstandar());
		//aca tenemos que repetir con la primera de lista y la nueva que trajo el policia
		
		Distribuciones dis_mejor=new Distribuciones(lista.get(0).getImagen().getMatriz());
		Map map_mejor = dis_mejor.get();
		Iterator it_mejor = map_mejor.keySet().iterator();
		System.out.println("Distribucion de la mejor imagen encontrada: ");
		while(it_mejor.hasNext()){
			Integer key_mejor = (Integer) it_mejor.next();
			System.out.println("Digito " + key_mejor + " -> Valor: " + (Double)(map_mejor.get(key_mejor)));
		}
		Histograma h_mejor= new Histograma(lista.get(0).getImagen().getNombre());
		h_mejor.Get_histograma(map_mejor);
		System.out.println(dis_mejor.cantidad_digitos);
		 System.out.println("la media es "+dis_mejor.get_Media());
		System.out.println("el desvio es de "+dis_mejor.get_DesvioEstandar());
		
		Imagen policia= new Imagen();
		policia.getValoresMat("Will_ej2");
		Distribuciones dis_policia=new Distribuciones(policia.getMatriz());
		Map map_policia = dis_policia.get();
		Iterator it_policia = map_policia.keySet().iterator();
		System.out.println("Distribucion de la imagen del policia: ");
		while(it_policia.hasNext()){
			Integer key_policia = (Integer) it_policia.next();
			System.out.println("Digito " + key_policia + " -> Valor: " + (Double)(map_policia.get(key_policia)));
		}
		Histograma h_policia= new Histograma(policia.getNombre());
		h_policia.Get_histograma(map_policia);
		System.out.println(dis_policia.cantidad_digitos);
		 System.out.println("la media es "+dis_policia.get_Media());System.out.println("el desvio es de "+dis_policia.get_DesvioEstandar());

		}*/
        Imagen policia= new Imagen();
        policia.getValoresMat("Will_ej2");
        Distribuciones dis_policia=new Distribuciones(policia.getMatriz());
        Huffman f=new Huffman(dis_policia.get_Distribucion(),policia);/*
        HashMap<Integer,Pair<Integer,Byte>> hash= new HashMap<>();
        f.generarTabla("",0,f.generarArbol(),hash);
        for(int clave:hash.keySet()){
        	System.out.print("clave : ");
			System.out.println(clave);
			System.out.print("codigo : ");
			System.out.println(hash.get(clave).getValue());
			System.out.print("longitud :");
            System.out.println(hash.get(clave).getKey());
		}*/
		f.codificar();
		int[][] matriz=policia.getMatriz();
		int[][] matriz2=f.decodificar();
		int distintos=0;
		for(int i=0;i<policia.getLargo();i++){
			for(int j=0;j<policia.getAncho();j++){
				if(matriz[j][i]!=matriz2[j][i]) {
					System.out.println("matriz1 "+matriz[j][i]+" matriz2 "+matriz2[j][i]);
					distintos++;
				}
			}
		}
		System.out.println("difieren en "+distintos+" pixeles");
	}
}





