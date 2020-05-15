import java.util.ArrayList;

public class ej8p2 {
	public ej8p2(){}
	public double[] calcular_autocorrelacion (int B[][], int n) { /dada una imagen cuadrada y su dimension n, retorna la autocorrelacion para las distancias de 0 a n
		double autoc[]= new double[n];
		for (int d=0;d<n;d++)
			autoc[d]=0;
		for(int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				for (int d=0;d<n;d++) {
					if(i+d<n)
						autoc[d]+=B[i][j]*B[i+d][j];
					if ((j+d<n)&&(d>0))
						autoc[d]+=B[i][j]*B[i][j+d];
				}
			}
		}
		for (int i=0;i<n;i++)
			autoc[i]=(double) autoc[i]/(n*n);
		return autoc;
	}
	
	public Salidaej8p2 calcularMayorCoincidencia(int A[][], int B[][], int dimA, int dimB) { /dadas dos imagenes con su dimension, retorna el par (i,j) desde donde una imagen y la otra tienen su mayor coincidencia
		ArrayList<Salidaej8p2> lista=new ArrayList<Salidaej8p2>();                           / y la correlacion cruzada de las imagenes a partir de ese punto
		for(int i=0;i<=dimB-dimA;i++) {
			for(int j=0;j<=dimB-dimA;j++) {
				Salidaej8p2 nodo=new Salidaej8p2(i,j,0.0);
				double ac=0.0;
				for(int k=0;k<dimA;k++) {
					for(int l=0;l<dimA;l++) {
						ac+=(double) A[k][l]*B[i+k][j+l];
					}
				}
				nodo.setAutoc(ac);
				lista.add(nodo);
			}
		}
		lista.sort(null);
		return lista.get(0);
		
	}
}


