package juego.desktop;

import juego.EdadDeDragon;
import juego.Main;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import juego.NomAleatoriosDragones;
import juego.VelocidadDeRecarga;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Main(), config);

		NomAleatoriosDragones nombre = new NomAleatoriosDragones();
		nombre.nomAleatorio();

		VelocidadDeRecarga velocidad = new VelocidadDeRecarga();
		velocidad.velocidadDeRecarga();

		EdadDeDragon edad = new EdadDeDragon();
		edad.edadDeDragon();

	}
}
