package de.amonbenson.ben.game;

import java.util.Random;

import de.amonbenson.ben.game.physics.GameWorld;
import de.amonbenson.ben.game.player.Player;
import de.amonbenson.ben.res.Tools;

public class WorldGenerator {
	
	private Random random;
	private Player player;
	
	private float difficulty;
	
	public WorldGenerator() {
		random = new Random();
	}
	
	public GameWorld generateWorld() {
		GameWorld world = new GameWorld();
		
		// Generate a random width
		world.setWorldWidth(Tools.mix(30, 100, difficulty));
		double worldWidth = world.getWorldWidth();
		
		// Set player
		world.setPlayer(player);
		
		// Set the floor
		double floorOffset = 2;
		Floor floor = new Floor(-floorOffset, GameWorld.HEIGHT - 0.6, worldWidth + 2 * floorOffset);
		world.setFloor(floor);
		
		return world;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public float getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(float difficulty) {
		this.difficulty = difficulty;
	}

	public void setSeed(long seed) {
		random.setSeed(seed);
	}
	
	public void randomSeed() {
		random = new Random();
	}
}
