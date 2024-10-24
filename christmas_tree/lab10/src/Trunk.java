import java.awt.*;

public class Trunk implements XmasShape{
    int x;
    int y;
    double scale;
    Color color;

    Trunk(int x,int y, double scale,Color color){
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
        g2d.fillRect(0,0,20,40);

    }
}
