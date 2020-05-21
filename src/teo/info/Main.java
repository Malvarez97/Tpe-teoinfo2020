<<<<<<< HEAD
package teo.info;
=======

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
>>>>>>> parent of c081e56... Revert "Cambios para conectar mejor los ejercicios"
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
<<<<<<< HEAD
		/** Ejercicio 1*/
		ArrayList<NodoImagen> lista = new Calculador().ordenarImagenes();// devuelve la correlacion de la imagen de mayor a menos , osea que la que este en la posicion 0 , sera la que tenaga mayor correlacion con la foto orginal
		System.out.println("La correlacion cruzada entra la imagen orginal y las siguientes imagenes ordenadas por las correlaciones fue  :");
		for (NodoImagen n : lista) {
			System.out.println("Imagen" + n.getImagen().getNombre() + " : " + n.getCorrelacion());
		}
		System.out.println();
		System.out.println();
		/**Ejercicio 2*/
			Imagen imagen = new Imagen();
			int[][] m_policia = imagen.getValoresMat("Will_ej2");
			int[][] m_W_Original = imagen.getValoresMat("Will(Original)");
			int[][] m_W_mayorcorrelacion = imagen.getValoresMat(lista.get(0).getImagen().getNombre());
			Distribuciones policia = new Distribuciones(m_policia);
			Distribuciones w_original = new Distribuciones(m_W_Original);
			Distribuciones w_mayorcorrelacion = new Distribuciones(m_W_mayorcorrelacion);
		/** Informacion de la imagen del policia*/
			System.out.println("la media de la imagen del policia es:  " + policia.get_Media() + "  y el desvio estandar de esta es :  " + policia.get_DesvioEstandar());
			Histograma hist = new Histograma();
			hist.Get_histograma(policia.get(), "Imagen que tenia el policia ");
		/** Informacion de la imagen Original de Will*/
			System.out.println("la media dela imagen Original de Will es:  " + w_original.get_Media() + "  y el desvio estandar de esta es :  " + w_original.get_DesvioEstandar());
			hist.Get_histograma(w_original.get(), "Imagen Original de Will ");
		/** Informacion de la imagen de correlacion cruzada mas similar obtenida en el ejercicio 1*/
			System.out.println("la media dela imagen Original de Will es:  " + w_mayorcorrelacion.get_Media() + "  y el desvio estandar de esta es :  " + w_mayorcorrelacion.get_DesvioEstandar());
			hist.Get_histograma(w_mayorcorrelacion.get(), "Imagen mas parecida obtenida por correlacion cruzazda  ");

	}

	}

=======
		Imagen original=new Imagen();
		int[][] matriz_original =original.getValoresMat("Will(Original)");

		/**  Main 1*/
        ArrayList<NodoImagen> lista=new Calculador().ordenarImagenes(original);
        /*for(NodoImagen n:lista) {
            System.out.print("la correlacion cruzada entre imagen original e imagen ");
            System.out.print(n.getImagen().getNombre());
            System.out.print(" es: ");
            System.out.println(n.getCorrelacion());
         }
         /* Main 2
         ej8p2 ej= new ej8p2();
         int[][] B= {{6,6,3},{4,9,3},{2,1,2}};
         int[][] A={{6,6},{9,3}};
         System.out.println(ej.calcularMayorCoincidencia(A, B, 2, 3).toString());*/
         
    	/** Main 3 , prueba de el ejercicio 2*/
		/**int [][] matriz = {{6,6,3},{4,9,3},{2,1,2}};;*/
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
		 System.out.println("la media es "+dis_policia.get_Media());
		System.out.println("el desvio es de "+dis_policia.get_DesvioEstandar());
>>>>>>> parent of c081e56... Revert "Cambios para conectar mejor los ejercicios"






