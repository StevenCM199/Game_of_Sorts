package juego;

import java.util.Random;

public class EdadDeDragon {

    public static void edadDeDragon(){
        Random numero = new Random();
        int edad = (numero.nextInt(100)+1);
        System.out.println(edad);
    }

}
