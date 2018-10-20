package juego;

public class ListasEnlazadas {

    Nodo cabeza;
    int size;

    public ListasEnlazadas(){
        cabeza=null;
        size = 0;
    }

    public void addPrimera(Object obj){
        if (cabeza==null){
            cabeza=new Nodo( obj );
        }
        else{
            Nodo temp = cabeza;
            Nodo nuevo = new Nodo(obj);
            nuevo.enlazarSiguiente(temp);
            cabeza = nuevo;
        }
    }

    public Object obtener(int index){
        int contador = 0;
        Nodo temporal = cabeza;
        while (contador < index){
            temporal = temporal.obtenerSiguiente();
            contador++;
        }
        return temporal.obtenerValor();
    }

    public int size(){return size;}

    public boolean estaVacia(){ return ((cabeza==null)?true:false); }


}
