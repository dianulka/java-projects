import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Star implements XmasShape {
    int x;
    int y;
    double scale;
    Color color;
    Star(int x,int y, double scale,Color color){
        this.x=x;
        this.y=y;
        this.scale=scale;
        this.color=color;
    }

    @Override
    public void render(Graphics2D g2d) {
        int[] xPoints = {42, 52, 72, 52, 60, 40, 15, 28, 9, 32, 42};
        int[] yPoints = {38, 62, 68, 80, 105, 85, 102, 75, 58, 60, 38};

        Polygon star = new Polygon(xPoints, yPoints, 10);

        g2d.setPaint(color);
        g2d.fillPolygon(star);
    }

    @Override
    public void transform(Graphics2D g2d) {

        g2d.translate(x, y);


        g2d.scale(scale, scale);
    }
}