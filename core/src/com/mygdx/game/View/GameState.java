package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private Plane plane;
    private Boat boat;

    private Package pack;
    private Package pack2;

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
    private Label packageLabel;

    private API database;

    private Location[] locations = new Location[5];

    //These two variables controls when the pick up location and delivery point are visible
    private boolean showTextureRegion = true;
    private boolean showTextureRegion2 = false;

    //This selects a random location for the pick up and delivery point.
    int randomNum = (int) Math.floor(Math.random() * locations.length);


    //Function to initialize locations
    //The coordinates are currently only approximate, but is easy to fix
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


    public GameState(final GameStateManager gsm, final API database) {
        super(gsm);
        this.database = database;
        initializeLocations();
        background = new Texture("gamescreens/theMap.jpg");
        //Creates the pick up location, but it doesn't get drawn until later in "render"
        pack = new Package(locations[randomNum].getLocationName(), locations[randomNum].getX(), locations[randomNum].getY(), 1000, 1000, new TextureRegion(new Texture("objects/packs.png")),true);
        cam.setToOrtho(false, background.getWidth(),background.getHeight());
        cam.zoom = (float)0.5;
        plane = new Plane(background.getWidth()/2-200,background.getHeight()/2-200,2,1,400,400,new TextureRegion(new Texture("planeTextures/plane.png")));
        boat = new Boat(2700,2700,1,1,300,300,new TextureRegion(new Texture("objects/boat.png")));
        font = new BitmapFont();
        font.getData().setScale(3f);
        packageFont = new BitmapFont();

        stage = new GameStage();

        pauseBtnSkin = new Skin(Gdx.files.internal("buttons/game/pauseBtn/pauseBtn.json"));
        leftBtnSkin = new Skin(Gdx.files.internal("buttons/game/leftBtn/leftBtn.json"));
        rightBtnSkin = new Skin(Gdx.files.internal("buttons/game/rightBtn/rightBtn.json"));
        boostBtnSkin = new Skin(Gdx.files.internal("buttons/game/boostBtn/boostBtn.json"));
        flameBtnSkin = new Skin(Gdx.files.internal("buttons/game/boostBtn/boostBtn.json"));
        //flameBtnSkin = new Skin(Gdx.files.internal("buttons/game/flameBtn/flameBtn.json"));

        pauseBtn = new Button(pauseBtnSkin);
        leftBtn = new Button(leftBtnSkin);
        rightBtn = new Button(rightBtnSkin);
        boostBtn = new Button(boostBtnSkin);
        flameBtn = new Button(flameBtnSkin);

        //The text on the top of the screen that displays where to pick up and deliver the package
        packageLabel = new Label("Get the package in: " + pack.getCity(), new Label.LabelStyle(packageFont, Color.WHITE));
        packageLabel.setFontScale(2f);
        table = new Table(flameBtnSkin);
        table.add(packageLabel).expandX().padTop(10);
        table.setPosition(stage.getWidth()/2, stage.getHeight()-table.getHeight() - 15);
        table.row();


        //Buttons and such. Magnus' work
        pauseBtn.setSize(width/8f,width/8f);  
        pauseBtn.setPosition(0,height-pauseBtn.getHeight());
        
        pauseBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new PauseState(gsm, database));
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
    //Function to check for collision. When the plane hits the package, the render function stops drawing the first package, and the delivery location starts being drawn.
    public void checkCollision(Package pack) {
        if (plane.getxPos() < pack.getX() + pack.getWidth() / 2 && plane.getxPos() + plane.getPlaneWidth() > pack.getX() &&
                plane.getyPos() < pack.getY() + pack.getHeight() && plane.getyPos() + plane.getplaneHeight() > pack.getY() + 100) {
            showTextureRegion = false;
            showTextureRegion2 = false;
        }
    }

    //Magnus again
    @Override
    public void update(float dt) {
        cam.update();
        plane.update(dt);
        boat.update(dt);
        handleInput();
    }

        @Override
        public void render (SpriteBatch sb){
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        boat.draw(sb);
        plane.draw(sb);

        //This is true by default, and the first package is drawn (pick up point)
        if (showTextureRegion) {
            pack.draw(sb);
            checkCollision(pack);
        }
        //This is false by default but is made true, when the plane hits the pick up point
        else if (showTextureRegion2) {
            pack2.draw(sb);
            checkCollision(pack2);
        }
        //When the plane hits the package, the first statement is made false, the second is false, and so this "else" sentence is activated.
        else {
            //Creates a random location for delivery
            int randomNum2 = (int) Math.floor(Math.random() * locations.length);
            //If the pick up point is the same as the delivery point, it creates a new random number
            while (randomNum2 == randomNum) {
                randomNum2 = (int) Math.floor(Math.random() * locations.length);
            }
            //The delivery point is instantiated, and the "showTextureRegion2" is made true. The next time the render function is called,
            //the delivery point will be drawn.
            pack2 = new Package(locations[randomNum2].getLocationName(), locations[randomNum2].getX(), locations[randomNum2].getY(), 1000, 1000, new TextureRegion(new Texture("objects/Target2.png")), true);
            packageLabel.setText("Deliver the package to " + pack2.getCity());
            showTextureRegion2 = true;

        }

        //This should be replaced with a stopwatch that is displayed at the top of the screen.
        font.draw(sb, "Score: " + score, width - width / 4f, height - height / 8f);
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

    //Checks whether the player flies outside of the map, and then teleports it to the corresponding latitude/longitude on the other side of the map.
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
