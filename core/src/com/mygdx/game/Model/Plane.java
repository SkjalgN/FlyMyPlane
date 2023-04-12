package com.mygdx.game.Model;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;

public class Plane {

    private float xPos,yPos;
    private float speed;
    private float angle;
    private float planeWidth;
    private float planeHeight;
    private TextureRegion planeTextureRegion;
    private TextureRegion[] airflow;
    private int airflowvar;
    private TextureRegion flames[];
    private float elapsedtime = 0;
    private int currentImage = 0;

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
        elapsedtime += delta;
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
        airflow = new TextureRegion[2];
        airflow[0] = new TextureRegion(new Texture("effects/airflow.png"));
        airflow[1] = new TextureRegion(new Texture("effects/empty.png"));
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            airflowvar = 0;
        }
        else {
            airflowvar = 1;
        }
        flames = new TextureRegion[2];
        flames[0] = new TextureRegion(new Texture("effects/flame1.png"));
        flames[1] = new TextureRegion(new Texture("effects/flame2.png"));
        if (elapsedtime > 0.1f) {
            elapsedtime -= 0.1f;
            currentImage = (currentImage + 1) % flames.length;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            batch.draw(flames[currentImage], xPos, yPos, planeWidth/2, planeHeight/2, planeWidth, planeHeight, 1, 1, (float) Math.toDegrees(angle), true);
        }
        batch.draw(airflow[airflowvar], xPos, yPos, planeWidth/2, planeHeight/2, planeWidth*4/5, planeHeight, 1, 1, (float) Math.toDegrees(angle), true);
        batch.draw(planeTextureRegion, xPos, yPos, planeWidth/2, planeHeight/2, planeWidth, planeHeight, 1, 1, (float) Math.toDegrees(angle), true);
        
    }
    
}

