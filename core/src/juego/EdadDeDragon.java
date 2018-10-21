package juego;
import java.util.Random;

public class EdadDeDragon {

    public static void edadDeDragon(){
        Random numero = new Random();
        int edad = (numero.nextInt(1000)+1);
        System.out.println("Edad del Dragon: "+edad);

    }
}