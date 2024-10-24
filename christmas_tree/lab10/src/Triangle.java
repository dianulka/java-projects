import java.awt.*;

public class Triangle implements XmasShape{

    int x;
    int y;
    double scale;

    Color color;

    Triangle (int x, int y, double scale, Color color){
        this.x=x;
        this.y=y;
        this.scale=scale;
        this.color=color;
    }
    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);

        g2d.scale(scale, scale);
    }

    @Override
    public void render(Graphics2D g2d) {

        g2d.setColor(color);
        g2d.fillArc(100,100,100,60,240,60);
    }
}
