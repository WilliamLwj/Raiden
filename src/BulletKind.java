
public enum BulletKind {
	SIMPLE,
	LIGHT,
	MISSLE,
	BLAST,
	E_BULLET_1,
	E_BULLET_2,
	E_BULLET_3,
	E_BULLET_LIGHT,
	E_BULLET_LASER;
	
	String changeBullet() {
		switch(this) {
			case SIMPLE:
				return ("files/Bullets/simple_bullet.png");
			case LIGHT:
				return("files/Bullets/light_bullet.png");
			case MISSLE:
				return("files/Bullets/missle.png");
			case BLAST:
				return("files/Bullets/blast.png");
			case E_BULLET_1:
				return("files/Bullets/enemy_bullet_1.png");
			case E_BULLET_2:
				return("files/Bullets/enemy_bullet_2.png");
			case E_BULLET_3:
				return("files/Bullets/enemy_bullet_3.png");	
			case E_BULLET_LIGHT:
				return("files/Bullets/enemy_bullet_light.png");	
			case E_BULLET_LASER:
				return("files/Bullets/enemy_bullet_laser.png");	
			default:
				return("files/Bullets/simple_bullet.png");
		}
	}
	
	public int getWidth() {
		switch(this) {
			case SIMPLE:
				return 20;
			case LIGHT:
				return 20;
			case MISSLE:
				return 20;
			case BLAST:
				return 400;
			case E_BULLET_1:	
				return 15;
			case E_BULLET_2:	
				return 15;
			case E_BULLET_3:	
				return 20;
			case E_BULLET_LIGHT:	
				return 20;
			case E_BULLET_LASER:
				return 100;	
			default:
				return 20;
		}	
	}
	
	public int getHeight() {
		switch(this) {
			case SIMPLE:
				return 20;
			case LIGHT:
				return 40;
			case MISSLE:
				return 40;
			case BLAST:
				return 300;
			case E_BULLET_1:	
				return 15;
			case E_BULLET_2:	
				return 15;
			case E_BULLET_3:	
				return 20;
			case E_BULLET_LIGHT:	
				return 40;
			case E_BULLET_LASER:
				return 360;	
			default:
				return 40;
		}	
	}
	
	public int getSpeed_x() {
		switch(this) {
			case SIMPLE:
				return 0;
			case LIGHT:
				return 2;
			case MISSLE:
				return 0;
			case BLAST:
				return 0;
			case E_BULLET_1:	
				return 1;
			case E_BULLET_2:	
				return 0;
			case E_BULLET_3:	
				return 0;
			case E_BULLET_LIGHT:	
				return 1;
			case E_BULLET_LASER:
				return 0;	
			default:
				return 2;
		}	
	}
	
	public int getSpeed_y() {
		switch(this) {
			case SIMPLE:
				return -10;
			case LIGHT:
				return -13;
			case MISSLE:
				return -15;
			case BLAST:
				return -10;
			case E_BULLET_1:	
				return 5;
			case E_BULLET_2:	
				return 7;
			case E_BULLET_3:	
				return 10;
			case E_BULLET_LIGHT:	
				return 13;
			case E_BULLET_LASER:
				return 4;	
			default:		
				return 5;
		}	
	}
	
}
