package juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Jugador extends Objeto {

    private int x;
    private int y;

    private int velocidad = 7;

    public Jugador(Texture textura, int x, int y) {
        super(textura, x, y);
    }

    //Se llama desde el metodo render
    public void dibujar(SpriteBatch batch) {
        sprite.draw(batch);
        sprite.setSize(70,70);

    }

    public void mover(){

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            sprite.translateX(-velocidad);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            sprite.translateX(velocidad);

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            sprite.translateY(velocidad);

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            sprite.translateY(-velocidad);

        if (sprite.getX() < 0)
            sprite.setX(1);

        if (sprite.getX() > 1200)
            sprite.setX(1199);

        if (sprite.getY() < 0)
            sprite.setY(1);

        if (sprite.getY() > 850)
            sprite.setY(849);

    }

    private void setX(int x){
        this.x = x;
    }

    float getX() {
        return sprite.getX();
    }

    private void setY(int y) {
        this.y = y;
    }

    float getY() {
        return sprite.getY();
    }

    float getWidth(){
        return sprite.getWidth();
    }
    float getHeight(){
        return sprite.getHeight();
    }
}
