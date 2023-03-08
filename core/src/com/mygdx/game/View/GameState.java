package com.mygdx.game.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


public class GameState extends State{
    
    private Texture background;

    public GameState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, 3500, 2000);
        cam.zoom = (float)0.5;
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
        moveCamera();
    }

    @Override
    public void dispose() {
        background.dispose();
    }

    public void moveCamera() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-6, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(6, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -6, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 6, 0);
        }
    }
}
