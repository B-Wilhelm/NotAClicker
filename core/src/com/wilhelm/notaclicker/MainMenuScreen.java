package com.wilhelm.notaclicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

/**
 * @author Brett_W
 */

class MainMenuScreen implements Screen {
    private MiscFunc mF = new MiscFunc();
    private Game game;
    private Viewport viewport;
    private Stage stage;
    private Dialog exitDialog;

    private Color blue = new Color(44f/255f, 182f/255f, 216f/255f, 1);
    private Color yellow = new Color(240, 240, 0, 1);

    MainMenuScreen(Game game) {
        Camera camera = new PerspectiveCamera();

        this.game = game;
        this.stage = new Stage();
        this.viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        init();

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render (float delta) {
        onBackPressed();

        Gdx.gl.glClearColor(44f/255f, 182f/255f, 216f/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose () {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    private void init() {
        final int menuButtonSizeX = 500, menuButtonSizeY = 180;
        int initButtonPos = Gdx.graphics.getHeight()*4/9;
        ArrayList<TextButton> menuButtons = new ArrayList<TextButton>();
        Color blu = new Color(44f/255f, 182f/255f, 216f/255f, 1);
        Color yellow = new Color(240, 240, 0, 1);

        createDialog();

        // Main Menu
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ubuntu_bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 75;
        BitmapFont font = generator.generateFont(parameter);
        Skin buttonSkin = new Skin();
        mF.fillLayeredRoundedRectangle(Color.YELLOW, blu, 0, 0, menuButtonSizeX, menuButtonSizeY, 40, 12);   // Button Pixmap (Not Pressed)
        Pixmap buttonPixmap = mF.getPixmap();
        mF.fillLayeredRoundedRectangle(Color.YELLOW, blu, 0, 0, menuButtonSizeX, menuButtonSizeY, 40, 12);  // Button Pixmap (Pressed)
        Pixmap pushButtonPixmap = mF.getPixmap();
        buttonSkin.add("yellow", new Texture(buttonPixmap));
        buttonSkin.add("default", font);
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.fontColor = Color.WHITE;
        buttonStyle.downFontColor = yellow;
        buttonStyle.up = buttonSkin.newDrawable("yellow", Color.WHITE);
        buttonStyle.down = buttonSkin.newDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(pushButtonPixmap))));
        buttonStyle.font = buttonSkin.getFont("default");
        buttonSkin.add("default", buttonStyle);

        for(int i = 0; i < 4; i++) {
            final int index = i;
            menuButtons.add(i, new TextButton("", buttonSkin));
            menuButtons.get(i).setPosition(Gdx.graphics.getWidth()/2-menuButtons.get(i).getWidth()/2, initButtonPos-(Gdx.graphics.getHeight()*i/9)-menuButtons.get(i).getHeight()/2);
            menuButtons.get(i).addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                    Stage stage = event.getTarget().getStage();
                    Vector2 mouse = stage.screenToStageCoordinates( new Vector2(Gdx.input.getX(), Gdx.input.getY()) );

                    if(stage.hit(mouse.x, mouse.y, true) == event.getTarget())
                    {
                        switch(index) {
                            case 0:
                                game.setScreen(new GameScreen(game));
                                break;
                            case 1:
                                break;
                            case 2:
                                game.setScreen(new OptionsScreen(game));
                                break;
                            case 3:
                                Gdx.app.exit();
                                break;
                            default:
                                break;
                        }
                    }
                }
            });
            stage.addActor(menuButtons.get(i));
        }

        menuButtons.get(0).setText("New Game");
        menuButtons.get(1).setText("Load Game");
        menuButtons.get(2).setText("Options");
        menuButtons.get(3).setText("Quit Game");

        generator.dispose();
        buttonPixmap.dispose();
        pushButtonPixmap.dispose();
    }

    private void createDialog() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ubuntu_bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 84;
        BitmapFont font = generator.generateFont(parameter);
        parameter.size = 72;
        BitmapFont exitButtonFont = generator.generateFont(parameter);
        Skin dialogSkin = new Skin();

        mF.fillLayeredRoundedRectangle(yellow, blue, 0, 0, Gdx.graphics.getWidth()*3/4, Gdx.graphics.getHeight()/4, 4, 24);
        Pixmap dialogPixmap = mF.getPixmap();
        mF.fillLayeredRoundedRectangle(yellow, blue, 0, 0, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/15, 32, 12);
        Pixmap buttonPixmap = mF.getPixmap();

        Window.WindowStyle wS = new Window.WindowStyle();
        wS.titleFont = font;
        wS.titleFontColor = yellow;
        wS.background = dialogSkin.newDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(dialogPixmap))));
        dialogSkin.add("default", wS);
        Label.LabelStyle lS = new Label.LabelStyle();
        lS.font = font;
        lS.fontColor = Color.WHITE;
        dialogSkin.add("default", lS);
        TextButton.TextButtonStyle tS = new TextButton.TextButtonStyle();
        tS.font = exitButtonFont;
        tS.fontColor = Color.WHITE;
        tS.downFontColor = yellow;
        tS.checkedFontColor = yellow;
        tS.up = dialogSkin.newDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(buttonPixmap))));
        tS.down = dialogSkin.newDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(buttonPixmap))));
        tS.over = dialogSkin.newDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(buttonPixmap))));
        dialogSkin.add("default", tS);

        exitDialog = new Dialog("", dialogSkin){
            public void result(Object obj) {
                if(obj.equals(true)) {
                    exitDialog.hide();
                    Gdx.app.exit();
                }
                else {
                    exitDialog.hide();
                }
            }
        };

        exitDialog.text("Exit Game?");
        exitDialog.button("Yes", true); // sends "true" as the result
        exitDialog.button("No", false); // sends "false" as the result
        exitDialog.getButtonTable().padBottom(Gdx.graphics.getHeight()/25);
        exitDialog.getButtonTable().getCells().get(0).padRight(Gdx.graphics.getWidth()/42);
        exitDialog.getButtonTable().getCells().get(1).padLeft(Gdx.graphics.getWidth()/42);
    }

    private void onBackPressed() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            exitDialog.show(stage);
        }
    }
}