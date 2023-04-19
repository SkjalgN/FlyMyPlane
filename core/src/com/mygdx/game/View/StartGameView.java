package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygdx.game.API;
import com.mygdx.game.Model.Buttons.StartGameButton;
import com.badlogic.gdx.audio.Music;

public class StartGameView extends State {

    private API database;
    // private Skin startSkin;
    // private Button startButton;
    private Texture background;
    private BitmapFont customFont;
    private StartGameButton startGameButton;

    public StartGameView(final GameStateManager gsm, API Database) {

        super(gsm);
        this.database = Database;

        background = new Texture("gamescreens/startPage.jpg");

        this.startGameButton = new StartGameButton();
        this.startGameButton.showButton();

        // Load a custom font KAN SIKKERET FJERNES
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
                Gdx.files.internal("Amatic_SC/AmaticSC-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24; // Set the font size here
        this.customFont = generator.generateFont(parameter);
        generator.dispose();

        // start music
        manager.get("Audio/background.ogg", Music.class).setLooping(true);
        manager.get("Audio/background.ogg", Music.class).play();

    }

    public Button getStartGameButton() {
        return this.startGameButton.getButton();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, width, height);
        sb.end();

        this.startGameButton.getStage().act(Gdx.graphics.getDeltaTime());
        this.startGameButton.getStage().draw();
    }

    @Override
    public void dispose() {
        this.startGameButton.getStage().dispose();
        this.startGameButton.dispose();
    }
}
