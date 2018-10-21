package juego.desktop;

import juego.*;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Main(), config);

		config.height = 900;
		config.width = 1200;

		ListasEnlazadas listaDragones = new ListasEnlazadas();

        for (int cant=0;cant<6;cant++) {

            for (int pos = 0; pos < 5; pos++) {
                CaracteristicasDelDragon Dragon = new CaracteristicasDelDragon();
                listaDragones.addPrimera( Dragon );
                System.out.print( listaDragones.obtenerDatoDragon( (Dragon), pos ) + " " );
            }
            System.out.println("");
        }
    }

}
