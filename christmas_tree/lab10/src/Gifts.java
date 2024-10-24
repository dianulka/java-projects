import javax.swing.*;
import java.awt.*;

public class Gifts implements XmasShape{
    int x;
    int y;
    double scale;
    Image image;
    Gifts(int x,int y,double scale){
        image= new ImageIcon("5847d828cef1014c0b5e4808.png").getImage();
        this.scale=scale;
        this.x=x;
        this.y=y;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale, scale);

    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(image,x,y,null);

    }
}
