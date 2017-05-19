package com.wilhelm.notaclicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

/**
 * Created by Brett_W on 5/17/2017.
 */

public class MiscFunc {
    private Pixmap drawnShape;

    public MiscFunc() {

    }

    public void fillRoundedRectangle(Color c, int x, int y, int width, int height, int radius) {
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

    public void fillLayeredRoundedRectangle(Color c1, Color c2, int x, int y, int width, int height, int radius) {
        fillRoundedRectangle(c1, x, y, width, height, radius);

        x += 12;
        y += 12;
        width -= 24;
        height -= 24;

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

    public Pixmap getPixmap() {
        return drawnShape;
    }
}