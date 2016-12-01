
public class Bike {
	
	private double d;
	private boolean dead;
	private Hitbox h;
	
	public Bike(double x, double y, double d) {
		
		this.h = new Hitbox(x, y, .02);
		this.d = d;
		this.dead = false;
		
	}
	
	public void setDirection(double d) {
		this.d = d;
	}
	
	public double getDirection() {
		return d;
	}
	
	public void moveBike(double d) {
		double y = h.getY();
		double x = h.getX();
		if (d == 0) {
			y += .005;
			h.setY(y);
		}
		if (d == 1) {
			x += .005;
			h.setX(x);
		}
		if (d == 2) {
			y -= .005;
			h.setY(y);
		}
		if (d == 3) {
			x -= .005;
			h.setX(x);
		}
	}
	
	public Hitbox getHitbox() {
		return h;
	}
	
	public void dead() {
		this.dead = true;
	}
	
	public boolean isDead() {
		return this.dead;
	}
	
	
}
