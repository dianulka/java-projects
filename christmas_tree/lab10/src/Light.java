import java.awt.*;

public class Light implements XmasShape{
    int x;
    int y;
    double scale;
    Color colorWax;
    Color colorWick;

    Light(int x,int y,double scale,Color colorWax,Color colorWick){
        this.x=x;
        this.y=y;
        this.scale=scale;
        this.colorWax=colorWax;
        this.colorWick=colorWick;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);

    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(colorWick);
        g2d.fillOval(2,0,5,10);
        g2d.setColor(colorWax);
        g2d.fillRect(0,10,10,15);
    }
}
