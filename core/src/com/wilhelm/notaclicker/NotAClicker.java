package com.wilhelm.notaclicker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.Color;

/**
 * @author Brett_W
 */

public class NotAClicker extends ApplicationAdapter {
	private SpriteBatch batch;
	private Viewport viewport;
	
	@Override
	public void create () {
		Stage stage = new Stage();
		Actor myActor = new Actor();
		Camera camera = new PerspectiveCamera();
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		batch = new SpriteBatch();
		stage.addActor(myActor);

		Skin skin = new Skin();
		Pixmap toggleButtonPixmap = new Pixmap(200, 30, Pixmap.Format.RGBA8888);
		toggleButtonPixmap.drawRectangle(10, 10, 200, 30);
		skin.add("green", new Texture(toggleButtonPixmap));
//		skin.add("default", font);
		ProgressBar bar = new ProgressBar(0, 1, .001f, true, skin);
		bar.setSize(200, 500);
		bar.setPosition(10, 10);
		bar.setSize(290, bar.getPrefHeight());
		bar.setAnimateDuration(2);
		stage.addActor(bar);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
	}
}