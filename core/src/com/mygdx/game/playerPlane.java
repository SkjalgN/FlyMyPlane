package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class playerPlane {

    //Plane characteristics
    int movementSpeed; //world units per seconds
    int shield;

    //position & dimension
    int xPos,yPos; //lower left corner of image
    int width, height;

    //graphics
    Texture planeTexture;

    //Constructor
    public playerPlane(int width, int height,
                       int xCentre, int yCentre,
                       int movementSpeed,
                       Texture planeTexture) {
        this.xPos = xCentre - width/2;
        this.yPos = yCentre - height/2;
        this.width = width;
        this.height = height;
        this.movementSpeed = movementSpeed;
        this.planeTexture = planeTexture;
    }


    public void draw(Batch batch){
        batch.draw(planeTexture,xPos,yPos,width,height);
    }

}
