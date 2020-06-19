package teo.info;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Byte.parseByte;

public class Huffman {
    ManejadorArchivos files;
    Map<Integer,Double> probabilidades;
    NodoArbolH arbol;
    Imagen imagen;

    public Huffman (Map<Integer,Double> probabilidades, Imagen im){
        this.imagen=im;
        this.probabilidades=probabilidades;
        files=new ManejadorArchivos();
        generarArbol();
    }

    public void setImagen(Imagen imagen){
        this.imagen=imagen;
    }

    private ArrayList<NodoArbolH> listaOrdenada(){
        ArrayList<NodoArbolH> lista=new ArrayList<NodoArbolH>();
        for(Integer clave: probabilidades.keySet())
        {
            NodoArbolH nodo =new NodoArbolH(clave,probabilidades.get(clave), null);
            lista.add(nodo);
        }
        lista.sort(null);
        return lista;
    }

    public void generarArbol(){
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
        arbol= lista.get(0);
    }
    public void generarTabla(String codigo, int lon,NodoArbolH arbol, HashMap<Integer, Pair<Integer,Byte>>hash){
       if(!arbol.esHoja()){
           generarTabla(codigo+"0",lon+1,arbol.getMenor(),hash);
           generarTabla(codigo+"1",lon+1,arbol.getMayor(),hash);
       }else
           hash.put(arbol.getSimbolo(),new Pair<Integer,Byte>(lon,Byte.parseByte(codigo,2)));
    }


    public void codificar(){
        int pos=0;
        int libre=8;
        int lon=0;
        byte actual;
        ArrayList<Byte> arreglo= new ArrayList<Byte>();
        HashMap<Integer,Pair<Integer,Byte>> hash=new HashMap<>();
        generarTabla("",0,this.arbol,hash);
        int[][] matriz=imagen.getMatriz();
        for(int i=0;i<imagen.getAncho();i++){
            for(int j=0;j<imagen.getLargo();j++){
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
        files.escribir(arreglo);
    }

   public int[][] decodificar(){
        ArrayList<Byte> codigo = files.leer();
        int[][] matriz=new int[imagen.getAncho()][imagen.getLargo()];
        NodoArbolH arbolaux= arbol;
        int pos=0;
        byte actual=codigo.get(pos);
        pos++;
        int bit=0;
        for(int i=0;i<imagen.getLargo();i++){
            for(int j=0;j<imagen.getAncho();j++){
                while((!arbolaux.esHoja())){
                    if(actual>=0)
                        arbolaux=arbolaux.getMenor();
                    else
                        arbolaux=arbolaux.getMayor();
                    bit++;
                    actual=(byte) (actual<<1);
                    if(bit>=8) {
                        bit = 0;
                        if(pos<codigo.size())
                            actual = codigo.get(pos);
                        pos++;
                    }
                }
                matriz[j][i]=arbolaux.getSimbolo();
                arbolaux=arbol;
                }
           }
          return matriz;
        }



    private void imprimirTabla(HashMap<Integer, Pair<Integer, Byte>> hash) {
        for(Integer i :hash.keySet()){
            System.out.println("simbolo: "+i);
            System.out.println("codigo: "+hash.get(i).getValue());
        }
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
