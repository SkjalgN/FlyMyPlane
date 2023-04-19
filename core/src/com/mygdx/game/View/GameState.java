package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
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
import com.badlogic.gdx.utils.TimeUtils;

public class GameState extends State {

    private Texture background;
    private Texture backgroundWater;
    private Plane plane;
    private Boat boat;
    private Package pack;
    private Package pack2;


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
    private Table textTable;
    private API database;
    private Location[] locations = new Location[6];
    private Batch batch;
    private int buttonWidth;
    private int buttonHeight;

    private Label packageLabel;
    private Label timeLabel;

    private long startTime;
    private long elapsedTime;

    private int seconds;

    private int packageIndex;
    private int destinationIndex;

    //Booleans to show and hide packages and locations
    private boolean showPackage = true;
    private boolean showDestination = false;

    public GameState(final GameStateManager gsm, final API database,int skinVar) {
        super(gsm);
        this.database = database;
        initializeLocations();
        this.pack = initializePackage(pack, 0, "objects/packs.png");
        background = new Texture("gamescreens/theMap.jpg");
        backgroundWater = new Texture("gamescreens/water.jpg");
        cam.setToOrtho(false, background.getWidth(), background.getHeight());
        cam.zoom = (float) 0.18;
        plane = new Plane(background.getWidth() / 2 - 25, background.getHeight() / 2 - 25, 3, 1, 50, 50,
                skinVar);
        
        boat = new Boat(2700, 2700, 1, 1, 300, 300, new TextureRegion(new Texture("objects/boat.png")));




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
        
        buttonWidth = SCREEN_WIDTH/8;
        buttonHeight = SCREEN_WIDTH/8;
        //createBoostButton("buttons/game/boostBtn/boostBtn.json", SCREEN_WIDTH - buttonWidth, 0, buttonWidth, buttonHeight);
        //createFlameButton("buttons/game/flameBtn/flameBtn.json", SCREEN_WIDTH - buttonWidth, buttonHeight, buttonWidth, buttonHeight);
        //createLeftButton("buttons/game/leftBtn/leftBtn.json", 0, 0, buttonWidth, buttonHeight);
        //createRightButton("buttons/game/rightBtn/rightBtn.json", buttonWidth * 1.2f, 0, buttonWidth, buttonHeight);
        //createPauseButton("buttons/game/pauseBtn/pauseBtn.json", 0, SCREEN_HEIGHT - buttonHeight, buttonWidth, buttonHeight);


        startTime = TimeUtils.millis();
        packageFont = new BitmapFont();




        //Table til å legge til packageLabel på
        textTable = new Table();
        packageFont = new BitmapFont();
        packageLabel = new Label("Get the package in: " + pack.getCity(), new Label.LabelStyle(packageFont, Color.WHITE));
        packageLabel.setFontScale(SCREEN_HEIGHT/200);
        textTable.add(packageLabel).expandX().padTop(10);
        textTable.setPosition(SCREEN_WIDTH/ 2f, SCREEN_HEIGHT-(SCREEN_HEIGHT*0.07f));
        textTable.row();

        //Label som holder styr på tida
        timeLabel = new Label("Time: ", new Label.LabelStyle(packageFont, Color.WHITE));
        timeLabel.setFontScale(SCREEN_HEIGHT/200);
        textTable.add(timeLabel).expandY().padTop(10);




        // BUTTONS!!!!

        pauseBtn.setSize(SCREEN_WIDTH / 8f, SCREEN_WIDTH / 8f);
        pauseBtn.setPosition(0, SCREEN_HEIGHT - buttonHeight);
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
        rightBtn.setPosition(buttonWidth * 1.2f, 0);
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
        boostBtn.setPosition(SCREEN_WIDTH - buttonWidth, 0);
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
        flameBtn.setPosition(SCREEN_WIDTH - buttonWidth, buttonHeight);
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
        stage.addActor(textTable);
        //stage.addActor(timeTable);

        Gdx.input.setInputProcessor(stage);
    }



    @Override
    public void update(float dt) {
        cam.update();
        plane.update(dt);
        boat.update(dt);
        handlePlaneOutsideScreen();
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

        timeLabel.setText("Time: " + seconds);
        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        pauseBtnSkin.dispose();
        leftBtnSkin.dispose();
        rightBtnSkin.dispose();
        boostBtnSkin.dispose();
        flameBtnSkin.dispose();
        stage.dispose();
    }

    public void handlePlaneOutsideScreen() {
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
        pauseBtn = createButton(path, height, startTime, width, height);
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
        leftBtn = createButton(path, height, startTime, width, height);
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
        rightBtn = createButton(path, height, startTime, width, height);
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
        boostBtn = createButton(path, height, startTime, width, height);
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
        flameBtn = createButton(path, height, startTime, width, height);
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
        Location Oslo = new Location("Oslo", 1512, 1278);
        Location Istanbul = new Location("Istanbul", 1656, 1062);
        Location Lagos = new Location("Lagos", 1476, 792);
        Location Manila = new Location("Manila", 2412, 839);
        Location NewYork = new Location("New York", 828, 1080);
        Location SaoPaulo = new Location("Sao Paulo", 1080, 540);
        locations[0] = Oslo;
        locations[1] = Istanbul;
        locations[2] = Lagos;
        locations[3] = Manila;
        locations[4] = NewYork;
        locations[5] = SaoPaulo;

     }
    //Function to generate random number
    private int generateRandomNumber(){
        return (int) Math.floor(Math.random() * locations.length);
    }

    //Function to check for collision. When the plane hits the package, the render function stops drawing the first package, and the delivery location starts being drawn.

    public void checkPackageCollision(Plane plane, Package package2) {

        if (package2 == pack) {
            Rectangle rect1 = new Rectangle(plane.getxPos(), plane.getyPos(), plane.getPlaneWidth()/3, plane.getPlaneHeight()/3);
            Rectangle rect2 = new Rectangle(package2.getX(), package2.getY(), package2.getWidth(), package2.getHeight());
            if (Intersector.overlaps(rect1, rect2)) {
                showPackage = false;
                showDestination = false;
            }
        }

        else if (package2 == pack2) {
            Rectangle rect1 = new Rectangle(plane.getxPos(), plane.getyPos(), plane.getPlaneWidth()/3, plane.getPlaneHeight()/3);
            Circle circle = new Circle(package2.getX() + package2.getWidth() / 3f, package2.getY() + package2.getHeight() / 3f, package2.getWidth() / 3f);
            if (Intersector.overlaps(circle, rect1)) {
                showPackage = false;
                showDestination = false;
                gsm.push(new VictoryState(gsm, database));
                elapsedTime = seconds;
            }
        }
    }

    //Function to initialize the package
    //Function to initialize the package
    public Package initializePackage(Package newPackage, int packNum, String texture){

        int randomNum = generateRandomNumber();
        newPackage = new Package(locations[randomNum].getLocationName(), locations[randomNum].getX(),
                locations[randomNum].getY(), 72, 72, new TextureRegion(new Texture(texture)), true);

        if(packNum == 0) {
            packageIndex = randomNum;
        }
        else {
            if(randomNum == packageIndex) {
                randomNum = generateRandomNumber();
            }
            destinationIndex = randomNum;
        }

        return newPackage;
    }


    //Function to handle logic around packages and locations
    public void packageLogic(SpriteBatch sb){

        //This is true by default, and the first package is drawn (pick up point)
        if (showPackage) {
            pack.draw(sb);
            checkPackageCollision(plane,pack);
        }
        //This is false by default but is made true, when the plane hits the pick up point
        else if (showDestination) {
            pack2.draw(sb);
            checkPackageCollision(plane,pack2);

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
            this.pack2 = initializePackage(pack2, 1, "objects/Target1.png");
            packageLabel.setText("Deliver the package to " + pack2.getCity());
            showDestination = true;

        }
    }

    
}
