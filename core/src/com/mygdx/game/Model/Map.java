package com.mygdx.game.Model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

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
        locations.add(new Location("Gaborone", 1650, 510));
        locations.add(new Location("Vilnius", 1620, 1210));
        locations.add(new Location("Melbourne", 2650, 410));
        locations.add(new Location("Funchal", 1280, 993));
        locations.add(new Location("Lahore", 2000, 1015));



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

    public Boolean pickUpState() {
        return pickUpState;
    }

    public Boolean gameOver() {
        return gameOver;
    }

    public String getPackageLocation() {
        return package1.getCity();
    }

    public String getTargetLocation(){
        return target1.getCity();
    }

    public void drawPackage(SpriteBatch sb){
        package1.draw(sb);
    }

    public void drawTarget(SpriteBatch sb){
        target1.draw(sb);
    }

    public void update() {
        handleCollision();
    }

    public void handleCollision(){
        if (pickUpState){
            if (checkPackageCollision(plane, package1)){
                pickUpState = false;
                target1 = initializePackage(true);
            }
        }
        else {
            if (checkPackageCollision(plane, target1)){
                gameOver = true;
            }
        }
    }
    // Prøvde å lage en bedre måte å sjekke kollisjon på. Skal gjøre det samme som checkPackageCollision, men funker ikke per nå. Hvis du leser dette: Gjerne se om du skjønner hvorfor den ikke virker!
    /*
    public Boolean checkCollision(Plane plane, Package pack) {
        if (Intersector.overlaps(plane.getBounds(), pack.getBounds())) {
            return true;
        }
        else {
            return false;
        }
    }
     */

    public Boolean checkPackageCollision(Plane plane, Package package2) {
        if (package2.isTarget()) {
            Rectangle rect1 = new Rectangle(plane.getxPos(), plane.getyPos(), plane.getPlaneWidth()/3, plane.getPlaneHeight()/3);
            Circle circle = new Circle(package2.getX() + package2.getWidth() / 3f, package2.getY() + package2.getHeight() / 3f, package2.getWidth() / 3f);
            if (Intersector.overlaps(circle, rect1)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            Rectangle rect1 = new Rectangle(plane.getxPos(), plane.getyPos(), plane.getPlaneWidth()/3, plane.getPlaneHeight()/3);
            Rectangle rect2 = new Rectangle(package2.getX(), package2.getY(), package2.getWidth(), package2.getHeight());
            if (Intersector.overlaps(rect1, rect2)) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    
}
