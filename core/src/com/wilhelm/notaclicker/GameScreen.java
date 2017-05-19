package com.wilhelm.notaclicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

    GameScreen(Game game) {
        this.game = game;

        stage = new Stage();
        Camera camera = new PerspectiveCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        init();
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

    private void update() {

    }

    private void init() {
        // Main Menu

    }

    private void createOverlay() {
        int upperDisplayWidth = Gdx.graphics.getWidth();
        int upperDisplayHeight = Gdx.graphics.getHeight()/10;
        int lowerLeftDisplayWidth = Gdx.graphics.getWidth()/2;
        int lowerLeftDisplayHeight = Gdx.graphics.getHeight()/10;

        ShapeRenderer sR = new ShapeRenderer();
        mF.renderLayeredShape(sR, Color.BLACK, Color.WHITE, 0, Gdx.graphics.getHeight()*9/10, upperDisplayWidth, upperDisplayHeight, 10);
        mF.renderLayeredShape(sR, Color.BLACK, Color.WHITE, 0, 0, lowerLeftDisplayWidth, lowerLeftDisplayHeight, 10);
        mF.renderLayeredShape(sR, Color.BLACK, Color.WHITE, lowerLeftDisplayWidth, 0, lowerLeftDisplayWidth, lowerLeftDisplayHeight, 10);

    }
}