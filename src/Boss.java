
import java.util.LinkedList;

// Boss is very strong, and it basically doesn't move
public class Boss extends Enemies{
	
    public static final int W = 360;
    public static final int H = 200;
    public static final int velocity_x = 1;
    public static final int velocity_y = 0;
    
    public static final BulletKind bullet1 = BulletKind.E_BULLET_3;
    public static final BulletKind bullet2 = BulletKind.E_BULLET_LIGHT;
    public static final BulletKind bullet3 = BulletKind.E_BULLET_LASER;
	private int courtWidth;
	private int courtHeight;
    
	// Because the boss is too big, there is no point in specifying its
	// position. We just put it to be (0,0)
    private static int posx = 0;
    private static int posy = 0;
    
    public static final int numOfBullets = 5;
    public static final int life = 300;
    
 
    public Boss(int courtWidth, int courtHeight, String Img_file) {
    	super(velocity_x, velocity_y, posx, posy, W, H, 
    		courtWidth, courtHeight, life, Img_file, EnemyKind.BOSS);
    	this.courtWidth = courtWidth;
    	this.courtHeight = courtHeight;
    }
    
    
    
    @Override
    public LinkedList<Bullet> shoot(){
    	double prob = Math.random();
    	if (prob < 0.3) {
    		return BossShoot1();
    	}
    	else if (prob < 0.7) {
    		return BossShoot3();
    	}
    	else {
    		return BossShoot2();
    	}
    } 
    
    
    
   public LinkedList<Bullet> BossShoot1(){
	   LinkedList<Bullet> bulletlist = new LinkedList<Bullet>();
   	   int bullet_position = this.getPx() - numOfBullets;
       for (int i = 1; i <= numOfBullets; i++) {
       	int d = this.getWidth()/numOfBullets;
       	bullet_position = bullet_position + d;
       	Bullet b = new Bullet(bullet1.getWidth(),bullet1.getHeight(),
       			bullet1.getSpeed_x(), bullet1.getSpeed_y(), bullet_position, this.getPy() + 100, 
       		courtWidth, courtHeight, bullet1.changeBullet());
       	bulletlist.add(b);
       }
       return bulletlist;
   }
   
   public LinkedList<Bullet> BossShoot2(){
	   LinkedList<Bullet> bulletlist = new LinkedList<Bullet>();
   	   int bullet_position = this.getPx() - numOfBullets;
   	   int d = this.getWidth()/numOfBullets;
   	   for (int i = 1; i <= numOfBullets /2; i++) {
    	
   		   bullet_position = bullet_position + d;
   		   Bullet l = new Bullet(bullet2.getWidth(),bullet2.getHeight(),
    			(-1)*bullet2.getSpeed_x(), bullet2.getSpeed_y(), bullet_position, this.getPy() + 5, 
    			courtWidth, courtHeight, bullet2.changeBullet());
   		   bulletlist.add(l);  									//Some of the bullets go to the left
   	   }
    
   		bullet_position = bullet_position + d;
   		Bullet m = new Bullet(bullet2.getWidth(),bullet2.getHeight(),    
			0, bullet2.getSpeed_y(), bullet_position, this.getPy() + 5, 
		courtWidth, courtHeight, bullet2.changeBullet());                    
   		bulletlist.add(m);											//Some of them stay straight
    	
    for (int i = numOfBullets /2 + 2; i <= numOfBullets; i++) {
    	bullet_position = bullet_position + d;
    	Bullet r = new Bullet(bullet2.getWidth(),bullet2.getHeight(),
    			bullet2.getSpeed_x(), bullet2.getSpeed_y(), bullet_position, this.getPy() + 5, 
    		courtWidth, courtHeight, bullet2.changeBullet());    //Some of them go to the right
    	bulletlist.add(r);
    }
       return bulletlist;
   }
   
   public LinkedList<Bullet> BossShoot3(){
	   LinkedList<Bullet> bulletlist = new LinkedList<Bullet>();
   	   int bullet_position = this.getPx() + this.getWidth()/2 - 50;
       	Bullet b = new Bullet(bullet3.getWidth(),bullet3.getHeight(),
       			bullet3.getSpeed_x(), bullet3.getSpeed_y(), bullet_position, this.getPy() + this.getHeight(), 
       		courtWidth, courtHeight, bullet3.changeBullet());
       	bulletlist.add(b);
       
       return bulletlist;
   }
   
}
