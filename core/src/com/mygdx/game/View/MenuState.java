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
        cam.setToOrtho(false, 2640, 2480);
        background = new Texture("MapClean.jpg");
        plane = new Plane(1000,1000,20,0,1000,1000,new TextureRegion(new Texture("plane.png")));
    }

    @Override
    public void update(float dt) {
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        handleInput();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0);
        plane.draw(sb);
        sb.end();
    }


    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            plane.setxPos(plane.getxPos()-plane.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            plane.setxPos(plane.getxPos()+plane.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            plane.setyPos(plane.getyPos()+plane.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            plane.setyPos(plane.getyPos()-plane.getSpeed());
        }

        // Assume you have an Actor object called "myActor" that you want to rotate
        //float angle = 45; // Replace this with the angle you want to rotate the actor to
        //myActor.setRotation(angle);
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
