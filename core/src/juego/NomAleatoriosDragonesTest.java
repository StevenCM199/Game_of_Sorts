package juego;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NomAleatoriosDragonesTest {

    int cantidad;
    String[] nomAleatorioDragones;



    @Before
    public void setUp() throws Exception {
        cantidad = 3;
        nomAleatorioDragones = new String[cantidad];

    }

    @Test
    public void generarNombreAleatorio() {
        String[] nombres = {"Zu", "Draco", "Quijote"};

        String[] apellidos = {"Zuzunaga", "Sorni", "Garza"};

        for (int i = 0; i < cantidad; i++) {
            nomAleatorioDragones[i] = nombres[(int) (Math.floor(Math.random() * ((nombres.length - 1) - 0 + 1) + 0))] + " " + apellidos[(int) (Math.floor(Math.random() * ((apellidos.length - 1) - 0 + 1) + 0))];
            assertTrue(nombres[i] == "Zu" || nombres[i] == "Draco" || nombres[i] == "Quijote");
            assertTrue(apellidos[i] == "Zuzunaga" || apellidos[i] == "Sorni" || apellidos[i] == "Garza");
        }



    }


}