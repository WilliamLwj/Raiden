import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Score extends AbleToFly{
	//If defeated an enemy

    private static final String IMG_FILE = "files/numbers/";
    private static BufferedImage[] img;
    private int scorewidth;
    	
    private String score;
    
    public Score(int scorewidth, int scoreheight,
    		int scoreposx, int scoreposy, int courtWidth, int courtHeight, int score) {
    	super(0, 0, scoreposx, scoreposy,  scorewidth,  scoreheight, courtWidth, courtHeight, 0);
    	this.score = "" + score;
    	this.scorewidth = scorewidth;
    	
        try {
        	img = new BufferedImage[10];
        	for (int i = 0; i < 10; i++) {
        		if(img[i] == null) {
        			img[i] = ImageIO.read(new File(IMG_FILE + i +".png"));
        		}
        	}
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    
    @Override
    public void draw(Graphics g) {
    	for (int i = 0; i< score.length(); i++) {
    		g.drawImage(img[Character.getNumericValue(score.charAt(i))], 
    				this.getPx() + i * scorewidth, this.getPy(), this.getWidth(), this.getHeight(), null);
    	}
    }
	
	
	
	public static int Defeated(EnemyKind k) {
		int score = 0;
		switch(k) {
			case FAST:{
				score = 100;
				break;
			}
			
			case WEAK:{
				score = 200;
				break;
			}
			
			case STRONG:{
				score = 500;
				break;
			}
			
			case BOSS:{
				score = 10000;
				break;
			}
			
			default:{
				score = 100;
			}
		}
		
		return score;
	}
	
	//If collected an reward
	public static int Collected(RewardKind k) {
		int score = 0;
		switch(k) {
			case STAR:{
				score = 100;
				break;
			}
			
			case PLUS_BULLET:{
				score = 200;
				break;
			}
			
			case LIGHT:{
				score = 500;
				break;
			}
			
			case MISSLE:{
				score = 500;
				break;
			}
			
			default:{
				score = 100;
			}
		}
		
		return score;
	}
	
	
	 @Override
	    public LinkedList<Bullet> shoot(){
	    	return null;
	    }  
	
}
