package com.mygdx.game;

import com.mygdx.game.Model.Score;

import java.util.ArrayList;

public interface API {
    void submitHighscore(Score score);
    void getHighscores(ArrayList<Score> dataHolder);

    public void FirstFireBaseTest();

    public void someFunction();


}
