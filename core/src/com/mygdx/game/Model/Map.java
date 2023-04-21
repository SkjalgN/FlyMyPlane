package com.mygdx.game.Model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.mygdx.game.Controller.GameController;
import com.mygdx.game.View.GameView;

public class Map {
    private List<Location> locations = new ArrayList<>();
    private Package package1;
    private Package target1;
    private Plane plane;
    private Boolean pickUpState = true;
    private Boolean gameOver = false;

    public Map(Plane plane) {
        this.plane = plane;
        initializeLocations();
        package1 = initializePackage(false);
        
    }

    public void initializeLocations() {
        locations.add(new Location("Oslo", 1512, 1278));
        locations.add(new Location("Istanbul", 1656, 1062));
        locations.add(new Location("Lagos", 1476, 792));
        locations.add(new Location("Manila", 2412, 839));
        locations.add(new Location("New York", 828, 1080));
        locations.add(new Location("Sao Paulo", 1080, 540));
    }

    //Function to initialize the package
    public Package initializePackage(Boolean isTarget) {
        int randomNum = generateRandomNumber();
        Location packageLocation = locations.remove(randomNum);
        TextureRegion texture;
        if (isTarget) {
            texture = new TextureRegion(new Texture("objects/Target1.png"));
        }
        else {
            texture = new TextureRegion(new Texture("objects/packs.png"));
        }
        Package newPackage = new Package(
            packageLocation.getLocationName(),
            packageLocation.getX(),
            packageLocation.getY(),
            texture,
            isTarget
        );
        return newPackage;
    }

    private int generateRandomNumber(){
        return (int) Math.floor(Math.random() * locations.size());
    }

    public Boolean checkCollision(Plane plane, Package pack) {
        if (Intersector.overlaps(plane.getBounds(), pack.getBounds())) {
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean pickUpState() {
        return pickUpState;
    }

    public Boolean gameOver() {
        return gameOver;
    }

    public void update(float dt) {
        handleCollision();
    }

    public void handleCollision(){
        if (checkCollision(plane, package1)){
            pickUpState = false;
            target1 = initializePackage(true);
        }
        if (checkCollision(plane, target1)){
            gameOver = true;
        }
    }
    
}
