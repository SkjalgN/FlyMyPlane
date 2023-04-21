package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.Gdx;


public class FontManager {

    private static FontManager instance;
    private BitmapFont customFont;


    private FontManager() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/AmaticSC-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24*5; // Set the font size here
        customFont = generator.generateFont(parameter);
        generator.dispose();
    }

    public static FontManager getInstance() {
        if (instance == null) {
            instance = new FontManager();
        }
        return instance;
    }

    public BitmapFont getFont() {
        return customFont;
    }
    public void scale(int size) {
        customFont.getData().setScale(size);
    }
}