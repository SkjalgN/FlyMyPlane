package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Package {

    private String city;
    private float xPos;
    private float yPos;

    private float width;

    private float height;
    private TextureRegion packageTextureRegion;
    private boolean active;

    //constructor

    public Package(String city, float xPos, float yPos, float width, float height, TextureRegion packageTextureRegion, boolean active) {
        this.city = city;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.packageTextureRegion = packageTextureRegion;
        this.active = active;
    }

    //Getters

    public String getCity() {
        return city;
    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }

    public float getWidth() {

        return width;
    }

    public float getHeight() {
        return height;
    }



    public TextureRegion getPackageTextureRegion() {
        return packageTextureRegion;
    }

    public boolean getActive() {
        return active;
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

            batch.draw(packageTextureRegion, xPos, yPos,300,200);

    }

    public void setActive(boolean active) {
        this.active = active;
    }

}


