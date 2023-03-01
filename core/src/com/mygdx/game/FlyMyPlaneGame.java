package com.mygdx.game;

import com.badlogic.gdx.Game;

//Her starter spillet!!!

public class FlyMyPlaneGame extends Game {

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