package teo.info;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Byte.parseByte;

public class Huffman {
    ManejadorArchivos files;


    public Huffman (){
        files=new ManejadorArchivos();
    }



    private ArrayList<NodoArbolH> listaOrdenada(Map<Integer,Double> probabilidades){
        ArrayList<NodoArbolH> lista=new ArrayList<NodoArbolH>();
        for(Integer clave: probabilidades.keySet())
        {
            NodoArbolH nodo =new NodoArbolH(clave,probabilidades.get(clave), null);
            lista.add(nodo);
        }
        lista.sort(null);
        return lista;
    }

    public NodoArbolH generarArbol(Map<Integer,Double> probabilidades){
        ArrayList<NodoArbolH> lista= listaOrdenada( probabilidades);
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
    public void generarTabla(String codigo, int lon,NodoArbolH arbol, HashMap<Integer, Pair<Integer,Byte>>hash){
       if(!arbol.esHoja()){
           generarTabla(codigo+"1",lon+1,arbol.getMenor(),hash);
           generarTabla(codigo+"0",lon+1,arbol.getMayor(),hash);
       }else
           hash.put(arbol.getSimbolo(),new Pair<Integer,Byte>(lon,Byte.parseByte(codigo,2)));

    }


    public int codificar(Map<Integer,Double> probabilidades, Imagen imagen){
        int pos=0;
        int libre=8;
        int lon=0;
        byte actual;
        ArrayList<Byte> arreglo= new ArrayList<Byte>();
        HashMap<Integer,Pair<Integer,Byte>> hash=new HashMap<>();
        generarTabla("",0,this.generarArbol(probabilidades),hash);
        int[][] matriz=imagen.getMatriz();
        for(int i=0;i<imagen.getLargo();i++){
            for(int j=0;j<imagen.getAncho();j++){
                int s= matriz[i][j];
                lon=hash.get(s).getKey();
                actual=hash.get(s).getValue();
                if(libre>=lon){              //       hay espacio para el codigo entero
                    actual= (byte) (actual<<libre-lon);  //desplazamos el codigo
                    if(pos>=arreglo.size())
                        arreglo.add(pos, (byte) 0);
                    arreglo.add(pos,(byte) (actual|arreglo.remove(pos)));
                    libre-=lon;
                    if(libre==0)  // ocupe todo el byte actual
                    {
                        libre=8;
                        pos++;
                        arreglo.add(pos, (byte) 0);
                    }
                }else { //si no hay espacio para el codigo entero
                    byte aux=(byte) (actual>>lon-libre);
                    libre=8-(lon-libre);
                    actual= (byte) (actual<<libre);
                    arreglo.add(pos,(byte)(aux|arreglo.remove(pos)));
                    pos++;
                    arreglo.add(pos,(byte)actual);
                }

            }
        }
        files.escribir(arreglo,probabilidades,imagen);
        return arreglo.size();
    }

   public int[][] decodificar(){
        ArrayList<Byte> codigo =new ArrayList<>();
        HashMap<Integer,Double> probabilidades=new HashMap<>();
        String nombre="";
        Integer dimension[]= new Integer[3];
        files.leer(codigo,dimension,probabilidades);
        int largo=dimension[0];
        int ancho=dimension[1];
        int tamaño=dimension[2];
        int[][] matriz=new int[largo][ancho];
        NodoArbolH arbol=generarArbol(probabilidades);
        NodoArbolH arbolaux= arbol;
        int pos=0;
        byte actual=codigo.get(pos);
        pos++;
        int bit=0;
        for(int i=0;i<largo;i++){
            for(int j=0;j<ancho;j++){
                while((!arbolaux.esHoja())){
                    if(actual>=0)
                        arbolaux=arbolaux.getMayor();
                    else
                        arbolaux=arbolaux.getMenor();
                    bit++;
                    actual=(byte) (actual<<1);
                    if(bit>=8) {
                        bit = 0;
                        if(pos<codigo.size())
                            actual = codigo.get(pos);
                        pos++;
                    }
                }
                matriz[i][j]=arbolaux.getSimbolo();
                arbolaux=arbol;
                }
           }
          return matriz;
        }



    private void imprimirTabla(HashMap<Integer, Pair<Integer, Byte>> hash) {
        for(Integer i :hash.keySet()){
            System.out.println("simbolo: "+i);
            System.out.println("codigo: "+hash.get(i).getValue());
            System.out.println("longitud: "+hash.get(i).getKey());
        }
    }

    public void imprimirArbol(NodoArbolH nodo,int lon){
        if(nodo!=null) {
            if (nodo.esHoja()) {
                System.out.print("es hoja, valor :");
                System.out.println(nodo.getSimbolo());
                System.out.println("longitud "+lon);
            } else {
                System.out.print(nodo.getProbabilidad());
                if (nodo.getMenor() != null) {
                   // System.out.println("menor: ");
                    imprimirArbol(nodo.getMenor(),lon++);
                }
                if (nodo.getMayor() != null) {
                    //System.out.println("mayor: ");
                    imprimirArbol(nodo.getMayor(),lon++);
                }
            }
        }
    }

    public double getTasaCompresion(Map<Integer,Double> probabilidades, Imagen imagen){
        int tnuevo=codificar(probabilidades,imagen);
        return ((double)(imagen.getAncho()*imagen.getLargo())/tnuevo);
    }
}
