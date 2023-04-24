package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.API;
import com.mygdx.game.FontManager;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class SelectionView extends State {
    private Texture background;

    // private Texture loading;
    // private Texture loadingbackground;
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
    private BitmapFont customFont;
    private TextField inputField;
    private String userInput;
    private int skinVar;
    private boolean nextPlayer;

    private API database;
    // private SelectionStateStatus selectionStateStatus =
    // SelectionStateStatus.NORMAL;

    // private enum SelectionStateStatus {
    // NORMAL,
    // LOADING,
    // SWITCHING
    // }

    // public SelectionStateStatus getSelectionStateStatus(){
    // return this.selectionStateStatus;
    // }
    // public void setSelectionStateStatusNormal(){
    // this.selectionStateStatus = SelectionStateStatus.NORMAL;
    // }
    // public void setSelectionStateStatusLoading(){
    // this.selectionStateStatus = SelectionStateStatus.LOADING;
    // }
    // public void setSelectionStateStatusSwitching(){
    // this.selectionStateStatus = SelectionStateStatus.SWITCHING;
    // }

    public SelectionView(final GameStateManager gsm, final API database, boolean nextPlayer) {
        super(gsm);
        this.database = database;
        if (!nextPlayer) {
            background = new Texture("gamescreens/selection1.jpg");
        }else{
            background = new Texture("gamescreens/selection2.jpg");
        }
        // loadingbackground = new Texture("gamescreens/mapClean.jpg");
        // loading = new Texture("gamescreens/loadingtexture.png");
        cam.setToOrtho(false, width, height);
        cam.zoom = (float) 1.0;
        cam.translate(0, 0);
        this.nextPlayer = nextPlayer;

        // Create a stage
        stage = new GameStage();

        // Load a skin from JSON file
        nextBtnSkin = new Skin(Gdx.files.internal("buttons/game/rightBtn/rightBtn.json"));
        boxSkin = new Skin(Gdx.files.internal("buttons/selection/box/box.json"));

        // Create a button
        nextBtn = new Button(nextBtnSkin);
        box1 = new Button(boxSkin);
        box2 = new Button(boxSkin);
        box3 = new Button(boxSkin);
        box4 = new Button(boxSkin);
        box5 = new Button(boxSkin);
        box6 = new Button(boxSkin);

        // Set button position, size and function
        nextBtn.setSize(width / 8f, width / 8f);
        nextBtn.setPosition(width - nextBtn.getWidth() * 1.2f, height / 2f - (nextBtn.getHeight() / 2f));
        nextBtn.setVisible(false);

        nextBtnSkin = new Skin(Gdx.files.internal("buttons/game/rightBtn/rightBtn.json"));

        // Create a new BitmapFont and add it to the Skin
        BitmapFont font = new BitmapFont();
        nextBtnSkin.add("font", font, BitmapFont.class);

        box1.setSize(width / 5f, width / 5f);
        box1.setPosition(width / 2f - width * 0.18f - (box1.getWidth() / 2f), height / 2f - (box1.getHeight() / 2f));
        resetColor();

        System.out.println("Width: " + width + " Height: " + height);

        box2.setSize(width / 5f, width / 5f);
        box2.setPosition(width / 2f - box1.getWidth() / 2f, height / 2f - (box1.getHeight() / 2f));
        box2.setColor(1, 1, 1, 0);

        box3.setSize(width / 5f, width / 5f);
        box3.setPosition(width / 2f + width * 0.18f - (box1.getWidth() / 2f), height / 2f - (box1.getHeight() / 2f));
        box3.setColor(1, 1, 1, 0);

        box4.setSize(width / 5f, width / 5f);
        box4.setPosition(width / 2f - width * 0.18f - box1.getWidth() / 2f,
                height / 2f - height * 0.25f - (box1.getHeight() / 2f));
        box4.setColor(1, 1, 1, 0);

        box5.setSize(width / 5f, width / 5f);
        box5.setPosition(width / 2f - (box1.getWidth() / 2f), height / 2f - height * 0.25f - (box1.getHeight() / 2f));
        box5.setColor(1, 1, 1, 0);

        box6.setSize(width / 5f, width / 5f);
        box6.setPosition(width / 2f + width * 0.18f - (box1.getWidth() / 2f),
                height / 2f - height * 0.25f - (box1.getHeight() / 2f));
        box6.setColor(1, 1, 1, 0);

        // Kanskje gjøres i Controller
       
        customFont = FontManager.getInstance().getFont();


        // USER INPUT!
        textFieldStyle = new TextFieldStyle();
        textFieldStyle.font = customFont; // You can use the same font from the nextBtnSkin or create a new one.
        textFieldStyle.fontColor = Color.BLACK; // Set the font color.
        /*
         * textFieldStyle.background = box1Skin.getDrawable("background"); // Use the
         * box1Skin background as the TextField background.
         */
        font.getData().setScale(1f);
        inputField = new TextField("", textFieldStyle);
        inputField.setSize(width / 4f, height / 10f);
        inputField.setPosition(width / 2f + (width / 20f) - (inputField.getWidth() / 2f),
                height * 0.70f - (inputField.getHeight() / 2f));

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

    public TextField getInputField() {
        return inputField;
    }

    public void changeBox(int i) {
        resetColor();
        getBoxButton(i).setColor(1, 1, 1, 1);
        skinVar = i - 1;
        nextBtn.setVisible(true);
    }

    public Button getNextButton() {
        return nextBtn;
    }

    public void resetColor() {
        box1.setColor(1, 1, 1, 0);
        box2.setColor(1, 1, 1, 0);
        box3.setColor(1, 1, 1, 0);
        box4.setColor(1, 1, 1, 0);
        box5.setColor(1, 1, 1, 0);
        box6.setColor(1, 1, 1, 0);
    }

    public Button getBoxButton(int i) {
        if (i == 1) {
            return box1;
        } else if (i == 2) {
            return box2;
        } else if (i == 3) {
            return box3;
        } else if (i == 4) {
            return box4;
        } else if (i == 5) {
            return box5;
        } else if (i == 6) {
            return box6;
        } else {
            return null;
        }
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
