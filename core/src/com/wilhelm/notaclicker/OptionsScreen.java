package com.wilhelm.notaclicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * @author Brett_W
 */

class OptionsScreen implements Screen {
    private MiscFunc mF;
    private Game game;
    private Stage stage;
    private Viewport viewport;
    private Dialog exitDialog;

    OptionsScreen(Game game) {
        Camera camera = new PerspectiveCamera();
        this.game = game;
        this.mF = new MiscFunc();
        this.stage = new Stage();
        this.viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        this.exitDialog = mF.createDialog(game, "Exit to Main Menu?", "main");

        init();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(44f/255f, 182f/255f, 216f/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        onBackPressed();

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

    }

    private void onBackPressed() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.setScreen(new MainMenuScreen(game));
        }
    }
}