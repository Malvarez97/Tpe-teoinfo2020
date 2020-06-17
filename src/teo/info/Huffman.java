package teo.info;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Byte.parseByte;

public class Huffman {
    Map<Integer,Double> probabilidades;
    public Huffman (Map<Integer,Double> probabilidades){
        this.probabilidades=probabilidades;
    }

    private ArrayList<NodoArbolH> listaOrdenada(){
        ArrayList<NodoArbolH> lista=new ArrayList<NodoArbolH>();
        for(Integer clave: probabilidades.keySet())
        {
            NodoArbolH nodo =new NodoArbolH(clave,probabilidades.get(clave), null);
            lista.add(nodo);
        }
        lista.sort(null);
        for(NodoArbolH nodo: lista){
            System.out.println("codigo: "+nodo.getSimbolo()+"probabilidad: "+nodo.getProbabilidad());
        }
        return lista;
    }

    public NodoArbolH generarArbol(){
        ArrayList<NodoArbolH> lista= listaOrdenada();
        while(lista.size()>1){
            NodoArbolH mayor=lista.remove(1);
            NodoArbolH menor=lista.remove(0);
            double probabilidad= mayor.getProbabilidad()+menor.getProbabilidad();
            NodoArbolH nodo =new NodoArbolH(-1,probabilidad,null);
            nodo.setMenor(menor);
            nodo.setMayor(mayor);
            mayor.setPadre(nodo);
            menor.setPadre(nodo);
            lista.add(nodo);
            lista.sort(null);
        }
        return lista.get(0);
    }
    public void generarTabla(String codigo, int lon, NodoArbolH arbol, HashMap<Integer, Pair<Integer,Byte>>hash){
       if(!arbol.esHoja()){
           generarTabla(codigo+"0",lon+1,arbol.getMenor(),hash);
           generarTabla(codigo+"1",lon+1,arbol.getMayor(),hash);
       }else
           hash.put(arbol.getSimbolo(),new Pair<Integer,Byte>(lon,Byte.parseByte(codigo,2)));
    }

    public ArrayList<Byte> codificar(Imagen im){
        int pos=0;
        int libre=8;
        int lon=0;
        byte actual;
        ArrayList<Byte> arreglo= new ArrayList<Byte>();
        HashMap<Integer,Pair<Integer,Byte>> hash=new HashMap<>();
        generarTabla("",0,generarArbol(),hash);
        int[][] matriz=im.getMatriz();
        for(int i=0;i<im.getAncho();i++){
            for(int j=0;j<im.getLargo();j++){
                int s= matriz[i][j];
                lon=hash.get(s).getKey();
                actual=hash.get(s).getValue();
                if(libre>lon){              //       hay espacio para el codigo entero
                    actual= (byte) (actual<<libre-lon);
                    if(pos>=arreglo.size())
                        arreglo.add(pos, (byte) 0);
                    arreglo.add(pos,(byte) (actual|arreglo.get(pos)));
                    libre-=lon;
                    if(libre==0)  // ocupe todo el byte actual
                    {
                        libre=8;
                        pos++;
                        arreglo.add(pos, (byte) 0);
                    }
                }else {
                    byte aux=(byte) (actual>>lon-libre);
                    libre-=8-(lon-libre);
                    actual= (byte) (actual<<libre);
                    arreglo.add(pos,(byte)(aux|arreglo.get(pos)));
                    pos++;
                    arreglo.add(pos,(byte)actual);
                }

            }
        }
        return arreglo;
    }

    public void imprimirArbol(NodoArbolH nodo){
        if(nodo!=null) {
            if (nodo.esHoja()) {
                System.out.print("es hoja, valor :");
                System.out.println(nodo.getSimbolo());
            } else {
                System.out.print(nodo.getProbabilidad());
                if (nodo.getMenor() != null) {
                    System.out.println("menor: ");
                    imprimirArbol(nodo.getMenor());
                }
                if (nodo.getMayor() != null) {
                    System.out.println("mayor: ");
                    imprimirArbol(nodo.getMayor());
                }
            }
        }
    }
}
