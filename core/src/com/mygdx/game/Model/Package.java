package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Package {

    private String city;
    private float xPos;
    private float yPos;

    private int packWidth;

    private int packHeight;
    private TextureRegion packageTextureRegion;
    private boolean active;

    //constructor

    public Package(String city, float xPos, float yPos, int packWidth, int packHeight, TextureRegion packageTextureRegion, boolean active) {
        this.city = city;
        this.xPos = xPos;
        this.yPos = yPos;
        this.packWidth = packWidth;
        this.packHeight = packHeight;
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
    public int getPackWidth() {
        return packWidth;
    }

    public int getPackHeight() {
        return packHeight;
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

    public void draw(Batch batch, int width, int height) {
        batch.draw(packageTextureRegion, xPos, yPos, width, height);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}


