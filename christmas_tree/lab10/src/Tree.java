import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tree implements XmasShape{
    int x;
    int y;
    double scale;
    GradientPaint color;


    Tree(int x,int y, double scale,GradientPaint color){
        this.x=x;
        this.y=y;
        this.scale=scale;
        this.color=color;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale, scale);

    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setPaint(color);
        g2d.fillArc(0,0,100,60,240,60);
        g2d.fillArc(0,20,100,60,240,60);
        g2d.fillArc(0,40,100,60,240,60);


    }
}
