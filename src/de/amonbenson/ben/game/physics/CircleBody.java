package de.amonbenson.ben.game.physics;

import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Polygon;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.amonbenson.ben.game.Updatable;

public class CircleBody extends GameBody implements Updatable {

	private Circle circle;

	public CircleBody(double x, double y, double radius) {
		// Call super constructor
		super();
		
		// Create the Circle
		circle = new Circle(radius);
		addFixture(circle);

		// Init body
		setMass(MassType.NORMAL);
		translate(x, y);
	}

	public double getRadius() {
		return circle.getRadius();
	}

	@Override
	public void update(GameWorld world, GameContainer gc, StateBasedGame game, int delta) {
		
	}
}
