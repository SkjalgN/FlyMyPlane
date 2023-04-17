package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.API;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;



public class SelectionState extends State{
    private Texture background;
    private Texture loading;
    private Texture loadingbackground;
    private Button nextBtn;

    private Button box1;
    private Button dragon1;
    private Skin nextBtnSkin;
    private Skin box1Skin;
    private Skin dragon1Skin;
    private GameStage stage;

    private TextFieldStyle textFieldStyle;
    private TextField inputField;
    private String userInput;

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
        background = new Texture("gamescreens/selection.jpg");
        loadingbackground = new Texture("gamescreens/mapClean.jpg");
        loading = new Texture("gamescreens/loadingtexture.png");


        //Create a stage
        stage = new GameStage();

        //Load a skin from JSON file
        nextBtnSkin = new Skin(Gdx.files.internal("buttons/game/rightBtn/rightBtn.json"));
        box1Skin = new Skin(Gdx.files.internal("buttons/selection/box/box.json"));
        dragon1Skin = new Skin(Gdx.files.internal("buttons/selection/box/box.json"));

        //Create a button
        nextBtn = new Button(nextBtnSkin);
        box1 = new Button(box1Skin);
        dragon1 = new Button(dragon1Skin);

        //Set button position, size and function
        nextBtn.setSize(width/8f, width/8f);
        nextBtn.setPosition(width-nextBtn.getWidth()*1.2f, height/2f-(nextBtn.getHeight()/2f));
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


        box1.setSize(width/4f, width/4f);
        box1.setPosition(width/2f-(box1.getWidth()/2f), height/2f-(box1.getHeight()/2f));
        box1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Box Pressed");
                return true;
            }
        });

        dragon1.setSize(width/4f, width/4f);
        dragon1.setPosition(width/2f-(dragon1.getWidth()/2f), height/2f-(dragon1.getHeight()/2f));
        dragon1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Dragon Pressed");
                return true;
            }
        });

        textFieldStyle = new TextFieldStyle();
        textFieldStyle.font = nextBtnSkin.getFont("font"); // You can use the same font from the nextBtnSkin or create a new one.
        textFieldStyle.fontColor = Color.BLACK; // Set the font color.
/*
        textFieldStyle.background = box1Skin.getDrawable("background"); // Use the box1Skin background as the TextField background.
*/
        font.getData().setScale(5f);
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
        stage.addActor(dragon1);
        stage.addActor(inputField);
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, width, height);

        if (selectionStateStatus == SelectionStateStatus.SWITCHING) {
            gsm.set(new GameState(gsm, database));
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
        box1Skin.dispose();
        nextBtnSkin.dispose();
        stage.dispose();
    }
}
