package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.API;

public class MenuState extends State{
    
    private Texture background;
    private Skin startGameButtonSkin;
    private Skin tutorialSkin;
    private Skin scoreboardSkin;
    private Button startGameButton;
    private Button tutorialButton;
    private Button scoreboardButton;
    private GameStage stage;
    private API database;

    public MenuState(final GameStateManager gsm, API Database) {
        super(gsm);
        database = Database;
        background = new Texture("gamescreens/mapClean.jpg");
        cam.setToOrtho(false, background.getWidth(),background.getHeight());
    
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
        startGameButton.setPosition(180, 250);
        startGameButton.setSize(304, 91);
        startGameButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new GameState(gsm));
                System.out.println("Button Pressed");
                return true;
            }
        });

        tutorialButton.setPosition(180, 150);
        tutorialButton.setSize(304, 91);
        tutorialButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new TutorialState(gsm));
                System.out.println("Button Pressed");
                return true;
            }
        });

        scoreboardButton.setPosition(180, 50);
        scoreboardButton.setSize(304, 91);
        scoreboardButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new ScoreboardState(gsm, database));
                System.out.println("Button Pressed");
                return true;
            }
        });

        // Add the button to the stage
        stage.addActor(startGameButton);
        stage.addActor(tutorialButton);
        stage.addActor(scoreboardButton);
        Gdx.input.setInputProcessor(stage);
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
        sb.draw(background,0,0);
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            gsm.set(new GameState(gsm));
        }
    }
}
