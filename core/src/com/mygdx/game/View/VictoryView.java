
package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
    private Score score1;
    private Score score2;
    private Score winner;
    private Score loser;



    public VictoryView(final GameStateManager gsm, final API database, Score score1, Score score2) {
        super(gsm);
        this.database = database;
        background = new Texture("gamescreens/victoryScreen.jpg");
        cam.setToOrtho(false, width, height);
        cam.zoom = (float) 1.0;
        cam.translate(0, 0);
        customFont = FontManager.getInstance().getFont();
        this.score1 = score1;
        this.score2 = score2;

        if(score1.getScore() < score2.getScore()) {
            winner = score1;
            loser = score2;
        } else {
            winner = score2;
            loser = score1;
        }

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

    public Button getNextButton() {
        return nextBtn;
    }

    @Override
    public void update(float dt) {
        cam.update();
    }

    public void renderScore(SpriteBatch sb) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, width, height);
        customFont.setColor(Color.BLACK);
        customFont.getData().setScale(.6f);

        // Calculate the x and y coordinates for the center of the screen
        float xCenter = width / 2f;
        float yCenter = height - (height / 4f);

        // Calculate the width and height of the player texts
        GlyphLayout playerLayout1 = new GlyphLayout(customFont, "Player: " + winner.getName());
        GlyphLayout playerLayout2 = new GlyphLayout(customFont, "Player: " + loser.getName());

        // Calculate the x coordinates for the left and right side of the middle
        float gap = 20f; // Adjust the gap between the scores to your liking
        float xLeft = xCenter - gap / 2 - Math.max(playerLayout1.width, playerLayout2.width);
        float xRight = xCenter + gap / 2;

        // Determine which score is higher and which is lower

            customFont.draw(sb, "Player: " + winner.getName(), xRight, yCenter);
            customFont.draw(sb, "Player: " + loser.getName(), xLeft - playerLayout2.width, yCenter);

            customFont.draw(sb, "Score: " + winner.getScore(), xRight, yCenter - playerLayout1.height);
            customFont.draw(sb,  "Score: " + loser.getScore(), xLeft - playerLayout2.width, yCenter - playerLayout2.height);
            //this.winner = new Score(score1.getScore(),score1.getName());

        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public Score getWinner() {
        return this.winner;
    }

    @Override
    public void dispose() {
        background.dispose();
        nextBtnSkin.dispose();
        stage.dispose();
    }
}
