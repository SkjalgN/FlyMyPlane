package com.mygdx.game.View;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {

    protected GameStateManager gsm;
    protected OrthographicCamera cam;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();

    }

    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
