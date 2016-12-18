package de.amonbenson.ben.game.physics;

import org.dyn4j.dynamics.Body;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import de.amonbenson.ben.game.Updatable;

public abstract class GameBody extends Body implements Updatable {
	
	private boolean deactivatable;
	
	public GameBody() {
		deactivatable = true;
	}
	
	public void pushTransform(Graphics g) {
		g.pushTransform();
		g.translate(
				GameWorld.unitsToPixels(getTransform().getTranslationX()),
				GameWorld.unitsToPixels(getTransform().getTranslationY())
		);
		g.rotate(0, 0, (float) Math.toDegrees(getTransform().getRotation()));
	}
	
	public void popTransform(Graphics g) {
		g.popTransform();
	}
	
	public boolean isDeactivatable() {
		return deactivatable;
	}

	public void setDeactivatable(boolean deactivatable) {
		this.deactivatable = deactivatable;
	}

	public void activate() {
		setActive(true);
	}

	public void deactivate() {
		if (deactivatable)
			setActive(false);
	}
}
