package com.mygdx.game.View;

import java.util.ArrayList;
import java.util.List;

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
import com.mygdx.game.FontManager;
import com.mygdx.game.GameOverEvent;
import com.mygdx.game.GameOverListener;
import com.mygdx.game.Model.Location;
import com.mygdx.game.Model.Map;
import com.mygdx.game.Model.Package;
import com.mygdx.game.Model.Plane;
import com.mygdx.game.Model.Boat;
import com.badlogic.gdx.utils.TimeUtils;

public class GameView extends State {

    private Texture background;
    private Texture backgroundWater;
    private Plane plane;
    private Boat boat;
    private Map map;


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


    private List<GameOverListener> gameOverListeners = new ArrayList<>();

    
    private int packageIndex;
    private int destinationIndex;

    //Booleans to show and hide packages and locations
    private boolean showPackage = true;
    private boolean showDestination = false;

    public GameView(final GameStateManager gsm, final API database,int skinVar) {
        super(gsm);
        this.database = database;
        background = new Texture("gamescreens/theMap.jpg");
        backgroundWater = new Texture("gamescreens/water.jpg");
        cam.setToOrtho(false, background.getWidth(), background.getHeight());
        cam.zoom = (float) 0.18;
        plane = new Plane(background.getWidth() / 2 - 25, background.getHeight() / 2 - 25, 3, 1, 50, 50,
                skinVar);
        
        boat = new Boat(2700, 2700, 1, 1, 300, 300, new TextureRegion(new Texture("objects/boat.png")));
        map = new Map(plane);
        

        
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


        this.startTime = TimeUtils.millis();
        packageFont = FontManager.getInstance().getFont();





        //Table til å legge til packageLabel på
        textTable = new Table();
        packageFont = FontManager.getInstance().getFont();
        packageLabel = new Label("Get the package in: " + map.getPackageLocation(), new Label.LabelStyle(packageFont, Color.WHITE));
        packageLabel.setFontScale(SCREEN_HEIGHT/900);
        textTable.add(packageLabel).expandX().padTop(10);
        textTable.setPosition(SCREEN_WIDTH/ 2f, SCREEN_HEIGHT-(SCREEN_HEIGHT*0.13f));
        textTable.row();

        //Label som holder styr på tida
        timeLabel = new Label("Time: ", new Label.LabelStyle(packageFont, Color.WHITE));
        timeLabel.setFontScale(SCREEN_HEIGHT/900);
        textTable.add(timeLabel).expandY().padTop(5);




        // BUTTONS!!!!

        pauseBtn.setSize(SCREEN_WIDTH / 8f, SCREEN_WIDTH / 8f);
        pauseBtn.setPosition(0, SCREEN_HEIGHT - buttonHeight);
        

        leftBtn.setSize(SCREEN_WIDTH / 8f, SCREEN_WIDTH / 8f);
        leftBtn.setPosition(0, 0);
        

        rightBtn.setSize(SCREEN_WIDTH / 8f, SCREEN_WIDTH / 8f);
        rightBtn.setPosition(buttonWidth * 1.2f, 0);
        

        boostBtn.setSize(SCREEN_WIDTH / 8f, SCREEN_WIDTH / 8f);
        boostBtn.setPosition(SCREEN_WIDTH - buttonWidth, 0);

        flameBtn.setSize(SCREEN_WIDTH / 8f, SCREEN_WIDTH / 8f);
        flameBtn.setPosition(SCREEN_WIDTH - buttonWidth, buttonHeight);
        
        stage.addActor(pauseBtn);
        stage.addActor(leftBtn);
        stage.addActor(rightBtn);
        stage.addActor(boostBtn);
        stage.addActor(flameBtn);
        stage.addActor(textTable);
        //stage.addActor(timeTable);

        Gdx.input.setInputProcessor(stage);
    }

    public Button getPauseButton(){
        return this.pauseBtn;
    }
    public Button getLeftButton(){
        return this.leftBtn;
    }
    public Button getRightButton(){
        return this.rightBtn;
    }
    public Button getBoostButton(){
        return this.boostBtn;
    }
    public Button getFlameButton(){
        return this.flameBtn;
    }
    public Plane getPlane(){
        return this.plane;
    }
    

    public void addGameOverListener(GameOverListener listener) {
        gameOverListeners.add(listener);
    }

    private void fireGameOverEvent() {
        GameOverEvent event = new GameOverEvent(this);
        for (GameOverListener listener : gameOverListeners) {
            listener.onGameOver(event);
        }
    }
    public void removeGameOverListener(GameOverListener listener) {
        gameOverListeners.remove(listener);
    }

    private void checkGameOver(){
        if (map.gameOver()){
            fireGameOverEvent();
        }
    }

    public void setInputProcessorManually(){
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float dt) {
        cam.update();
        plane.update(dt);
        boat.update(dt);
        map.update(dt);
        checkGameOver();
        handlePlaneOutsideScreen();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(backgroundWater, -4000, -1000, 16000, 8000);
        sb.draw(background, 0, 0);

        //Nytt, fiks dette
        if (map.pickUpState()){
            map.drawPackage(sb);
        }
        else {
            map.drawTarget(sb);
            packageLabel.setText("Deliver the package to " + map.getTargetLocation());
        }

        boat.draw(sb);
        plane.draw(sb);

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


    public int getElapsedTime(){
        long elapsedTime = TimeUtils.timeSinceMillis(startTime);
        int seconds = (int) (elapsedTime / 1000);
        return seconds;
    }

    //TODO: ALT MED PLANES KAN FLYTTES UT FRA DENNE FUNKSJONEN!

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

     
   

    




    
}
