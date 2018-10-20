package juego;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Bala extends Objeto{

    public Bala(Texture textura, float x, float y) {
        super(textura, x, y);

    }

    float x = sprite.getX();
    float y = sprite.getY();
    float ancho = sprite.getWidth();
    float alto = sprite.getHeight();

    public Rectangle recta = new Rectangle(x,y,30,30);



   // Rectangle recta = sprite.getBoundingRectangle();


    public static final int velocidad = 7;
    public boolean quitar = false;



    public void update(){
        sprite.translateX(velocidad);
        recta.x += velocidad;
      //  System.out.println(recta.getX());
       // System.out.print(recta.getY());

        System.out.println(recta.getSize(Vector2.Y));

        if (sprite.getX() > 650)
            quitar = true;
    }

    public void dibujar(SpriteBatch batch) {

        sprite.setSize(32,30);
        recta.setSize(sprite.getWidth(),sprite.getHeight());
        sprite.draw(batch);
        //recta = new Rectangle(x,y,ancho,alto);


        //recta.setSize(1,1);
    }




}
