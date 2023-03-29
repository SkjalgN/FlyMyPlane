package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Model.Plane;

public class PauseState extends State{
    
    private Texture background;
    private Texture pause;

    public PauseState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("TheMap.jpg");
        pause = new Texture("pause-symbol.png");
        cam.setToOrtho(false, background.getWidth(),background.getHeight());
        cam.zoom = (float)0.18;
        cam.translate(gsm.getGameState().cam.position.x-4096, gsm.getGameState().cam.position.y-2500);
    }

    @Override
    public void update(float dt) {
        cam.update();
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0);
        sb.draw(pause, gsm.getGameState().cam.position.x-100, gsm.getGameState().cam.position.y-100);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)){
            gsm.pop();
        }
    }
}
