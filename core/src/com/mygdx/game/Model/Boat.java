package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;



public class Boat {
    // TODO: Boat vises ikke i spillet, fjern den hvis ikke vi får fikset!

    private float xPos,yPos;
    private float speed;
    private float angle;
    private float boatWidth;
    private float boatHeight;
    private TextureRegion boatTextureRegion;
    private TextureRegion[] waveRegions;
    private float elapsedTime = 0;
    private int currentImage = 0;

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
        if(xPos < 2600 || xPos > 3400) {
            angle = (float) Math.PI - angle;
        }
        if(yPos < 2400 || yPos > 3000) {
            angle = -angle;
        }
        elapsedTime += delta;
    }

    public void draw(Batch batch) {
        waveRegions = new TextureRegion[2];
        waveRegions[0] = new TextureRegion(new Texture("effects/wave1.png"));
        waveRegions[1] = new TextureRegion(new Texture("effects/wave2.png"));
        if (elapsedTime > 0.5f) {
            elapsedTime -= 0.5f;
            currentImage = (currentImage + 1) % waveRegions.length;
        }
        batch.draw(waveRegions[currentImage], xPos, yPos, boatWidth/2, boatHeight/2, boatWidth*2/3, boatHeight*13/14, 1, 1, (float) Math.toDegrees(angle), true);
        batch.draw(boatTextureRegion, xPos, yPos, boatWidth/2, boatHeight/2, boatWidth, boatHeight, 1, 1, (float) Math.toDegrees(angle), true);
        
    }
}

