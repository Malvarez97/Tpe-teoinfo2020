package teo.info;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Imagen {
    public Imagen() {
    }

    private BufferedImage getImage(String filename) {
        try {
            InputStream in = getClass().getResourceAsStream(filename);// ingresa el directorio en la entrada que luego es cargada en la
            return ImageIO.read(in);
        } catch (IOException e) {
            System.out.println("The image was not loaded.");
        }
        return null;
    }

    public int[][] getValoresMat(String nombrefoto) {// ¿podriamos agregarle que ponga el nombre de la foto a ingresar y lo haga automatico del Main ?
        // creo la imagen
        BufferedImage imagen = this.getImage("/"+nombrefoto+".bmp");//lee lo que hay en la carpeta imagenes ..curiosidad la lee de la carpeta la primera vez y hacer una copia de "segurirdad en la carpeta out de produccion, si la borras , queda guardad en esa copia y se puede volver a usar .
        /** recorrer el buffer e ingreso los datos en la matriz */
        int matriz[][] = new int[imagen.getWidth()][imagen.getHeight()];
        for (int alto = 0; alto < imagen.getHeight(); alto++) {
            for (int ancho = 0; ancho < imagen.getWidth(); ancho++) {
                Color Tpixel =new Color(imagen.getRGB(ancho, alto),true); /** devuelve el tono de color del pixel(en este caso el azul , pero podria ser por cualquiera de los 3 por que los tonos de gris tienen la misma cantidad de cada color*/
                matriz[ancho][alto] =Tpixel.getBlue() ;
            }
        }

        /**imprimo la matriz (para testear)
        for (int a = 0; a < imagen.getWidth(); a++) {
            System.out.println();
            for (int l = 0; l < imagen.getHeight(); l++) {
                System.out.print(matriz[a][l]);
            }
        }
        System.out.println();
        System.out.println("largo de imagen  " + imagen.getHeight());
        System.out.println("ancho de la imagen  " + imagen.getWidth());
        */
        return matriz;
    }
}






