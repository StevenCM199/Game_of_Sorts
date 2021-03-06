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
	public static Jugador jugador;
	public static Texture texturaJugador;

	//Variables globales
	private ArrayList<Bala> balas;
	private ArrayList<Bala> balasEnemigas;
	private Texture texturaBala, texturaBalaEnemy;
	public static ArrayList<Esbirro> esbirros;
	public static ArrayList<Esbirro> TESTesbirros;
	public static Texture texturaEsbirro, texturaEsbirro2, texturaEsbirro3;
	private int killedDragons = 0;
	private int Nronda = 1;
	Fondo sky, rocks1, rocks2, clouds1, clouds2, clouds3, clouds4;
	Fondo rocks1DUP, rocks2DUP, clouds1DUP,clouds2DUP,clouds3DUP,clouds4DUP;
	Texture texturaSky, texturaRocks1, texturaRocks2, texturaClouds1, texturaClouds2, texturaClouds3, texturaClouds4;
	float esbirroSpawnTimer;
    float RondaTimer;
	Random random;
	ShapeRenderer shape;
	BitmapFont font, font1;
	int timeFrame = 10;
	boolean gameOver;
	boolean PasaRonda = true;
	private boolean isPaused;

	//Cargar texturas de los objetos
	void cargarTexturas(){
		texturaJugador = new Texture("jugador.png");
		texturaBala = new Texture("bala.png");
		texturaBalaEnemy = new Texture ("balaEnemiga.png");
		texturaEsbirro = new Texture("enemy.png");
        texturaEsbirro2 = new Texture("enemy2.png");
		texturaEsbirro3 = new Texture("enemy3.png");
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
		jugador = new Jugador(texturaJugador,30,360);
		balas = new ArrayList<Bala>();
		balasEnemigas = new ArrayList<Bala>();
		esbirros = new ArrayList<Esbirro>();
		TESTesbirros = new ArrayList<Esbirro>();
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
    public static ArrayList<Esbirro> SelectionSort(ArrayList<Esbirro> esbirros){

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
    public static ArrayList<Esbirro> InsertionSort(ArrayList<Esbirro> esbirros){
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

        LinkedList lista = new LinkedList(  );
        treeAVL.preOrder( treeAVL.root,lista );

        /*System.out.print("Preorder traversal of constructed tree is: ");
        treeAVL.preOrderTree(treeAVL.root);

        System.out.print( "Edades antes de acomodarse: " );
        for (int i=0; i<esbirros.size(); i++){
            System.out.print( esbirros.get( i ).edad +" ");
        }*/

        for (int i=0; i<lista.size(); i++){
            for (int j=0; j<esbirros.size(); j++) {
                int eD = (Integer) lista.get(i);
                if (eD==esbirros.get( j ).edad){
                    Esbirro x = esbirros.get( i );
                    esbirros.set( i, esbirros.get( j ));
                    esbirros.set( j,x );
                }
            }
            int j=0;
        }

        /*System.out.print( "Edades despues de acomodarse: " );
        for (int i=0; i<esbirros.size(); i++){
            System.out.print( esbirros.get( i ).edad +" ");
        }*/
    }
    //posicion inicial para cada esbirro

    private static void ReacomodoDeEsbirros (ArrayList<Esbirro> esbirros) {
        int yPosicion = 670;
        for (int i = 0; i < esbirros.size(); i++) {
            esbirros.get( i ).sprite.setY( yPosicion );
            esbirros.get( i ).recta.y=yPosicion;
            if (yPosicion > 120) {
                yPosicion -= 60;
            } else {
                yPosicion = 640;
            }
        }
    }

	//Loop del juego
	@Override
	public void render () {
		deltaTime = Gdx.graphics.getDeltaTime();

		//Salir del juego con la tecla escape
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}

		//Si se presiona Enter, pausa el juego
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			isPaused = true;
		}

		//Para reanudar la pausa, presionar backspace
		if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) && isPaused == true)  {
			isPaused = false;
		}

		if (isPaused == false) {
            RondaTimer +=1*deltaTime;

			//Si se presiona espacio el dragon dispara una bola de fuego
			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				balas.add(new Bala(texturaBala, jugador.getX(), jugador.getY()));
			}

			//Spawn de los esbirros
			esbirroSpawnTimer += 1 * deltaTime;
			if (esbirroSpawnTimer > timeFrame) {
				esbirros.add(new Esbirro(texturaEsbirro,1100,640 ));
				esbirros.add(new Esbirro(texturaEsbirro2, 1100, 580));
				esbirros.add(new Esbirro(texturaEsbirro3, 1100, 520));
				esbirros.add(new Esbirro(texturaEsbirro, 1100, 460));
				esbirros.add(new Esbirro(texturaEsbirro2, 1100, 400));
				esbirros.add(new Esbirro(texturaEsbirro3, 1100, 340));
				esbirros.add(new Esbirro(texturaEsbirro, 1100, 280));
				esbirros.add(new Esbirro(texturaEsbirro2, 1100, 220));
				esbirros.add(new Esbirro(texturaEsbirro3, 1100, 160));
				esbirros.add(new Esbirro(texturaEsbirro, 1100, 90));
				esbirroSpawnTimer = 0;
			}


            /*for ( int i=0; i<esbirros.size(); i++) {
                balas.add( new Bala( texturaBala, esbirros.get( i ).getposX(), esbirros.get( i ).getposY() ) );
            }*/

			//Movimiento de las bolas de fuego
			ArrayList<Bala> balasParaQuitar = new ArrayList<Bala>();

			//Eliminar las bolas de fuego al alcanzar el borde de la pantalla
			for (Bala bala : balas) {
				bala.update();
				if (bala.quitar)
					balasParaQuitar.add(bala);
			}
			for (Bala bala : balasEnemigas) {
				bala.updateEnemy();
				if (bala.quitar)
					balasParaQuitar.add(bala);
			}

			//Acciones de los enemigos
			ArrayList<Esbirro> esbirrosParaQuitar = new ArrayList<Esbirro>();
			//esbirroFireTimer -= deltaTime;
			for (Esbirro esbirro : esbirros) {
				esbirro.velocidadRecarga -= 1 * deltaTime;
				esbirro.update();

				//Disparar balas del enemigo
				if (esbirro.velocidadRecarga < 0){
					balasEnemigas.add(new Bala(texturaBalaEnemy,esbirro.getposX(), esbirro.getposY()));
					esbirro.velocidadRecarga = random.nextInt((4)+1);
				}

				//Si un enemigo alcanza el borde de la pantalla, restarle vida al jugador
				if (esbirro.quitar) {
					esbirrosParaQuitar.add(esbirro);
					jugador.hitPoints -= 1;
				}

				//Si el enemigo es un comandante, darle mas puntos de vida
				if (esbirro.clase.equals("Comandante")){
					esbirro.hitPoints =+ 5;
					esbirro.velocidadRecarga = 1;
					esbirro.sprite.setSize(100,100);
					esbirro.clase = "Comandante ";
				}
			}

			//Movimiento del jugador
			jugador.mover();

			//Movimiento del fondo
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
						esbirro.hitPoints -= 1;

						//Matar al enemigo si su vida llega a 0
						if (esbirro.hitPoints == 0) {
							esbirrosParaQuitar.add(esbirro);
							killedDragons += 1;
						}
						CantidadDeColisiones += 1;
						//Aqui se seleciona depende de la vez que se haya chochado
						if (CantidadDeColisiones==1){ SelectionSort(esbirros); }
						if (CantidadDeColisiones==2){ InsertionSort(esbirros);}
						if (CantidadDeColisiones==3){ QuickSort( esbirros );}
						if (CantidadDeColisiones==4){ TreeAVL( esbirros );CantidadDeColisiones=0; }

						ReacomodoDeEsbirros( esbirros );
					}
				}
			}

			//Disminuir la vida del jugador si una bala lo alcanza
			for( Bala bala : balasEnemigas){
				if (bala.recta.overlaps(jugador.recta)) {
					balasParaQuitar.add(bala);
					jugador.hitPoints -= 1;
				}
			}
			//Se la vida del jugador llega a 0 acaba el juego
			if (jugador.hitPoints <= 0){
				gameOver = true;
			}

			balas.removeAll(balasParaQuitar);
			balasEnemigas.removeAll(balasParaQuitar);
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

		for (Bala bala : balasEnemigas)
			bala.dibujarEnemigo(batch);

		//Hacer click en los enemigos para ver sus caracteristicas
		for (Esbirro esbirro : esbirros) {
            esbirro.dibujar(batch);
            if (Gdx.input.isTouched())
                esbirro.mostrar();
        }


        //Texto en pantalla
		font.draw(batch, "Vida: " + jugador.hitPoints, 70, 820);

		font.draw(batch, "Dragones eliminados: " + killedDragons, 200, 820);

		if(Nronda < 5) {
			font.draw(batch, "Ronda: " + Nronda, 70, 50);
		} else{
			font.draw(batch, "Ronda: Max.", 70, 50);
		}


		//Manejo de las rondas
        if (killedDragons == 30 && PasaRonda){
        	Nronda += 1;
        	timeFrame -=2;
        	PasaRonda = false;
		}

		if (killedDragons == 70 && PasaRonda == false){
			Nronda += 1;
			timeFrame -=2;
			PasaRonda = true;
		}

		if (killedDragons == 110 && PasaRonda){
			Nronda += 1;
			timeFrame -=2;
			PasaRonda = false;
		}

		if (killedDragons == 200 && PasaRonda == false){
			Nronda += 1;
			timeFrame -=1;
			PasaRonda = true;
		}


        if (isPaused == true && gameOver == false){
            font.draw(batch, "Pausa, presiona BackSpace para continuar", 300, 500);
        }

        if (gameOver) {
        	isPaused = true;
			font.draw(batch, "Has muerto. Presiona ESC para salir", 300, 500);
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
