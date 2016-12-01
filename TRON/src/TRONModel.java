import java.util.ArrayList;

public class TRONModel {

	private Bike bike1;
	private Bike bike2;

	private ArrayList<Point> bike1line;
	private ArrayList<Point> bike2line;

	public TRONModel() {
		bike1 = new Bike(.25, .5, 1); // facing east
		bike2 = new Bike(.75, .5, 3); // facing west

		bike1line = new ArrayList<>();
		Point p = new Point(.25, .5);
		bike1line.add(p);

		bike2line = new ArrayList<>();
		Point q = new Point(.75, .5);
		bike2line.add(q);
	}

	public Bike getBike1() {
		return bike1;
	}

	public Bike getBike2() {
		return bike2;
	}

	public ArrayList<Point> getBike1Line() {
		return bike1line;
	}

	public ArrayList<Point> getBike2Line() {
		return bike2line;
	}

	public void advance() {
		bike1.moveBike(bike1.getDirection());
		bike2.moveBike(bike2.getDirection());

		Hitbox hitbox1 = bike1.getHitbox();
		Hitbox hitbox2 = bike2.getHitbox();

		ArrayList<Point> bike1stream = getBike1Line();
		ArrayList<Point> bike2stream = getBike2Line();

		// bikes colliding with eachother
		if (overlapsBike(bike1, bike2)) {
			bike1.dead();
			bike2.dead();
		}

		// bikes colliding with walls
		overlapsWall(bike1);
		overlapsWall(bike2);

		// bikes colliding with streams
		overlapsStream(bike1, hitbox1, hitbox2, bike1stream, bike2stream);
		overlapsStream(bike2, hitbox2, hitbox1, bike2stream, bike1stream);

	}

	public int winner() {
		if (bike1.isDead() == true) {
			if (bike2.isDead() == true) {
				return 3;
			}
			return 2;
		}

		if (bike2.isDead() == true) {
			if (bike1.isDead() == true) {
				return 3;
			}
			return 1;
		}

		return 0;
	}

	public boolean approxEqual(double a, double b) {
		if (Math.abs(a - b) < .0001) {
			return true;
		}
		return false;
	}

	public boolean overlapsBike(Bike bike1, Bike bike2) {
		Hitbox hitbox1 = bike1.getHitbox();
		Hitbox hitbox2 = bike2.getHitbox();

		if (approxEqual(hitbox1.getX(), hitbox2.getX())) {
			if (Math.abs(hitbox1.getY() - hitbox2.getY()) < .04) {
				return true;
			}
		}

		if (approxEqual(hitbox1.getY(), hitbox2.getY())) {
			if (Math.abs(hitbox1.getX() - hitbox2.getX()) < .04) {
				return true;
			}
		}

		return false;
	}

	public void overlapsWall(Bike bike) {

		Hitbox hitbox = bike.getHitbox();

		if (hitbox.getX() - .01 < 0 || hitbox.getX() + .01 > 1) {
			bike.dead();
		}

		if (hitbox.getY() - .01 < 0 || hitbox.getY() + .01 > 1) {
			bike.dead();
		}

	}

	public void overlapsStream(Bike bike, Hitbox hitbox, Hitbox otherhitbox, ArrayList<Point> stream,
			ArrayList<Point> otherstream) {

		// bike overlapping own stream
		for (int i = 1; i < stream.size(); i++) {
			Point p0 = stream.get(i - 1);
			Point p1 = stream.get(i);

			// vertical streams
			if (approxEqual(hitbox.getX(), p1.getX())) {
				if (approxEqual(p1.getX(), p0.getX())) {

					if (hitbox.getY() < p1.getY()) {
						if (hitbox.getY() > p0.getY()) {
							bike.dead();
						}
					}

					if (hitbox.getY() > p1.getY()) {
						if (hitbox.getY() < p0.getY()) {
							bike.dead();
						}
					}
				}
			}

			// horizontal streams
			if (approxEqual(hitbox.getY(), p1.getY())) {
				if (approxEqual(p1.getY(), p0.getY())) {

					if (hitbox.getX() < p1.getX()) {
						if (hitbox.getX() > p0.getX()) {
							bike.dead();
						}
					}

					if (hitbox.getX() > p1.getX()) {
						if (hitbox.getX() < p0.getX()) {
							bike.dead();
						}
					}
				}
			}
		}

		// bike overlapping opponent's stream
		for (int i = 1; i < otherstream.size(); i++) {
			Point p0 = otherstream.get(i - 1);
			Point p1 = otherstream.get(i);

			// vertical streams
			if (approxEqual(hitbox.getX(), p1.getX())) {

				// incomplete streams
				if (approxEqual(hitbox.getX(), otherhitbox.getX())) {
					if (hitbox.getY() < p1.getY()) {
						if (hitbox.getY() > otherhitbox.getY()) {
							bike.dead();
						}
					}

					if (hitbox.getY() > p1.getY()) {
						if (hitbox.getY() < otherhitbox.getY()) {
							bike.dead();
						}
					}
				}

				// complete streams
				if (approxEqual(p1.getX(), p0.getX())) {

					if (hitbox.getY() < p1.getY()) {
						if (hitbox.getY() > p0.getY()) {
							bike.dead();
						}
					}

					if (hitbox.getY() > p1.getY()) {
						if (hitbox.getY() < p0.getY()) {
							bike.dead();
						}
					}
				}
			}

			// horizontal streams
			if (approxEqual(hitbox.getY(), p1.getY())) {

				// incomplete streams
				if (approxEqual(hitbox.getY(), otherhitbox.getY())) {
					if (hitbox.getX() < p1.getX()) {
						if (hitbox.getX() > otherhitbox.getX()) {
							bike.dead();
						}
					}

					if (hitbox.getX() > p1.getX()) {
						if (hitbox.getX() < otherhitbox.getX()) {
							bike.dead();
						}
					}
				}

				// complete streams
				if (approxEqual(p1.getY(), p0.getY())) {

					if (hitbox.getX() < p1.getX()) {
						if (hitbox.getX() > p0.getX()) {
							bike.dead();
						}
					}

					if (hitbox.getX() > p1.getX()) {
						if (hitbox.getX() < p0.getX()) {
							bike.dead();
						}
					}
				}
			}
		}
	}

}
