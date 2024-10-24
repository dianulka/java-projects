import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

import static java.awt.BasicStroke.JOIN_MITER;

public class ClockWithGui extends JPanel {

    LocalTime time = LocalTime.now();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        ClockWithGui clockWithGui = new ClockWithGui();

        frame.setContentPane(clockWithGui); // Use the existing instance
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);


    }
    ClockWithGui(){
        ClockThread c= new ClockThread();
        c.start();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();


        g2d.translate(getWidth() / 2, getHeight() / 2);
        g2d.setPaint(new Color(	255, 202, 212));
        g2d.fillOval(-165,-175,getWidth()/2,getHeight()/2);
        g2d.setPaint(new Color(	157, 129, 137));

        for (int i = 1; i < 13; i++) {
            AffineTransform at = new AffineTransform();
            at.rotate(2 * Math.PI / 12 * i);
            Point2D src = new Point2D.Float(0, -120);
            Point2D trg = new Point2D.Float();
            at.transform(src, trg);
            //g2d.drawLine((int) trg.getX()-5, (int) trg.getY()-5,(int) trg.getX()-2, (int) trg.getY()-2);

            Font boldFont = new Font("Arial", Font.BOLD, 14);
            g2d.setFont(boldFont);
            g2d.setPaint(Color.blue);
            g2d.drawString(Integer.toString(i), (int) trg.getX(), (int) trg.getY());
        }
        g2d.setPaint(Color.black);


        AffineTransform saveAT = g2d.getTransform();

        g2d.rotate((time.getHour() % 12 + time.getMinute() / 60.0) * 2 * Math.PI / 12);
        float strokeWidth = 8.0f;
        g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, JOIN_MITER));
        g2d.drawLine(0, 0, 0, -30);
        g2d.setTransform(saveAT);


        g2d.rotate((time.getMinute() + time.getSecond() / 60.0) * 2 * Math.PI / 60);
        strokeWidth = 4.0f;
        g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, JOIN_MITER));
        g2d.setPaint(Color.cyan);
        g2d.drawLine(0, 0, 0, -70);
        g2d.setTransform(saveAT);


        g2d.rotate(time.getSecond() * 2 * Math.PI / 60);
        strokeWidth = 2.0f;
        g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT, JOIN_MITER));
        g2d.setPaint(Color.red);
        g2d.drawLine(0, 0, 0, -100);
        g2d.fillOval(-5,-100,10,5);
        g2d.setTransform(saveAT);

        g2d.setPaint(Color.PINK);
        g2d.drawOval(-165,-175,getWidth()/2,getHeight()/2);

        g2d.dispose();
    }

        class ClockThread extends Thread{
            @Override
            public void run() {
                for(;;){
                    time = LocalTime.now();
                    System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    repaint();
                }
            }
        }
    }

