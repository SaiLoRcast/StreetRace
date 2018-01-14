import com.sun.xml.internal.bind.v2.TODO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Road extends JPanel implements ActionListener, Runnable {

    Timer mainTimer = new Timer(20, this);

    Image img = new ImageIcon("res/road.png").getImage();

    Player player = new Player();

    Thread enemiesFactory = new Thread(this);

    List<Enemy> enemies = new ArrayList<Enemy>();

    public Road() {
        mainTimer.start();
        enemiesFactory.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    @Override
    public void run() {
        while (true) {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(2000));
                enemies.add(new Enemy(1200, random.nextInt(530), random.nextInt(30), this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, player.layer1, 0, null);
        g.drawImage(img, player.layer2, 0, null);
        g.drawImage(player.img, player.x, player.y, null);

        double speed = (100 / Player.MAX_SPEED) * player.speed;
        g.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.drawString("Скорость: " + speed + "км/ч", 50, 50);

        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (e.x >= 2400 || e.x <= -2400) {
                i.remove();
            } else {
                //TODO Убрать метод move() из paint()
                e.move();
                g.drawImage(e.img, e.x, e.y, null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move();
        repaint();
        collisionWithEnemies();
//        System.out.println(enemies.size());
    }

    private void collisionWithEnemies() {
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy enemy = i.next();
            if (player.getRect().intersects(enemy.getRect())) {
                JOptionPane.showMessageDialog(null, "Вы проиграли!");
                System.exit(1);

            }
        }
    }
}
