package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;

public class GameScreen implements Screen {

    //screen
    private Camera camera;
    private Viewport viewport;

    //graphics
    private SpriteBatch batch;
    private Sprite sprite;

    private Texture background;
    private Texture playerPlaneTexture;

    //World Parameters
    private final int WorldWidth = 1000;
    private final int WorldHeight = 700;

    //game Objects
    private playerPlane playerPlane;



    GameScreen(){

        //Camera
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WorldWidth,WorldHeight,camera);

        //Textures
        background = new Texture("MapClean.jpg");
        playerPlaneTexture = new Texture("Plane.png");


        //set up Game Object
        playerPlane = new playerPlane(500,400,WorldWidth/2,
                WorldHeight/2,4,playerPlaneTexture);
        batch = new SpriteBatch();
        sprite = new Sprite(playerPlaneTexture);

    }



    @Override
    public void render(float deltaTime) {
        batch.begin();

        //Background
        batch.draw(background,0,0,WorldWidth,WorldHeight);

        //player plane
        batch.draw(playerPlaneTexture,playerPlane.xPos,playerPlane.yPos,playerPlane.width,playerPlane.height);
        playerPlane.yPos += playerPlane.movementSpeed;

        batch.end();

        if (playerPlane.yPos >= WorldHeight- playerPlane.height/2-50 || playerPlane.yPos <= -150){
            playerPlane.movementSpeed = -playerPlane.movementSpeed;
        }


    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void show() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


}
