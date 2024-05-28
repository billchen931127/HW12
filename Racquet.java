package pingpong;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Racquet {
    private int x;
    private static final int y = 570;
    private static final int WIDTH = 120;
    private static final int HEIGHT = 30;
    private Window window;

    public Racquet(Window w) {
        this.window = w;
        reset();
    }

    public void moveLeft() {
        if (x - 20 >= 0) {
            x -= 20;
        }
    }

    public void moveRight() {
        if (x + 20 + WIDTH <= window.getWidth()) {
            x += 20;
        }
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void reset() {
        x = window.getWidth() / 2 - WIDTH / 2;
    }
}
