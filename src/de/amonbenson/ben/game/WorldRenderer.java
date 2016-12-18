package de.amonbenson.ben.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import de.amonbenson.ben.game.physics.GameWorld;
import de.amonbenson.ben.game.player.Ben;
import de.amonbenson.ben.game.player.Player;
import de.amonbenson.ben.res.Resources;

public class WorldRenderer {

	public static void render(GameContainer gc, StateBasedGame game, Graphics g, GameWorld world) {
		
		// Render the floor
		Floor floor = world.getFloor();
		float floorW = GameWorld.unitsToPixels(floor.getWidth());
		float floorH = GameWorld.unitsToPixels(floor.getHeight());
		float floorX = GameWorld.unitsToPixels(floor.getX()) % floorW;
		float floorY = GameWorld.unitsToPixels(floor.getY());
		Resources.IMG_FLOOR.draw(floorX, floorY, floorW, floorH);
		Resources.IMG_FLOOR.draw(floorX + floorW, floorY, floorW, floorH);
		
		// Render the player
		renderPlayer(gc, game, g, world.getPlayer());
		
	}

	private static void renderPlayer(GameContainer gc, StateBasedGame game, Graphics g, Player player) {
		
		float x = GameWorld.unitsToPixels(player.getX());
		float y = GameWorld.unitsToPixels(player.getY());
		float w = GameWorld.unitsToPixels(player.getWidth());
		float h = GameWorld.unitsToPixels(player.getHeight());
		
		if (player instanceof Ben) {
			// Render the ben
			Ben ben = (Ben) player;
			ben.pushTransform(g);
			
			Resources.IMG_PLAYER_BEN.draw(0, 0, w, h);
			
			ben.popTransform(g);
		}
	}
}
