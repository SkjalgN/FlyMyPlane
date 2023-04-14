package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Model.Plane;

public class PauseState extends State{
    
    private Texture background;
    private Texture pause;
    private Texture tutorial;
    private Texture exit;

    public PauseState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("gamescreens/pauseMenu.jpg");
        pause = new Texture("buttons/pauseBtn1.png");
        tutorial = new Texture("buttons/pauseBtn2.png");
        exit = new Texture("buttons/pauseBtn3.png");
        cam.setToOrtho(false, background.getWidth(),background.getHeight());
        cam.zoom = (float)1.0;
        cam.translate(0, 0);
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
        sb.draw(pause, background.getWidth()/2-pause.getWidth()/2, background.getHeight()/2-pause.getHeight()/2);
        sb.draw(tutorial, 400, 200);
        sb.draw(exit, 1200, 220);
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
