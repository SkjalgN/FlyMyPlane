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
        plane = new Plane(2700,1200,10,0,1000,1000,new TextureRegion(new Texture("plane.png")));
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
    }

    @Override
    public void dispose() {
        background.dispose();
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			gsm.set(new MenuState(gsm));
		}
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            plane.setxPos(plane.getxPos()-plane.getSpeed());
            cam.translate(-plane.getSpeed(), 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            plane.setxPos(plane.getxPos()+plane.getSpeed());
            cam.translate(plane.getSpeed(), 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            plane.setyPos(plane.getyPos()+plane.getSpeed());
            cam.translate(0, plane.getSpeed(), 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            plane.setyPos(plane.getyPos()-plane.getSpeed());
            cam.translate(0, -plane.getSpeed(), 0);
        }

        // Assume you have an Actor object called "myActor" that you want to rotate
        //float angle = 45; // Replace this with the angle you want to rotate the actor to
        //myActor.setRotation(angle);
        
    }
}
