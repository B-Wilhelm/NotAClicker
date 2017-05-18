package com.wilhelm.notaclicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
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
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.Font;

/**
 * @author Brett_W
 */

public class MainMenuScreen implements Screen {
    private MiscFunc mF = new MiscFunc();
    private final NotAClicker game;
    private SpriteBatch batch;
    private Viewport viewport;
    private FreeTypeFontGenerator generator;
    private Stage stage;

    public MainMenuScreen(final NotAClicker game) {
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
        Color blu = new Color(44f/255f, 182f/255f, 216f/255f, 1);

        // Main Menu
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ubuntu_bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 75;
        BitmapFont font = generator.generateFont(parameter);
        Skin buttonSkin = new Skin();
        Pixmap buttonPixmap = mF.fillLayeredRoundedRectangle(Color.YELLOW, 0, 0, menuButtonSizeX, menuButtonSizeY, 40);
        buttonSkin.add("yellow", new Texture(buttonPixmap));
        buttonSkin.add("default", font);
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.fontColor = blu;
        buttonStyle.up = buttonSkin.newDrawable("yellow", Color.YELLOW);
        buttonStyle.down = buttonSkin.newDrawable("yellow", Color.DARK_GRAY);
        buttonStyle.font = buttonSkin.getFont("default");
        buttonSkin.add("default", buttonStyle);

        TextButton main1 = new TextButton("", buttonSkin);
        main1.setText("New Game");
        main1.setPosition(Gdx.graphics.getWidth()/2-main1.getWidth()/2, Gdx.graphics.getHeight()*4/9-main1.getHeight()/2);
        main1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {  }
        });
        stage.addActor(main1);
        TextButton main2 = new TextButton("", buttonSkin);
        main2.setText("Load");
        main2.setPosition(Gdx.graphics.getWidth()/2-main2.getWidth()/2, Gdx.graphics.getHeight()*3/9-main2.getHeight()/2);
        main2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {  }
        });
        stage.addActor(main2);
        TextButton main3 = new TextButton("", buttonSkin);
        main3.setText("Options");
        main3.setPosition(Gdx.graphics.getWidth()/2-main3.getWidth()/2, Gdx.graphics.getHeight()*2/9-main3.getHeight()/2);
        main3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {  }
        });
        stage.addActor(main3);
        TextButton main4 = new TextButton("", buttonSkin);
        main4.setText("Quit");
        main4.setPosition(Gdx.graphics.getWidth()/2-main4.getWidth()/2, Gdx.graphics.getHeight()/9-main4.getHeight()/2);
        main4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {  }
        });
        stage.addActor(main4);
    }
}