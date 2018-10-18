package juego;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import tools.Colisiones;

public class Bala extends Objeto{

    public Bala(Texture textura, float x, float y) {
        super(textura, x, y);
        this.rect = new Colisiones(x,y,30,30);
    }

    float x = sprite.getX();
    float y = sprite.getY();
    Rectangle recta = sprite.getBoundingRectangle();

    public static final int velocidad = 7;
    public boolean quitar = false;
    Colisiones rect;


    public void update(){
        sprite.translateX(velocidad);
        recta.x += velocidad;
        rect.mover(x,y);
        if (sprite.getX() > 650)
            quitar = true;
    }

    public void dibujar(SpriteBatch batch) {
        sprite.setSize(30,30);
        sprite.draw(batch);
        recta.setSize(30,30);
    }

    public Colisiones getColisiones(){
        return rect;
    }


}
