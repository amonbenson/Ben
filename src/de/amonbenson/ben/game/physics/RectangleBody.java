package de.amonbenson.ben.game.physics;

import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Polygon;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.amonbenson.ben.game.Updatable;
import de.amonbenson.ben.res.Tools;

public class RectangleBody extends GameBody implements Updatable {

	private Polygon polygon;
	private double w, h;

	public RectangleBody(double x, double y, double w, double h) {
		// Call super constructor
		super();
		
		// Set the dimensions
		this.w = w;
		this.h = h;
		
		// Create Rectangle polygon
		Vector2[] vertices = new Vector2[4];
		vertices[0] = new Vector2(0, 0);
		vertices[1] = new Vector2(w, 0);
		vertices[2] = new Vector2(w, h);
		vertices[3] = new Vector2(0, h);
		polygon = new org.dyn4j.geometry.Polygon(vertices);
		addFixture(polygon);

		// Init body
		setMass(MassType.NORMAL);
		translate(x, y);
	}

	public double getWidth() {
		return w;
	}

	public double getHeight() {
		return h;
	}

	@Override
	public void update(GameWorld world, GameContainer gc, StateBasedGame game, int delta) {
		
	}
}
