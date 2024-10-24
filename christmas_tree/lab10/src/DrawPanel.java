import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;

import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    List<XmasShape> shapes = new ArrayList<>();

    public DrawPanel(){
        setBackground(new Color(145, 50, 80));
        addObjects();
    }
    private void addObjects() {

       // Bubble bubble1= new Bubble(65,25,5,null,Color.darkGray);
       // shapes.add(bubble1);

        BigBubble bubble1= new BigBubble(55,25,5,null,new GradientPaint(0,0,new Color(199, 136, 155),30,80, new Color(161, 56, 89)));
        shapes.add(bubble1);

        Snow snow1=new Snow(60,450,Color.WHITE,5);
        shapes.add(snow1);
        Snow snow2=new Snow(160,515,Color.WHITE,3);
        shapes.add(snow2);

        Trunk trunk1=new Trunk(285,400,2,new Color(219,148,52,255));
        shapes.add(trunk1);

       // Snow snow3=new Snow(200,445,new Color(145, 50, 80),2);
        //shapes.add(snow3);

        Tree tree1=new Tree(100,0,4,new GradientPaint(0,0,new Color(77, 181, 112),0,100, new Color(0, 149, 51)));
        shapes.add(tree1);


        Star star=new Star(240,-5,1.5,new Color(254, 242, 81));
        shapes.add(star);

      //  Bucket bucket=new Bucket(150,200);
       // shapes.add(bucket);
        //Bubble bubble1=new Bubble()
        Bow bow1=new Bow(220,180,0.05);
        shapes.add(bow1);

        Bow bow2=new Bow(270,320,0.05);
        shapes.add(bow2);

        Bubble bubble2= new Bubble(310,175,0.3,new Color(152, 5, 112),new Color(228, 8, 167));
        shapes.add(bubble2);

        Bubble bubble3= new Bubble(250,275,0.3,new Color(152, 5, 112),new Color(228, 8, 167));
        shapes.add(bubble3);

        Bubble bubble4= new Bubble(290,225,0.3,new Color(152, 5, 112),new Color(228, 8, 167));
        shapes.add(bubble4);

        Bubble bubble5= new Bubble(315,270,0.3,new Color(152, 5, 112),new Color(228, 8, 167));
        shapes.add(bubble5);

        Bubble bubble6= new Bubble(240,360,0.3,new Color(152, 5, 112),new Color(228, 8, 167));
        shapes.add(bubble6);

        Light light=new Light(200,200,1,Color.WHITE,Color.YELLOW);
        shapes.add(light);

        Light light2=new Light(390,280,1,Color.WHITE,Color.YELLOW);
        shapes.add(light2);

        Light light3=new Light(200,360,1,Color.WHITE,Color.YELLOW);
        shapes.add(light3);

        Gifts gifts=new Gifts(250,330,0.15);
        shapes.add(gifts);






    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (XmasShape s : shapes) {
            s.draw((Graphics2D) g);
        }
    }
}