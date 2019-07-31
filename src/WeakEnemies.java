
import java.util.LinkedList;

// Weak Enemies are weak and they move slowly
// Weak Enemies can only shoot a few times
public class WeakEnemies extends Enemies{
	public static final String IMG_FILE = "files/Enemies/weak_enemies.png";
	public static final BulletKind bullet = BulletKind.E_BULLET_1;
    public static final int W = 60;
    public static final int H = 50;
    public static final int velocity_x = -3;
    public static final int velocity_y = -1;
    
    private static int posy = 0;
    
    public static final int numOfBullets = 3;
    public static final int life = 5;
    public static final int bullet_speed_y = 5;
	public static final int bullet_speed_x = 2;
	private int courtWidth;
	private int courtHeight;
    
    public WeakEnemies(int posx, int courtWidth, int courtHeight) {
    	super(velocity_x, velocity_y, posx, posy, W, H, 
    		courtWidth, courtHeight, life, IMG_FILE, EnemyKind.WEAK);
    	this.courtWidth = courtWidth;
    	this.courtHeight = courtHeight;

    }
    
    
    
    
    @Override
    public LinkedList<Bullet> shoot(){
    	LinkedList<Bullet> bulletlist = new LinkedList<Bullet>();
    	if (numOfBullets > 0) {
    		int bullet_position = this.getPx();
    		Bullet b = new Bullet(bullet.getWidth(),bullet.getHeight(),
        			bullet_speed_x, bullet_speed_y, bullet_position, this.getPy() + 5, 
        		courtWidth, courtHeight, bullet.changeBullet());
    		bulletlist.add(b);
    	}
        return bulletlist;
    } 
  
}
