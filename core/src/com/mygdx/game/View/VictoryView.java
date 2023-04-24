
package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.API;
import com.mygdx.game.FontManager;
import com.mygdx.game.Model.Score;

public class VictoryView extends State {
    private Texture background;
    private Button nextBtn;
    private Skin nextBtnSkin;
    private GameStage stage;
    private API database;
    private BitmapFont customFont;
    private Score score;


    public VictoryView(final GameStateManager gsm, final API database, Score score) {
        super(gsm);
        this.database = database;
        background = new Texture("gamescreens/victoryScreen.jpg");
        cam.setToOrtho(false, width,height);
        cam.zoom = (float)1.0;
        cam.translate(0, 0);
        customFont = FontManager.getInstance().getFont();
        this.score = score;


        // Create a stage
        stage = new GameStage();

        // Load a skin from JSON file
        nextBtnSkin = new Skin(Gdx.files.internal("buttons/game/rightBtn/rightBtn.json"));

        // Create a button
        nextBtn = new Button(nextBtnSkin);

        // Set button position, size and function
        nextBtn.setSize(width / 8f, width / 8f);
        nextBtn.setPosition(width - nextBtn.getWidth() * 1.2f, height / 2f - (nextBtn.getHeight() / 2f));

        stage.addActor(nextBtn);
        Gdx.input.setInputProcessor(stage);
    }

    public Button getNextButton(){
        return nextBtn;
    }
    @Override
    public void update(float dt) {
        cam.update();
    }

    public void renderScore(SpriteBatch sb){


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, width, height);
        customFont.setColor(Color.BLACK);
        customFont.getData().setScale(1);
        String Cong = "Congratulations!";
        String scoreText = "Player: " + score.getName() + "\t Score: " + score.getScore();
        System.out.println(scoreText);
        customFont.draw(sb, Cong, width, height);
        customFont.draw(sb, scoreText, width, height);      
        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        nextBtnSkin.dispose();
        stage.dispose();
    }
}
