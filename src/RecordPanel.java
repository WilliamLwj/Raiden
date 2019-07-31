
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class RecordPanel extends JPanel{
	
	public static final int COURT_WIDTH = 400;
    public static final int COURT_HEIGHT = 600;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Ranking rank = new Ranking(COURT_WIDTH,COURT_HEIGHT);
        rank.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}
