import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MyButton extends JButton {

	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	public MyButton(String File) {
		super();
		setPreferredSize(new Dimension(70,25));
		try {
            if (img == null) {
                img = ImageIO.read(new File(File));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, 70, 25, null);
	}
}
