
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;


public class Bullet extends AbleToFly {
    
    private String IMG_FILE;
    
    private BufferedImage img;

    public Bullet (int width, int height, int vx, int vy, 
    	int px, int py, int courtWidth, int courtHeight,  String img_file) {
        super(vx, vy, px, py, width, height, courtWidth, courtHeight, 1);

        this.IMG_FILE = img_file;
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        
    }
    
    public String getBulletPath() {
    	return IMG_FILE;
    }
    
    // A bullet cannot shoot;
    @Override
    public LinkedList<Bullet> shoot(){
    	return null;
    }  
    
 
    
    @Override
    public void draw(Graphics g) {
    	g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
    }
}