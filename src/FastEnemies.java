
import java.util.LinkedList;


//FastEnemies cannot shoot, but they move very fast
public class FastEnemies extends Enemies {
	public static final String IMG_FILE = "files/Enemies/fast_enemies.png";
	public static final int W = 50;
	public static final int H = 30;
	public static final int velocity_x = 4;
	public static final int velocity_y = 10;

	private static int posy = 0;

	public static final int numOfBullets = 0;
	public static final int life = 1;

	public FastEnemies(int posx, int courtWidth, int courtHeight) {
		super(velocity_x, velocity_y, posx, posy, W, H, courtWidth, courtHeight, life, IMG_FILE, EnemyKind.FAST);

	}

	// Fast enemies cannot shoot

	@Override
	public LinkedList<Bullet> shoot() {
		LinkedList<Bullet> bulletlist = new LinkedList<Bullet>();
		return bulletlist;
	}
}
