package teo.info;

import java.io.*;
import java.util.ArrayList;

public class ManejadorArchivos {
    public ArrayList<Byte> leer() {
        File archivo = null;
        FileReader lee = null;
        BufferedReader buf = null;
        ArrayList<Byte> codigos = new ArrayList();
        ArrayList<Byte> b = new ArrayList();
        String linea;
        try {
            archivo = new File("Sources\\codificacion.txt");
            if (!archivo.exists())
            {System.out.println("el archivo no existe");}
            lee = new FileReader(archivo);
            buf = new BufferedReader(lee);
            while ((linea = buf.readLine()) != null) {
                Byte A = (Byte.parseByte(linea.substring(0, linea.length())));
                codigos.add(A);
            }
            return codigos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (lee!=null)
                    lee.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return codigos;
    }

    public void escribir(ArrayList<Byte> codigo)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(new File("Sources\\codificacion.txt"));
            pw = new PrintWriter(fichero);

            for (byte b: codigo)
                pw.println(b);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}

