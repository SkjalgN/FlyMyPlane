package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.API;
import com.badlogic.gdx.audio.Music;

public class StartGameState extends State {

    private API database;
    private Skin startSkin;
    private Button startButton;
    private GameStage stage;
    private Texture background;

    public StartGameState(final GameStateManager gsm, API Database){
        super(gsm);
        this.database = Database;
        background = new Texture("gamescreens/startPage.jpg");

        //start music
        manager.get("Audio/background.ogg",Music.class).setLooping(true);
        manager.get("Audio/background.ogg",Music.class).play();

        // Create a stage
        stage = new GameStage();

        //Load a skin from a JSON file
        startSkin = new Skin(Gdx.files.internal("buttons/playBtn/playBtn.json"));

        //Create a button
        startButton = new Button(startSkin);

        //Set button position, size and function
        startButton.setSize(width/4f, height/4f);
        startButton.setPosition(width/2f-startButton.getWidth()/2f,height/2.5f-startButton.getHeight()/2f);
        
        startButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new MenuState(gsm, database));
                System.out.println("Button Pressed");
                return true;
            }
        });
        stage.addActor(startButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float dt) {
        
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, width, height);
        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        startSkin.dispose();
    }
}
