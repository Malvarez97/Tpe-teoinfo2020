package teo.info;
import java.lang.Comparable;
public class NodoArbolH implements Comparable{
    int simbolo;
    Byte codigo;
    double probabilidad;
    NodoArbolH menor;
    NodoArbolH mayor;
    NodoArbolH padre;

    public NodoArbolH(int simbolo, double probabilidad, NodoArbolH padre) {
        this.simbolo = simbolo;
        this.padre=padre;
        this.probabilidad = probabilidad;
        this.menor=null;
        mayor=null;
    }

    public int getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(int simbolo) {
        this.simbolo = simbolo;
    }

    public Byte getCodigo() {
        return codigo;
    }

    public void setCodigo(Byte codigo) {
        this.codigo = codigo;
    }

    public double getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(double probabilidad) {
        this.probabilidad = probabilidad;
    }

    public NodoArbolH getMenor() {
        return menor;
    }

    public void setMenor(NodoArbolH menor) {
        this.menor = menor;
    }

    public NodoArbolH getMayor() {
        return mayor;
    }

    public void setMayor(NodoArbolH mayor) {
        this.mayor = mayor;
    }

    public NodoArbolH getPadre() {
        return padre;
    }

    public void setPadre(NodoArbolH padre) {
        this.padre = padre;
    }

    public boolean esHoja(){
        return ((mayor==null)&&(menor==null));
    }

    @Override
    public int compareTo(Object otroNodo) {
        double result= this.probabilidad-((NodoArbolH) otroNodo).getProbabilidad();
        if (result<0)
            return -1;
        else if (result>0)
            return 1;
        return (int) result;
    }

}
