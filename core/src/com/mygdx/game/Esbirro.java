package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


//Clase de los dragones normales
public class Esbirro extends Objeto{

    String nombre = "Enemigo";
    int velocidadRecarga = 2;
    int edad = 40;
    int hitPoints = 1;
    String padre = "PadreDragon";
    public static final int velocidad = 2;

    public Esbirro(Texture textura, float x, float y) {
        super(textura, x, y);
    }

    public String getNombre() {
        return nombre;
    }

    public int getVelocidadRecarga() {
        return velocidadRecarga;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getEdad() {
        return edad;
    }

    public String getPadre() {
        return padre;
    }

    public boolean quitar = false;


    public void update(){
        sprite.translateX(-velocidad);
        if (sprite.getX() < 100)
            quitar = true;
    }

    public void dibujar(SpriteBatch batch) {
        sprite.setSize(30,30);
        sprite.draw(batch);

    }

}
