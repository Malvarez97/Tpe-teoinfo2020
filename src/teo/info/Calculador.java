package teo.info;
import java.util.ArrayList;

public class Calculador{  //esta clase se encargara de hacer los calculos con las matrices 
	public Calculador() {}
	public int getCorrelacionCruzada(int[][] matrizA, int [][] matrizB, int ancho, int largo) {
		int cc=0;
		for(int i=0;i<ancho;i++) {
			for(int j=0; j<ancho;j++) {
				cc+=matrizA[i][j]*matrizB[i][j];
			}
		}
		return cc;
	}
	
	public ArrayList<NodoImagen> ordenarImagenes(Imagen original){
		ArrayList<NodoImagen> lista =new ArrayList<NodoImagen>();
		int[][] morig= original.getMatriz();//cargo la imagen original
		for (int i=1;i<=5;i++) {
			Imagen obtenida= new Imagen(); //creo una imagen
			int[][] mob=obtenida.getValoresMat("Will_"+i); //cargo una nueva imagen
			NodoImagen nodo=new NodoImagen(obtenida,this.getCorrelacionCruzada(morig, mob, original.getAncho(), original.getAncho()));//creo un nodo con la imagen y la correlacion
			lista.add(nodo);
		}
		lista.sort(null);
		return lista;
	}
}
