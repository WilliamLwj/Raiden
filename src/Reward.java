import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.*;

public class Reward extends AbleToFly{
	

    public static final int velocity_x = (int) Math.round(Math.random() * 4) + 1;
    public static final int velocity_y = (int) Math.round(Math.random() * 5) + 1;

 
    private String IMG_FILE;
    private BufferedImage img;
    private RewardKind kind;
    
    public Reward(int posx, int posy, int width, int height,
    			int courtWidth, int courtHeight, String img_file, RewardKind kind) {
    	super(velocity_x, velocity_y, posx, posy, width, height, courtWidth, courtHeight, 3);
    	this.IMG_FILE = img_file;
    	this.kind = kind;
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }  
    }
    
    public RewardKind getKind() {
    	return kind;
    }
    
    //A reward cannot shoot
    @Override
    public LinkedList<Bullet> shoot(){
    	return null;
    } 

    
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
    }
}
