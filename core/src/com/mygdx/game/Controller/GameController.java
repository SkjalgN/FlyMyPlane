package com.mygdx.game.Controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.View.GameState;
import com.mygdx.game.View.GameStateManager;
import com.mygdx.game.View.MenuState;
import com.mygdx.game.View.State;


public class GameController extends Game {
	private GameStateManager gsm;
	private  SpriteBatch batch;

	public GameController() {
    }

	@Override
	public void create() {
		batch = new SpriteBatch();
 		gsm = new GameStateManager();
		gsm.push(new GameState(gsm));
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
		handleInput();
	}

	public void handleInput() {
		
		
	}
}