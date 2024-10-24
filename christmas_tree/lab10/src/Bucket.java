import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bucket implements XmasShape {
    List<XmasShape> shapes = new ArrayList<>();
    int x;
    int y;
    Bucket(int x,int y){
        this.x = x;
        this.y = y;
        Random r = new Random();
        int i = 0;

        while (i < 5) {
            Bubble b = new Bubble();
            b.x = r.nextInt(0, 100)+20;
            b.y = r.nextInt(0, 100)+20;
            b.scale = 0.3;
            b.fillColor = Color.pink;
            b.lineColor = Color.black;
            shapes.add(b);
            i++;
        }
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
    }

    @Override
    public void render(Graphics2D g2d) {
       // g2d.setColor(new Color(192,192,192));
        //g2d.fillRect(0,0,100,100);
        for(var b:shapes)b.draw(g2d);
    }


}
