package com.mygdx.game.Controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.API;
import com.mygdx.game.Model.Score;
import com.mygdx.game.View.GameState;
import com.mygdx.game.View.MenuState;
import com.mygdx.game.View.GameStateManager;

import java.util.ArrayList;


public class GameController extends Game {
	private GameStateManager gsm;
	private  SpriteBatch batch;

	private API _FBIC;

	public GameController(API FBIC) {
		_FBIC = FBIC;
    }
	public ArrayList<Score> listen;
	@Override
	public void create() {
		batch = new SpriteBatch();
 		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm, _FBIC));
		listen = new ArrayList<>();
		_FBIC.submitHighscore(new Score(3000,"Christian"));
		_FBIC.getHighscores(listen);



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