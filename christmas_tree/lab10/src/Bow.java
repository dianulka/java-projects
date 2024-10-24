import javax.swing.*;
import java.awt.*;

public class Bow implements XmasShape {
    int x;
    int y;
    double scale;
    Image image;
    Bow(int x,int y,double scale){
        image= new ImageIcon("cute-pink-bow-png-0.png").getImage();
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
