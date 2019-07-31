import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class PlayerStatus extends AbleToFly{
	//If defeated an enemy

    
    private static BufferedImage lifeImg;
    private BufferedImage lifenumImg;
    private static BufferedImage BlastImg;
    private BufferedImage BlastnumImg;
    private static final int width = 30;
    private static final int height = 30;	
    private int life;
    private int blast;
    public PlayerStatus(int courtWidth, int courtHeight, int life, int blast) {
    	super(0, 0, 0, 530,  width,  height, courtWidth, courtHeight, 0);
    	this.life = life;
    	this.blast = blast;
        try {
        	  if(lifeImg == null) {
        		 lifeImg = ImageIO.read(new File("files/life.png"));
        	  }
        	  if(lifenumImg == null) {
         		 lifenumImg = ImageIO.read(new File("files/numbers/" + life + ".png"));
         	  }
        	  
        	  if(BlastImg == null) {
         		 BlastImg = ImageIO.read(new File("files/blast.png"));
         	  }
         	  if(BlastnumImg == null) {
          		 BlastnumImg = ImageIO.read(new File("files/numbers/" + blast + ".png"));
          	  }
         	  
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    
    @Override
    public void draw(Graphics g) {
    	g.drawImage(lifeImg, this.getPx() , this.getPy(), this.getWidth(), this.getHeight() , null);
    	g.drawImage(lifenumImg, this.getPx() + 10 + this.getWidth() ,
    			this.getPy(), this.getWidth() - 10, this.getHeight() - 10, null);
    	g.drawImage(BlastImg, this.getPx() , this.getPy() + getHeight(), this.getWidth() , this.getHeight(), null);
    	g.drawImage(BlastnumImg, this.getPx() + 10 + this.getWidth(), 
    			this.getPy() + this.getHeight() + 5, this.getWidth() - 10, this.getHeight() - 10, null);
    	}
    
    @Override
    public LinkedList<Bullet> shoot(){
    	return null;
    }  
    
 }
	
	

