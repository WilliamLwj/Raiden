import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public abstract class Enemies extends AbleToFly{
	private String IMG_FILE;
 
    private BufferedImage img;
    private EnemyKind kind;
    public Enemies(int vx, int vy, int px, int py, int width, int height, 
    		int courtWidth, int courtHeight, int life, String img_file, EnemyKind kind) {
    		
    	super(vx, vy, px, py, width, height, courtWidth, courtHeight, life);
    	this.kind = kind;
    	this.IMG_FILE = img_file;
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    public EnemyKind getKind() {
    	return kind;
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
    }
   
}
