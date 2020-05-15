import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
    	ArrayList<NodoImagen> lista=new Calculador().ordenarImagenes();    
    	for(NodoImagen n:lista) {
    	System.out.print("la correlacion cruzada entre imagen original e imagen ");
    	System.out.print(n.getImagen().getNombre());
    	System.out.print(" es: ");
    	System.out.println(n.getCorrelacion());
    }
}
};