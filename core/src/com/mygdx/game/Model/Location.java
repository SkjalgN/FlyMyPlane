package com.mygdx.game.Model;

public class Location {

    private String locationName;
    private float xPos;
    private float yPos;

    //Constructor
    public Location(String locationName, float xPos, float yPos) {
        this.locationName = locationName;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public String getLocationName() {
        return locationName;
    }

    public int getX() {
        return (int) xPos;
    }

    public int getY() {
        return (int) yPos;
    }
}