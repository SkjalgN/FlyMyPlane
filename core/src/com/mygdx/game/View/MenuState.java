package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuState extends State{
    
    private Texture background;
    private Button playButton;
    private Skin skin;
    private GameStage stage;

    public MenuState(final GameStateManager gsm) {
        super(gsm);
        background = new Texture("gamescreens/mapClean.jpg");
        cam.setToOrtho(false, background.getWidth(),background.getHeight());
    
        // Create a stage
        stage = new GameStage();
    
        // Load a skin from a JSON file
        skin = new Skin(Gdx.files.internal("skin/PlayButton.json"));
    
        // Create a text button with a label
        playButton = new Button(skin);
    
        // Set the button's position and size
        playButton.setPosition(200, 150);
        playButton.setSize(200, 200);
        playButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new GameState(gsm));
                System.out.println("Button Pressed");
                return true;
            }
        });
    
        // Add the button to the stage
        stage.addActor(playButton);
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
        skin.dispose();
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            gsm.set(new GameState(gsm));
        }
    }
}
