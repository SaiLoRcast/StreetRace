import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame f = new JFrame("Street Race");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(1100, 630);
        f.setResizable(false);
        f.setLocationRelativeTo(null);

        f.add(new Road());

        f.setVisible(true);


    }
}
