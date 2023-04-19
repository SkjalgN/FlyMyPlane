package com.mygdx.game.Model.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.View.GameStage;

public class StartGameButton extends Button {
    private Button startButton;
    private Skin startSkin;


    public StartGameButton() {
        System.out.println(Gdx.graphics.getWidth() + "HER SKAL DET VÆRE NOE");
        startSkin = new Skin(Gdx.files.internal("buttons/startscreen/playBtn/playBtn.json"));
        startButton = new Button(startSkin);
        startButton.setSize(Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() / 4f);
        startButton.setPosition(Gdx.graphics.getWidth() / 2f - startButton.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2.5f - startButton.getHeight() / 2f);
    }



    public Button getButton() {
        return startButton;
    }

    public void dispose() {
        startSkin.dispose();
    }

    
}
