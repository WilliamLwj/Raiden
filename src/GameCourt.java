
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    // the state of the game logic
    private Plane plane;  //the user's plane, moves
    private Map map;
    private LinkedList<Bullet> bulletlist; // the list of bullets
    private LinkedList<Bullet> enemybulletlist;   // the list of enemy bullets
    private LinkedList<Enemies> enemies;
    private LinkedList<Reward> rewards;
    private Ranking r;
    
    private String username = "William";
    private int score = 0;
    
    public boolean playing = false; // whether the game is running 
   

    // Game constants
    public static final int COURT_WIDTH = 400;
    public static final int COURT_HEIGHT = 600;
    public static final int PLANE_VELOCITY = 4;
    
    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

    private boolean bossmode = false;
    public GameCourt(String username) {
        // creates border around the court area, JComponent method
    	this.username = username;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        javax.swing.Timer timer = new javax.swing.Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start();
        
        javax.swing.Timer timerFastEnemy = new javax.swing.Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (!bossmode) {
            	 int posx = (int) Math.round(Math.random() * COURT_WIDTH);
            	 FastEnemies enemie1 = new FastEnemies(posx, COURT_WIDTH,COURT_HEIGHT);
                 enemies.add(enemie1);
            	}
            }
        });
        timerFastEnemy.start(); 
        
        javax.swing.Timer timerWeakEnemy = new javax.swing.Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (!bossmode) {
            	 int posx = (int) Math.round(Math.random() * COURT_WIDTH);
            	 WeakEnemies enemie1 = new WeakEnemies(posx, COURT_WIDTH,COURT_HEIGHT);
                 enemies.add(enemie1);
            	}
            }
        });
        timerWeakEnemy.start(); 
        
        
        javax.swing.Timer timerStrongEnemy = new javax.swing.Timer(10000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (!bossmode) {
            		int posx = (int) Math.round(Math.random() * COURT_WIDTH);
            		StrongEnemies enemie1 = new StrongEnemies(posx, COURT_WIDTH,COURT_HEIGHT);
            		enemies.add(enemie1);
            	}
            }
        });
        timerStrongEnemy.start(); 
        
        javax.swing.Timer timerBoss = new javax.swing.Timer(100000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 bossmode = true;
            	 double prob = Math.random();
            	 String boss = null;
            	 if (prob < 0.3) {
            		 boss = "files/Enemies/boss2.png";
            	 }else if (prob < 0.7) {
            		 boss = "files/Enemies/boss3.png";
            	 }else {
            		 boss = "files/Enemies/boss.png";
            	 }
            	 Boss enemie1 = new Boss(COURT_WIDTH,COURT_HEIGHT, boss);
                 enemies.add(enemie1);
                 timerFastEnemy.restart();
                 timerWeakEnemy.restart();
                 timerStrongEnemy.restart();
            }
        });
        timerBoss.start(); 
        
        javax.swing.Timer timerEnemyShoot = new javax.swing.Timer(10000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	for (Enemies ene: enemies) {
            		LinkedList<Bullet> blist = ene.shoot();
                    for (Bullet b: blist) {
                    	enemybulletlist.add(b);
                    }
            	}
            }
        });
        timerEnemyShoot.start(); 
        
        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
            	if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                	LinkedList<Bullet> blist = plane.shoot();
                    for (Bullet b: blist) {
                    	bulletlist.add(b);
                    }
                }
            	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    plane.setVx(-PLANE_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    plane.setVx(PLANE_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    plane.setVy(PLANE_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    plane.setVy(-PLANE_VELOCITY);
                } 
                else if (e.getKeyCode() == KeyEvent.VK_B) {
                	if (plane.getBlast() > 0) {
                		Bullet b = plane.blast();
                		bulletlist.add(b);
                		Iterator<Enemies> Eitr = enemies.iterator();
                        while(Eitr.hasNext()) {
                        	Eitr.next();
                        	Eitr.remove();
                        	bossmode = false;
                        }
                        
                        	
                	}
                } 
            }

            public void keyReleased(KeyEvent e) {
                plane.setVx(0);
                plane.setVy(0);
            }
        }
        );

        
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset(boolean playing) {
 
        plane  = new Plane(COURT_WIDTH, COURT_HEIGHT);
        map  =  new Map(COURT_WIDTH, COURT_HEIGHT, "files/map1.png");
        bulletlist = new LinkedList<Bullet>();
        enemybulletlist = new LinkedList<Bullet>();
        enemies = new LinkedList<Enemies>();
        rewards = new LinkedList<Reward>();
        this.playing = playing;
        score = 0;
        bossmode = false;
        r = new Ranking(COURT_WIDTH, COURT_HEIGHT);
        

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }
    
    public int getScore() {
    	return score;
    }
    

    public String getName() {
    	return username;
    }
    
    public boolean getBossMode(){
    	return bossmode;
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
        	//The plane should move
            
            plane.move();
            map.move();
            

            
            //Bullets
            Iterator<Bullet> itr = bulletlist.iterator();
            while(itr.hasNext()) {
            	Bullet b = itr.next();
            	b.move();
            	if((b.hitWall()) != null) {
            		itr.remove();
            	}
            	else {
            		for(Enemies enemy: enemies) {
            			
            			if (b.Collide(enemy)){
            				itr.remove();
            				enemy.Injured();
            				break;
            			}
            		}
            	}
            }
            
            Iterator<Bullet> bitr = enemybulletlist.iterator();
            while(bitr.hasNext()) {
            	Bullet b = bitr.next();
            	b.move();
            	if((b.hitWall()) != null) {
            		bitr.remove();
            	}
            	else {
            		if (b.Collide(plane)){
            			plane.Injured();
                    	plane.destroyed();
                    	bitr.remove();
            			}
            		}
            	}
            
            
            // Enemies, make them bounce off walls
            Iterator<Enemies> Eitr = enemies.iterator();
            while(Eitr.hasNext()) {
            	Enemies ene = Eitr.next();
            	if (ene.getLife() <= 0){
        			Eitr.remove();
        			if(ene.getKind() == EnemyKind.BOSS) {
        				bossmode = false;
        			}
        			score = score + Score.Defeated(ene.getKind());
        			double prob = Math.random();
        			newReward(ene, prob);
        		}
            	else if (ene.Collide(plane) && ene.getKind() != EnemyKind.BOSS) {
            		plane.Injured();
            		plane.destroyed();
            		Eitr.remove();
            	}
            	
            	else {
            		ene.move();
            		if(ene.hitWall() != Direction.DOWN) {
            			ene.bounce(ene.hitWall());
            			}
            		else {
            			Eitr.remove();
            		}
            	}
            }
            
          // Rewards, make them bounce off walls
            Iterator<Reward> Ritr = rewards.iterator();
            while(Ritr.hasNext()) {
            	Reward r = Ritr.next();
            	r.move();
            	boolean removed = false;
            	if(r.hitWall() != Direction.DOWN) {
            		r.bounce(r.hitWall());
            	}
            	else {
            		Ritr.remove();
            		removed = true;
            	}
            	// To avoid IllegalStateException, we use this removed boolean value
            	// to indicate whether a reward has already being removed 
            	if(r.Collide(plane) && !removed) { 
            		plane.upGrade(r.getKind());
            		score = score + Score.Collected(r.getKind());
            		Ritr.remove(); 
            	}
            }
            
            
          
            // How do people win/lose
            if (plane.getLife() <= 0) {
                playing = false;
                
            } 
            
            // update the display
            repaint();
        }
     
    }
    
    public void newReward(Enemies e, double prob) {
    	
    	RewardKind reward = null;
    	if (prob > 0.7 && prob < 0.8) {
			reward = RewardKind.STAR;
		}else if (prob > 0.8 && prob < 0.85) {
			reward = RewardKind.PLUS_BULLET;
            
		}else if (prob > 0.85 && prob < 0.9) {
			reward = RewardKind.LIGHT;
		}else if (prob > 0.9) {
			reward = RewardKind.MISSLE;
		}
    	if(reward != null) {
    		Reward r = new Reward(e.getPx(), e.getPy(),
    				reward.getWidth(),reward.getHeight(),
    				COURT_WIDTH,COURT_HEIGHT, reward.Rewarded(), reward);
    		rewards.add(r);
    	}
        
    }
    public void setName(String name) {
    	this.username = name;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        	map.draw(g);
        	plane.draw(g);
 
        	for(Bullet b: bulletlist) {
        		b.draw(g);
        	}
        	for(Bullet eb: enemybulletlist) {
        		eb.draw(g);
        	}
        	for(Enemies e: enemies) {
        		e.draw(g);
        	}
        	for(Reward r: rewards) {
        		r.draw(g);
        	}
        
        	Score s = new Score(30, 30, 200, 0, COURT_WIDTH, COURT_HEIGHT, score);
        	s.draw(g);
        
        	PlayerStatus ps = new PlayerStatus(COURT_WIDTH, COURT_HEIGHT,
        		plane.getLife(),plane.getBlast());
        	ps.draw(g);
        
        if (!playing) {
        	if (score > Ranking.scores[2]) {
        		r.updateRecord(username, score);
        	}
        	
            GameOver over = new GameOver(COURT_WIDTH, COURT_HEIGHT, score);
            over.draw(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}