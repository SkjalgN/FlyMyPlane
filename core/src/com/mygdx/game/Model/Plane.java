package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.View.GameState;

public class Plane {

    private float xPos,yPos;
    private float speed;
    private float angle;
    private float planeWidth;
    private float planeHeight;
    private TextureRegion planeTextureRegion;

    public Plane(float xPos, float yPos,float speed, float angle, float planeWidth, float planeHeight, TextureRegion planeTextureRegion) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = speed;
        this.angle = angle;
        this.planeWidth = planeWidth;
        this.planeHeight = planeHeight;
        this.planeTextureRegion = planeTextureRegion;
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
        batch.draw(planeTextureRegion, xPos, yPos, planeWidth/2, planeHeight/2, planeWidth, planeHeight, 1, 1, (float) Math.toDegrees(angle), true);
    }
    
}

