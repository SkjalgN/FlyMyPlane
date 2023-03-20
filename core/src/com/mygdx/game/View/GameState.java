package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Model.Package;
import com.mygdx.game.Model.Plane;


public class GameState extends State{
    
    private Texture background;
    private Plane plane;
    private Package pack;

    boolean showTextureRegion=true;

    public GameState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("MapChart.png");
        //cam.setToOrtho(false, 3500, 2000);
        cam.setToOrtho(false, background.getWidth(),background.getHeight());
        cam.zoom = (float)0.1;
        plane = new Plane(3070,1550,2,1,300,300,new TextureRegion(new Texture("plane.png")));
        pack = new Package("Ålesund",2000, 1000, 100, 100, new TextureRegion(new Texture("Pack.png")), true);
        System.out.print("X-kkordinate: "+ pack.getX());
        System.out.print("Y-koordinat: " + pack.getY());
        System.out.print("Bredde : "+ pack.getPackWidth());
        System.out.print("Høyde: " + pack.getPackHeight());

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

        checkCollision();

        if(showTextureRegion) {
            pack.draw(sb, pack.getPackWidth(), pack.getPackHeight());
        }
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
            plane.rotate(0.02f);
            
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            plane.rotate(-0.02f);
        }
        cam.translate((float) (plane.getSpeed() * Math.cos(plane.getAngle())), (float) (plane.getSpeed() * Math.sin(plane.getAngle())), 0);

        // Assume you have an Actor object called "myActor" that you want to rotate
        //float angle = 45; // Replace this with the angle you want to rotate the actor to
        //myActor.setRotation(angle);
        
    }

    public void checkCollision() {
        if (plane.getXPos() < pack.getX() + pack.getPackWidth() / 2 && plane.getXPos() + plane.getPlaneWidth() > pack.getX() &&
                plane.getYPos() < pack.getY() + pack.getPackHeight() && plane.getYPos() + plane.getplaneHeight() > pack.getY() + 100) {
            showTextureRegion = false;
            System.out.println("Collision detected!");
        }
    }
}
//(plane.getYPos() < ((pack.getY()) + 50)) &&
