package com.mygdx.game.Controller;

import com.badlogic.gdx.Game;
import com.mygdx.game.GameScreen;

//Her starter spillet!!!

public class GameController extends Game {

	GameScreen gamescreen;

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		gamescreen.resize(width, height);
	}

	@Override
	public void create() {
		gamescreen = new GameScreen();
		setScreen(gamescreen);
	}

	@Override
	public void dispose() {
		gamescreen.dispose();
	}
}