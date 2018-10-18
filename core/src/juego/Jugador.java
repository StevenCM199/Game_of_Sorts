package juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Jugador extends Objeto {

    private int x;
    private int y;

    public Jugador(Texture textura, int x, int y) {
        super(textura, x, y);
    }



    //Se llama desde el metodo render
    public void dibujar(SpriteBatch batch) {
        sprite.draw(batch);
        sprite.setSize(50,50);

    }

    public void mover(){

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            sprite.translateX(-1);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            sprite.translateX(1);

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            sprite.translateY(1);

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            sprite.translateY(-1);

        if (sprite.getX() < 0)
            sprite.setX(1);

        if (sprite.getX() > 600)
            sprite.setX(599);

        if (sprite.getY() < 0)
            sprite.setY(1);

        if (sprite.getY() > 425)
            sprite.setY(424);

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
}
