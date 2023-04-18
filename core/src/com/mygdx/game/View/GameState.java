package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.API;
import com.mygdx.game.Model.Location;
import com.mygdx.game.Model.Package;
import com.mygdx.game.Model.Plane;
import com.mygdx.game.Model.Boat;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import com.badlogic.gdx.utils.TimeUtils;

public class GameState extends State {

    private Texture background;
    private Texture backgroundWater;
    private Plane plane;
    private Boat boat;
    private Package pack;
    private Package pack2;
    private long startTime;
    private long elapsedTime;
    private BitmapFont font;
    private BitmapFont font2;
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
    private Batch batch;

    private int packageIndex;
    private Label packageLabel;
    private int destinationIndex;

    public GameState(final GameStateManager gsm, final API database) {
        super(gsm);
        this.database = database;
        initializeLocations();
        this.pack = initializePackage(pack, packageIndex, "assets/objects/packs.png");
        background = new Texture("gamescreens/theMap.jpg");
        backgroundWater = new Texture("gamescreens/water.jpg");
        cam.setToOrtho(false, background.getWidth(), background.getHeight());
        cam.zoom = (float) 0.18;
        plane = new Plane(background.getWidth() / 2 - 200, background.getHeight() / 2 - 200, 7, 1, 400, 400,
                new TextureRegion(new Texture("planeTextures/dragon.png")));
        boat = new Boat(2700, 2700, 1, 1, 300, 300, new TextureRegion(new Texture("objects/boat.png")));

        font = new BitmapFont();
        font.getData().setScale(3f);
        font2 = new BitmapFont();
        font2.getData().setScale(3f);
        packageFont = new BitmapFont();
        // FreeTypeFontGenerator generator = new
        // FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));

        stage = new GameStage();

        pauseBtnSkin = new Skin(Gdx.files.internal("buttons/game/pauseBtn/pauseBtn.json"));
        leftBtnSkin = new Skin(Gdx.files.internal("buttons/game/leftBtn/leftBtn.json"));
        rightBtnSkin = new Skin(Gdx.files.internal("buttons/game/rightBtn/rightBtn.json"));
        boostBtnSkin = new Skin(Gdx.files.internal("buttons/game/boostBtn/boostBtn.json"));
        flameBtnSkin = new Skin(Gdx.files.internal("buttons/game/flameBtn/flameBtn.json"));
        pauseBtn = new Button(pauseBtnSkin);
        leftBtn = new Button(leftBtnSkin);
        rightBtn = new Button(rightBtnSkin);
        boostBtn = new Button(boostBtnSkin);
        flameBtn = new Button(flameBtnSkin);

        // Scorelabel er feil, det er teksten som viser hvor du skal hente pakke. Må
        // endre navn
        // Mangler også den andre skrifttypen
        startTime = TimeUtils.millis();
        packageFont = new BitmapFont();
        ;
        //scoreLabel = new Label("Get the package in: " + pack.getCity(), new Label.LabelStyle(packageFont, Color.WHITE));
        //scoreLabel.setFontScale(2f);
        table = new Table(flameBtnSkin);
        table.add(scoreLabel).expandX().padTop(10);
        table.setPosition(stage.getWidth() / 2, stage.getHeight() - table.getHeight() - 10);
        table.row();

        // BUTTONS!!!!

        pauseBtn.setSize(SCREEN_WIDTH / 8f, SCREEN_WIDTH / 8f);
        pauseBtn.setPosition(0, SCREEN_HEIGHT - pauseBtn.getHeight());
        pauseBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.push(new PauseState(gsm, database));
                System.out.println("Button Pressed");
                return true;
            }
        });

        leftBtn.setSize(SCREEN_WIDTH / 8f, SCREEN_WIDTH / 8f);
        leftBtn.setPosition(0, 0);
        leftBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                plane.rotateLeft();
                System.out.println("Turn Left");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                plane.stopRotateLeft();
            }
        });

        rightBtn.setSize(SCREEN_WIDTH / 8f, SCREEN_WIDTH / 8f);
        rightBtn.setPosition(leftBtn.getWidth() * 1.2f, 0);
        rightBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                plane.rotateRight();
                System.out.println("Turn Right");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                plane.stopRotateRight();
            }
        });

        boostBtn.setSize(SCREEN_WIDTH / 8f, SCREEN_WIDTH / 8f);
        boostBtn.setPosition(SCREEN_WIDTH - boostBtn.getWidth(), 0);
        boostBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                plane.setSpeed(15);
                plane.setAirflowvar(0);
                System.out.println("Button Pressed");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                plane.setSpeed(3);
                plane.setAirflowvar(1);
            }
        });

        flameBtn.setSize(SCREEN_WIDTH / 8f, SCREEN_WIDTH / 8f);
        flameBtn.setPosition(SCREEN_WIDTH - flameBtn.getWidth(), boostBtn.getWidth());
        flameBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                plane.setFlamevar(1);
                System.out.println("Button Pressed");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
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



    @Override
    public void update(float dt) {
        cam.update();
        plane.update(dt);
        boat.update(dt);
        handleInput();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(backgroundWater, -4000, -1000, 16000, 8000);
        sb.draw(background, 0, 0);
        boat.draw(sb);
        plane.draw(sb);

        if(Gdx.input.isKeyPressed(Input.Keys.P)) {
            gsm.push(new PauseState(gsm, database));
        }

        packageLogic(sb);

        long elapsedTime = TimeUtils.timeSinceMillis(startTime);
        int seconds = (int) (elapsedTime / 1000);
        font.draw(sb, "Tid: " + Integer.toString(seconds), plane.getxPos() + SCREEN_WIDTH * 1.2f, plane.getyPos() + SCREEN_HEIGHT * 1.3f);
        font.getData().setScale(3f);

        if (showPackage) {
            font2.draw(sb, "Find the package in " + pack.getCity(), plane.getxPos(), plane.getyPos() + SCREEN_HEIGHT * 1.3f);
            font2.getData().setScale(3f);
        }
        else if (showDestination){
            font2.draw(sb, "Deliver the package to " + pack2.getCity(), plane.getxPos(), plane.getyPos() + SCREEN_HEIGHT * 1.3f);
            font2.getData().setScale(3f);
        }




        packageFont.draw(sb, Integer.toString(seconds), plane.getxPos() + SCREEN_WIDTH, plane.getyPos() + SCREEN_HEIGHT);
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
        if (plane.getxPos() > background.getWidth() + 300) {
            plane.setxPos(-500);
            cam.translate(-(background.getWidth() + 800), 0);
        }
        if (plane.getxPos() < -500) {
            plane.setxPos(background.getWidth() + 300);
            cam.translate(background.getWidth() + 800, 0);
        }
        if (plane.getyPos() > background.getHeight() + 200) {
            plane.setyPos(-400);
            cam.translate(0, -(background.getHeight() + 600));
        }
        if (plane.getyPos() < -400) {
            plane.setyPos(background.getHeight() + 200);
            cam.translate(0, background.getHeight() + 600);
        }

        cam.translate((float) (plane.getSpeed() * Math.cos(plane.getAngle())),
                (float) (plane.getSpeed() * Math.sin(plane.getAngle())));

    }


    private Button createButton(String path, float x, float y, float width, float height){
        Skin skin = new Skin(Gdx.files.internal(path));
        Button button = new Button(skin);
        button.setSize(width, height);
        button.setPosition(x, y);
        return button;
    }

    private void createPauseButton(String path, float x, float y, float width, float height){
        pauseBtn = createButton(null, height, startTime, width, height);
        pauseBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.push(new PauseState(gsm, database));
                return true;
            }
        });
        stage.addActor(pauseBtn);
    }

    private void createLeftButton(String path, float x, float y, float width, float height){
        leftBtn = createButton(null, height, startTime, width, height);
        leftBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                plane.rotateLeft();
                System.out.println("Turn Left");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                plane.stopRotateLeft();
            }
        });
        stage.addActor(leftBtn);
    }

    private void createRightButton(String path, float x, float y, float width, float height){
        rightBtn = createButton(null, height, startTime, width, height);
        rightBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                plane.rotateRight();
                System.out.println("Turn Right");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                plane.stopRotateRight();
            }
        });
        stage.addActor(rightBtn);
    }

    private void createBoostButton(String path, float x, float y, float width, float height){
        boostBtn = createButton(null, height, startTime, width, height);
        boostBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                plane.setSpeed(15);
                plane.setAirflowvar(0);
                System.out.println("Button Pressed");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                plane.setSpeed(3);
                plane.setAirflowvar(1);
            }
        });
        stage.addActor(boostBtn);
    }

    private void createFlameButton(String path, float x, float y, float width, float height){
        flameBtn = createButton(null, height, startTime, width, height);
        flameBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                plane.setFlamevar(1);
                System.out.println("Button Pressed");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                plane.setFlamevar(0);
                System.out.println("DSADASDSA Pressed");
            }
        });
        stage.addActor(flameBtn);
    }

     // Function to initialize locations
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
    //Function to generate random number
    private int generateRandomNumber(){
        return (int) Math.floor(Math.random() * locations.length);
    }

    //Booleans to show and hide packages and locations
    private boolean showPackage = true;
    private boolean showDestination = false;

    //Function to check for collision. When the plane hits the package, the render function stops drawing the first package, and the delivery location starts being drawn.
    public void checkCollision(Package pack) {
        if (plane.getxPos() < pack.getX() + pack.getWidth() / 2 && plane.getxPos() + plane.getPlaneWidth() > pack.getX() &&
                plane.getyPos() < pack.getY() + pack.getHeight() && plane.getyPos() + plane.getplaneHeight() > pack.getY() + 100) {
            showPackage = false;
            showDestination = false;
            if (pack == pack2){
                gsm.push(new VictoryState(gsm, database));
            }
            //packageLabel.setText("You have delivered the package to " + pack2.getCity());
        }

    }

    //Function to initialize the package
    public Package initializePackage(Package newPackage, int randomNum, String texture){
        randomNum = generateRandomNumber();
        newPackage = new Package(locations[randomNum].getLocationName(), locations[randomNum].getX(),
                locations[randomNum].getY(), 1000, 1000, new TextureRegion(new Texture(texture)), true);
        return newPackage;
    }


    //Function to handle logic around packages and locations
    public void packageLogic(SpriteBatch sb){

        //This is true by default, and the first package is drawn (pick up point)
        if (showPackage) {
            pack.draw(sb);
            checkCollision(pack);
        }
        //This is false by default but is made true, when the plane hits the pick up point
        else if (showDestination) {
            pack2.draw(sb);
            checkCollision(pack2);
        }
        //When the plane hits the package, the first statement is made false, the second is false, and so this "else" sentence is activated.
        else {
            //Creates a random location for delivery
            destinationIndex = generateRandomNumber();
            //If the pick up point is the same as the delivery point, it creates a new random number
            while (destinationIndex == packageIndex) {
                destinationIndex = generateRandomNumber();
            }
            //The delivery point is instantiated, and the "showTextureRegion2" is made true. The next time the render function is called,
            //the delivery point will be drawn.
            this.pack2 = initializePackage(pack2, destinationIndex, "assets/objects/Target1.png");
            //packageLabel.setText("Deliver the package to " + pack2.getCity());
            showDestination = true;

        }
    }

    
}
