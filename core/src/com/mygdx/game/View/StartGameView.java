package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.API;

public class StartGameView extends State {

    private API database;
    private Texture background;
    private Button startButton;
    private GameStage stage;
    private Skin startSkin;

    public StartGameView(final GameStateManager gsm, API Database) {

        super(gsm);
        this.database = Database;

        background = new Texture("gamescreens/startPage.jpg");

        //Button
        startSkin = new Skin(Gdx.files.internal("buttons/startscreen/playBtn/playBtn.json"));
        startButton = new Button(startSkin);
        startButton.setSize(Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() / 4f);
        startButton.setPosition(Gdx.graphics.getWidth() / 2f - startButton.getWidth() / 2f, Gdx.graphics.getHeight() / 2.5f - startButton.getHeight() / 2f);


        //New Stage
        stage = new GameStage();
        stage.addActor(startButton);
        Gdx.input.setInputProcessor(stage);

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

    public Button getStartButton(){
        return this.startButton;
    }

    @Override
    public void dispose() {
        this.stage.dispose();
        this.startSkin.dispose();
    }
}
