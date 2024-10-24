import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
public class Snow implements XmasShape{
    int x;
    int y;
    double scale;
    Color fillColor;

    Snow(int x,int y,Color fillColor,double scale){
        this.x=x;
        this.y=y;
        this.scale=scale;
        this.fillColor=fillColor;
    }
    Snow(){}

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }

    @Override
    public void render(Graphics2D g2d) {

        g2d.setColor(fillColor);
        g2d.fillOval(0,0,100,20);



    }
}
