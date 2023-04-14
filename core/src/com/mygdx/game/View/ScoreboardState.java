package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.API;
import com.mygdx.game.Model.Score;

import java.util.ArrayList;

public class ScoreboardState extends State{
    private API database;
    private ArrayList<Score> scoreboardList;
    private Texture background;
    protected ScoreboardState(GameStateManager gsm, API Database) {
        super(gsm);
        this.database = Database;
        background = new Texture("gamescreens/scoreboard.jpg");
        scoreboardList = new ArrayList<>();
        database.getHighscores(scoreboardList);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(6);
        sb.begin();
        sb.draw(background,0,0);
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, "-----------------------------------");
        float centerX = (Gdx.graphics.getWidth() - layout.width) / 2f;

        // iterate over the scoreboard list and draw each score
        float y = Gdx.graphics.getHeight() - 150; // start drawing scores from the top of the screen
        int index = 0;
        for (Score score : scoreboardList) {
            String scoreText = score.getName() + ": " + score.getScore();
            font.draw(sb, scoreText, centerX, y);
            y -= 140; // space out the scores vertically
            index++;
            if (index > 7){
                break;
            }
        }
        sb.end();
        font.dispose();
    }

    @Override
    public void dispose() {

    }
}
