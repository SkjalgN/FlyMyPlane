package com.mygdx.game.View;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.API;

public class TutorialState extends State {
    private SpriteBatch batch;
    private TextureRegion background[];
    private int backgroundVar = 0;
    private Button nextBtn;
    private Button exitBtn;
    private Button backBtn;
    private Skin nextBtnSkin;
    private Skin exitBtnSkin;
    private Skin backBtnSkin;
    private GameStage stage;
    private API database;
    TextButton.TextButtonStyle textButtonStyle;

    public TutorialState(final GameStateManager gsm, final API database) {
        super(gsm);
        batch = new SpriteBatch();
        background = new TextureRegion[2];
        background[0] = new TextureRegion(new Texture("gamescreens/tutorial1.jpg"));
        background[1] = new TextureRegion(new Texture("gamescreens/tutorial2.jpg"));
        backgroundVar = 0;
        // Create a stage
        stage = new GameStage();

        // Load a skin from JSON file
        nextBtnSkin = new Skin(Gdx.files.internal("buttons/tutorial/next/next.json"));
        exitBtnSkin = new Skin(Gdx.files.internal("buttons/tutorial/exit/exit.json"));
        backBtnSkin = new Skin(Gdx.files.internal("buttons/tutorial/back/back.json"));

        // Create a button
        nextBtn = new Button(nextBtnSkin);
        exitBtn = new Button(exitBtnSkin);
        backBtn = new Button(backBtnSkin);

        // Set button position, size and function
        nextBtn.setSize(SCREEN_WIDTH / 7f, SCREEN_HEIGHT / 11f);
        nextBtn.setPosition(SCREEN_WIDTH * 5f / 8f, SCREEN_HEIGHT * 1f / 8f);
        nextBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Button Pressed");
                nextBtn.setVisible(false);
                backBtn.setVisible(true);
                backgroundVar = 1;
                return true;
            }
        });

        exitBtn.setSize(SCREEN_WIDTH / 7f, SCREEN_HEIGHT / 11f);
        exitBtn.setPosition(SCREEN_WIDTH * 1.8f / 8f, SCREEN_HEIGHT * 1f / 8f);
        exitBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new MenuState(gsm, database));
                System.out.println("Button Pressed");
                return true;
            }

        });

        backBtn.setVisible(false);
        backBtn.setSize(SCREEN_WIDTH / 7f, SCREEN_HEIGHT / 11f);
        backBtn.setPosition(SCREEN_WIDTH * 3.8f / 8f, SCREEN_HEIGHT * 1f / 8f);
        backBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backgroundVar = 0;
                nextBtn.setVisible(true);
                backBtn.setVisible(false);
                return true;
            }

        });

        stage.addActor(nextBtn);
        stage.addActor(exitBtn);
        stage.addActor(backBtn);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(background[backgroundVar], 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
    }
}
