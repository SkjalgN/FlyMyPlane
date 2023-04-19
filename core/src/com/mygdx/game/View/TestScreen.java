package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Controller.GameController;


public class TestScreen implements Screen {
    
    private final GameController gameController;
    private final Stage stage;
    private final Color backgroundColor = Color.WHITE;
    
    public TestScreen(GameController gameController) {
        this.gameController = gameController;
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
    }
    
    @Override
    public void show() {
        // create background
        Image background = new Image(new TextureRegion(new Texture(Gdx.files.internal("objects/Target1.png"))));
        background.setColor(backgroundColor);
        background.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(background);
    }
    
    @Override
    public void render(float delta) {
        // clear screen
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // render stage
        stage.act(delta);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    
    @Override
    public void pause() {
        // empty
    }
    
    @Override
    public void resume() {
        // empty
    }
    
    @Override
    public void hide() {
        // empty
    }
    
    @Override
    public void dispose() {
        stage.dispose();
    }
    
    // other methods...
}
