package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Plane {

    private float xPos,yPos;
    private float speed;
    private float direction;
    private float planeWidth;
    private float planeHeight;
    private TextureRegion planeTextureRegion;

    public Plane(float xPos, float yPos, float speed, float direction, float planeWidth, float planeHeight, TextureRegion planeTextureRegion) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = speed;
        this.direction = direction;
        this.planeWidth = planeWidth;
        this.planeHeight = planeHeight;
        this.planeTextureRegion = planeTextureRegion;
    }

    public float getX() {
        return xPos;
    }

    public void setX(float x) {
        this.xPos = x;
    }

    public float getY() {
        return yPos;
    }

    public void setY(float y) {
        this.yPos = y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

    public float getPlaneWidth() {
        return planeWidth;
    }

    public void setPlaneWidth(float width) {
        this.planeWidth = width;
    }

    public float getplaneHeight() {
        return planeHeight;
    }

    public void setplaneHeight(float height) {
        this.planeHeight = height;
    }

    public TextureRegion getplaneTextureRegion() {
        return planeTextureRegion;
    }

    public void setTexture(TextureRegion texture) {
        this.planeTextureRegion = texture;
    }


    public void draw(Batch batch) {
        batch.draw(planeTextureRegion, xPos, yPos, planeWidth, planeHeight);
    }
}

