

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class Distribuciones {
     private int[][] M_imagen;
      double cantidad_digitos;

    public Distribuciones(int[][] M_imagen) {
        this.M_imagen = M_imagen;
        this.cantidad_digitos = (M_imagen.length *M_imagen[0].length);
    }

    public Map<Integer,Double> get() {
        Map <Integer,Double> distribuciones = new HashMap<Integer, Double>();
        for (int i = 0; i < M_imagen.length; i++) {
            for (int j = 0; j < M_imagen[i].length; j++) {
                if (distribuciones.containsKey(M_imagen[i][j])) {
                    Double cantidad = distribuciones.get(M_imagen[i][j]) + 1.0;
                    distribuciones.put(M_imagen[i][j], cantidad);
                } else
                    distribuciones.put((M_imagen[i][j]), 1.0); // si no existe el valor lo inserto en el mapa
            }

        }
        return distribuciones;
    }
    public Map<Integer,Double> get_Distribucion() {
        Map<Integer, Double> distribuciones = this.get();
        Map porcentaje = new HashMap<Integer, Double>();
        Iterator it = distribuciones.keySet().iterator();
        while (it.hasNext()) {
            Integer key = (Integer) it.next();
            porcentaje.put(key, (distribuciones.get(key) / cantidad_digitos));
        }
        return porcentaje;
    }

    public double get_Media() {
        Double resultado = 0.0;
        Map distribucion = this.get();
        Iterator it = distribucion.keySet().iterator();
        while (it.hasNext()) {
            Integer clave = (Integer) it.next();
            resultado=resultado+((Double)((clave) * (double)distribucion.get(clave)));
        }
        return resultado/cantidad_digitos;
    }

    public double get_DesvioEstandar(){
        double media=this.get_Media();
        double resultado =0;
        Map distribucion = this.get();
        Iterator it = distribucion.keySet().iterator();
        while (it.hasNext()) {// hago la sumatoria del desvio
            Integer clave = (Integer) it.next();
            resultado+=(Math.pow((clave-media),2)* (Double)(distribucion.get(clave))) ;
        }
        resultado=resultado/cantidad_digitos;
        return ( Math.sqrt(resultado));
    }

}


