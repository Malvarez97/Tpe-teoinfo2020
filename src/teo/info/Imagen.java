package teo.info;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Imagen {
	private int ancho;
	private int largo;
	private String nombre;
	private int[][] matriz;
    public Imagen() {
    	largo=0;
    	ancho=0;
    }
    public Imagen(int ancho,int largo,String nombre,int[][] matriz ){
        this.ancho=ancho;
        this.largo=largo;
        this.nombre=nombre;
        this.matriz=matriz;
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
    	this.nombre=nombrefoto;
        BufferedImage imagen = this.getImage("/"+nombrefoto+".bmp");//lee lo que hay en la carpeta imagenes ..curiosidad la lee de la carpeta la primera vez y hacer una copia de "segurirdad en la carpeta out de produccion, si la borras , queda guardad en esa copia y se puede volver a usar .
        /** recorrer el buffer e ingreso los datos en la matriz */
        this.ancho=imagen.getWidth();
        this.largo=imagen.getHeight();
        this.matriz = new int[largo][ancho];
        for (int fila = 0; fila < largo; fila++) {
            for (int col = 0; col < ancho; col++) {
                Color Tpixel =new Color(imagen.getRGB(col, fila),true); //** devuelve el tono de color del pixel
                matriz[fila][col] =Tpixel.getBlue() ;
            }
        }
        

       /*imprimo la matriz (para testear)
        for (int a = 0; a < imagen.getWidth(); a++) {
            System.out.println();
            for (int l = 0; l < imagen.getHeight(); l++) {
                System.out.print(matriz[a][l]);
            }
        }
        System.out.println();
        System.out.println("largo de imagen  " + imagen.getHeight());
        System.out.println("ancho de la imagen  " + imagen.getWidth());*/
       
        return matriz;
    }
    
    public int[][] getMatriz(){
    	return this.matriz;
    }
    
    public String getNombre() {
    	return nombre;
    }
    
    public int getAncho() {
    	return ancho;
    }
    public int getLargo() {
    	return largo;
    }
}






