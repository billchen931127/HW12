package pingpong;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements KeyListener {
    static int score;
    private Racquet racquet;
    private Ball ball;
    private GamePanel gamePanel;

    public Window() {
        this.setTitle("不讓球掉下來!!");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);

        racquet = new Racquet(this);
        ball = new Ball(this);

        gamePanel = new GamePanel();
        this.add(gamePanel);

        // 遊戲循環
        new Thread(() -> {
            while (true) {
                ball.moveBall();
                checkCollision();
                gamePanel.repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        this.setVisible(true);
    }

    private void checkCollision() {
        if (ball.getBounds().intersects(racquet.getBounds())) {
            ball.invertYDirection();
            score++;
        }
        if (ball.getY() + Ball.getBallSize() > getHeight()) {
            System.out.println("Game Over!");
            ball.reset();
            racquet.reset();
            score = 0;
        }
    }

    public static void main(String[] args) {
        new Window();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            racquet.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            racquet.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // 在視窗上設計分數
            g2d.setColor(Color.GRAY);
            g2d.setFont(new Font("Verdana", Font.BOLD, 50));
            g2d.drawString(String.valueOf(score), 500, 120);

            // 畫球和球拍
            ball.paint(g2d);
            racquet.paint(g2d);
        }
    }
}
