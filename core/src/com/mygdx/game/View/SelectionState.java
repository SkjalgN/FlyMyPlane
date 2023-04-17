package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.API;

public class SelectionState extends State{
    private Texture background;
    private Button nextBtn;
    private Button box1;
    private Skin nextBtnSkin;
    private Skin box1Skin;
    private GameStage stage;
    private API database;
    protected SelectionState(final GameStateManager gsm, final API database) {
        super(gsm);
        this.database = database;
        background = new Texture("gamescreens/selection.jpg");

        //Create a stage
        stage = new GameStage();

        //Load a skin from JSON file
        nextBtnSkin = new Skin(Gdx.files.internal("buttons/game/rightBtn/rightBtn.json"));
        box1Skin = new Skin(Gdx.files.internal("buttons/selection/box/box.json"));

        //Create a button
        nextBtn = new Button(nextBtnSkin);
        box1 = new Button(box1Skin);

        //Set button position, size and function
        nextBtn.setSize(width/8f, width/8f);
        nextBtn.setPosition(width-nextBtn.getWidth()*1.2f, height/2f-(nextBtn.getHeight()/2f));
        nextBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new GameState(gsm, database));
                System.out.println("Button Pressed");
                return true;
            }


        });

        box1.setSize(width/4f, width/4f);
        box1.setPosition(width/2f-(box1.getWidth()/2f), height/2f-(box1.getHeight()/2f));
        box1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Box Pressed");
                return true;
            }
        });

        stage.addActor(nextBtn);
        stage.addActor(box1);
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0,width,height);
        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        nextBtnSkin.dispose();
        stage.dispose();
    }
}
