package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.API;
import com.mygdx.game.Model.Plane;

public class PauseState extends State{
    
    private Texture background;
    private Texture pause;
    private Texture tutorial;
    private Texture exit;
    private Skin continueBtnSkin;
    private Skin tutorialBtnSkin;
    private Skin exitBtnSkin;
    private Skin backBtnSkin;
    private Button continueBtn;
    private Button tutorialBtn;
    private Button backBtn;
    private Button exitBtn;
    private GameStage stage;
    private API database;




    public PauseState(final GameStateManager gsm, final API database) {
        super(gsm);
        this.database = database;
        background = new Texture("gamescreens/pauseMenu.jpg");
        // pause = new Texture("buttons/pauseBtn1.png");
        // tutorial = new Texture("buttons/pauseBtn2.png");
        // exit = new Texture("buttons/pauseBtn3.png");
        cam.setToOrtho(false, background.getWidth(),background.getHeight());
        cam.zoom = (float)1.0;
        cam.translate(0, 0);

        // Create a stage
        stage = new GameStage();

        // Load a skin from a JSON file
        continueBtnSkin = new Skin(Gdx.files.internal("buttons/pause/next/next.json"));
        tutorialBtnSkin = new Skin(Gdx.files.internal("buttons/pause/tutorial/tutorial.json"));
        exitBtnSkin = new Skin(Gdx.files.internal("buttons/pause/exit/exit.json"));
        backBtnSkin = new Skin(Gdx.files.internal("buttons/pause/back/back.json"));

        // Create a button with the skin
        continueBtn = new Button(continueBtnSkin);
        tutorialBtn = new Button(tutorialBtnSkin);
        exitBtn = new Button(exitBtnSkin);
        backBtn = new Button(backBtnSkin);

        // Set the properties of the button
        continueBtn.setSize(width/5f, width/5f);
        continueBtn.setPosition(width/2f-(continueBtn.getWidth()/2f), height/2f-(continueBtn.getHeight()/2f));
        continueBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new GameState(gsm, database));
                System.out.println("Button Pressed");
                return true;
            }
        });
        tutorialBtn.setSize(width/4f, height/7f);
        tutorialBtn.setPosition(width/2f-tutorialBtn.getWidth()*1.2f, height/2f-tutorialBtn.getHeight()*1.2f);
        tutorialBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new TutorialState(gsm, database));
                System.out.println("Button Pressed");
                return true;
            }
        });
        exitBtn.setSize(width/4f, width/7f);
        exitBtn.setPosition(width/2f+exitBtn.getWidth()*1.2f, height/2f-exitBtn.getHeight()*1.2f);
        exitBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new MenuState(gsm, database));
                System.out.println("Button Pressed");
                return true;
            }
        });

        backBtn.setSize(width/4f, width/7f);
        backBtn.setPosition(width/2f-backBtn.getWidth()*1.2f+100, height/2f-backBtn.getHeight()*1.2f+100);
        backBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.pop();
                System.out.println("Button Pressed");
                return true;
            }
        });



        // Add the button to the stage
        stage.addActor(continueBtn);
        stage.addActor(tutorialBtn);
        stage.addActor(exitBtn);
        stage.addActor(backBtn);



    }

    @Override
    public void update(float dt) {
        cam.update();
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0);
        /*sb.draw(pause, background.getWidth()/2-pause.getWidth()/2, background.getHeight()/2-pause.getHeight()/2);
        sb.draw(tutorial, 400, 200);
        sb.draw(exit, 1200, 220);*/
        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
        continueBtnSkin.dispose();
        tutorialBtnSkin.dispose();
        exitBtnSkin.dispose();

    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)){
            gsm.pop();
        }
    }
}
