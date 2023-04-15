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
        startGameButton.setSize(width/4f, height/7f);
        startGameButton.setPosition(width/2f-startGameButton.getWidth()/2f, height*6f/10f);
        
        startGameButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new SelectionState(gsm, database));
                System.out.println("Button Pressed");
                return true;
            }
        });

        tutorialButton.setSize(width/4f, height/7f);
        tutorialButton.setPosition(width/2f-tutorialButton.getWidth()/2f, height*4f/10f);
        tutorialButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new TutorialState(gsm, database));
                System.out.println("Button Pressed");
                return true;
            }
        });

        scoreboardButton.setSize(width/4f, height/7f);
        scoreboardButton.setPosition(width/2f-scoreboardButton.getWidth()/2f, height*2f/10f);
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            gsm.set(new GameState(gsm, database));
        }
    }
}
