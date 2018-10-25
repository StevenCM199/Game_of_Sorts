package juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.scenes.scene2d.InputEvent.Type.touchDown;


//Clase de los dragones normales
public class Esbirro extends Objeto{

    CaracteristicasDelDragon Dragon = new CaracteristicasDelDragon();

    String nombre = Dragon.nombreD;
    String clase = Dragon.claseD;
    int velocidadRecarga = Dragon.velocidadD;
    int edad = Dragon.edadD;
    int hitPoints = Dragon.resistenciaD;
    String padre = "PadreDragon";
    public static final int velocidad = 1;


    float x = sprite.getX();
    float y = sprite.getY();

    float ancho = sprite.getWidth();
    float alto = sprite.getHeight();

    public Rectangle recta = new Rectangle(x,y,ancho,alto);


    public Esbirro(Texture textura, float x, float y) {
        super(textura, x, y);
    }



    public String getNombre() {
        if (nombre == null){
            nombre = "name";
        }
        return nombre;
    }

    public int getVelocidadRecarga() {
        return velocidadRecarga;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getEdad() {
        return this.edad;
    }

    public String getPadre() {
        return padre;
    }

    public String getClase() {
        return clase;
    }

    public boolean quitar = false;


    public void update(){
        sprite.translateX(-velocidad);

        recta.x -= velocidad;
        if (sprite.getX() < 100)
            quitar = true;

    }

    public void updateXY(int x, int y){
        sprite.translateX(x);
        sprite.translateY( y );
    }

    public void dibujar(SpriteBatch batch) {
        recta.setSize(sprite.getWidth(),sprite.getHeight());
        sprite.setSize(50,50);
        sprite.draw(batch);

    }

    public void mostrar(){
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() < sprite.getX() + sprite.getWidth() && Gdx.input.getX() > sprite.getX() && 900 - Gdx.input.getY() < sprite.getY() + sprite.getHeight() && 900 - Gdx.input.getY() > sprite.getY()) {

                System.out.println("-------------Caracteriticas del dragon-------------");
                System.out.println("Nombre: " + getNombre());
                System.out.println("Edad: " + getEdad());
                System.out.println("Velocidad: " + getVelocidadRecarga());
                System.out.println("Vida: " + getHitPoints());
                System.out.println("Padre: " + getPadre());
                System.out.println("Clase: " + getClase());
            }
        }
    }




}