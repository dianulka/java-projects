import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.List;

import static java.lang.Thread.sleep;


public class DrawPanel  extends JPanel {
    BufferedImage background;
 //   Zombie zombie;
    List <Zombie> zombieArray = new ArrayList<Zombie>();
    List<Sprite> sprites = new ArrayList<>();

    SpriteFactory factory;
    ZombieFactory zombieFactory;

    DrawPanel(URL backgroundImagageURL, SpriteFactory factory, ZombieFactory factoryZomb) {
        try {
            background = ImageIO.read(backgroundImagageURL);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        this.factory = factory;
        new AnimationThread().start();
        this.zombieFactory = factoryZomb;
    }



    //ajak mam w setframe to dopasujey do frame ale pierszy i tak byle jak bo sie setframne nie outworzylo
//    DrawPanel(URL backgroundImagageURL) throws IOException, InterruptedException {
//        try {
//            background = ImageIO.read(backgroundImagageURL);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
//       // zombie = new Zombie(background.getWidth(), (int)(background.getHeight()*0.6),1);
//
//      //  new AnimationThread().start();
////        for (int i = 0; i< 10; i++){
////            Zombie zombie = new Zombie(background.getWidth(), (int)(background.getHeight()*0.6), Math.random()+0.7);
////            zombieArray.add(zombie);
////            int finalI = i;
////            new Thread(() -> {
////                try {
////                    sleep(300 * finalI);
////                    new AnimationThread(zombie).start();
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////            }).start();
////
////        }
//
//        for (int i = 0; i< 10; i++){
//            Zombie zombie = new Zombie(background.getWidth(), (int)(background.getHeight()*0.6), Math.random()+0.7);
//            sprites.add(zombie);
//            int finalI = i;
//            new Thread(() -> {
//                try {
//                    sleep(300 * finalI);
//                    new AnimationThread(zombie).start();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//
//        }
//
//
//        }



    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
       // zombie.draw(g, this);
//        for (Zombie z : zombieArray){
//            z.draw(g,this);
//        }

        for (Sprite s : sprites){
            s.draw(g,this);
        }

    }

    class AnimationThread extends Thread {
//        private Zombie zombie;
//        public AnimationThread(Zombie zombie) {
//            this.zombie = zombie;
//        }
//        public void run() {
//            for (int i = 0; ; i++) {
//                zombie.next();
//                repaint();
//                try {
//                    sleep(1000 / 30);  // 30 klatek na sekundę
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

//        private Sprite sprite;
//        public AnimationThread(Sprite sprite){
//            this.sprite = sprite;
//        }

        public AnimationThread(){
        }

//        @Override
//        public void run() {
//            for (int i = 0; ; i++) {
//                sprite.next();
//                repaint();
//                try {
//                    sleep(1000 / 30);  // 30 klatek na sekundę
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        @Override
        public void run(){
            for(int i=0;;i++) {
                //..

                if(i%30==0) {
                    try {
                        sprites.add(factory.newSprite(getWidth(),(int)(0.6*getHeight())));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }


    }
}

