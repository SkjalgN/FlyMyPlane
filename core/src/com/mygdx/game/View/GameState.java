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
    private Texture boat1;
    private float boatposx= 3500;
    private float boatposy = 2000;
    private float boatspeed = 2;

    public GameState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("TheMap.jpg");
        boat1 = new Texture("Boat1.png");
        cam.setToOrtho(false, background.getWidth(),background.getHeight());
        cam.zoom = (float)0.18;
        plane = new Plane(background.getWidth()/2-100,background.getHeight()/2-100,3,1,200,200,new TextureRegion(new Texture("plane.png")));
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
        sb.draw(boat1,boatposx,boatposy,200,200);
        plane.draw(sb);

        sb.end();

        boatposy += boatspeed;

        if (boatposy >3500 || boatposy <1000){
            boatspeed = -boatspeed;
        }
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
            plane.rotate(0.04f);
            
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            plane.rotate(-0.04f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            plane.setSpeed(8);
        }
        else{
            plane.setSpeed(3);
        }
        if (plane.getxPos() > background.getWidth()-200){
            plane.setxPos(200);
            cam.translate(-(background.getWidth()-400),0);
        }
        if(plane.getxPos() < 200){
            plane.setxPos(background.getWidth()-200);
            cam.translate(background.getWidth()-400,0);
        }
        if (plane.getyPos() > background.getHeight()-400){
            plane.setyPos(400);
            cam.translate(0,-(background.getHeight()-800));
        }
        if(plane.getyPos() < 400){
            plane.setyPos(background.getHeight()-400);
            cam.translate(0,background.getHeight()-800);
        }



        cam.translate((float) (plane.getSpeed() * Math.cos(plane.getAngle())), (float) (plane.getSpeed() * Math.sin(plane.getAngle())));

        // Assume you have an Actor object called "myActor" that you want to rotate
        //float angle = 45; // Replace this with the angle you want to rotate the actor to
        //myActor.setRotation(angle);
        
    }
}
