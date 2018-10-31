package juego;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Bala extends Objeto{

    public Bala(Texture textura, float x, float y) {
        super(textura, x, y);

    }

    float x = sprite.getX();
    float y = sprite.getY();
    float ancho = sprite.getWidth();
    float alto = sprite.getHeight();

    public Rectangle recta = new Rectangle(x,y,ancho,alto);

    public static final int velocidad = 7;
    public boolean quitar = false;



    public void update(){
        sprite.translateX(velocidad);
        recta.x += velocidad;

        if (sprite.getX() > 1250)
            quitar = true;
    }

    public void updateEnemy(){
        sprite.translateX(-velocidad);
        recta.x -= velocidad;

        if (sprite.getX() < 0)
            quitar = true;
    }


    public void dibujar(SpriteBatch batch) {
        sprite.setSize(42,40);
        recta.setSize(sprite.getWidth(),sprite.getHeight());
        sprite.draw(batch);

    }




}
