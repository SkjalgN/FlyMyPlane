package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;


public class Boat {

    private float xPos,yPos;
    private float speed;
    private float angle;
    private float boatWidth;
    private float boatHeight;
    private TextureRegion boatTextureRegion;

    public Boat(float xPos, float yPos, float speed, float angle, float boatWidth, float boatHeight, TextureRegion boatTextureRegion) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = speed;
        this.angle = angle;
        this.boatWidth = boatWidth;
        this.boatHeight = boatHeight;
        this.boatTextureRegion = boatTextureRegion;
    }

    public void rotate(float angle) {
        this.angle += angle;
    }

    public void update(float delta) {
        xPos += speed * Math.cos(angle);
        yPos += speed * Math.sin(angle);
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float x) {
        this.xPos = x;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float y) {
        this.yPos = y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getBoatWidth() {
        return boatWidth;
    }

    public void setBoatWidth(float boatWidth) {
        this.boatWidth = boatWidth;
    }

    public float getBoatHeight() {
        return boatHeight;
    }

    public void setBoatHeight(float boatHeight) {
        this.boatHeight = boatHeight;
    }

    public TextureRegion getBoatTextureRegion() {
        return boatTextureRegion;
    }

    public void setBoatTextureRegion(TextureRegion boatTextureRegion) {
        this.boatTextureRegion = boatTextureRegion;
    }

    public void draw(Batch batch) {
        batch.draw(boatTextureRegion, xPos, yPos, boatWidth/2, boatHeight/2, boatWidth, boatHeight, 1, 1, (float) Math.toDegrees(angle), true);
    }
}

