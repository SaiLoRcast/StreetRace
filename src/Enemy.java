import javax.swing.*;
import java.awt.*;

public class Enemy {

    Image img = new ImageIcon("res/enemy.png").getImage();

    int x;
    int y;
    int enemySpeed;

    Road road;

    //TODO Сделать класс персонаж и этот метод, и геттеры, от него унаследовать в Enemy и Player
    public Rectangle getRect() {
        //TODO Шиниру и высоту сделать через геттеры
        return new Rectangle(x, y, 100, 50);
    }

    public Enemy(int x, int y, int enemySpeed, Road road) {
        this.x = x;
        this.y = y;
        this.enemySpeed = enemySpeed;
        this.road = road;
    }

    public void move() {
        x = x - road.player.speed + enemySpeed;
    }

}
