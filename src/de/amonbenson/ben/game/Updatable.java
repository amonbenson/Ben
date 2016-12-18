package de.amonbenson.ben.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.amonbenson.ben.game.physics.GameWorld;

public interface Updatable {
	public void update(GameWorld world, GameContainer gc, StateBasedGame game, int delta);
}
