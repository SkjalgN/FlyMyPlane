package com.mygdx.game.View;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TutorialState extends State{
    private SpriteBatch batch;
    private BitmapFont font = new BitmapFont();
    private Texture background;
    TextButton.TextButtonStyle textButtonStyle;

    private float WIDTH = Gdx.graphics.getWidth();
    private float HEIGHT = Gdx.graphics.getHeight();
    

    public TutorialState(GameStateManager gsm) {
        super(gsm);
        batch = new SpriteBatch();
        System.out.println(WIDTH);
        System.out.println(HEIGHT);
        cam.setToOrtho(false, WIDTH * 4, HEIGHT * 4);
        
        background = new Texture("MapClean.jpg");
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, WIDTH/4, HEIGHT/4);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.WHITE;


        TextButton textButton = new TextButton("Sjekk her da", textButtonStyle);
        textButton.setPosition(WIDTH, WIDTH);
        textButton.draw(sb, 1);
        sb.end();

    }

    @Override
    public void dispose() {

        background.dispose();

    }
}
