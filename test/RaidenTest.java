import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.NoSuchElementException;
import java.util.*;
import org.junit.*;


public class RaidenTest {
	 private static final int COURT_WIDTH = 400;
	 private static final int COURT_HEIGHT = 600;
	 
	 @Test
	 public void testSetters() {
		 Plane p = new Plane(COURT_WIDTH, COURT_HEIGHT);
		 p.setPx(200);
		 assertEquals(p.getPx(), 200);
		 p.setPy(100);
		 assertEquals(p.getPy(), 100);
	 }
	 
	 @Test
	 public void testSettersOutOfBound() {
		 Plane p = new Plane(COURT_WIDTH, COURT_HEIGHT);
		 p.setPx(500);
		 assertEquals(p.getPx(), COURT_WIDTH - p.getWidth());
		 p.setPy(800);
		 assertEquals(p.getPy(), COURT_HEIGHT-p.getHeight());
		 p.setPx(-100);
		 assertEquals(p.getPx(), 0);
		 p.setPy(-100);
		 assertEquals(p.getPy(), 0);
	 }
	 
	 
	 @Test
	 public void testPlaneMoved() {
		 Plane p = new Plane(COURT_WIDTH, COURT_HEIGHT);
		 p.setVx(4);
		 p.setVy(4);
		 int posx = p.getPx();
		 int posy = p.getPy();
		 assertEquals(posx, 200);
		 assertEquals(posy, 500);
		 p.move();
		 assertEquals(p.getPx(), 204);
		 assertEquals(p.getPy(), 504);
	 }
	 
	 @Test
	 public void testPlaneHitWall() {
		 Plane p = new Plane(COURT_WIDTH, COURT_HEIGHT);
		 assertEquals(p.hitWall(), null);
		 p.setPx(400);
		 p.setVx(4);
		 assertEquals(p.hitWall(), Direction.RIGHT);
		 p.setPx(200);
		 p.setPy(0);
		 p.setVy(-1);
		 assertEquals(p.hitWall(), Direction.UP);
		 
	 }
	 
	 @Test
	 public void testPlaneInjured() {
       Plane p = new Plane(COURT_WIDTH, COURT_HEIGHT);
       assertEquals(p.getLife(), 5);
       p.Injured();
       assertEquals(p.getLife(), 4);
	 }
	 
	 @Test
	 public void testPlaneBlast() {
       Plane p = new Plane(COURT_WIDTH, COURT_HEIGHT);
       assertEquals(p.getBlast(), 5);
       p.blast();
       assertEquals(p.getBlast(), 4);
	 }
	 
	 @Test
	 public void testPlaneUpGrade() {
       Plane p = new Plane(COURT_WIDTH, COURT_HEIGHT);
       assertEquals(p.getBulletKind(), BulletKind.SIMPLE);
       p.upGrade(RewardKind.PLUS_BULLET);
       assertEquals(p.getBullets(), 3);
       p.upGrade(RewardKind.LIGHT);
       assertEquals(p.getBulletKind(), BulletKind.LIGHT);
       assertFalse(p.getMissle());
       p.upGrade(RewardKind.MISSLE);
       assertTrue(p.getMissle());
	 }
	 
	@Test
	public void testPlaneCollision() {
		Plane p = new Plane(COURT_WIDTH,COURT_HEIGHT);
		Plane q = new Plane(COURT_WIDTH,COURT_HEIGHT);
		assertTrue(p.Collide(q));
		p.setVx(4);
		for(int i = 0; i < 10; i++) {
			p.move();
			assertTrue(p.Collide(q));
		}
		p.move();
		assertFalse(p.Collide(q));
		
	}
	 
    
    @Test
    public void testBossShoot() {
    	Boss b = new Boss(COURT_WIDTH,COURT_HEIGHT, "files/Enemies/boss.png");
    	LinkedList<Bullet> bl = b.BossShoot1();
    	assertEquals(bl.size(), 5);
    	Bullet bullet = bl.getFirst();
    	assertEquals(bullet.getBulletPath(), BulletKind.E_BULLET_3.changeBullet());
    	bl = b.BossShoot2();
    	assertEquals(bl.size(), 5);
    	bullet = bl.getFirst();
    	assertEquals(bullet.getBulletPath(), BulletKind.E_BULLET_LIGHT.changeBullet());
    	bl = b.BossShoot3();
    	assertEquals(bl.size(), 1);
    	bullet = bl.getFirst();
    	assertEquals(bullet.getBulletPath(), BulletKind.E_BULLET_LASER.changeBullet());
    	
    }
    
    @Test
	public void testFastEnemiesMove() {
		FastEnemies f = new FastEnemies(100, COURT_WIDTH, COURT_HEIGHT);
		f.move();
		assertEquals(f.getPx(), 104);
		assertEquals(f.getPy(), 10);
	} 
    
    @Test
	public void testFastEnemiesShoot() {
		FastEnemies f = new FastEnemies(100, COURT_WIDTH, COURT_HEIGHT);
		assertTrue(f.shoot().isEmpty());
	} 
    
    @Test
   	public void testStrongEnemiesShoot() {
   		StrongEnemies f = new StrongEnemies(100, COURT_WIDTH, COURT_HEIGHT);
   		assertEquals(f.shoot().size(), 3);
   	} 
    
    @Test
   	public void testWeakEnemiesShoot() {
   		WeakEnemies f = new WeakEnemies(100, COURT_WIDTH, COURT_HEIGHT);
   		assertEquals(f.shoot().size(), 1);
   	} 
    
    @Test
   	public void testOthersShoot() {
    	BulletKind bullet = BulletKind.SIMPLE;
   		Bullet b = new Bullet(bullet.getWidth(),bullet.getHeight(),
    			(-1)*bullet.getSpeed_x(), bullet.getSpeed_y(), 0, 0,
    			COURT_WIDTH, COURT_HEIGHT,bullet.changeBullet());
   		assertEquals(b.shoot(), null);
   		Map map = new Map(COURT_WIDTH, COURT_HEIGHT, "files/map1.png");
   		assertEquals(map.shoot(),null);
   		
   	} 
    
    @Test
   	public void testGameCourt() {
   		GameCourt gc = new GameCourt("Nicole");
   		assertEquals(gc.getName(), "Nicole");
   		gc.setName("William");
   		assertEquals(gc.getName(), "William");
   		gc.reset(false);
   		assertEquals(gc.getScore(), 0);
   		assertFalse(gc.getBossMode());
   	} 
    
    @Test
   	public void testScoreDefeated() {
    	int score = 0;
   		score = score + Score.Defeated(EnemyKind.BOSS);
   		score = score + Score.Defeated(EnemyKind.WEAK);
   		score = score + Score.Defeated(EnemyKind.STRONG);
   		assertEquals(score, 10700);
   	} 
    
    @Test
   	public void testScoreCollected() {
    	int score = 0;
   		score = score + Score.Collected(RewardKind.STAR);
   		score = score + Score.Collected(RewardKind.MISSLE);
   		score = score + Score.Collected(RewardKind.LIGHT);
   		assertEquals(score, 1100);
   	} 
}
