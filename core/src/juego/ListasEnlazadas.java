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

    /*public Object Obt(ListasEnlazadas list, int index1,int index2){
        ListasEnlazadas listaF = new ListasEnlazadas();
        listaF.addPrimera(list.obtener( index1 ));
        return listaF.obtener( index2 );

    }*/

    public Object obtenerDatoDragon(CaracteristicasDelDragon Dragon, int index){
        if (index==0){return Dragon.getNombreD();}
        if (index==1){return Dragon.getClaseD(); }
        if (index==2){return Dragon.getVelocidadD();}
        if (index==3){return Dragon.getEdadD();}
        if (index==4){return Dragon.getResistenciaD();}

        return null;
    }

    public int size(){return size;}

    public boolean estaVacia(){ return ((cabeza==null)?true:false); }
}