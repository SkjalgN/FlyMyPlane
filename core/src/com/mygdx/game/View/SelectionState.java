package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.API;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;



public class SelectionState extends State{
    private Texture background;
    private Texture loading;
    private Texture loadingbackground;
    private Button nextBtn;

    private Button box1;
    private Button box2;
    private Button box3;
    private Button box4;
    private Button box5;
    private Button box6;
    private Skin nextBtnSkin;
    private Skin boxSkin;
    private GameStage stage;

    private TextFieldStyle textFieldStyle;
    private TextField inputField;
    private String userInput;
    private int skinVar;

    private API database;
    private SelectionStateStatus selectionStateStatus = SelectionStateStatus.NORMAL;


    private enum SelectionStateStatus {
        NORMAL,
        LOADING,
        SWITCHING
    }

    protected SelectionState(final GameStateManager gsm, final API database) {
        super(gsm);
        this.database = database;
        background = new Texture("gamescreens/selection1.jpg");
        loadingbackground = new Texture("gamescreens/mapClean.jpg");
        loading = new Texture("gamescreens/loadingtexture.png");
        cam.setToOrtho(false, width,height);
        cam.zoom = (float)1.0;
        cam.translate(0, 0);




        //Create a stage
        stage = new GameStage();

        //Load a skin from JSON file
        nextBtnSkin = new Skin(Gdx.files.internal("buttons/game/rightBtn/rightBtn.json"));
        boxSkin = new Skin(Gdx.files.internal("buttons/selection/box/box.json"));

        //Create a button
        nextBtn = new Button(nextBtnSkin);
        box1 = new Button(boxSkin);
        box2 = new Button(boxSkin);
        box3 = new Button(boxSkin);
        box4 = new Button(boxSkin);
        box5 = new Button(boxSkin);
        box6 = new Button(boxSkin);

        //Set button position, size and function
        nextBtn.setSize(width/8f, width/8f);
        nextBtn.setPosition(width-nextBtn.getWidth()*1.2f, height/2f-(nextBtn.getHeight()/2f));
        nextBtn.setVisible(false);
        nextBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                selectionStateStatus = SelectionStateStatus.LOADING;
                return true;
            }


        });
        nextBtnSkin = new Skin(Gdx.files.internal("buttons/game/rightBtn/rightBtn.json"));

        // Create a new BitmapFont and add it to the Skin
        BitmapFont font = new BitmapFont();
        nextBtnSkin.add("font", font, BitmapFont.class);

        box1.setSize(width/5f, width/5f);
        box1.setPosition(width/2f-(box1.getWidth()/2f+115), height/2f-(box1.getHeight()/2f));
        box1.setColor(1,1,1,0);
        box1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Box Pressed");
                box1.setColor(1,1,1,1);
                box2.setColor(1,1,1,0);
                box3.setColor(1,1,1,0);
                box4.setColor(1,1,1,0);
                box5.setColor(1,1,1,0);
                box6.setColor(1,1,1,0);
                skinVar = 0;
                nextBtn.setVisible(true);
                return true;
            }
        });

        box2.setSize(width/5f, width/5f);
        box2.setPosition(width/2f-(box1.getWidth()/2f), height/2f-(box1.getHeight()/2f));
        box2.setColor(1,1,1,0);
        box2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Box Pressed");
                box1.setColor(1,1,1,0);
                box2.setColor(1,1,1,1);
                box3.setColor(1,1,1,0);
                box4.setColor(1,1,1,0);
                box5.setColor(1,1,1,0);
                box6.setColor(1,1,1,0);
                skinVar = 1;
                nextBtn.setVisible(true);
                return true;
            }
        });

        box3.setSize(width/5f, width/5f);
        box3.setPosition(width/2f-(box1.getWidth()/2f-115), height/2f-(box1.getHeight()/2f));
        box3.setColor(1,1,1,0);
        box3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Box Pressed");
                box1.setColor(1,1,1,0);
                box2.setColor(1,1,1,0);
                box3.setColor(1,1,1,1);
                box4.setColor(1,1,1,0);
                box5.setColor(1,1,1,0);
                box6.setColor(1,1,1,0);
                skinVar = 2;
                nextBtn.setVisible(true);
                return true;
            }
        });

        box4.setSize(width/5f, width/5f);
        box4.setPosition(width/2f-(box1.getWidth()/2f+115), height/2f-(box1.getHeight()/2f+120));
        box4.setColor(1,1,1,0);
        box4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Box Pressed");
                box1.setColor(1,1,1,0);
                box2.setColor(1,1,1,0);
                box3.setColor(1,1,1,0);
                box4.setColor(1,1,1,1);
                box5.setColor(1,1,1,0);
                box6.setColor(1,1,1,0);
                skinVar = 3;
                nextBtn.setVisible(true);
                return true;
            }
        });

        box5.setSize(width/5f, width/5f);
        box5.setPosition(width/2f-(box1.getWidth()/2f), height/2f-(box1.getHeight()/2f+120));
        box5.setColor(1,1,1,0);
        box5.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Box Pressed");
                box1.setColor(1,1,1,0);
                box2.setColor(1,1,1,0);
                box3.setColor(1,1,1,0);
                box4.setColor(1,1,1,0);
                box5.setColor(1,1,1,1);
                box6.setColor(1,1,1,0);
                skinVar = 4;
                nextBtn.setVisible(true);
                return true;
            }
        });
        
        box6.setSize(width/5f, width/5f);
        box6.setPosition(width/2f-(box1.getWidth()/2f-115), height/2f-(box1.getHeight()/2f+120));
        box6.setColor(1,1,1,0);
        box6.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Box Pressed");
                box1.setColor(1,1,1,0);
                box2.setColor(1,1,1,0);
                box3.setColor(1,1,1,0);
                box4.setColor(1,1,1,0);
                box5.setColor(1,1,1,0);
                box6.setColor(1,1,1,1);
                skinVar = 5;
                nextBtn.setVisible(true);
                return true;
            }
        });

        textFieldStyle = new TextFieldStyle();
        textFieldStyle.font = nextBtnSkin.getFont("font"); // You can use the same font from the nextBtnSkin or create a new one.
        textFieldStyle.fontColor = Color.BLACK; // Set the font color.
/*
        textFieldStyle.background = box1Skin.getDrawable("background"); // Use the box1Skin background as the TextField background.
*/
        font.getData().setScale(1f);
        inputField = new TextField("", textFieldStyle);
        inputField.setSize(width / 4f, width / 4f);
        inputField.setPosition(width / 2f + (width / 20f) - (inputField.getWidth() / 2f), height*0.70f - (inputField.getHeight() / 2f));
        inputField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                userInput = textField.getText();
                System.out.println("User input: " + userInput);
                
            }
        });




        stage.addActor(nextBtn);
        stage.addActor(box1);
        stage.addActor(box2);
        stage.addActor(box3);
        stage.addActor(box4);
        stage.addActor(box5);
        stage.addActor(box6);
        stage.addActor(inputField);
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void update(float dt) {
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, width, height);

        if (selectionStateStatus == SelectionStateStatus.SWITCHING) {
            gsm.set(new GameState(gsm, database,skinVar));
        }

        if (selectionStateStatus == SelectionStateStatus.LOADING) {
            sb.draw(loadingbackground, 0, 0, width, height);
            sb.draw(loading, width/2f-loading.getWidth()/2f, height/2f-loading.getHeight()/2f, width/4f, height/4f);
            stage.clear();
            selectionStateStatus = SelectionStateStatus.SWITCHING;
        }

        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose() {
        background.dispose();
        boxSkin.dispose();
        nextBtnSkin.dispose();
        stage.dispose();
    }
}
