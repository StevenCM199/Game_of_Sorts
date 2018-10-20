package juego;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Random;

public class Main extends ApplicationAdapter {

	private float deltaTime;

	SpriteBatch batch;

	//Inicializar Sprites
	public Jugador jugador;
	private Texture texturaJugador;

	private ArrayList<Bala> balas;
	private Texture texturaBala;

	private ArrayList<Esbirro> esbirros;
	private Texture texturaEsbirro;

	float esbirroSpawnTimer;
	Random random;

	ShapeRenderer shape;


	//Cargar texturas de los objetos
	private void cargarTexturas(){
		texturaJugador = new Texture("jugador.png");
		texturaBala = new Texture("bala.png");
		texturaEsbirro = new Texture("enemy.png");
	}

	//Inicializar objetos
	@Override
	public void create () {
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		cargarTexturas();
		cargarObjetos();
	}

	//Precargar objetos
	private void cargarObjetos() {
		jugador = new Jugador(texturaJugador,30,300);
		balas = new ArrayList<Bala>();
		esbirros = new ArrayList<Esbirro>();
		random = new Random();
	}

	//Loop del juego
	@Override
	public void render () {
		deltaTime = Gdx.graphics.getDeltaTime();

		//Si se presiona espacio el dragon dispara una bola de fuego
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			balas.add(new Bala(texturaBala,jugador.getX(),jugador.getY()));
		}
			//System.out.println(balas);

		//Spawn de los esbirros
		esbirroSpawnTimer +=1*deltaTime;
			//System.out.println(esbirroSpawnTimer);
		if (esbirroSpawnTimer > 1){
			esbirros.add(new Esbirro(texturaEsbirro,600, random.nextInt(420-70)+70));
			esbirroSpawnTimer = 0;
		}

		//Movimiento de las bolas de fuego
		ArrayList<Bala> balasParaQuitar = new ArrayList<Bala>();
		for (Bala bala : balas) {
			//System.out.println(bala.recta);
			bala.update();
			if (bala.quitar)
				balasParaQuitar.add(bala);
		}


		//Movimiento de los esbirros
			//System.out.println(jugador.getY());
		ArrayList<Esbirro> esbirrosParaQuitar = new ArrayList<Esbirro>();
		for (Esbirro esbirro : esbirros){
			esbirro.update();
			if (esbirro.quitar)
				esbirrosParaQuitar.add(esbirro);
		}


		//Movimiento del jugador
		jugador.mover();

		//Colisiones
		for(Bala bala : balas){
			for (Esbirro esbirro : esbirros){
				if (bala.recta.overlaps(esbirro.recta)){
					//System.out.println("Choque");
					balasParaQuitar.add(bala);
					esbirrosParaQuitar.add(esbirro);
				}
			}
		}
		balas.removeAll(balasParaQuitar);
		esbirros.removeAll(esbirrosParaQuitar);

		//Color de fondo
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		//Visualizacion de sprites en la pantalla de juego
		batch.enableBlending();
		batch.begin();


		for (Bala bala : balas)
			bala.dibujar(batch);

		for (Esbirro esbirro : esbirros)
			esbirro.dibujar(batch);

		jugador.dibujar(batch);

		batch.end();

	}
	@Override
	public void dispose () {
		batch.dispose();
	}
}
