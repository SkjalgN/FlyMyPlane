package com.mygdx.game.View;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameState extends State{

    protected GameState(GameStateManager gsm) {
        super(gsm);
        System.out.println("Biggen hadde rett");
        System.out.println("OBOS var for lett");
        System.out.println("Først tok vi Ranheim");
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
