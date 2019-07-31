
import java.util.LinkedList;

// Strong Enemies are strong and they move very slowly
// They can shoot multiple bullets multiple times

public class StrongEnemies extends Enemies{
	public static final String IMG_FILE = "files/Enemies/strong_enemies.png";
	public static final BulletKind bullet = BulletKind.E_BULLET_2;
    public static final int W = 90;
    public static final int H = 70;
    public static final int velocity_x = -1;
    public static final int velocity_y = 0;

	private int courtWidth;
	private int courtHeight;
    
    private static int posy = 0;
    
    public static final int numOfBullets = 3;
    public static final int life = 10;
    
 
    public StrongEnemies(int posx, int courtWidth, int courtHeight) {
    	super(velocity_x, velocity_y, posx, posy, W, H, 
    		courtWidth, courtHeight, life, IMG_FILE, EnemyKind.STRONG);
    	this.courtWidth = courtWidth;
    	this.courtHeight = courtHeight;
    }
    
    
    @Override
    public LinkedList<Bullet> shoot(){
    	LinkedList<Bullet> bulletlist = new LinkedList<Bullet>();
    	int bullet_position = this.getPx() - numOfBullets;
        for (int i = 1; i <= numOfBullets; i++) {
        	int d = this.getWidth()/numOfBullets;
        	bullet_position = bullet_position + d;
        	Bullet b = new Bullet(bullet.getWidth(),bullet.getHeight(),
        			bullet.getSpeed_x(), bullet.getSpeed_y(), bullet_position, this.getPy() + 5, 
        		courtWidth, courtHeight, bullet.changeBullet());
        	bulletlist.add(b);
        }
        return bulletlist;
    } 
    
   
}
