package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Model.Plane;
import com.mygdx.game.Model.Boat;


public class GameState extends State{
    
    private Texture background;
    private Plane plane;
    private Boat boat;
    private int score = 5000;
    private BitmapFont font;
    private GameStage stage;
    private Skin pauseBtnSkin;
    private Button pauseBtn;

    
 
    public GameState(final GameStateManager gsm) {
        super(gsm);
        background = new Texture("gamescreens/theMap.jpg");
        cam.setToOrtho(false, background.getWidth(),background.getHeight());
        cam.zoom = (float)0.18;
        plane = new Plane(background.getWidth()/2-200,background.getHeight()/2-200,1,1,400,400,new TextureRegion(new Texture("planeTextures/dragon.png")));
        boat = new Boat(2700,2700,1,1,300,300,new TextureRegion(new Texture("objects/boat.png")));
        font = new BitmapFont();
        font.getData().setScale(3f);

        stage = new GameStage();

        pauseBtnSkin = new Skin(Gdx.files.internal("buttons/game/pauseBtn/pauseBtn.json"));

        pauseBtn = new Button(pauseBtnSkin);

        pauseBtn.setPosition(0,380);
        pauseBtn.setSize(100,100);
        pauseBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new PauseState(gsm));
                System.out.println("Button Pressed");
                return true;
            }
        });

        stage.addActor(pauseBtn);
        Gdx.input.setInputProcessor(stage);

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
        sb.draw(background,0,0);
        boat.draw(sb);
        plane.draw(sb);
        font.draw(sb, "Score: " + score, plane.getxPos()-400, plane.getyPos()+ 600);
        score -= 1;
        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
        //plane.dispose();
        //boat.dispose();
        pauseBtnSkin.dispose();
    }

    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            plane.rotate(0.04f);
            
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            plane.rotate(-0.04f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            plane.setSpeed(12);
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.UP)){
            plane.setSpeed(3);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            gsm.push(new PauseState(gsm));
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
        
    }
}
