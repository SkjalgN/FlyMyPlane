package com.mygdx.game.View;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameState extends State{

    protected GameState(GameStateManager gsm) {
        super(gsm);
        System.out.println("Magnus");
    }

    @Override
    public void update(float dt) {


    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }
}
