package com.mygdx.game.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.Model.Plane;

public class PlaneController extends InputAdapter {
    
    private Plane plane;
   
    
    public PlaneController(Plane plane) {
        this.plane = plane;
        Gdx.input.setInputProcessor(this);
    }
    
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                plane.setAngle(-1.0f); 
                break;
            case Input.Keys.RIGHT:
                plane.setAngle(1.0f); 
                break;
            default:
                break;
        }
        return true;
    }
    
    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.DOWN:
                plane.setAngle(0.0f);
                break;
            case Input.Keys.LEFT:
            case Input.Keys.RIGHT:
                plane.setAngle(0.0f);
                break;
            default:
                break;
        }
        return true;
    }
    

    
}