package com.mygdx.game.Controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygdx.game.API;
import com.mygdx.game.Model.Score;
import com.mygdx.game.View.MenuView;
import com.mygdx.game.View.GameStateManager;
import com.mygdx.game.View.SelectionView;
import com.mygdx.game.View.StartGameView;
import com.mygdx.game.View.TutorialView;

import java.util.ArrayList;


public class GameController extends Game {
	public interface Callback {
		void execute();
	}

	private GameStateManager gsm;
	private  SpriteBatch batch;



	// ALL THE STATES ARE CREATED HERE
	private StartGameView startGameView;
	private MenuView menuView;
	private SelectionView SelectionView;
	private TutorialView tutorialView;
	



	private API Database;

	public GameController(API database) {
		Database = database;
    }
	public ArrayList<Score> listen;
	@Override
	public void create() {

		batch = new SpriteBatch();
		gsm = new GameStateManager();
		//THIS VIEW IS CREATED FIRST
		startGameView();
/*
		gsm.push(this.StartGameView);
*/
		/*
		* Database.submitHighscore(new Score(3490,"Skjalg"));
		* THIS METHOD IS USED TO SAVE SCORES TO DB IN THIS FORMAT!
		* CREATE A NEW SCORE WITH SCORE FIRST, THEN USERNAME
		* */
		Database.submitHighscore(new Score(3490,"Skjalg"));

	}
	//The button is activated at correct view
	public void startMenuView(){
		changeStateButton(this.startGameView.getStartGameButton(), new Callback() {
			@Override
			public void execute() {
				menuView();
			}
		});
	}
	//The button is activated at correct view
	public void startSelectionView(){
		changeStateButton(this.menuView.getSelectionButton(), new Callback() {
			@Override
			public void execute() {
				System.out.println("Started SelectionView");
				selectionView();
			}
		});
	}
	public void startTutorialView(){
		changeStateButton(this.menuView.getTutorialButton(), new Callback() {
			@Override
			public void execute() {
				tutorialView();
			}
		});
	}

	// StartGameButton
	public void changeStateButton(Button button, final Callback callback){
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				callback.execute();
				return true;
			}
		});
	}
	public void startGameView(){
		this.startGameView = new StartGameView(gsm, Database);
		gsm.push(this.startGameView);

		//Knappene som aktiveres skal hit
		startMenuView();
	}

	public void menuView(){
		this.menuView = new MenuView(gsm, Database);
		gsm.set(this.menuView);

		//Knappene som aktiveres på MenuView
		startSelectionView();
		startTutorialView();
	}
	public void selectionView(){
		this.SelectionView = new SelectionView(gsm, Database);
		gsm.set(this.SelectionView);

		//Knappene som aktiveres på SelectionView
	}
	public void tutorialView(){
		this.tutorialView = new TutorialView(gsm, Database);
		gsm.set(this.tutorialView);

		//Knappene som aktiveres på TutorialView
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