package juego.desktop;

import juego.*;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

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

		ResistenciaDeDragon resistencia = new ResistenciaDeDragon();
		resistencia.resistenciaDeDragon();

	}
}
