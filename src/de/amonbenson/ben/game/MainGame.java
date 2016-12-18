package de.amonbenson.ben.game;

import java.util.ArrayList;
import java.util.Iterator;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.amonbenson.ben.BenGame;
import de.amonbenson.ben.game.physics.CircleBody;
import de.amonbenson.ben.game.physics.GameBody;
import de.amonbenson.ben.game.physics.GameWorld;
import de.amonbenson.ben.game.physics.RectangleBody;

public class MainGame extends BasicGameState {
	
	private GameWorld world;
	ArrayList<GameBody> testBodys;
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		world = new GameWorld();
		
		testBodys = new ArrayList<GameBody>();
		RectangleBody floor = new RectangleBody(0, GameWorld.HEIGHT - 0.002, GameWorld.WIDTH, 0.5);
		floor.setMass(MassType.INFINITE);
		testBodys.add(floor);
		world.addBody(floor);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
		// Transform the world
		world.pushTransform(g);
		
		// Draw the Gamebodys
		Iterator<Body> bodies = world.getBodyIterator();
		while (bodies.hasNext()) {
			GameBody body = (GameBody) bodies.next();
			
			body.pushTransform(g);
			
			if (body instanceof CircleBody) {
				CircleBody circle = (CircleBody) body;
				
				float r = GameWorld.unitsToPixels(circle.getRadius());
				g.setColor(Color.white);
				g.drawOval(-r, -r, 2 * r, 2 * r);
			}
			
			if (body instanceof RectangleBody) {
				RectangleBody rect = (RectangleBody) body;

				float w = GameWorld.unitsToPixels(rect.getWidth());
				float h = GameWorld.unitsToPixels(rect.getHeight());
				g.setColor(Color.white);
				g.drawRect(0, 0, w, h);
			}
			
			body.popTransform(g);
		}
		
		// Pop the world translation
		world.popTransform(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		world.update(gc, game, delta);
		
		Input in = gc.getInput();
		if (in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			
			double size = Math.random() * 0.1 + 0.05;
			
			CircleBody c = new CircleBody(
					world.getTransformedX(GameWorld.pixelsToUnits(in.getMouseX())),
					world.getTransformedY(GameWorld.pixelsToUnits(in.getMouseY())),
					size
			);
			testBodys.add(c);
			world.addBody(c);
		}
	}

	@Override
	public int getID() {
		return BenGame.STATE_MAIN_GAME;
	}
}
