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
        cam.setToOrtho(false, background.getWidth(),background.getHeight());
        cam.zoom = (float)0.05;
        plane = new Plane(3070,1550,1,1,300,300,new TextureRegion(new Texture("plane.png")));
    }

    @Override
    public void update(float dt) {
        cam.update();
        plane.update(dt);
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
			gsm.push(new PauseState(gsm));
		}
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            plane.rotate(0.02f);
            
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            plane.rotate(-0.02f);
        }
        cam.translate((float) (plane.getSpeed() * Math.cos(plane.getAngle())), (float) (plane.getSpeed() * Math.sin(plane.getAngle())));

        // Assume you have an Actor object called "myActor" that you want to rotate
        //float angle = 45; // Replace this with the angle you want to rotate the actor to
        //myActor.setRotation(angle);
        
    }
}
