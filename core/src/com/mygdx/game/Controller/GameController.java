package com.mygdx.game.Controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.API;
import com.mygdx.game.Model.Score;
import com.mygdx.game.View.GameState;
import com.mygdx.game.View.MenuState;
import com.mygdx.game.View.GameStateManager;
import com.mygdx.game.View.StartGameView;
import com.mygdx.game.View.VictoryState;

import java.util.ArrayList;


public class GameController extends Game {

	private GameStateManager gsm;
	private  SpriteBatch batch;



	// ALL THE STATES ARE CREATED HERE
	private StartGameView StartGameView;
	private MenuState MenuView;
	



	private API Database;

	public GameController(API database) {
		Database = database;
    }
	public ArrayList<Score> listen;
	@Override
	public void create() {
		//THIS VIEW IS CREATED FIRST
		StartGameView();

		batch = new SpriteBatch();
 		gsm = new GameStateManager();
		gsm.push(this.StartGameView);
		listen = new ArrayList<>();
		/*
		* Database.submitHighscore(new Score(3490,"Skjalg"));
		* THIS METHOD IS USED TO SAVE SCORES TO DB IN THIS FORMAT!
		* CREATE A NEW SCORE WITH SCORE FIRST, THEN USERNAME
		* */
		Database.submitHighscore(new Score(3490,"Skjalg"));
		Database.getHighscores(listen);

	}

	public void StartGameView(){
		this.StartGameView = new StartGameView(gsm, Database);
		startGameButton();
	}

	// StartGameButton
	public void startGameButton(){
		this.StartGameView.getStartGameButton().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                MenuView();
                System.out.println("Started MenuView");
                return true;
            }
        });
	}
	public void MenuView(){
		this.MenuView = new MenuState(gsm, Database);
		gsm.set(this.MenuView);
	}
	//

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}

	public void handleInput() {
		
		
	}
}