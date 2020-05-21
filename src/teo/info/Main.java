package teo.info;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
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







