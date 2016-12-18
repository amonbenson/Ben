package de.amonbenson.ben.game;

import org.dyn4j.geometry.MassType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.amonbenson.ben.game.physics.GameWorld;
import de.amonbenson.ben.game.physics.RectangleBody;

public class Floor extends RectangleBody {

	public static final double FLOOR_HEIGHT = 2;
	
	public Floor(double x, double y, double w) {
		super(x, y, w, FLOOR_HEIGHT);
		setMass(MassType.INFINITE);
	}

	@Override
	public void update(GameWorld world, GameContainer gc, StateBasedGame game, int delta) {
		
	}

}
