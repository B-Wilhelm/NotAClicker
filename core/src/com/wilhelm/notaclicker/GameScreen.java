package com.wilhelm.notaclicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
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
        final int lineThickness = 10;

        ShapeRenderer sR = new ShapeRenderer();
        timeBar = new TimeBar(sR, Color.BLACK, yellow, 0, Gdx.graphics.getHeight()*19/20, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/20, lineThickness);
        playerBar = new PlayerBar(sR, Color.BLACK, yellow, 0, 0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/13, lineThickness);
        gameBar = new GameBar(sR, Color.BLACK, yellow, Gdx.graphics.getWidth()/2, 0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/13, lineThickness);
        exitDialog = mF.createDialog(game, "Exit to Main Menu?", "main");
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