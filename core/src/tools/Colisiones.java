package tools;

public class Colisiones {
    float x, y;
    int ancho, alto;

    public Colisiones(float x, float y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void mover (float x, float y){
        this.x = x;
        this.y = y;

    }

    public boolean chocaCon(Colisiones rect){
        return x < rect.x + rect.ancho && y < rect.y + rect.alto && x + ancho > rect.x && y + alto > rect.alto;

    }

}
