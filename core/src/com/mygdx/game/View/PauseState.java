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
import com.mygdx.game.Model.Plane;

public class PauseState extends State{
    
    private Texture background;
    private Skin continueBtnSkin;
    private Skin tutorialBtnSkin;
    private Skin exitBtnSkin;
    private Button continueBtn;
    private Button exitBtn;
    private GameStage stage;
    private API database;




    public PauseState(final GameStateManager gsm, final API database) {
        super(gsm);
        this.database = database;
        background = new Texture("gamescreens/pauseMenu.jpg");
        cam.setToOrtho(false, background.getWidth(),background.getHeight());
        cam.zoom = (float)1.0;
        cam.translate(0, 0);

        // Create a stage
        stage = new GameStage();

        // Load a skin from a JSON file
        continueBtnSkin = new Skin(Gdx.files.internal("buttons/pause/continue/continue.json"));
        exitBtnSkin = new Skin(Gdx.files.internal("buttons/pause/exit/exit.json"));

        // Create a button with the skin
        continueBtn = new Button(continueBtnSkin);
        exitBtn = new Button(exitBtnSkin);

        // Set the properties of the button
        continueBtn.setSize(width/4f, width/4f);
        continueBtn.setPosition(width/2f-(continueBtn.getWidth()/2f), height/2f-(continueBtn.getHeight()/2f));
        continueBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.pop();
                System.out.println("Button Pressed");
                return true;
            }
        });

        exitBtn.setSize(width/4f, width/9f);
        exitBtn.setPosition(width/2f - exitBtn.getWidth()/2f, height/5f);
        exitBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new MenuState(gsm, database));
                System.out.println("Button Pressed");
                return true;
            }
        });

        // Add the button to the stage
        stage.addActor(continueBtn);
        stage.addActor(exitBtn);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float dt) {
        cam.update();
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
        continueBtnSkin.dispose();
        exitBtnSkin.dispose();

    }
}
