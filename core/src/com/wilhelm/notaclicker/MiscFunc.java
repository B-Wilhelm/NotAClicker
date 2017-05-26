package com.wilhelm.notaclicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Brett_W on 5/17/2017.
 */

class MiscFunc {
    private Pixmap drawnShape;
    private Dialog exitDialog;

    MiscFunc() {

    }

    Dialog createDialog(final Game game, String dialogQuestion, final String screenName) {
        Color blue = new Color(44f/255f, 182f/255f, 216f/255f, 1);
        Color yellow = new Color(240, 240, 0, 1);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ubuntu_bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 84;
        BitmapFont font = generator.generateFont(parameter);
        parameter.size = 72;
        BitmapFont exitButtonFont = generator.generateFont(parameter);
        Skin dialogSkin = new Skin();

        fillLayeredRoundedRectangle(yellow, blue, 0, 0, Gdx.graphics.getWidth()*3/4, Gdx.graphics.getHeight()/4, 4, 24);
        Pixmap dialogPixmap = getPixmap();
        fillLayeredRoundedRectangle(yellow, blue, 0, 0, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/15, 32, 12);
        Pixmap buttonPixmap = getPixmap();

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
                    if(screenName.equals("exit")) { Gdx.app.exit(); }
                    else if(screenName.equals("main")) { game.setScreen(new MainMenuScreen(game)); }
                }
                else {
                    exitDialog.hide();
                }
            }
        };

        exitDialog.text(dialogQuestion);
        exitDialog.button("Yes", true); // sends "true" as the result
        exitDialog.button("No", false); // sends "false" as the result
        exitDialog.getButtonTable().padBottom(Gdx.graphics.getHeight()/25);
        exitDialog.getButtonTable().getCells().get(0).padRight(Gdx.graphics.getWidth()/42);
        exitDialog.getButtonTable().getCells().get(1).padLeft(Gdx.graphics.getWidth()/42);

        return exitDialog;
    }

    private void fillRoundedRectangle(Color c, int x, int y, int width, int height, int radius) {
        drawnShape = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        // Central rectangle
        drawnShape.setColor(c);
        drawnShape.fillRectangle(x + radius, y + radius, width - 2*radius, height - 2*radius);

        // Four side rectangles, in clockwise order
        drawnShape.fillRectangle(x + radius, y, width - 2*radius, radius);
        drawnShape.fillRectangle(x + width - radius, y + radius, radius, height - 2*radius);
        drawnShape.fillRectangle(x + radius, y + height - radius, width - 2*radius, radius);
        drawnShape.fillRectangle(x, y + radius, radius, height - 2*radius);

        // Four arches, clockwise too
        drawnShape.fillCircle(x + radius, y + radius, radius);
        drawnShape.fillCircle(x + width - radius, y + radius, radius);
        drawnShape.fillCircle(x + width - radius, y + height - radius, radius);
        drawnShape.fillCircle(x + radius, y + height - radius, radius);
    }

    void fillLayeredRoundedRectangle(Color c1, Color c2, int x, int y, int width, int height, int radius, int thickness) {
        fillRoundedRectangle(c1, x, y, width, height, radius);

        x += thickness;
        y += thickness;
        width -= (thickness*2);
        height -= (thickness*2);

        // Central rectangle
        drawnShape.setColor(c2);
        drawnShape.fillRectangle(x + radius, y + radius, width - 2*radius, height - 2*radius);

        // Four side rectangles, in clockwise order
        drawnShape.fillRectangle(x + radius, y, width - 2*radius, radius);
        drawnShape.fillRectangle(x + width - radius, y + radius, radius, height - 2*radius);
        drawnShape.fillRectangle(x + radius, y + height - radius, width - 2*radius, radius);
        drawnShape.fillRectangle(x, y + radius, radius, height - 2*radius);

        // Four arches, clockwise too
        drawnShape.fillCircle(x + radius, y + radius, radius);
        drawnShape.fillCircle(x + width - radius, y + radius, radius);
        drawnShape.fillCircle(x + width - radius, y + height - radius, radius);
        drawnShape.fillCircle(x + radius, y + height - radius, radius);
    }

    Pixmap getPixmap() {
        return drawnShape;
    }
}