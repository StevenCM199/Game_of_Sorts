package juego;

import java.util.Random;

public class CaracteristicasDelDragon {

    Random numero = new Random();
    int velocidad = (numero.nextInt(1000)+1);
    int edad = (numero.nextInt(100)+1);
    int resistencia = (numero.nextInt(3)+1);

    public CaracteristicasDelDragon() {

        String[] caracteristicas = new String[6];

        NomAleatoriosDragones nombre = new NomAleatoriosDragones();
        String[] nom = nombre.generarNombreAleatorio(1);
        String nombreD = new String(nom[0]);

        ClaseDeDragon clase = new ClaseDeDragon();
        String[] cla = clase.claseDeDragon(1);
        String claseD = new String(cla[0]);

        String velocidadD = Integer.toString(velocidad);
        String edadD = Integer.toString(edad);
        String resistenciaD = Integer.toString(resistencia);

        caracteristicas[0] = claseD;
        caracteristicas[1] = nombreD;
        caracteristicas[2] = resistenciaD;
        caracteristicas[3] = velocidadD;
        caracteristicas[4] = edadD;

        System.out.println( "Clase: "+caracteristicas [0]);
        System.out.println( "Nombre: "+caracteristicas [1]);
        System.out.println( "Resistencia: "+caracteristicas [2]);
        System.out.println( "Velocidad: "+caracteristicas [3]);
        System.out.println( "Edad: "+caracteristicas [4]);
    }
}
