package de.amonbenson.ben.game.physics;

import java.util.Iterator;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.AABB;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import de.amonbenson.ben.BenGame;
import de.amonbenson.ben.game.Floor;
import de.amonbenson.ben.game.player.Player;
import de.amonbenson.ben.res.Tools;

public class GameWorld extends World {

	public static final int PIXELS_PER_UNIT = 300;
	public static final double GRAVITY = 9.81;
	
	public static final double WIDTH = pixelsToUnits(BenGame.GAME_WIDTH);
	public static final double HEIGHT = pixelsToUnits(BenGame.GAME_HEIGHT);
	public static final double PLAYER_X_PERCENTAGE = 0.3, SCROLL_SPEED = 0.03;
	
	private double prefScrollX, scrollX, prefScrollY, scrollY, zoom;
	private double worldWidth;
	
	private Floor floor;
	private Player player;

	public GameWorld() {
		// Set gravity and speed
		setGravity(new Vector2(0, GRAVITY));

		// Change some settings
		getSettings().setMaximumLinearCorrection(0.01);
		
		// Reset float and scroll
		prefScrollX = 0;
		scrollX = 0;
		prefScrollY = 0;
		scrollY = 0;
		zoom = 1;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {

		// transformed world bounds (used to determin which bodies are laying outside the view, so they can be disabled
		double viewX = getTransformedX(0);
		double viewY = getTransformedY(0);
		double viewW = getTransformedX(WIDTH);
		double viewH = getTransformedY(HEIGHT);
		
		// Update the scroll values
		prefScrollX = -getPlayer().getX() * zoom + WIDTH * PLAYER_X_PERCENTAGE;
		scrollX = Tools.mix(scrollX, prefScrollX, SCROLL_SPEED);
		if (scrollX > 0) scrollX = 0;
		if (scrollX < -worldWidth + WIDTH) scrollX = -worldWidth + WIDTH;
		
		// Update bodies (use iterator so they can be removed)
		Iterator<Body> bodies = getBodyIterator();
		while (bodies.hasNext()) {
			GameBody body = (GameBody) bodies.next();

			// Call the update method
			body.update(this, container, game, delta);

			// Disable bodies physically if the lay outside the world's bounds
			AABB bounds = body.createAABB();
			if (bounds.overlaps(new AABB(viewX, viewY, viewW, viewH))) {
				body.activate();
			} else {
				body.deactivate();
			}
		}

		// Update world
		try {
			update((double) delta / 1000);
			
		} catch (Exception ex) {
			System.err.println("Physics have stopped responding...");
		}
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		if (player == null) {
			removeBody(this.player);
		} else {
			addBody(player);
		}
		
		this.player = player;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		if (floor == null) {
			removeBody(this.floor);
		} else {
			addBody(floor);
		}
		
		this.floor = floor;
	}

	public double getWorldWidth() {
		return worldWidth;
	}

	public void setWorldWidth(double worldWidth) {
		this.worldWidth = worldWidth;
	}

	public double getScrollX() {
		return scrollX;
	}

	public void setScrollX(double scrollX) {
		this.scrollX = scrollX;
	}

	public double getScrollY() {
		return scrollY;
	}

	public void setScrollY(double scrollY) {
		this.scrollY = scrollY;
	}

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	public void pushTransform(Graphics g) {
		g.pushTransform();
		g.translate(
				GameWorld.unitsToPixels(scrollX),
				GameWorld.unitsToPixels(scrollY)
		);
		g.scale((float) zoom, (float) zoom);
	}
	
	public void popTransform(Graphics g) {
		g.popTransform();
	}
	
	public double getTransformedX(double untransX) {
		return (untransX - scrollX) / zoom;
	}
	
	public double getTransformedY(double untransY) {
		return (untransY - scrollY) / zoom;
	}

	public static double pixelsToUnits(float pixels) {
		return pixels / PIXELS_PER_UNIT;
	}

	public static float unitsToPixels(double units) {
		return (float) (units * PIXELS_PER_UNIT);
	}
}
