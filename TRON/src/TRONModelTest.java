import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TRONModelTest {

	private TRONModel model;

	@Before
	public void setUp() throws Exception {
		model = new TRONModel();
	}

	@Test
	public void testBikesCollideWall() {
		Bike bike1 = model.getBike1();
		Bike bike2 = model.getBike2();

		bike1.setDirection(3);
		bike2.setDirection(1);

		for (int i = 0; i < 150; i++) {
			model.advance();
		}
		assertTrue(model.getBike1().isDead());
		assertTrue(model.getBike2().isDead());
	}

	@Test
	public void testBikesCollideEachother() {
		for (int i = 0; i < 100; i++) {
			model.advance();
		}
		assertTrue(model.getBike1().isDead());
		assertTrue(model.getBike2().isDead());
		assertEquals(model.winner(), 3);
	}

	@Test
	public void testBikeCollideStream4turns() {

		Bike b = model.getBike1();
		Hitbox h = model.getBike1().getHitbox();
		ArrayList<Point> bike1line = model.getBike1Line();

		for (int i = 0; i < 100; i++) {
			model.advance();
		}
		assertTrue(model.approxEqual(h.getX(), .75));
		bike1line.add(new Point(h.getX(), h.getY()));
		b.setDirection(0);

		for (int i = 0; i < 50; i++) {
			model.advance();
		}
		assertTrue(model.approxEqual(h.getY(), .75));
		bike1line.add(new Point(h.getX(), h.getY()));
		b.setDirection(3);

		for (int i = 0; i < 50; i++) {
			model.advance();
		}
		assertTrue(model.approxEqual(h.getX(), .5));
		bike1line.add(new Point(h.getX(), h.getY()));
		b.setDirection(2);

		for (int i = 0; i < 50; i++) {
			model.advance();
		}
		assertFalse(h.getX() >= 1);
		assertFalse(h.getY() >= 1);
		assertEquals(b.isDead(), true);
	}

	@Test
	public void testBikeCollideStream5turns() {

		Bike b = model.getBike1();
		Hitbox h = model.getBike1().getHitbox();
		ArrayList<Point> bike1line = model.getBike1Line();

		for (int i = 0; i < 50; i++) {
			model.advance();
		}
		bike1line.add(new Point(h.getX(), h.getY()));
		b.setDirection(0);

		for (int i = 0; i < 50; i++) {
			model.advance();
		}
		bike1line.add(new Point(h.getX(), h.getY()));
		b.setDirection(3);

		for (int i = 0; i < 30; i++) {
			model.advance();
		}
		bike1line.add(new Point(h.getX(), h.getY()));
		b.setDirection(2);

		for (int i = 0; i < 25; i++) {
			model.advance();
		}
		b.setDirection(1);

		for (int i = 0; i < 75; i++) {
			model.advance();
		}
		
		assertFalse(h.getX() >= 1);
		assertFalse(h.getY() >= 1);
		assertEquals(b.isDead(), true);
	}

	@Test
	public void testBikeCollideOtherCompleteStream() {
		Bike bike1 = model.getBike1();
		Bike bike2 = model.getBike2();

		Hitbox hitbox1 = bike1.getHitbox();
		Hitbox hitbox2 = model.getBike2().getHitbox();

		ArrayList<Point> bike2line = model.getBike2Line();

		bike2.setDirection(0);
		bike2line.add(new Point(hitbox2.getX(), hitbox2.getY()));

		bike1.setDirection(0);

		for (int i = 0; i < 25; i++) {
			model.advance();
		}

		bike1.setDirection(1);

		for (int i = 0; i < 25; i++) {
			model.advance();
		}

		bike2.setDirection(3);
		bike2line.add(new Point(hitbox2.getX(), hitbox2.getY()));

		for (int i = 0; i < 100; i++) {
			model.advance();
		}

		assertFalse(hitbox1.getX() >= 1);
		assertFalse(hitbox1.getY() >= 1);
		assertEquals(bike1.isDead(), true);
	}

	@Test
	public void testBikeCollideOtherIncompleteStream() {
		Bike bike1 = model.getBike1();
		Bike bike2 = model.getBike2();

		Hitbox hitbox1 = bike1.getHitbox();
		Hitbox hitbox2 = bike2.getHitbox();

		ArrayList<Point> bike2line = model.getBike2Line();

		bike2.setDirection(0);
		bike2line.add(new Point(hitbox2.getX(), hitbox2.getY()));

		bike1.setDirection(0);

		for (int i = 0; i < 25; i++) {
			model.advance();
		}

		bike1.setDirection(1);

		for (int i = 0; i < 100; i++) {
			model.advance();
		}

		assertFalse(hitbox1.getX() >= 1);
		assertFalse(hitbox1.getY() >= 1);
		assertEquals(bike1.isDead(), true);
	}

	@Test
	public void testBikeCollideStreamCorner() {

		Bike bike1 = model.getBike1();
		Bike bike2 = model.getBike2();

		Hitbox hitbox1 = bike1.getHitbox();
		Hitbox hitbox2 = bike2.getHitbox();

		ArrayList<Point> bike2line = model.getBike2Line();

		bike2.setDirection(0);
		bike2line.add(new Point(hitbox2.getX(), hitbox2.getY()));

		for (int i = 0; i < 25; i++) {
			model.advance();
		}

		bike2.setDirection(1);
		bike2line.add(new Point(hitbox2.getX(), hitbox2.getY()));

		bike1.setDirection(0);

		for (int i = 0; i < 25; i++) {
			model.advance();
		}

		bike1.setDirection(1);

		for (int i = 0; i < 75; i++) {
			model.advance();
		}

		assertFalse(hitbox1.getX() >= 1);
		assertFalse(hitbox1.getY() >= 1);
		assertEquals(bike1.isDead(), true);

	}
}
