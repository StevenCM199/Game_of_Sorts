package juego;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClaseDeDragonTest {

    int cantidad;

    @Before
    public void setUp() throws Exception {
        cantidad = 3;
    }

    @Test
    public void claseDeDragon() {
        String[] claseAleatoria = new String[cantidad];
        String[] tipos = {"Comandante", "Capitan", "Infanteria"};

        for (int i = 0; i < cantidad; i++) {
            claseAleatoria[i] = tipos[(int) (Math.floor(Math.random() * ((tipos.length - 1) - 0 + 1) + 0))];
            assertTrue(claseAleatoria[i] == "Comandante" || claseAleatoria[i] == "Capitan" || claseAleatoria[i] == "Infanteria");
        }

    }

    @After
    public void tearDown() throws Exception {
        cantidad = 0;
    }
}