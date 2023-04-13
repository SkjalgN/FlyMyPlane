package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.API;
import com.mygdx.game.Model.Score;

import java.util.ArrayList;

public class ScoreboardState extends State{
    private API database;
    private ArrayList<Score> scoreboardList;
    protected ScoreboardState(GameStateManager gsm, API Database) {
        super(gsm);
        this.database = Database;
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
        sb.begin();
        // iterate over the scoreboard list and draw each score
        int y = Gdx.graphics.getHeight() - 50; // start drawing scores from the top of the screen
        for (Score score : scoreboardList) {
            String scoreText = score.getName() + ": " + score.getScore();
            font.draw(sb, scoreText, 50, y);
            y -= 20; // space out the scores vertically
        }
        sb.end();
        font.dispose();
    }

    @Override
    public void dispose() {

    }
}
