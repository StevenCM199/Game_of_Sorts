package juego;
import java.util.Random;
public class VelocidadDeRecarga {

    public static void velocidadDeRecarga (){

        Random numero = new Random();
        int velocidad = (numero.nextInt(100)+1);
        System.out.println("Velocidad de Dragon: "+velocidad);

    }
}