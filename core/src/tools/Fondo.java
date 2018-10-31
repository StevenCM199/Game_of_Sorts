package tools;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import juego.Objeto;

public class Fondo extends Objeto {

    float x = sprite.getX();
    float y = sprite.getY();



    public Fondo(Texture textura, float x, float y) {
        super(textura, x, y);

    }

    public void dibujar(SpriteBatch batch) {
        sprite.draw(batch);

    }
    public void mover(float x) {
        sprite.translateX(x);
        if(sprite.getX() + 1920 <= 0){
            sprite.setX(1920);
        }
    }

    private void setX(int x){
        this.x = x;
    }

    public float getX() {
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