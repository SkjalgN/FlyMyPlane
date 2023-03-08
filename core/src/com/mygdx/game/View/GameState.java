package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Model.Plane;


public class GameState extends State{
    
    private Texture background;
    private Plane plane;

    public GameState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("MapChart.png");
        //cam.setToOrtho(false, 3500, 2000);
        cam.setToOrtho(false, background.getWidth(),background.getHeight());
        cam.zoom = (float)0.2;
        plane = new Plane(1000,1000,10,0,1000,1000,new TextureRegion(new Texture("plane.png")));
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
        plane.draw(sb);
        sb.end();
        moveCamera();
    }

    @Override
    public void dispose() {
        background.dispose();
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			gsm.set(new MenuState(gsm));
		}
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
