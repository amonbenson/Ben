package de.amonbenson.ben.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.amonbenson.ben.BenGame;
import de.amonbenson.ben.game.physics.GameWorld;
import de.amonbenson.ben.game.player.Ben;
import de.amonbenson.ben.game.player.Player;
import de.amonbenson.ben.res.Resources;

public class MainGame extends BasicGameState {
	
	private WorldGenerator generator;
	private GameWorld world;
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		// Init the World Generator
		generator = new WorldGenerator();
		generator.setDifficulty(0);
		generator.setPlayer(new Ben(0, 0));
		
		// Generate the world
		world = generator.generateWorld();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
		// Transform the world
		world.pushTransform(g);
		
		// Draw the World
		WorldRenderer.render(gc, game, g, world);
		
		// Pop the world translation
		world.popTransform(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		world.update(gc, game, delta);
	}

	@Override
	public int getID() {
		return BenGame.STATE_MAIN_GAME;
	}
}
