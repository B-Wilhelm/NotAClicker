package com.wilhelm.notaclicker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
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

/**
 * @author Brett_W
 */

public class NotAClicker extends ApplicationAdapter {
	private SpriteBatch batch;
	private Viewport viewport;
	private ProgressBar bar;
	private ProgressBar.ProgressBarStyle barStyle;
	
	@Override
	public void create () {
		Stage stage = new Stage();
		Actor myActor = new Actor();
		Camera camera = new PerspectiveCamera();
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		batch = new SpriteBatch();
		stage.addActor(myActor);

		init();
		loading();
	}

	@Override
	public void render () {
		float delta = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update(delta);

		batch.begin();
			bar.draw(batch, 1);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	public void update(float delta) {
		bar.setValue((bar.getValue()+2));
		bar.act(delta);
	}

	public void init() {
		//	Loading
		Skin skin = new Skin();
		Pixmap yellowBar = new Pixmap(100, 20, Pixmap.Format.RGBA8888);
		yellowBar.setColor(Color.YELLOW);
		yellowBar.fill();
		skin.add("yellow", new Texture(yellowBar));
		TextureRegionDrawable textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(yellowBar)));
		barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("yellow", Color.BLACK), textureBar);
		barStyle.knobBefore = barStyle.knob;

		//
	}

	public void loading() {
		bar = new ProgressBar(0, 100, 0.5f, false, barStyle);
		bar.setColor(Color.WHITE);
		bar.setSize(Gdx.graphics.getWidth(), bar.getPrefHeight());
		bar.setPosition(Gdx.graphics.getWidth()/2-bar.getWidth()/2, Gdx.graphics.getHeight()-bar.getHeight());
	}
}