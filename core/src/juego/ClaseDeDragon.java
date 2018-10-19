package juego;

public class ClaseDeDragon {

    public static String[] claseDeDragon(int cantidad) {
        String[] claseAleatoria = new String[cantidad];
        String[] tipos = {"Comandante", "Capitan", "Infanteria"};

        for (int i = 0; i < cantidad; i++) {
            claseAleatoria[i] = tipos[(int) (Math.floor(Math.random() * ((tipos.length - 1) - 0 + 1) + 0))];
        }
        return claseAleatoria;
    }

    public static void claseAle() {
        String [] claseD = claseDeDragon(1);
        System.out.println("Clase de Dragon: "+claseD[0]);
    }
}