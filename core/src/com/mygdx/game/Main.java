package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {

	/*public static final float ANCHO = 1200;
	public static final float ALTO = 800;*/

	SpriteBatch batch;

	public Jugador jugador;
	private Texture texturaJugador;
	private Texture texturaBala;

	private Bala bala;
	private ArrayList<Bala> balas;

	//Cargar texturas de los objetos
	private void cargarTexturas(){
		texturaJugador = new Texture("jugador.png");
		texturaBala = new Texture("bala.png");
	}

	//Inicializar objetos
	@Override
	public void create () {
		batch = new SpriteBatch();
		cargarTexturas();
		crearObjetos();
	}

	//Precargar objetos
	private void crearObjetos() {
		jugador = new Jugador(texturaJugador,30,300);
		balas = new ArrayList<Bala>();
	}

	//Loop del juego
	@Override
	public void render () {

		//Si se presiona espacio el dragon dispara una bola de fuego
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			balas.add(new Bala(texturaBala,jugador.getX(),jugador.getY()));
		}

		//Movimiento de las bolas de fuego
		ArrayList<Bala> BalasParaQuitar = new ArrayList<Bala>();
		for (Bala bala : balas) {
			bala.update();
			if (bala.quitar)
				BalasParaQuitar.add(bala);
		}
		balas.removeAll(BalasParaQuitar);


		//Movimiento del jugador
		jugador.mover();

		//Color de fondo
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		//Creacion de sprites en la pantalla de juego
		batch.begin();

		for (Bala bala : balas) bala.dibujar(batch);
		jugador.dibujar(batch);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
