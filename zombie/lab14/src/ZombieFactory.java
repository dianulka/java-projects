import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ZombieFactory implements SpriteFactory{

     BufferedImage tape;
    private ZombieFactory(){}
    public static ZombieFactory get() {
        return instance;
    }
    private static ZombieFactory instance = new ZombieFactory();
    @Override
    public Sprite newSprite(int x, int y) throws IOException {
        double scale = Math.random() * 1.8 + 0.2;// wylosuj liczbę z zakresu 0.2 do 2.0
        this.tape = ImageIO.read(getClass().getResource("/resources/walkingdead.png"));
        Zombie z = new Zombie(x,y,scale,tape);
        return z;
    }
}
