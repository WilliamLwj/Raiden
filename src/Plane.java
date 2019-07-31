
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.*;

public class Plane extends AbleToFly{
	public static final String IMG_FILE = "files/player.png";
	
	public static int numOfLife = 5;
    public static final int size = 40;
    public static final int posx = 200;
    public static final int posy = 500;
    public static final int velocity_x = 0;
    public static final int velocity_y = 0;
    private int courtWidth;
    private int courtHeight;
    
    private int numOfBullets = 1;
    private BulletKind bullet = BulletKind.SIMPLE;
    
    private LinkedList<Bullet> bulletlist;
    
    private boolean missle = false;
    private int blast = 5;

    private static BufferedImage img;

    public Plane(int courtWidth, int courtHeight) {
    	super(velocity_x, velocity_y, posx, posy, size, size, courtWidth, courtHeight, numOfLife);
    	this.courtWidth = courtWidth;
    	this.courtHeight = courtHeight;
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }  
       
    }
    
    public int getBullets() {
    	return numOfBullets;
    }
    
    public BulletKind getBulletKind() {
    	return bullet;
    }
    
    public boolean getMissle() {
    	return missle;
    }
    
    //Upload the bullets into the plane
    public void uploadBullets(BulletKind bullet) {
    	bulletlist = new LinkedList<Bullet>();
    	int bullet_position = this.getPx() - 18; 
    	int d = size/numOfBullets;
        for (int i = 1; i <= numOfBullets /2; i++) {
        	
        	bullet_position = bullet_position + d;
        	Bullet l = new Bullet(bullet.getWidth(),bullet.getHeight(),
        			(-1)*bullet.getSpeed_x(), bullet.getSpeed_y(), bullet_position, this.getPy() + 5, 
        		courtWidth, courtHeight, bullet.changeBullet());
        	bulletlist.add(l);  									//Some of the bullets go to the left
        }
        
        bullet_position = bullet_position + d;
    	Bullet m = new Bullet(bullet.getWidth(),bullet.getHeight(),    
    			0, bullet.getSpeed_y(), bullet_position, this.getPy() + 5, 
    		courtWidth, courtHeight, bullet.changeBullet());                    
    	bulletlist.add(m);											//Some of them stay straight
        	
        for (int i = numOfBullets /2 + 2; i <= numOfBullets; i++) {
        	bullet_position = bullet_position + d;
        	Bullet r = new Bullet(bullet.getWidth(),bullet.getHeight(),
        			bullet.getSpeed_x(), bullet.getSpeed_y(), bullet_position, this.getPy() + 5, 
        		courtWidth, courtHeight, bullet.changeBullet());    //Some of them go to the right
        	bulletlist.add(r);
        }
        
        
        if (missle) {
        	Bullet b = new Bullet(BulletKind.MISSLE.getWidth(), BulletKind.MISSLE.getHeight(),
        			BulletKind.MISSLE.getSpeed_x(), BulletKind.MISSLE.getSpeed_y(), this.getPx()-40, this.getPy() + 5, 
	        		courtWidth, courtHeight, BulletKind.MISSLE.changeBullet());
			Bullet c =  new Bullet(BulletKind.MISSLE.getWidth(), BulletKind.MISSLE.getHeight(),
					BulletKind.MISSLE.getSpeed_x(), BulletKind.MISSLE.getSpeed_y(), this.getPx()+40, this.getPy() + 15, 
	        		courtWidth, courtHeight, BulletKind.MISSLE.changeBullet());
			bulletlist.add(b);
			bulletlist.add(c);
        }
    }
    
    //Upgrade the plane by receiving award
    public void upGrade(RewardKind reward) {
    	switch(reward) {
    		case PLUS_BULLET:{
    			if (this.numOfBullets < 5)
    				this.numOfBullets = this.numOfBullets + 2;
    			break;
    		}
    		case LIGHT:{
    			this.bullet = BulletKind.LIGHT;
    			break;
    		}
    		case MISSLE:{
    			this.missle = true;
    			break;
    		}
    		default:
    			{}
    	}
    	
    }
    
    
    //If the plane is destroyed
    public void destroyed() {
    	if(this.getLife() > 0) {
    		this.numOfBullets = 1;
    		this.bullet = BulletKind.SIMPLE;
    		this.missle = false;
    	}
    }
    
    public Bullet blast() {
    	if (blast > 0) {
    		this.blast = this.blast - 1;
    	}
    	Bullet b = new Bullet(BulletKind.BLAST.getWidth(), BulletKind.BLAST.getHeight(),
				BulletKind.BLAST.getSpeed_x(), BulletKind.BLAST.getSpeed_y(), this.getPx()+40, this.getPy() + 15, 
        		courtWidth, courtHeight, BulletKind.BLAST.changeBullet());
    	return b;
    }

    public int getBlast() {
    	return (blast);
    }

    @Override
    public LinkedList<Bullet> shoot(){
    	uploadBullets(bullet);
        return bulletlist;
    } 

    
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
    }
}
