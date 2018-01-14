import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public static final int MAX_SPEED = 50;
    public static final int MAX_TOP = 10;
    public static final int MAX_BOTTOM = 530;

    Image img_center = new ImageIcon("res/player.png").getImage();
    Image img_left = new ImageIcon("res/player_left.png").getImage();
    Image img_right = new ImageIcon("res/player_right.png").getImage();

    Image img = img_center;

    int speed = 0;
    int acceleration = 0;
    int s = 0;

    int x = 30;
    int y = 100;
    int dy = 0;

    int layer1 = 0;
    int layer2 = 1800;

    public Rectangle getRect() {
        //TODO Шиниру и высоту сделать через геттеры
        return new Rectangle(x, y, 100, 50);
    }

    public void move() {
        s += speed;
        speed += acceleration;
        if (speed <= 0) {
            speed = 0;
        }
        if (speed >= MAX_SPEED) {
            speed = MAX_SPEED;
        }
        y -= dy;
        if (y <= MAX_TOP) {
            y = MAX_TOP;
        }
        if (y >= MAX_BOTTOM) {
            y = MAX_BOTTOM;
        }

        if (layer2 - speed <= 0) {
            layer1 = 0;
            layer2 = 1800;

        } else {
            layer1 -= speed;
            layer2 -= speed;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            acceleration = 1;
        }
        if (key == KeyEvent.VK_LEFT) {
            acceleration = -1;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 5;
            img = img_left;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = -5;
            img = img_right;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            acceleration = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
            img = img_center;
        }
    }
}
