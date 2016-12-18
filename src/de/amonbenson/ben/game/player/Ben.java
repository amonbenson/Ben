package de.amonbenson.ben.game.player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.amonbenson.ben.game.physics.GameWorld;

public class Ben extends Player {
	
	public static final double BEN_PLAYER_HEIGHT = 1.7;

	public Ben(double x, double y) {
		super(x, y, BEN_PLAYER_HEIGHT);
	}

	@Override
	protected void updatePlayer(GameWorld world, GameContainer gc, StateBasedGame game, int delta) {
		
	}

}
