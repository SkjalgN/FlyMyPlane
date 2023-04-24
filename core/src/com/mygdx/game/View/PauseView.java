package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class PauseView extends State {

    private Texture background;
    private Skin continueBtnSkin;
    private Skin exitBtnSkin;
    private Skin soundOnBtnSkin;
    private Skin soundOffBtnSkin;
    private Button continueBtn;
    private Button exitBtn;
    private Button soundBtn;
    private GameStage stage;

    public PauseView(final GameStateManager gsm) {
        super(gsm);
        background = new Texture("gamescreens/pauseMenu.jpg");
        cam.setToOrtho(false, width, height);
        cam.zoom = (float) 1.0;
        cam.translate(0, 0);

        // Create a stage
        stage = new GameStage();

        // Load a skin from a JSON file
        continueBtnSkin = new Skin(Gdx.files.internal("buttons/pause/continue/continue.json"));
        exitBtnSkin = new Skin(Gdx.files.internal("buttons/pause/exit/exit.json"));
        soundOnBtnSkin = new Skin(Gdx.files.internal("buttons/pause/soundOn/soundOn.json"));
        soundOffBtnSkin = new Skin(Gdx.files.internal("buttons/pause/soundOff/soundOff.json"));

        // Create a button with the skin
        continueBtn = new Button(continueBtnSkin);
        exitBtn = new Button(exitBtnSkin);
        soundBtn = new Button(soundOnBtnSkin);

        // Set the properties of the button
        continueBtn.setSize(width / 4f, width / 4f);
        continueBtn.setPosition(width / 2f - (continueBtn.getWidth() / 2f),
                height / 2f - (continueBtn.getHeight() / 2f));

        exitBtn.setSize(width / 4f, width / 9f);
        exitBtn.setPosition(width / 2f - exitBtn.getWidth() / 2f, height / 9f);

        soundBtn.setSize(width / 9f, width / 9f);
        soundBtn.setPosition(width - soundBtn.getWidth(), height-soundBtn.getHeight());
        // Add the button to the stage
        stage.addActor(continueBtn);
        stage.addActor(exitBtn);
        stage.addActor(soundBtn);
        Gdx.input.setInputProcessor(stage);
    }

    public Button getContinueButton() {
        return this.continueBtn;
    }

    public Button getExitButton() {
        return this.exitBtn;
    }

    public Button getSoundButton() {
        return this.soundBtn;
    }

    public void soundOnButton(){
        soundBtn.setSkin(soundOnBtnSkin);
    }

    public void soundOffButton(){
        soundBtn.setSkin(soundOffBtnSkin);
    }

    @Override
    public void update(float dt) {
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, width, height);
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
