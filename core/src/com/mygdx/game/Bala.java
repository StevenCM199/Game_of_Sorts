package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bala extends Objeto{

    public Bala(Texture textura, float x, float y) {
        super(textura, x, y);
    }

    float x, y;
    public static final int velocidad = 7;
    private static Texture textura;
    public boolean quitar = false;


    private void cargarTextura() {
        textura = new Texture("bala.png");
    }


    public void update(){
        sprite.translateX(velocidad);
        if (sprite.getX() > 650)
            quitar = true;
    }

    public void dibujar(SpriteBatch batch) {
        cargarTextura();
        sprite.setSize(30,30);
        sprite.draw(batch);

    }

}
