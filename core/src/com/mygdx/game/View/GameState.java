package com.mygdx.game.View;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;




public class GameState extends ApplicationAdapter {

    int posX = 0;
    int posY = 0;
    int velX = 4;
    int velY = 4;

    SpriteBatch batch;
    TextureRegion img;
    Sprite sprite;

    private Plane plane;
     

    @Override
    public void create () {
        batch = new SpriteBatch();
    }

    @Override
    public void render () {
        ScreenUtils.clear(2f, 0, 2f, 1);
        batch.begin();
        
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        }

}