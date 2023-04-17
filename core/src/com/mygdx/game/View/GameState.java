package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.API;
import com.mygdx.game.Model.Location;
import com.mygdx.game.Model.Package;
import com.mygdx.game.Model.Plane;
import com.mygdx.game.Model.Boat;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class GameState extends State{
    
    private Texture background;
    private Texture backgroundWater;
    private Plane plane;
    private Boat boat;

    private Package pack;
    private int score = 5000;
    private BitmapFont font;

    private BitmapFont packageFont;
    private GameStage stage;
    private Skin pauseBtnSkin;
    private Skin leftBtnSkin;
    private Skin rightBtnSkin;
    private Skin boostBtnSkin;
    private Skin flameBtnSkin;
    private Button pauseBtn;
    private Button leftBtn;
    private Button rightBtn;
    private Button boostBtn;
    private Button flameBtn;

    private Table table;
    private Label scoreLabel;

    private API database;

    private Location[] locations = new Location[5];





    //Function to initialize locations
    public void initializeLocations() {
        Location Oslo = new Location("Oslo", 3700, 3500);
        Location Istanbul = new Location("Istanbul", 4500, 2500);
        Location Lagos = new Location("Lagos", 3300, 1500);
        Location Manila = new Location("Manila", 7000, 2600);
        Location NewYork = new Location("NewYork", 1000, 3500);
        locations[0] = Oslo;
        locations[1] = Istanbul;
        locations[2] = Lagos;
        locations[3] = Manila;
        locations[4] = NewYork;
    }


    private boolean showTextureRegion = true;


    public GameState(final GameStateManager gsm, final API database) {
        super(gsm);
        this.database = database;
        initializeLocations();
        background = new Texture("gamescreens/theMap.jpg");
        backgroundWater = new Texture("gamescreens/water.jpg");

        int randomNum = (int) Math.floor(Math.random() * locations.length);

        pack = new Package(locations[randomNum].getLocationName(), locations[randomNum].getX(), locations[randomNum].getY(), 1000, 1000, new TextureRegion(new Texture("objects/packs.png")),true);

        cam.setToOrtho(false, background.getWidth(),background.getHeight());
        cam.zoom = (float)1.18;
        plane = new Plane(background.getWidth()/2-200,background.getHeight()/2-200,2,1,400,400,new TextureRegion(new Texture("planeTextures/dragon.png")));
        boat = new Boat(2700,2700,1,1,300,300,new TextureRegion(new Texture("objects/boat.png")));
        font = new BitmapFont();
        font.getData().setScale(3f);
        packageFont = new BitmapFont();
        //FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));



        stage = new GameStage();

        pauseBtnSkin = new Skin(Gdx.files.internal("buttons/game/pauseBtn/pauseBtn.json"));
        leftBtnSkin = new Skin(Gdx.files.internal("buttons/game/leftBtn/leftBtn.json"));
        rightBtnSkin = new Skin(Gdx.files.internal("buttons/game/rightBtn/rightBtn.json"));
        boostBtnSkin = new Skin(Gdx.files.internal("buttons/game/boostBtn/boostBtn.json"));
        flameBtnSkin = new Skin(Gdx.files.internal("buttons/game/flameBtn/flameBtn.json"));
        //flameBtnSkin = new Skin(Gdx.files.internal("buttons/game/flameBtn/flameBtn.json"));

        pauseBtn = new Button(pauseBtnSkin);
        leftBtn = new Button(leftBtnSkin);
        rightBtn = new Button(rightBtnSkin);
        boostBtn = new Button(boostBtnSkin);
        flameBtn = new Button(flameBtnSkin);

        //Scorelabel er feil, det er teksten som viser hvor du skal hente pakke. Må endre navn
        //Mangler også den andre skrifttypen
        packageFont = new BitmapFont();;
        scoreLabel = new Label("Get the package in: " + pack.getCity(), new Label.LabelStyle(packageFont, Color.WHITE));
        scoreLabel.setFontScale(2f);
        table = new Table(flameBtnSkin);
        table.add(scoreLabel).expandX().padTop(10);
        table.setPosition(stage.getWidth()/2, stage.getHeight()-table.getHeight() - 10);
        table.row();



        pauseBtn.setSize(width/8f,width/8f);  
        pauseBtn.setPosition(0,height-pauseBtn.getHeight());
        
        pauseBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.push(new PauseState(gsm, database));
                System.out.println("Button Pressed");
                return true;
            }
        });

        leftBtn.setSize(width/8f,width/8f);
        leftBtn.setPosition(0,0);
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

        rightBtn.setSize(width/8f,width/8f);
        rightBtn.setPosition(leftBtn.getWidth()*1.2f,0);
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

        boostBtn.setSize(width/8f,width/8f);
        boostBtn.setPosition(width-boostBtn.getWidth(),0);
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
        
        flameBtn.setSize(width/8f,width/8f);
        flameBtn.setPosition(width-flameBtn.getWidth(),boostBtn.getWidth());
        flameBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                plane.setFlamevar(1);
                System.out.println("Button Pressed");
                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                plane.setFlamevar(0);
                System.out.println("DSADASDSA Pressed");
            }
        });


        stage.addActor(pauseBtn);
        stage.addActor(leftBtn);
        stage.addActor(rightBtn);
        stage.addActor(boostBtn);
        stage.addActor(flameBtn);
        stage.addActor(table);


        Gdx.input.setInputProcessor(stage);

    }
    public void checkCollision() {
        if (plane.getxPos() < pack.getX() + pack.getWidth() / 2 && plane.getxPos() + plane.getPlaneWidth() > pack.getX() &&
                plane.getyPos() < pack.getY() + pack.getHeight() && plane.getyPos() + plane.getplaneHeight() > pack.getY() + 100) {
            showTextureRegion = false;
            System.out.println("Collision detected!");
        }
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
        sb.draw(backgroundWater,-4000,-1000,16000,8000);
        sb.draw(background,0,0);
        boat.draw(sb);
        plane.draw(sb);
        checkCollision();

        if(showTextureRegion) {
            pack.draw(sb);
        }

        font.draw(sb, "Score: " + score, width-width/4f, height-height/8f);
        packageFont.getData().setScale(10);
        packageFont.setColor(Color.RED);
        packageFont.draw(sb, "Pick up package from: " + pack.getCity(), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        score -= 1;
        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
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
