package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
    protected int width;
    protected int height;
    protected GameStateManager gsm;
    protected OrthographicCamera cam;
    protected AssetManager manager;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();
        manager = new AssetManager();
        manager.load("Audio/background.ogg",Music.class);
        manager.finishLoading();
    }

    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
