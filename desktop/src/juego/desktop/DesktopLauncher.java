package juego.desktop;

import juego.Main;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import juego.VelocidadDeRecarga;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Main(), config);

		VelocidadDeRecarga velocidad = new VelocidadDeRecarga();
		velocidad.velocidadDeRecarga();

	}
}
