package pingpong;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

public class Ball {
    private static final int ballSize = 60;
    private int x;
    private int y;
    private int step = 5;
    private int incX = step;
    private int incY = step;
    private Window window;

    public Ball(Window w) {
        this.window = w;
        reset();
    }

    public int getY() {
        return y;
    }

    public static int getBallSize() {
        return ballSize;
    }

    public void moveBall() {
        if (x + incX > window.getWidth() - ballSize || x + incX < 0) {
            incX = -incX;
        }
        if (y + incY < 0) {
            incY = -incY;
        }
        x += incX;
        y += incY;
    }

    public void invertYDirection() {
        incY = -incY;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillOval(x, y, ballSize, ballSize);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ballSize, ballSize);
    }

    public void reset() {
        x = window.getWidth() / 2 - ballSize / 2;
        y = window.getHeight() / 2 - ballSize / 2;
        incX = step;
        incY = step;
    }
}
