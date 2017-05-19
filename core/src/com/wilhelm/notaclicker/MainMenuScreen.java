package com.wilhelm.notaclicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

/**
 * @author Brett_W
 */

public class MainMenuScreen implements Screen {
    private MiscFunc mF = new MiscFunc();
    private Game game;
    private SpriteBatch batch;
    private Viewport viewport;
    private FreeTypeFontGenerator generator;
    private Stage stage;

    public MainMenuScreen(Game game) {
        this.game = game;
        stage = new Stage();
        Camera camera = new PerspectiveCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        batch = new SpriteBatch();

        init();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(44f/255f, 182f/255f, 216f/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        batch.begin();
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose () {
        batch.dispose();
        generator.dispose();
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

    private void update(float delta) {
        //
    }

    private void init() {
        final int menuButtonSizeX = 500, menuButtonSizeY = 180;
        int initButtonPos = Gdx.graphics.getHeight()*4/9;
        Color blu = new Color(44f/255f, 182f/255f, 216f/255f, 1);

        // Main Menu
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ubuntu_bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 75;
        BitmapFont font = generator.generateFont(parameter);
        Skin buttonSkin = new Skin();
        mF.fillLayeredRoundedRectangle(Color.WHITE, blu, 0, 0, menuButtonSizeX, menuButtonSizeY, 40);   // Button Pixmap (Not Pressed)
        Pixmap buttonPixmap = mF.getPixmap();
        mF.fillLayeredRoundedRectangle(Color.YELLOW, blu, 0, 0, menuButtonSizeX, menuButtonSizeY, 40);  // Button Pixmap (Pressed)
        Pixmap pushButtonPixmap = mF.getPixmap();
        buttonSkin.add("yellow", new Texture(buttonPixmap));
        buttonSkin.add("default", font);
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.fontColor = Color.WHITE;
        buttonStyle.downFontColor = Color.YELLOW;
        buttonStyle.up = buttonSkin.newDrawable("yellow", Color.WHITE);
        buttonStyle.down = buttonSkin.newDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(pushButtonPixmap))));
        buttonStyle.font = buttonSkin.getFont("default");
        buttonSkin.add("default", buttonStyle);

        ArrayList<TextButton> menuButtons = new ArrayList<TextButton>();

        for(int i = 0; i < 4; i++) {
            final int index = i;
            menuButtons.add(i, new TextButton("", buttonSkin));
            menuButtons.get(i).setPosition(Gdx.graphics.getWidth()/2-menuButtons.get(i).getWidth()/2, initButtonPos-(Gdx.graphics.getHeight()*i/9)-menuButtons.get(i).getHeight());
            menuButtons.get(i).addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    switch(index) {
                        case 0:
                            game.setScreen(new GameScreen(game));
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        default:
                            break;
                    }
                    return true;
                }
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {  }
            });
            stage.addActor(menuButtons.get(i));
        }

        menuButtons.get(0).setText("New Game");
        menuButtons.get(1).setText("Load Game");
        menuButtons.get(2).setText("Options");
        menuButtons.get(3).setText("Quit Game");
    }
}