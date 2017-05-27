package com.wilhelm.notaclicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * @author Brett_W
 */

class Overlay extends Actor {
    private Batch batch;
    private BitmapFont yellowFont, whiteFont;
    private TimeBar timeBar;
    private PlayerBar playerBar;
    private GameBar gameBar;
    private GlyphLayout layout = new GlyphLayout();
    private Color yellow = new Color(240, 240, 0, 1);
    private Color blue = new Color(44f/255f, 182f/255f, 216f/255f, 1);
    private int textPadding = Gdx.graphics.getWidth()/60;

    Overlay(Stage stage) {
        final int lineThickness = 10;
        Color borderColor = yellow;
        ShapeRenderer sR = new ShapeRenderer();

        createFont();

        this.batch = stage.getBatch();
        this.timeBar = new TimeBar(sR, borderColor, blue, 0, Gdx.graphics.getHeight()*19/20, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/20, lineThickness);
        this.playerBar = new PlayerBar(sR, borderColor, blue, 0, 0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/13, lineThickness);
        this.gameBar = new GameBar(sR, borderColor, blue, Gdx.graphics.getWidth()/2, 0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/13, lineThickness);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        timeBar.drawShape();
        playerBar.drawShape();
        gameBar.drawShape();

        timeBar.drawInfo();
        playerBar.drawInfo();
        gameBar.drawInfo();
    }

    private void createFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ubuntu_bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Gdx.graphics.getWidth()/34;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.color = yellow;
        yellowFont = generator.generateFont(parameter);
        parameter.color = Color.WHITE;
        whiteFont = generator.generateFont(parameter);
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

        void drawInfo() {
            String text1 = "";
            String text2 = "";
            String text3 = "";
            String bName = "";
            String pName = "";
            String exp = "";

            yellowFont.draw(batch, text1, x+textPadding, y+ Gdx.graphics.getHeight()/16);
            layout.setText(whiteFont,bName);
            whiteFont.draw(batch, bName, x+width-textPadding-layout.width-(thickness/2), y+Gdx.graphics.getHeight()/16);

            yellowFont.draw(batch, text2, x+textPadding, y+Gdx.graphics.getHeight()/24);
            layout.setText(whiteFont,pName);
            whiteFont.draw(batch, pName, x+width-textPadding-layout.width-(thickness/2), y+Gdx.graphics.getHeight()/24);

            yellowFont.draw(batch, text3, x+textPadding, y+Gdx.graphics.getHeight()/50);
            layout.setText(whiteFont,exp);
            whiteFont.draw(batch, exp, x+width-textPadding-layout.width-(thickness/2), y+Gdx.graphics.getHeight()/50);
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

        void drawInfo() {
            String text1 = "";
            String text2 = "";
            String text3 = "";
            String bName = "";
            String pName = "";
            String exp = "";

            yellowFont.draw(batch, text1, x+textPadding, y+Gdx.graphics.getHeight()/16);
            layout.setText(whiteFont,bName);
            whiteFont.draw(batch, bName, x+width-textPadding-layout.width-(thickness/2), y+Gdx.graphics.getHeight()/16);

            yellowFont.draw(batch, text2, x+textPadding, y+Gdx.graphics.getHeight()/24);
            layout.setText(whiteFont,pName);
            whiteFont.draw(batch, pName, x+width-textPadding-layout.width-(thickness/2), y+Gdx.graphics.getHeight()/24);

            yellowFont.draw(batch, text3, x+textPadding, y+Gdx.graphics.getHeight()/50);
            layout.setText(whiteFont,exp);
            whiteFont.draw(batch, exp, x+width-textPadding-layout.width-(thickness/2), y+Gdx.graphics.getHeight()/50);
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

        void drawInfo() {
            String text1 = "sup";
            String text2 = "";
            String text3 = "boyo";
            String bName = "";
            String pName = "";
            String exp = "";

            yellowFont.draw(batch, text1, x+textPadding, y+Gdx.graphics.getHeight()/16);
            layout.setText(whiteFont,bName);
            whiteFont.draw(batch, bName, x+width-textPadding-layout.width-(thickness/2), y+Gdx.graphics.getHeight()/16);

            yellowFont.draw(batch, text2, x+textPadding, y+Gdx.graphics.getHeight()/24);
            layout.setText(whiteFont,pName);
            whiteFont.draw(batch, pName, x+width-textPadding-layout.width-(thickness/2), y+Gdx.graphics.getHeight()/24);

            yellowFont.draw(batch, text3, x+textPadding, y+Gdx.graphics.getHeight()/50);
            layout.setText(whiteFont,exp);
            whiteFont.draw(batch, exp, x+width-textPadding-layout.width-(thickness/2), y+Gdx.graphics.getHeight()/50);
        }
    }
}
