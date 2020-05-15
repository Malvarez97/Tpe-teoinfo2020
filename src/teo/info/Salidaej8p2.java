
public class Salidaej8p2 implements Comparable {
	private int i;
	private int j; 
	private double ccruzada;
	public Salidaej8p2(int i, int j, double autoc) {
		this.i=i;
		this.j=j;
		this.ccruzada=autoc;
	}
	public Salidaej8p2() {
		this.i=0;
		this.j=0;
		this.ccruzada=0.0;
	}
	
	@Override
	public int compareTo(Object otra) {
		if (this.ccruzada< ((Salidaej8p2) otra).getCorrelacionCruzada())
			return 1;
		else if (this.ccruzada> ((Salidaej8p2) otra).getCorrelacionCruzada())
			return -1;
		return 0;
	}
	
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public double getCorrelacionCruzada() {
		return ccruzada;
	}
	public void setAutoc(double cc) {
		this.ccruzada = cc;
	}
	public String toString() {
		return "Posicion de inicio: "+this.i+", "+this.j+" correlacion cruzada: "+this.ccruzada;
		
	}
	
}
