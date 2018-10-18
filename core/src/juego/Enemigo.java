package juego;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemigo{

    protected Sprite sprite;

    protected String nombre;

    protected int velocidadRecarga;

    protected int edad;

    protected int hitPoints;

    protected String padre;

    public Enemigo(Texture textura, float x, float y) {
       // super(textura, x, y);
    }
}