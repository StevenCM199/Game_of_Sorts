package juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Jugador extends Objeto {

    float x = sprite.getX();
    float y = sprite.getY();
    float ancho = sprite.getWidth();
    float alto = sprite.getHeight();

    private float deltaTime;

    private float HPtimer;

    int hitPoints = 10;

    private int velocidad = 7;

    public Jugador(Texture textura, int x, int y) {
        super(textura, x, y);
    }

    public Rectangle recta = new Rectangle(x,y,ancho,alto);




    //Se llama desde el metodo render
    public void dibujar(SpriteBatch batch) {
        recta.setSize(70,70);
        sprite.setSize(70,70);
        sprite.draw(batch);

    }

    public void mover(){

        deltaTime = Gdx.graphics.getDeltaTime();
        if(hitPoints < 10) {
            HPtimer += 1 * deltaTime;
        }
        if(HPtimer > 10){
            hitPoints += 1;
            HPtimer = 0;
        }


        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            sprite.translateX(-velocidad);
            recta.x -= velocidad;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            sprite.translateX(velocidad);
            recta.x += velocidad;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            sprite.translateY(velocidad);
            recta.y += velocidad;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            sprite.translateY(-velocidad);
            recta.y -= velocidad;
        }
        if (sprite.getX() < 0) {
            sprite.setX(1);
            recta.setX(1);
        }
        if (sprite.getX() > 1200) {
            sprite.setX(1199);
            recta.setX(1199);
        }
        if (sprite.getY() < 0) {
            sprite.setY(1);
            recta.setY(1);
        }
        if (sprite.getY() > 850) {
            sprite.setY(849);
            recta.setY(849);
        }
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
