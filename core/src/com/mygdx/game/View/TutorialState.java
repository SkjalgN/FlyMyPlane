package com.mygdx.game.View;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TutorialState extends State{
    private SpriteBatch batch;
    private BitmapFont font = new BitmapFont();
    

    protected TutorialState(GameStateManager gsm) {
        super(gsm);
        batch = new SpriteBatch();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        font.draw(batch, "TutorialStaten!", 200 / 2, 200 / 2);
        batch.end();

    }

    @Override
    public void dispose() {

    }
}
