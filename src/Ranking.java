import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Ranking extends AbleToFly {
	// If defeated an enemy

	private static BufferedImage Rank;
	private int courtWidth;
	private int courtHeight;
	public static String[] users;
	public static int[] scores;

	public Ranking(int courtWidth, int courtHeight) {
		super(0, 0, 0, 0, courtWidth, courtHeight, courtWidth, courtHeight, 0);
		this.courtWidth = courtWidth;
		this.courtHeight = courtHeight;
		try {
			if (Rank == null) {
				Rank = ImageIO.read(new File("files/ranking.png"));
			}
			if (scores == null) {
				scores = new int[3];
				users = new String[3];
				Scanner sc = new Scanner(new File("files/record.txt"));
				for (int i = 0; i < 3; i++) {
					if (sc.hasNextLine()) {
						String[] record = sc.nextLine().split(" ", 2);
						users[i] = record[0];
						scores[i] = Integer.parseInt(record[1]);
					}
				}
				sc.close();
			}

		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}

	}

	// Only call this method when the score is at least larger
	// than the 3rd highest
	public void updateRecord(String username, int score) {
		if (score < scores[1]) {
			scores[2] = score;
			users[2] = username;
		} else if (score < scores[0]) {
			scores[2] = scores[1];
			users[2] = users[1];
			scores[1] = score;
			users[1] = username;
		} else {
			scores[2] = scores[1];
			users[2] = users[1];
			scores[1] = scores[0];
			users[1] = users[0];
			scores[0] = score;
			users[0] = username;
		}

		try {
			Writer writer = new FileWriter("files/record.txt");
			for (int i = 0; i < 3; i++) {
				writer.write(users[i] + " " + scores[i] + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Rank, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
		for (int i = 0; i < 3; i++) {
			g.drawString(users[i], 280, 110 * (i) + 150);
			Score s = new Score(20, 20, 280, 110 * (i) + 160, courtWidth, courtHeight, scores[i]);
			s.draw(g);
		}
	}
	 @Override
	    public LinkedList<Bullet> shoot(){
	    	return null;
	    }  
}
