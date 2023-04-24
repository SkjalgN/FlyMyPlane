package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Null;
import com.mygdx.game.API;
import com.mygdx.game.Model.Buttons.StartGameButton;
import com.badlogic.gdx.audio.Music;

public class StartGameView extends State {

    private API database;
    // private Skin startSkin;
    // private Button startButton;
    private Texture background;
    private StartGameButton startGameButton;
    private GameStage stage;

    public StartGameView(final GameStateManager gsm, API Database) {

        super(gsm);
        this.database = Database;

        background = new Texture("gamescreens/startPage.jpg");

        //Button
        startGameButton = new StartGameButton();


        //New Stage
        stage = new GameStage();
        stage.addActor(startGameButton.getButton());
        Gdx.input.setInputProcessor(stage);


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

        if (stage != null) {
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();

        }
    }

    @Override
    public void dispose() {
        this.startGameButton.dispose();
        this.stage.dispose();
    }
}
