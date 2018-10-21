package juego;

import java.util.Random;

public class CaracteristicasDelDragon {

    Random numero = new Random();
    int velocidadD = (numero.nextInt(1000)+1); //Velocidad del Dragon
    int edadD = (numero.nextInt(100)+1); //Edad del Dragon
    int resistenciaD = (numero.nextInt(3)+1); //Resistencia del Dragon
    //Nombre del Dragon
    NomAleatoriosDragones nombre = new NomAleatoriosDragones();
    String[] nom = nombre.generarNombreAleatorio(1);
    String nombreD = new String(nom[0]);
    //Clase del Dragon
    ClaseDeDragon clase = new ClaseDeDragon();
    String[] cla = clase.claseDeDragon(1);
    String claseD = new String(cla[0]);

    ListasEnlazadas Dragon = new ListasEnlazadas();

    public CaracteristicasDelDragon() {

        Dragon.addPrimera( resistenciaD );
        Dragon.addPrimera( edadD );
        Dragon.addPrimera( velocidadD );
        Dragon.addPrimera( claseD );
        Dragon.addPrimera( nombreD );

        //System.out.println(nombreD+" "+claseD+" "+velocidadD+" "+edadD+" "+" "+resistenciaD);
    }

    public int getEdadD() { return edadD;}
    public int getResistenciaD() { return resistenciaD;}
    public int getVelocidadD() { return velocidadD; }
    public String getNombreD(){ return nombreD;}
    public String getClaseD(){ return claseD;}

}
