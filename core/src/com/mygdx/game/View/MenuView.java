package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.API;

public class MenuView extends State{
    
    private Texture background;
    private Skin startGameButtonSkin;
    private Skin tutorialSkin;
    private Skin scoreboardSkin;
    private Button startGameButton;
    private Button tutorialButton;
    private Button scoreboardButton;
    private GameStage stage;
    private API database;

    public MenuView(final GameStateManager gsm, API Database) {
        super(gsm);
        database = Database;
        cam.setToOrtho(false, width,height);
        cam.zoom = (float)1;
        cam.translate(0, 0);
        background = new Texture("gamescreens/mapClean.jpg");

        // Create a stage
        stage = new GameStage();
    
        // Load a skin from a JSON file
        startGameButtonSkin = new Skin(Gdx.files.internal("buttons/menu/startGame/startGame.json"));
        tutorialSkin = new Skin(Gdx.files.internal("buttons/menu/tutorial/tutorial.json"));
        scoreboardSkin = new Skin(Gdx.files.internal("buttons/menu/scoreboard/scoreboard.json"));

        // Create a text button with a label
        startGameButton = new Button(startGameButtonSkin);
        tutorialButton = new Button(tutorialSkin);
        scoreboardButton = new Button(scoreboardSkin);
    
        // Set the button's position and size
        startGameButton.setSize(width/2f, height/3.5f);
        startGameButton.setPosition(width/2f-startGameButton.getWidth()/2f, height*6f/10f+20);

        tutorialButton.setSize(width/2f, height/3.5f);
        tutorialButton.setPosition(width/2f-tutorialButton.getWidth()/2f, height*4f/10f);

        scoreboardButton.setSize(width/2f, height/3.5f);
        scoreboardButton.setPosition(width/2f-scoreboardButton.getWidth()/2f, height*2f/10f-20);

        // Add the button to the stage
        stage.addActor(startGameButton);
        stage.addActor(tutorialButton);
        stage.addActor(scoreboardButton);
        Gdx.input.setInputProcessor(stage);
    }
    public Button getSelectionButton() {
        return startGameButton;
    }
    public Button getTutorialButton() {
        return tutorialButton;
    }

    public Button getScoreBoardButton(){
        return scoreboardButton;
    }
    @Override
    public void update(float dt) {
        cam.update();
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0,width,height);
        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
        startGameButtonSkin.dispose();
        tutorialSkin.dispose();
        scoreboardSkin.dispose();
    }

    public void handleInput() {
    }
}
