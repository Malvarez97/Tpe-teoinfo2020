package teo.info;
import teo.info.Distribuciones;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
    	/**  Main 1
    	ArrayList<NodoImagen> lista=new Calculador().ordenarImagenes();    
    	for(NodoImagen n:lista) {
    		System.out.print("la correlacion cruzada entre imagen original e imagen ");
    		System.out.print(n.getImagen().getNombre());
    		System.out.print(" es: ");
    		System.out.println(n.getCorrelacion());
		 }
		 //* Main 2
		 ej8p2 ej= new ej8p2();
		 int[][] B= {{6,6,3},{4,9,3},{2,1,2}};
		 int[][] A={{6,6},{9,3}};
		 System.out.println(ej.calcularMayorCoincidencia(A, B, 2, 3).toString());
		 */
    	/** Main 3 , prueba de el ejercicio 2*/
		int [][] matriz = {{2,4},{6,6}};
		Distribuciones A=new Distribuciones(matriz);
		Map J = A.get_Distribucion();
		Iterator it = J.keySet().iterator();
		while(it.hasNext()){
			Integer key = (Integer) it.next();
			System.out.println("Digito " + key + " -> Valor: " + (Double)(J.get(key)));
		}
		System.out.println(A.cantidad_digitos);
		 System.out.println("la media es "+A.get_Media());
		System.out.println("el desvio es de "+A.get_DesvioEstandar());


		}



    }
