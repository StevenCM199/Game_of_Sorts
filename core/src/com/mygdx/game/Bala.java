package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bala extends Objeto{

    public Bala(Texture textura, float x, float y) {
        super(textura, x, y);
    }

    public static final int velocidad = 7;
    public boolean quitar = false;

    public void update(){
        sprite.translateX(velocidad);
        if (sprite.getX() > 650)
            quitar = true;
    }

    public void dibujar(SpriteBatch batch) {
        sprite.setSize(30,30);
        sprite.draw(batch);
    }

}
