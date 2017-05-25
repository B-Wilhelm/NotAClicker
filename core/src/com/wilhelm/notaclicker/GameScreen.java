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

    private final int lineThickness = 10;
    private Color blue = new Color(44f/255f, 182f/255f, 216f/255f, 1);
    private Color yellow = new Color(240, 240, 0, 1);

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
        onBackPressed();

        Gdx.gl.glClearColor(44f/255f, 182f/255f, 216f/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    private void init() {
        createDialog();

        ShapeRenderer sR = new ShapeRenderer();
        timeBar = new TimeBar(sR, Color.BLACK, yellow, 0, Gdx.graphics.getHeight()*19/20, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/20, lineThickness);
        playerBar = new PlayerBar(sR, Color.BLACK, yellow, 0, 0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/13, lineThickness);
        gameBar = new GameBar(sR, Color.BLACK, yellow, Gdx.graphics.getWidth()/2, 0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/13, lineThickness);
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
                    game.setScreen(new MainMenuScreen(game));
                }
                else {
                    exitDialog.hide();
                }
            }
        };

        exitDialog.text("Return to Main Menu?");
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