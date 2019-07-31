import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;


public class Map extends AbleToFly{

    public static final int velocity_x = 0;
    public static final int velocity_y = 3;

    private String IMG_FILE;
    private static BufferedImage img;
    private int c1 = 0;

    private int MAP_SPEED = 1;
    public Map(int courtWidth, int courtHeight, String file) {
    	super(velocity_x, velocity_y, 0, 0,  courtWidth,  courtHeight, courtWidth, courtHeight, 0);
    	this.IMG_FILE = file;
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(img,
        		this.getPx(), 
        		this.getPy(),
        		this.getWidth(),
        		this.getHeight(),
        		0,
        		2270 - c1 * MAP_SPEED,
        		640,
        		2870 - c1 * MAP_SPEED,
        		null);
        if (c1 < 2270) {
        	c1 = c1 + 1;
        }
        else {
        	c1 = 0;
        }
        
    }
    
    @Override
    public LinkedList<Bullet> shoot(){
    	return null;
    }  
}
