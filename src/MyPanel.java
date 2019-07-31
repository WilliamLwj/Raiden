
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class MyPanel extends JPanel{
	
	private int COURT_WIDTH;
    private int COURT_HEIGHT;
    private BufferedImage background;
    
    public MyPanel(int courtWidth, int courtHeight, String IMG_FILE) {
    	
    	this.COURT_WIDTH = courtWidth;
    	this.COURT_HEIGHT = courtHeight;
    	try {
              if (background == null) {
                  background = ImageIO.read(new File(IMG_FILE));
              }
          } catch (IOException e) {
              System.out.println("Internal Error:" + e.getMessage());
          }  
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, COURT_WIDTH,COURT_HEIGHT,
        		     0, 0, background.getWidth(), background.getHeight(), null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}
