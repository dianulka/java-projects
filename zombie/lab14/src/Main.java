import javax.swing.*;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        // write your code here

        JFrame frame = new JFrame("Zombie");
        SpriteFactory factoryMain = null;
        ZombieFactory zombiefac = null;
        DrawPanel panel = new DrawPanel(Main.class.getResource("/resources/tloZombie.jpg"), factoryMain, zombiefac);


        frame.setContentPane(panel);
        //frame.setSize(1000, 700);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

    }
    }