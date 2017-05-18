package com.wilhelm.notaclicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * @author Brett_W
 */

public class MainMenuScreen implements Screen {
    private final NotAClicker game;
    private SpriteBatch batch;
    private Viewport viewport;
    private ProgressBar bar;
    private ProgressBar.ProgressBarStyle barStyle;

    public MainMenuScreen(final NotAClicker game) {
        this.game = game;

        Stage stage = new Stage();
        Camera camera = new PerspectiveCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        batch = new SpriteBatch();

        init();
        loading();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        batch.begin();
            if(!bar.isDisabled()) bar.draw(batch,1);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
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

    public void update(float delta) {

        // Loading
        if(bar.getValue() >= 100) bar.setDisabled(true);
        bar.setValue((bar.getValue()+2));
        bar.act(delta);


    }

    public void init() {
        // Loading
        Skin skin = new Skin();
        Pixmap yellowBar = new Pixmap(100, 20, Pixmap.Format.RGBA8888);
        yellowBar.setColor(Color.YELLOW);
        yellowBar.fill();
        skin.add("yellow", new Texture(yellowBar));
        TextureRegionDrawable textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(yellowBar)));
        barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("yellow", Color.BLACK), textureBar);
        barStyle.knobBefore = barStyle.knob;
        bar = new ProgressBar(0, 0, 1, false, barStyle);
        bar.setDisabled(true);

        // Main Menu

    }

    private void roundedRect(Pixmap toggleButtonPixmap, Color c, int x, int y, int width, int height, int radius){
        // Central rectangle
        toggleButtonPixmap.setColor(c);
        toggleButtonPixmap.fillRectangle(x + radius, y + radius, width - 2*radius, height - 2*radius);

        // Four side rectangles, in clockwise order
        toggleButtonPixmap.fillRectangle(x + radius, y, width - 2*radius, radius);
        toggleButtonPixmap.fillRectangle(x + width - radius, y + radius, radius, height - 2*radius);
        toggleButtonPixmap.fillRectangle(x + radius, y + height - radius, width - 2*radius, radius);
        toggleButtonPixmap.fillRectangle(x, y + radius, radius, height - 2*radius);

        // Four arches, clockwise too
        toggleButtonPixmap.fillCircle(x + radius, y + radius, radius);
        toggleButtonPixmap.fillCircle(x + width - radius, y + radius, radius);
        toggleButtonPixmap.fillCircle(x + width - radius, y + height - radius, radius);
        toggleButtonPixmap.fillCircle(x + radius, y + height - radius, radius);
    }

    public void loading() {
        bar = new ProgressBar(0, 100, 0.5f, false, barStyle);
        bar.setColor(Color.WHITE);
        bar.setSize(Gdx.graphics.getWidth(), bar.getPrefHeight());
        bar.setPosition(Gdx.graphics.getWidth()/2-bar.getWidth()/2, Gdx.graphics.getHeight()-bar.getHeight());
        bar.setDisabled(false);
    }
}