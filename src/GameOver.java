import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class GameOver extends AbleToFly {
	// If defeated an enemy

	private static BufferedImage Gameover;
	private int courtWidth;
	private int courtHeight;
	private int score;
	private int score_posx;

	public GameOver(int courtWidth, int courtHeight, int score) {
		super(0, 0, 0, 0, courtWidth, courtHeight, courtWidth, courtHeight, 0);
		this.courtWidth = courtWidth;
		this.courtHeight = courtHeight;
		this.score = score;
		if (score < 1000) {
			score_posx = 180;
		}else if (score < 100000) {
			score_posx = 140;
		}else if (score < 100000) {
			score_posx = 120;
		}else {
			score_posx = 100;
		}
			
		try {
			if (Gameover == null) {
				Gameover = ImageIO.read(new File("files/gameover.png"));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Gameover, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
		Score s = new Score(30, 30, score_posx, 280, courtWidth, courtHeight, score);
		s.draw(g);
		
		}
	
	@Override
	    public LinkedList<Bullet> shoot(){
	    	return null;
	    }  
}
