package com.wilhelm.notaclicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Brett_W on 5/17/2017.
 */

class MiscFunc {
    private Pixmap drawnShape;

    MiscFunc() {

    }

    void renderLayeredShape(ShapeRenderer s, Color c1, Color c2, int x, int y, int width, int height, int thickness) {
        s.begin(ShapeRenderer.ShapeType.Filled);
        s.setColor(c1);
        s.rect(x, y+thickness, thickness, height-(thickness*2));    // Left Side
        s.rect(x+thickness, y, width-(thickness*2), thickness);    // Bottom Side
        s.rect(x+width-thickness, y+thickness, thickness, height-(thickness*2));    // Right Side
        s.rect(x+thickness, y+height-thickness, width-(thickness*2), thickness);    // Top Side
        s.rect(x, y, thickness, thickness);    // Bottom Left Corner
        s.rect(x+width-thickness, y, thickness, thickness);    // Bottom Right Corner
        s.rect(x, y+height-thickness, thickness, thickness);    // Top Left Corner
        s.rect(x+width-thickness, y+height-thickness, thickness, thickness);    // Top Right Corner
        s.end();

        s.begin(ShapeRenderer.ShapeType.Filled);    // Inner Shape
        s.setColor(c2);
        s.rect(x+thickness, y+thickness, width-(thickness*2), height-(thickness*2));
        s.end();
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

    void fillLayeredRoundedRectangle(Color c1, Color c2, int x, int y, int width, int height, int radius) {
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

    Pixmap getPixmap() {
        return drawnShape;
    }
}