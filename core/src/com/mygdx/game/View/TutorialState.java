package com.mygdx.game.View;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.API;


public class TutorialState extends State{
    private SpriteBatch batch;
    private Texture background;
    private Button nextBtn;
    private Skin nextBtnSkin;
    private GameStage stage;
    private API database;
    TextButton.TextButtonStyle textButtonStyle;
    

    public TutorialState(GameStateManager gsm) {
        super(gsm);
        batch = new SpriteBatch();
        background = new Texture("gamescreens/tutorial1.jpg");

                // Create a stage
                stage = new GameStage();

                // Load a skin from JSON file
                nextBtnSkin = new Skin(Gdx.files.internal("buttons/game/rightBtn/rightBtn.json"));
        
                // Create a button
                nextBtn = new Button(nextBtnSkin);
        
                // Set button position, size and function
                nextBtn.setSize(width / 8f, width / 8f);
                nextBtn.setPosition(width - nextBtn.getWidth() * 1.2f, height / 2f - (nextBtn.getHeight() / 2f));
                nextBtn.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        gsm.set(new MenuState(gsm, database));
                        System.out.println("Button Pressed");
                        return true;
                    }
        
                });
        
                stage.addActor(nextBtn);
                Gdx.input.setInputProcessor(stage);
            }
    

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(background, 0,0,width,height);
        sb.end();

    }

    @Override
    public void dispose() {

        background.dispose();

    }
}
