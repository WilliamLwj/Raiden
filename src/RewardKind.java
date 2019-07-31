
public enum RewardKind {
	STAR,
	PLUS_BULLET,
	LIGHT,
	MISSLE;
	
	String Rewarded() {
		switch(this) {
			case STAR:
				return ("files/Rewards/star.png");
			case PLUS_BULLET:
				return ("files/Rewards/plus_bullet_reward.png");
			case LIGHT:
				return("files/Rewards/light_reward.png");
			case MISSLE:
				return("files/Rewards/missle_reward.png");
			default:
				return("files/Rewards/star.png");
		}
	}
	public int getWidth() {
		switch(this) {
			case STAR:
				return 40;
			case PLUS_BULLET:
				return 60;
			case LIGHT:
				return 60;
			case MISSLE:
				return 70;
			default:
				return 40;
		}	
	}
	
	public int getHeight() {
		switch(this) {
			case STAR:
				return 40;
			case PLUS_BULLET:
				return 40;
			case LIGHT:
				return 40;
			case MISSLE:
				return 70;
			default:
				return 40;
		}	
	}
	
}
