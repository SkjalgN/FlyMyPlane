package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Model.Plane;

public class MenuState extends State{
    
    private Texture background;
    private Plane plane;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("Menu.jpg");
        cam.setToOrtho(false, background.getWidth(),background.getHeight());
    }

    @Override
    public void update(float dt) {
        cam.update();
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        handleInput();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            gsm.set(new GameState(gsm));
        }
    }
}
