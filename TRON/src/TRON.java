import java.util.ArrayList;

public class TRON {

	public static void main(String[] args) {
		new TRON().run();
	}

	private TRONModel model;

	public TRON() {
		model = new TRONModel();
	}

	public void draw() {
		StdDraw.setPenColor(18, 20, 78);
		StdDraw.filledRectangle(0, 0, 1, 1);
		StdDraw.setPenColor(StdDraw.WHITE);
		double linemark = 1.0 / 15.0;
		for (int i = 0; i < 14; i++) {
			StdDraw.line(linemark, 0, linemark, 1);
			StdDraw.line(0, linemark, 1, linemark);
			linemark += 1.0 / 15.0;
		}
		drawBike(model.getBike1());
		drawBike(model.getBike2());
		drawStream(model.getBike1Line());
		drawStream(model.getBike2Line());

		StdDraw.show(10);
	}

	public void run() {
		while (true) {
			model = new TRONModel();
			int winner = model.winner();
			titleScreen();
			while (winner == 0) {
				draw();
				handleKeyPresses();
				model.advance();
				winner = model.winner();
			}
			draw();
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledRectangle(.5, .9, .2, .04);
			StdDraw.filledRectangle(.5, .1, .25, .04);
			StdDraw.setPenColor(StdDraw.WHITE);
			String w = "PLAYER 1";
			if (winner == 2) {
				w = "PLAYER 2";
			}
			if (winner == 3) {
				w = "NEITHER PLAYER";
			}
			StdDraw.text(0.5, 0.9, w + " wins!");
			StdDraw.text(0.5, 0.1, "Press space to play again.");
			StdDraw.show(0);
			while (!StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_SPACE)) {
				// Wait for spacebar
			}
		}
	}

	public void drawBike(Bike b) {
		Hitbox h = b.getHitbox();
		double x = h.getX();
		double y = h.getY();
		double r = h.getRadius();

		if (b == model.getBike1()) {
			StdDraw.setPenColor(80, 205, 225);
			StdDraw.filledCircle(x, y, r);
		} else {
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledCircle(x, y, r);
		}
	}

	public void drawStream(ArrayList<Point> b) {

		StdDraw.setPenRadius(.025);

		if (b == model.getBike1Line()) {
			StdDraw.setPenColor(80, 205, 225);
		} else {
			StdDraw.setPenColor(StdDraw.ORANGE);
		}

		if (b.size() == 1) {
			Point p0 = b.get(0);
			if (b == model.getBike1Line()) {
				Hitbox h = (model.getBike1()).getHitbox();
				StdDraw.line(p0.getX(), p0.getY(), h.getX(), h.getY());
			} else {
				Hitbox h = (model.getBike2()).getHitbox();
				StdDraw.line(p0.getX(), p0.getY(), h.getX(), h.getY());
			}
		}

		for (int i = 1; i < b.size(); i++) {
			Point p1 = b.get(i);
			Point p0 = b.get(i - 1);
			StdDraw.line(p0.getX(), p0.getY(), p1.getX(), p1.getY());
			if (i + 1 == b.size()) {
				if (b == model.getBike1Line()) {
					Hitbox h = (model.getBike1()).getHitbox();
					StdDraw.line(p1.getX(), p1.getY(), h.getX(), h.getY());
				} else {
					Hitbox h = (model.getBike2()).getHitbox();
					StdDraw.line(p1.getX(), p1.getY(), h.getX(), h.getY());
				}

			}
		}
		StdDraw.setPenRadius(.005);
	}

	/** Displays the game title and instructions. */
	public void titleScreen() {
		StdDraw.clear(StdDraw.BLACK);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(0.5, 0.8, "TRON");
		StdDraw.setPenColor(80, 205, 225);
		StdDraw.text(.5, .6, "PLAYER 1 USE W A S D");
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.text(.5, .4, "PLAYER 2 USE I J K L");
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(.5, .2, "PRESS SPACE TO START");
		StdDraw.show(0);
		while (!StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_SPACE)) {
			// wait for spacebar
		}
	}

	public void handleKeyPresses() {
		Bike bike1 = model.getBike1();
		Hitbox hitbox1 = bike1.getHitbox();
		ArrayList<Point> bike1line = model.getBike1Line();

		if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_W)) {
			if (bike1.getDirection() != 2 && bike1.getDirection() != 0) {
				bike1.setDirection(0);
				Point p = new Point(hitbox1.getX(), hitbox1.getY());
				bike1line.add(p);
			}
		}
		if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_A)) {
			if (bike1.getDirection() != 1 && bike1.getDirection() != 3) {
				bike1.setDirection(3);
				Point p = new Point(hitbox1.getX(), hitbox1.getY());
				bike1line.add(p);
			}
		}
		if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_S)) {
			if (bike1.getDirection() != 0 && bike1.getDirection() != 2) {
				bike1.setDirection(2);
				Point p = new Point(hitbox1.getX(), hitbox1.getY());
				bike1line.add(p);
			}
		}
		if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_D)) {
			if (bike1.getDirection() != 3 && bike1.getDirection() != 1) {
				bike1.setDirection(1);
				Point p = new Point(hitbox1.getX(), hitbox1.getY());
				bike1line.add(p);
			}
		}

		Bike bike2 = model.getBike2();
		Hitbox hitbox2 = bike2.getHitbox();
		ArrayList<Point> bike2line = model.getBike2Line();

		if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_I)) {
			if (bike2.getDirection() != 2 && bike2.getDirection() != 0) {
				bike2.setDirection(0);
				Point p = new Point(hitbox2.getX(), hitbox2.getY());
				bike2line.add(p);
			}
		}
		if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_J)) {
			if (bike2.getDirection() != 1 && bike2.getDirection() != 3) {
				bike2.setDirection(3);
				Point p = new Point(hitbox2.getX(), hitbox2.getY());
				bike2line.add(p);
			}
		}
		if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_K)) {
			if (bike2.getDirection() != 0 && bike2.getDirection() != 2) {
				bike2.setDirection(2);
				Point p = new Point(hitbox2.getX(), hitbox2.getY());
				bike2line.add(p);
			}
		}
		if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_L)) {
			if (bike2.getDirection() != 3 && bike2.getDirection() != 1) {
				bike2.setDirection(1);
				Point p = new Point(hitbox2.getX(), hitbox2.getY());
				bike2line.add(p);
			}
		}

	}

}
