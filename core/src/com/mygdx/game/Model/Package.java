package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Package {

    private String city;
    private Rectangle bounds;
    private float xPos;
    private float yPos;
    private float width = 72;
    private float height = 72;
    private TextureRegion packageTextureRegion;
    private boolean isTarget;

    //constructor

    public Package(String city, float xPos, float yPos, TextureRegion packageTextureRegion, boolean isTarget) {
        this.city = city;
        this.bounds = new Rectangle(xPos-200, yPos-200, 400, 400);
        this.xPos = xPos;
        this.yPos = yPos;
        this.packageTextureRegion = packageTextureRegion;
        this.isTarget = isTarget;
    }

    //Getters

    public String getCity() {
        return city;
    }

    public float getWidth() {

        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }



    public TextureRegion getPackageTextureRegion() {
        return packageTextureRegion;
    }

    public boolean isTarget() {
        return isTarget;
    }
    //setters

    public void setCity(String city) {
        this.city = city;
    }

    public void setX(float x) {
        this.xPos = x;
    }

    public void setY(float y) {
        this.yPos = y;
    }

    public void setPackageTextureRegion(TextureRegion packageTextureRegion) {
        this.packageTextureRegion = packageTextureRegion;
    }

    public void draw(Batch batch) {

            batch.draw(packageTextureRegion, xPos, yPos,width,height);

    }
/* 
    public void setActive(boolean active) {
        this.active = active;
    }
*/
}


