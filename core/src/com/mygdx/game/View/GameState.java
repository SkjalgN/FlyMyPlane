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
    private Skin leftBtnSkin;
    private Skin rightBtnSkin;
    private Skin boostBtnSkin;
    private Button pauseBtn;
    private Button leftBtn;
    private Button rightBtn;
    private Button boostBtn;

    
 
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
        leftBtnSkin = new Skin(Gdx.files.internal("buttons/game/leftBtn/leftBtn.json"));
        rightBtnSkin = new Skin(Gdx.files.internal("buttons/game/rightBtn/rightBtn.json"));
        boostBtnSkin = new Skin(Gdx.files.internal("buttons/game/boostBtn/boostBtn.json"));

        pauseBtn = new Button(pauseBtnSkin);
        leftBtn = new Button(leftBtnSkin);
        rightBtn = new Button(rightBtnSkin);
        boostBtn = new Button(boostBtnSkin);

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

        leftBtn.setPosition(0,0);
        leftBtn.setSize(100,100);
        leftBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                plane.rotateLeft();
                System.out.println("Turn Left");
                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                plane.stopRotateLeft();
            }
        });

        rightBtn.setPosition(100,0);
        rightBtn.setSize(100,100);
        rightBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                plane.rotateRight();
                System.out.println("Turn Right");
                return true;
            }
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                plane.stopRotateRight();
            }
        });

        boostBtn.setPosition(530,0);
        boostBtn.setSize(100,100);
        boostBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                plane.setSpeed(12);
                plane.setAirflowvar(0);
                System.out.println("Button Pressed");
                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                plane.setSpeed(3);
                plane.setAirflowvar(1);
            }
        });

        stage.addActor(pauseBtn);
        stage.addActor(leftBtn);
        stage.addActor(rightBtn);
        stage.addActor(boostBtn);
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
