/* The abstract class AbleToFly, which extends the class GameObj
 * 
 */

import java.util.*;
import java.awt.Graphics;

public abstract class AbleToFly{
	private int life;
	private int px; 
	private int py;
	private int width;
	private int height;
	private int vx;
	private int vy;
	private int maxX;
	private int maxY;
	
	public AbleToFly(int vx, int vy, int px, int py, int width, int height, int courtWidth,
	        int courtHeight, int life) {
		this.life = life;
		this.vx = vx;
	    this.vy = vy;
	    this.px = px;
	    this.py = py;
	    this.width  = width;
	    this.height = height;
	    this.maxX = courtWidth - width;
	    this.maxY = courtHeight - height;
	}
	
	public boolean Collide(AbleToFly that) {
		return (this.px + this.width >= that.px
	            && this.py + this.height >= that.py
	            && that.px + that.width >= this.px 
	            && that.py + that.height >= this.py);
	}
	
	
	// Getting the life, positions, velocities, sizes
	public int getLife() {
		return life;
	}
	public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }
    
    public int getVx() {
    	
        return this.vx;
    }
    
    public int getVy() {
        return this.vy;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    
    // Setting the positions and velocities
    public void setPx(int px) {
        this.px = px;
        clip();
    }

    public void setPy(int py) {
        this.py = py;
        clip();
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }
    
    
    // Cannot go outside of the court
    private void clip() {
        this.px = Math.min(Math.max(this.px, 0), this.maxX);
        this.py = Math.min(Math.max(this.py, 0), this.maxY);
    }
    
    
    public void move() {
        this.px += this.vx;
        this.py += this.vy;

        clip();
    }
    
    public void bounce(Direction d) {
        if (d == null) return;
        
        switch (d) {
        case UP:
            this.vy = Math.abs(this.vy);
            break;  
        case DOWN:
            this.vy = -Math.abs(this.vy);
            break;
        case LEFT:
            this.vx = Math.abs(this.vx);
            break;
        case RIGHT:
            this.vx = -Math.abs(this.vx);
            break;
        }
    }
    
    public Direction hitWall() {
        if (this.px + this.vx < 0) {
            return Direction.LEFT;
        } else if (this.px + this.vx > this.maxX) {
           return Direction.RIGHT;
        }

        if (this.py + this.vy < 0) {
            return Direction.UP;
        } else if (this.py + this.vy > this.maxY) {
            return Direction.DOWN;
        } else {
            return null;
        }
    }
    
	public void Injured() {
		this.life = this.life - 1;
	}

	public abstract LinkedList<Bullet> shoot();
	
	public abstract void draw(Graphics g);
}
