package com.wilhelm.notaclicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

/**
 * Created by Brett_W on 5/17/2017.
 */

public class MiscFunc {

    public MiscFunc() {

    }

    public Pixmap fillRoundedRectangle(Color c, int x, int y, int width, int height, int radius) {
        Pixmap drawnShape = new Pixmap(width, height, Pixmap.Format.RGBA8888);

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

        return drawnShape;
    }

    public Pixmap fillLayeredRoundedRectangle(Color c, int x, int y, int width, int height, int radius) {
        Pixmap drawnShape = new Pixmap(width, height, Pixmap.Format.RGBA8888);

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

        x += 10;
        y += 10;
        width -= 10;
        height -= 10;
        c = Color.WHITE;

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

        return drawnShape;
    }
}