package com.mygdx.game.Model;

public class Location {

    private String locationName;
    private int xPos;
    private int yPos;

    //Constructor
    public Location(String locationName, int xPos, int yPos) {
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