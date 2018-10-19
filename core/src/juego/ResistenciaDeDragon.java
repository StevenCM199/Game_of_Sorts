package juego;

import java.util.Random;

public class ResistenciaDeDragon {
    public static void resistenciaDeDragon(){
        Random numero = new Random();
        int resistencia = (numero.nextInt(3)+1);
        System.out.println("Resistencia de Dragon: "+resistencia);
    }
}
