package juego;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import tools.Fondo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Main extends ApplicationAdapter {

    //Para elegir con cual Ordenamiento se hace los dragones
    int CantidadDeColisiones = 0;

	private float deltaTime;

	SpriteBatch batch;

	InputListener input;

	//Inicializar Sprites
	public Jugador jugador;
	private Texture texturaJugador;

	private ArrayList<Bala> balas;
	private Texture texturaBala;

	private ArrayList<Esbirro> esbirros;
	private Texture texturaEsbirro;
	private Esbirro esbirro;


	Fondo sky, rocks1, rocks2, clouds1, clouds2, clouds3, clouds4;
	Fondo rocks1DUP, rocks2DUP, clouds1DUP,clouds2DUP,clouds3DUP,clouds4DUP;
	Texture texturaSky, texturaRocks1, texturaRocks2, texturaClouds1, texturaClouds2, texturaClouds3, texturaClouds4;

	float esbirroSpawnTimer;
	Random random;

	ShapeRenderer shape;
	BitmapFont font, font1;


	String ENnombre;


	private boolean isPaused;

	//Cargar texturas de los objetos
	private void cargarTexturas(){
		texturaJugador = new Texture("jugador.png");
		texturaBala = new Texture("bala.png");
		texturaEsbirro = new Texture("enemy.png");
		texturaSky = new Texture("sky.png");
		texturaRocks1 = new Texture("rocks_1.png");
		texturaRocks2 = new Texture("rocks_2.png");
		texturaClouds1	 = new Texture("clouds_1.png");
		texturaClouds2 = new Texture("clouds_2.png");
		texturaClouds3 = new Texture("clouds_3.png");
		texturaClouds4 = new Texture("clouds_4.png");
	}

	//Inicializar objetos
	@Override
	public void create () {
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		input = new InputListener();
		font = new BitmapFont(Gdx.files.internal("RockwellBlack.fnt"));
		font1 = new BitmapFont(Gdx.files.internal("RockwellBlack24.fnt"));

		cargarTexturas();
		cargarObjetos();
	}

	//Precargar objetos
	private void cargarObjetos() {
		jugador = new Jugador(texturaJugador,30,300);
		balas = new ArrayList<Bala>();
		esbirros = new ArrayList<Esbirro>();
		random = new Random();
		sky = new Fondo(texturaSky,0,0);

		rocks1 = new Fondo(texturaRocks1,0,0);
		rocks1DUP = new Fondo(texturaRocks1,1920,0);

		rocks2 = new Fondo(texturaRocks2,0,0);
		rocks2DUP = new Fondo(texturaRocks2,1920,0);

		clouds1 = new Fondo(texturaClouds1,0,0);
		clouds1DUP = new Fondo(texturaClouds1,1920,0);

		clouds2 = new Fondo(texturaClouds2,0,0);
		clouds2DUP = new Fondo(texturaClouds2,1920,0);

		clouds3 = new Fondo(texturaClouds3,0,0);
		clouds3DUP = new Fondo(texturaClouds3,1920,0);

		clouds4 = new Fondo(texturaClouds4,0,0);
		clouds4DUP = new Fondo(texturaClouds4,1920,0);

	}

	//Acomodo por edad de cada dragon SELECTION SORT
    private static ArrayList<Esbirro> SelectionSort(ArrayList<Esbirro> esbirros){

        for (int i = 0; i < esbirros.size() - 1; i++){
            int index = i;
            for (int j = i + 1; j < esbirros.size(); j++)
                if (esbirros.get( j ).edad < esbirros.get( index ).edad)
                    index = j;
            Esbirro smallerNumber = esbirros.get( index );
            esbirros.set( index,esbirros.get( i ) );
            esbirros.set( i,smallerNumber );
        }
        return esbirros;
    }

    //Acomodo de Dragones por velocidad INSERTION SORT
    private static ArrayList<Esbirro> InsertionSort(ArrayList<Esbirro> esbirros){
        Esbirro temp;
        for (int i = 1; i < esbirros.size(); i++) {
            for(int j = i ; j > 0 ; j--){
                if(esbirros.get( j-1 ).velocidadRecarga > esbirros.get( j ).velocidadRecarga){
                    temp = esbirros.get( j );
                    esbirros.set( j, esbirros.get( j-1 ));
                    esbirros.set( j-1,temp );
                }
            }

        }
        return esbirros;
    }

    //Acomodo de los Dragones por edad con QUICK SORT
	private static void QuickSort(ArrayList<Esbirro> esbirros){
		int n = esbirros.size();
		quickSortAux( esbirros, 0, n-1 );}

	// Auxiliar para tener un mayor y menor
	private static void quickSortAux (ArrayList<Esbirro> esbirros, int low, int high) {
		if (low < high) {
			int pi = partition( esbirros, low, high );

			//Recursividad de la particion
			quickSortAux( esbirros, low, pi - 1 );
			quickSortAux( esbirros, pi + 1, high );
		}
	}

	static int partition(ArrayList<Esbirro> esbirros, int low, int high) {
		int pivot = esbirros.get( high ).edad;
		int i = (low-1); // indice del elemento mas pequeno
		for (int j=low; j<high; j++)
		{
			// compara el elemento menor(j) con el pivot
			if (esbirros.get( j ).edad <= pivot)
			{
				i++;

				// Cambio de posiciones
				int temp = esbirros.get( i ).edad;
				esbirros.get( i ).edad = esbirros.get( j ).edad;
				esbirros.get( j ).edad = temp;
			}
		}
		// Cambio de posiciones
		int temp = esbirros.get( i+1 ).edad;
		esbirros.get( i+1 ).edad = esbirros.get( high ).edad;
		esbirros.get( high ).edad = temp;

		return i+1;
	}

	//Funcion para imprimir el array de Esbirros
	static void printEsbirro(ArrayList<Esbirro> esbirros)
	{
		int n = esbirros.size();
		for (int i=0; i<n; ++i)
			System.out.print(esbirros.get( i ).edad+" ");
		System.out.println();
	}

	//Para acomodar los Dragones en un Arbol AVL por edades
    private static void TreeAVL(ArrayList<Esbirro> esbirros) {

        AVLTree treeAVL = new AVLTree();

        for (int i=0; i<esbirros.size();i++) {
            treeAVL.root = treeAVL.insert( treeAVL.root, esbirros.get( i ).edad);
        }

        /*//Lista de las edades
        LinkedList ListaNodos = null;
        ListaNodos.add(treeAVL.preOrder( treeAVL.root ));*/

        /*for (int i=0; i<ListaNodos.size(); i++){
            System.out.print( ListaNodos.get( i ) +" ");
        }*/

        /*for (int i=0; i<lista1.length; i++){

            for (int j=0; j<lista2.length; j++) {
                if (lista1[i]==lista2[j]){
                    int x = lista2[i];
                    lista2[i]=lista1[i];
                    lista2[j]=x;
                }
            }
            int j=0;
        }*/

    }


	//Loop del juego
	@Override
	public void render () {
		deltaTime = Gdx.graphics.getDeltaTime();

		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			isPaused = true;
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) && isPaused == true)  {
			isPaused = false;
		}

		if (isPaused == false) {




			//Si se presiona espacio el dragon dispara una bola de fuego
			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				balas.add(new Bala(texturaBala, jugador.getX(), jugador.getY()));
			}

			//Spawn de los esbirros
			esbirroSpawnTimer += 1 * deltaTime;
			if (esbirroSpawnTimer > 3) {
				esbirros.add(new Esbirro(texturaEsbirro, 1150, random.nextInt(860 - 60) + 60));

				/*esbirros.add(new Esbirro(texturaEsbirro, 1150, random.nextInt(860 - 60) + 60));
				esbirros.add(new Esbirro(texturaEsbirro, 1150, random.nextInt(860 - 60) + 60));
				esbirros.add(new Esbirro(texturaEsbirro, 1150, random.nextInt(860 - 60) + 60));
				esbirros.add(new Esbirro(texturaEsbirro, 1150, random.nextInt(860 - 60) + 60));
				esbirros.add(new Esbirro(texturaEsbirro, 1200, random.nextInt(860 - 60) + 60));
				esbirros.add(new Esbirro(texturaEsbirro, 1200, random.nextInt(860 - 60) + 60));
				esbirros.add(new Esbirro(texturaEsbirro, 1200, random.nextInt(860 - 60) + 60));
				esbirros.add(new Esbirro(texturaEsbirro, 1200, random.nextInt(860 - 60) + 60));
				esbirros.add(new Esbirro(texturaEsbirro, 1200, random.nextInt(860 - 60) + 60));*/
				esbirroSpawnTimer = 0;
			}

			//Movimiento de las bolas de fuego
			ArrayList<Bala> balasParaQuitar = new ArrayList<Bala>();

			for (Bala bala : balas) {
				bala.update();
				if (bala.quitar)
					balasParaQuitar.add(bala);
			}

			//Movimiento de los esbirros
			ArrayList<Esbirro> esbirrosParaQuitar = new ArrayList<Esbirro>();
			for (Esbirro esbirro : esbirros) {
				esbirro.update();
				if (esbirro.quitar)
					esbirrosParaQuitar.add(esbirro);

			}

			//Movimiento del jugador
			jugador.mover();

			rocks1.mover(-0.5f);
			rocks1DUP.mover(-0.5f);

			rocks2.mover(-1);
			rocks2DUP.mover(-1);

			clouds1.mover(-2.8f);
			clouds1DUP.mover(-2.8f);

			clouds2.mover(-1.9f);
			clouds2DUP.mover(-1.9f);

			clouds3.mover(-1.2f);
			clouds3DUP.mover(-1.2f);

			clouds4.mover(-1.3f);
			clouds4DUP.mover(-1.3f);

			//Colisiones
			for (Bala bala : balas) {
				for (Esbirro esbirro : esbirros) {
					if (bala.recta.overlaps(esbirro.recta)) {
						balasParaQuitar.add(bala);
						esbirrosParaQuitar.add(esbirro);

						//Aqui se seleciona depende de la vez que se haya chochado
						/*System.out.println(esbirros);

						SelectionSort(esbirros);
						System.out.println("Selection" + esbirros);

						InsertionSort(esbirros);
						System.out.println("Insertion" + esbirros);

						printEsbirro( esbirros );
						QuickSort( esbirros );
						printEsbirro( esbirros );*/

						TreeAVL( esbirros );

                    /*if (CantidadDeColisiones==9){ CantidadDeColisiones++;InsertionSort( esbirros );
                        System.out.println( "Insertion"+esbirros );}*/
					}
				}
			}
			balas.removeAll(balasParaQuitar);
			esbirros.removeAll(esbirrosParaQuitar);
		}

		//Color de fondo
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);




		//Visualizacion de sprites en la pantalla de juego
		//batch.enableBlending();
		batch.begin();


		sky.dibujar(batch);
		rocks1.dibujar(batch);
		rocks1DUP.dibujar(batch);

		rocks2.dibujar(batch);
		rocks2DUP.dibujar(batch);

		clouds1.dibujar(batch);
		clouds1DUP.dibujar(batch);

		clouds2.dibujar(batch);
		clouds2DUP.dibujar(batch);

		clouds3.dibujar(batch);
		clouds3DUP.dibujar(batch);

		clouds4.dibujar(batch);
		clouds4DUP.dibujar(batch);

		jugador.dibujar(batch);

		for (Bala bala : balas)
			bala.dibujar(batch);

		for (Esbirro esbirro : esbirros) {
            esbirro.dibujar(batch);
            if (Gdx.input.isTouched())
                esbirro.mostrar();
        }

        if (isPaused == true){
            font.draw(batch, "Pausa, presiona BackSpace para continuar", 300, 500);
        }

		batch.end();

	}
	@Override
	public void dispose () {
		batch.dispose();
		shape.dispose();
		font.dispose();
	}
}
