package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Plane {

    //Plane characteristics
    int movementSpeed; //world units per seconds
    int shield;

    //position & dimension
    int xPos,yPos; //lower left corner of image
    int width, height;

    //graphics
    Texture planeTexture;

    //Constructor
    public Plane(int width, int height,
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

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public int getShield() {
        return shield;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
