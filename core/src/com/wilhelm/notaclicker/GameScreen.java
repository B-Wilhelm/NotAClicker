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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.sql.Time;

/**
 * @author Brett_W
 */

class GameScreen implements Screen {
    private MiscFunc mF = new MiscFunc();
    private Game game;
    private Stage stage;
    private Viewport viewport;
    private TimeBar timeBar;
    private PlayerBar playerBar;
    private GameBar gameBar;
    private Dialog exitDialog;
    private boolean backBuffer;

    GameScreen(Game game) {
        this.game = game;

        stage = new Stage();
        Camera camera = new PerspectiveCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        init();
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(44f/255f, 182f/255f, 216f/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();
        createOverlay();

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

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    private void init() {
        backBuffer = false;
        Color yellow = new Color(240, 240, 0, 1);
        int upperDisplayWidth = Gdx.graphics.getWidth();
        int displayHeight = Gdx.graphics.getHeight()/10;
        int lowerDisplayWidth = Gdx.graphics.getWidth()/2;
        final int lineThickness = 10;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ubuntu_bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        BitmapFont font = generator.generateFont(parameter);
        Skin buttonSkin = new Skin();
        Pixmap buttonPixmap = new Pixmap(40, 40, Pixmap.Format.RGBA8888);
        buttonPixmap.setColor(Color.YELLOW);
        buttonPixmap.fillRectangle(0, 0, 40, 40);
        buttonSkin.add("yellow", new Texture(buttonPixmap));
        buttonSkin.add("default", font);
        Window.WindowStyle wS = new Window.WindowStyle();
        wS.titleFont = font;
        wS.titleFontColor = Color.BLACK;
        wS.background = buttonSkin.newDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(buttonPixmap))));
        buttonSkin.add("default", wS);
        Label.LabelStyle lS = new Label.LabelStyle();
        lS.font = font;
        lS.fontColor = Color.BLACK;
        buttonSkin.add("default", lS);
        TextButton.TextButtonStyle tS = new TextButton.TextButtonStyle();
        tS.font = font;
        tS.fontColor = Color.BLACK;
        buttonSkin.add("default", tS);

        exitDialog = new Dialog("Exit?", buttonSkin){
            public void result(Object obj) {
                if(obj.equals(true)) {
                    game.setScreen(new MainMenuScreen(game));
                }
                else { exitDialog.hide(); }
            }
        };

        ShapeRenderer sR = new ShapeRenderer();
        timeBar = new TimeBar(sR, Color.BLACK, yellow, 0, Gdx.graphics.getHeight()*9/10, upperDisplayWidth, displayHeight, lineThickness);
        playerBar = new PlayerBar(sR, Color.BLACK, yellow, 0, 0, lowerDisplayWidth, displayHeight, lineThickness);
        gameBar = new GameBar(sR, Color.BLACK, yellow, lowerDisplayWidth, 0, lowerDisplayWidth, displayHeight, lineThickness);
    }

    private void update() {
        onBackPressed();
    }

    private void onBackPressed() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) && !backBuffer) {
            exitDialog.text("Return to Main Menu?");
            exitDialog.button("Yes", true); //sends "true" as the result
            exitDialog.button("No", false);  //sends "false" as the result
            exitDialog.show(stage);
            backBuffer = true;
        }
        else if(backBuffer) {
            backBuffer = false;
        }
    }

    private void createOverlay() {
        timeBar.drawShape();
        playerBar.drawShape();
        gameBar.drawShape();

    }

    private class TimeBar {
        private ShapeRenderer s;
        private Color c1, c2;
        private int x, y, width, height, thickness;

        private TimeBar(ShapeRenderer s, Color c1, Color c2, int x, int y, int width, int height, int thickness) {
            this.s = s;
            this.c1 = c1;
            this.c2 = c2;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.thickness = thickness;
        }

        void drawShape() {
            s.begin(ShapeRenderer.ShapeType.Filled);
            s.setColor(c1);
            s.rect(x+thickness, y, width-(thickness*2), thickness);    // Bottom Side
            s.rect(x, y, thickness, thickness);    // Bottom Left Corner
            s.rect(x+width-thickness, y, thickness, thickness);    // Bottom Right Corner
            s.end();

            s.begin(ShapeRenderer.ShapeType.Filled);    // Inner Shape
            s.setColor(c2);
            s.rect(x, y+thickness, width, height-thickness);
            s.end();
        }
    }

    private class PlayerBar {
        private ShapeRenderer s;
        private Color c1, c2;
        private int x, y, width, height, thickness;

        private PlayerBar(ShapeRenderer s, Color c1, Color c2, int x, int y, int width, int height, int thickness) {
            this.s = s;
            this.c1 = c1;
            this.c2 = c2;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.thickness = thickness;
        }

        void drawShape() {
            s.begin(ShapeRenderer.ShapeType.Filled);
            s.setColor(c1);
            s.rect(x+width-(thickness/2), y+thickness, thickness/2, height-(thickness*2));    // Right Side
            s.rect(x+thickness, y+height-thickness, width-thickness, thickness);    // Top Side
            s.rect(x+width-(thickness/2), y, thickness/2, thickness);    // Bottom Right Corner
            s.rect(x, y+height-thickness, thickness, thickness);    // Top Left Corner
            s.rect(x+width-(thickness/2), y+height-thickness, thickness/2, thickness);    // Top Right Corner
            s.end();

            s.begin(ShapeRenderer.ShapeType.Filled);    // Inner Shape
            s.setColor(c2);
            s.rect(x, y, width-(thickness/2), height-thickness);
            s.end();
        }
    }

    private class GameBar {
        private ShapeRenderer s;
        private Color c1, c2;
        private int x, y, width, height, thickness;

        private GameBar(ShapeRenderer s, Color c1, Color c2, int x, int y, int width, int height, int thickness) {
            this.s = s;
            this.c1 = c1;
            this.c2 = c2;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.thickness = thickness;
        }

        void drawShape() {
            s.begin(ShapeRenderer.ShapeType.Filled);
            s.setColor(c1);
            s.rect(x, y+thickness, thickness/2, height-(thickness*2));    // Left Side
            s.rect(x+thickness/2, y+height-thickness, width-thickness, thickness);    // Top Side
            s.rect(x, y, thickness/2, thickness);    // Bottom Left Corner
            s.rect(x, y+height-thickness, thickness/2, thickness);    // Top Left Corner
            s.rect(x+width-thickness, y+height-thickness, thickness, thickness);    // Top Right Corner
            s.end();

            s.begin(ShapeRenderer.ShapeType.Filled);    // Inner Shape
            s.setColor(c2);
            s.rect(x+thickness/2, y, width-(thickness/2), height-thickness);
            s.end();
        }
    }
}