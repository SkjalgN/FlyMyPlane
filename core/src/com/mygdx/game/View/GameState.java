package com.mygdx.game.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameState extends State{
    
    private Texture background;

    public GameState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, 2640, 2480);
        background = new Texture("MapChart.png");
    }

    @Override
    public void update(float dt) {
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
