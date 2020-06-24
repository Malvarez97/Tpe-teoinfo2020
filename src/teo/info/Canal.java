package teo.info;

import javafx.util.Pair;

import java.util.HashMap;

public class Canal {
    private static int CANT_TONOS = 256;
    private static int MIN_PRUEBAS=5000;
    private double[][] matriz;
    private double[]ocurrencias;
    private double[][] m_acum;
    private double[] probs;
    private  Imagen foto;
    private Imagen canal;

    public Canal(Imagen foto, Imagen canal) {
        this.foto=foto;
        this.canal=canal;
        this.ocurrencias = new double[CANT_TONOS];
        this.matriz = matrizTransicion();
        m_acum=getMatAcum();
        probs=getArrAcum();
    }

    ;

    private double[][] matrizTransicion() {
        double[][] transiciones = new double[CANT_TONOS][CANT_TONOS];
        HashMap<Pair<Integer, Integer>, Double> probabilidades = new HashMap<>();
        int[][] m_foto = foto.getMatriz();
        int[][] m_canal = canal.getMatriz();
        for (int i = 0; i < foto.getLargo(); i++) {
            for (int j = 0; j < foto.getAncho(); j++) {
                int x = m_foto[i][j];
                int y = m_canal[i][j];
                ocurrencias[x]++;
                Pair<Integer, Integer> par = new Pair<Integer, Integer>(x, y);
                if (probabilidades.containsKey(par)) {
                    double valor = probabilidades.get(par);
                    valor++;
                    probabilidades.put(par, valor);
                } else
                    probabilidades.put(par, 1.0);
            }
        }
        for (Pair<Integer, Integer> par : probabilidades.keySet()) {
            int x = par.getKey();
            int y = par.getValue();
            transiciones[x][y] = ((double) probabilidades.get(par) / ocurrencias[x]);
        }
        for (int i=0;i<CANT_TONOS;i++)
            ocurrencias[i]=((double)ocurrencias[i]/(foto.getLargo()*foto.getAncho()));
        return transiciones;

    }

    public double[][] getMatTransiciones() {
        return this.matriz;
    }


    public double getRuido() {
        double ruido = 0.0;
        double ruido_col=0.0;
        for (int i = 0; i < CANT_TONOS; i++) {
            ruido_col = 0.0;
            for (int j = 0; j < CANT_TONOS; j++) {
                if (matriz[i][j] != 0)
                    ruido_col += ((double) matriz[i][j] * (-Math.log(matriz[i][j]) / Math.log(2)));
            }
            ruido += ((double) ruido_col * ocurrencias[i]);
        }
        return ruido;
    }

    private double[] getArrAcum() {
        double acum=0.0;
        double[] arr=new double[CANT_TONOS];
        for(int i=0;i<CANT_TONOS;i++){
            acum+=((double)ocurrencias[i]);
            arr[i]=acum;
        }
        return arr;
    }

    private double[][] getMatAcum(){
        double m_acum[][]=new double[CANT_TONOS][CANT_TONOS];
        for(int i=0;i<CANT_TONOS;i++){
            double acum=0.0;
            for(int j=0;j<CANT_TONOS;j++){
                acum+=matriz[i][j];
                m_acum[i][j]=acum;
            }
        }
        return m_acum;
    }

    public int getX() {
        double rand = Math.random();
        for (int i = 0; i < CANT_TONOS; i++) {
            if (rand <= probs[i])
                return i;
        }
        return -1;
    }

    public int getY(int x){
        double rand=Math.random();
        for(int i=0;i<CANT_TONOS;i++){
            if(rand<=m_acum[x][i])
                return i;
        }
        return -1;
    }

    public double getRuidoMuestreo(double epsilon){
        int[] cant_x=new int[CANT_TONOS];
        int[][] aciertos = new int[CANT_TONOS][CANT_TONOS];
        double[] sumas=new double[CANT_TONOS];
        int tiradas=0;
        double ruido_ant=-1.0;
        double ruido_act=0.0;
        while((tiradas<MIN_PRUEBAS)||(!converge(ruido_act,ruido_ant,epsilon))){
            tiradas++;
            int x=getX();
            int y=getY(x);
            cant_x[x]++;
            aciertos[x][y]++;
            for(int i=0;i<CANT_TONOS;i++){
                double prob=((double)aciertos[x][i]/cant_x[x]);
                sumas[x]+=((double)prob*(-Math.log(prob)/Math.log(2)));
            }
            ruido_ant=ruido_act;
            ruido_act=0.0;
            for(int i=0;i<CANT_TONOS;i++){
                ruido_act+=(cant_x[i]/((double)tiradas))*(sumas[i]);
            }

        }
        return ((double)ruido_act/tiradas);
    }

    private boolean converge(double ruido_act, double ruido_ant,double epsilon) {
        return(Math.abs(ruido_act-ruido_ant)<epsilon);
    }
}



