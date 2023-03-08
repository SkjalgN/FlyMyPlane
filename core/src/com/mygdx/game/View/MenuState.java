package com.mygdx.game.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Model.Plane;



public class MenuState extends State{
    
    private Texture background;
    private Plane plane;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, 2640, 2480);
        background = new Texture("MapClean.jpg");
        plane = new Plane(1000,1000,10,0,100,100,new TextureRegion(new Texture("plane.png")));
    }

    @Override
    public void update(float dt) {
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0);
        plane.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
