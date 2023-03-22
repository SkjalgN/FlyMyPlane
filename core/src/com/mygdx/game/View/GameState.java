package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Model.Plane;
import com.mygdx.game.Model.Boat;


public class GameState extends State{
    
    private Texture background;
    private Texture background2;
    private Plane plane;
    private Boat boat;
    private int score = 5000;
    private BitmapFont font;
    private float xPos;
    private float yPos;
    private float BW;
    private float BH;
    private float var1;
 
    public GameState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("TheMap.jpg");
        background2 = new Texture("Blue.jpg");
        cam.setToOrtho(false, background.getWidth(),background.getHeight());
        cam.zoom = (float)0.58;
        plane = new Plane(background.getWidth()/2-200,background.getHeight()/2-200,1,1,400,400,new TextureRegion(new Texture("dragon.png")));
        boat = new Boat(2700,2700,1,1,300,300,new TextureRegion(new Texture("boat1.png")));
        font = new BitmapFont();
        font.getData().setScale(3f);

    }

    

    @Override
    public void update(float dt) {
        cam.update();
        plane.update(dt);
        boat.update(dt);
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background2,-3000,-2000,background.getWidth()+6000,background.getHeight()+4000);
        sb.draw(background,0,0);
        boat.draw(sb);
        plane.draw(sb);
        font.draw(sb, "Score: " + score, plane.getxPos()-400, plane.getyPos()+ 600);
        score -= 1;
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
    }


    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            plane.rotate(0.04f);
            
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            plane.rotate(-0.04f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            plane.setSpeed(20);
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.UP)){
            plane.setSpeed(3);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            gsm.push(new PauseState(gsm));
        }

        xPos = plane.getxPos()+plane.getPlaneWidth()/2;
        yPos = plane.getyPos()+plane.getPlaneHeight()/2;
        BW = background.getWidth();
        BH = background.getHeight();
        var1 = 700;

        if (xPos > BW +var1+plane.getPlaneWidth()/2){
            plane.setxPos(-var1+plane.getPlaneWidth()/2);
            cam.translate(-(background.getWidth()+var1*2)+plane.getPlaneWidth()/2,0);
        }
        if((xPos)< (-var1)+plane.getPlaneWidth()/2){
            plane.setxPos(background.getWidth()+var1+(plane.getPlaneWidth()/2));
            cam.translate(background.getWidth()+var1*2+plane.getPlaneWidth(),0);
        }
        if ((yPos) > BH+var1+plane.getPlaneHeight()/2){
            plane.setyPos(-var1+(plane.getPlaneHeight()/2));
            cam.translate(0,-(background.getHeight()+var1*2)+plane.getPlaneHeight());
        }
        if((yPos) < (-var1)+plane.getPlaneHeight()/2){
            plane.setyPos(background.getHeight()+var1+(plane.getPlaneHeight()/2));
            cam.translate(0,background.getHeight()+var1*2+plane.getPlaneHeight());
        }



        cam.translate((float) (plane.getSpeed() * Math.cos(plane.getAngle())), (float) (plane.getSpeed() * Math.sin(plane.getAngle())));

        // Assume you have an Actor object called "myActor" that you want to rotate
        //float angle = 45; // Replace this with the angle you want to rotate the actor to
        //myActor.setRotation(angle);
        
    }
}
