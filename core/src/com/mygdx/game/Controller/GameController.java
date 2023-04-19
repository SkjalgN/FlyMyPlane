package com.mygdx.game.Controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.API;
import com.mygdx.game.Model.Score;
import com.mygdx.game.View.GameState;
import com.mygdx.game.View.MenuState;
import com.mygdx.game.View.GameStateManager;
import com.mygdx.game.View.StartGameState;
import com.mygdx.game.View.TestScreen;
import com.mygdx.game.View.VictoryState;

import java.util.ArrayList;


public class GameController extends Game {
	private GameStateManager gsm;
	private  SpriteBatch batch;
	private TestScreen testScreen;

	private API _FBIC;

	public GameController(API FBIC) {
		_FBIC = FBIC;
    }
	public ArrayList<Score> listen;
	@Override
	public void create() {
		batch = new SpriteBatch();
		testScreen = new TestScreen(this);
		setScreen(testScreen);
		testScreen.show();
 		//gsm = new GameStateManager();
		//gsm.push(new StartGameState(gsm, _FBIC));
		//listen = new ArrayList<>();
		/*
		* _FBIC.submitHighscore(new Score(3490,"Skjalg"));
		* THIS METHOD IS USED TO SAVE SCORES TO DB IN THIS FORMAT!
		* CREATE A NEW SCORE WITH SCORE FIRST, THEN USERNAME
		* */
		_FBIC.submitHighscore(new Score(3490,"Skjalg"));
		_FBIC.getHighscores(listen);

	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//gsm.update(Gdx.graphics.getDeltaTime());
		//gsm.render(batch);
		testScreen.render(Gdx.graphics.getDeltaTime());

	}

	public void handleInput() {
		
		
	}
}