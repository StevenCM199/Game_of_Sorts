package juego;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class CaracteristicasDelDragonTest {

    Random numero = new Random();
    int edadD;
    float velocidadD;
    int resistenciaD;

    @Before
    public void setUp() throws Exception {
        edadD = (numero.nextInt(100)+1);
        velocidadD = (numero.nextInt(4)+1);
        resistenciaD = (numero.nextInt(2)+1);
    }

    @Test
    public void getEdadD() {
        assertNotNull(edadD);
        assertTrue(edadD >= 1 && edadD <= 100);
    }

    @Test
    public void getResistenciaD() {
        assertNotNull(resistenciaD);
        assertTrue(resistenciaD >= 1 && resistenciaD <= 2);
    }

    @Test
    public void getVelocidadD() {
        assertNotNull(velocidadD);
        assertTrue(velocidadD >= 1 && velocidadD <= 4);
    }


    @After
    public void tearDown() throws Exception {
        edadD = 0;
        velocidadD = 0;
        resistenciaD = 0;
    }
}