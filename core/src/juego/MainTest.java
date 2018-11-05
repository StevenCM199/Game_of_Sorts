package juego;

import com.badlogic.gdx.ApplicationAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class MainTest extends ApplicationAdapter {

    public int TestListaED[] = {4,3,6,2,1,5};
    public float TestListaVEL[] = {1.2f, 3.2f, 2.6f, 2.4f, 3.1f};
    public static int[] TestListaED2 = {8,5,4,7,3,1,9,2,6};


    @Before
    public void setUp() throws Exception {
    }

    //Inicializa LibGDX
    @Override
    public void create () {}

    @Override
    public void render(){
        Main.jugador.mover();
    }

    //Prueba Carga de texturas
    @Test
    public void testcargarTexturas(){
        assertNull(Main.texturaJugador);

    }

    //Prueba Carga de objetos
    @Test
    public void testcargarObjetos(){
        assertNull(Main.jugador);
    }

    //Prueba Selection Sort
    @Test
    public void testSelectionSort() {
        for (int i = 0; i < TestListaED.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < TestListaED.length; j++) {
                if (TestListaED[j] < TestListaED[index]) {
                    index = j;
                }
            }
            int smallerNumber = TestListaED[index];
            TestListaED[index] = TestListaED[i];
            TestListaED[i] = smallerNumber;
        }
        System.out.println("Prueba SelectionSort con ints");
        for (int i : TestListaED) {
            System.out.print(i + " ");
        }
        System.out.println(" ");
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6},new int[]{1,2,3,4,5,6});
    }

    //Prueba Insertion Sort
    @Test
    public void testInsertionSort(){
        float temp;
        for (int i = 1; i < TestListaVEL.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(TestListaVEL[j - 1] > TestListaVEL[j]){
                    temp = TestListaVEL[j];
                    TestListaVEL[j] = TestListaVEL[j-1];
                    TestListaVEL[j-1] = temp;
                }
            }
        }
        System.out.println("Prueba InsertionSort con floats");
        for (float i : TestListaVEL) {
            System.out.print(i + " ");
        }
        System.out.println(" ");
        assertArrayEquals(new float[]{1.2f, 2.4f, 2.6f,3.1f,3.2f}, new float[]{1.2f, 2.4f, 2.6f,3.1f,3.2f}, 0.1f);
    }

    //Prueba Quick Sort
    @Test
    public void testQuickSort(){
        int n = TestListaED2.length;
        quickSortAux(TestListaED2,0, n-1 );

        System.out.println("Prueba QuickSort con ints");
        for (int e : TestListaED2) {
            System.out.print(e + " ");
        }
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6,7,8,9},new int[]{1,2,3,4,5,6,7,8,9});
    }

    private static void quickSortAux(int[] lista, int low, int high){
        if (low < high) {
            int pi = partition( TestListaED2, low, high );

            //Recursividad de la particion
            quickSortAux( TestListaED2, low, pi - 1 );
            quickSortAux( TestListaED2, pi + 1, high );
        }

    }
    static int partition( int[] lista, int low, int high) {
        int pivot = TestListaED2[high];
        int i = (low-1); // indice del elemento mas pequeno
        for (int j=low; j<high; j++)
        {
            // compara el elemento menor(j) con el pivot
            if (TestListaED2[j] <= pivot)
            {
                i++;

                // Cambio de posiciones
                int temp = TestListaED2[i];
                TestListaED2[i] = TestListaED2[j];
                TestListaED2[j] = temp;
            }
        }
        // Cambio de posiciones
        int temp = TestListaED2[i + 1];
        TestListaED2[i + 1] = TestListaED2[high];
        TestListaED2[high] = temp;

        return i+1;
    }

    @Test
    public void testTreeAVL() {

        AVLTree treeAVL = new AVLTree();

        for (int i=0; i < TestListaED2.length;i++) {
            treeAVL.root = treeAVL.insert( treeAVL.root, TestListaED2[i]);
        }

        LinkedList lista = new LinkedList(  );
        treeAVL.preOrder( treeAVL.root,lista );

        System.out.print("Preorder traversal of constructed tree is: ");
        treeAVL.preOrderTree(treeAVL.root);

        System.out.print( "Edades antes de acomodarse: " );
        for (int i=0; i< TestListaED2.length; i++){
            System.out.print( TestListaED2[i] +" ");
        }

        for (int i=0; i <= 8; i++){
            for (int j=0; j< TestListaED2.length; j++) {
                int eD = (Integer) lista.get(i);
                if (eD==TestListaED2[j]){
                    int x = TestListaED2[i];
                    TestListaED2[i] = TestListaED2[j];
                    TestListaED2[j] = x;
                }
            }
            int j=0;
        }

        System.out.print( "Edades despues de acomodarse: " );
        for (int i=0; i<TestListaED2.length; i++){
            System.out.print( TestListaED2[i] +" ");
        }
    }






    //Limpieza de variables
    @After
    public void tearDown() throws Exception {
        TestListaED = null;
        TestListaVEL = null;
        TestListaED2 = null;
    }
}