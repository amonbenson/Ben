package de.amonbenson.ben.game.player;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import de.amonbenson.ben.BenGame;
import de.amonbenson.ben.game.Updatable;
import de.amonbenson.ben.game.physics.GameWorld;
import de.amonbenson.ben.game.physics.RectangleBody;

public abstract class Player extends RectangleBody implements Updatable {

	public static final double PLAYER_WIDTH = 0.6;
	
	public static final double JUMP_FORCE = 400;
	public static final double WALK_SPEED = 3;
	
	public static final int STANDING = 0, WALKING_LEFT = 1, WALKING_RIGHT = 2, JUMPING = 3;
	
	private int state = STANDING;
	private boolean jumpAllowed = false;
	
	public Player(double x, double y, double h) {
		super(x, y, PLAYER_WIDTH, h);
		setDeactivatable(false);
		
		getFixture(0).setFriction(0.9);
		setLinearDamping(0.0001);
	}
	
	@Override
	public void update(GameWorld world, GameContainer gc, StateBasedGame game, int delta) {
		Input input = gc.getInput();
		
		// Disable rotation
		setAngularVelocity(0);
		getTransform().setRotation(0);
		
		// Walk
		if (input.isKeyDown(BenGame.KEY_RIGHT)) {
			setLinearVelocity(WALK_SPEED, getLinearVelocity().y);
			state = WALKING_LEFT;
		} else if (input.isKeyDown(BenGame.KEY_LEFT)) {
			setLinearVelocity(-WALK_SPEED, getLinearVelocity().y);
			state = WALKING_RIGHT;
		} else {
			setLinearVelocity(0, getLinearVelocity().y);
			state = STANDING;
		}
		
		if (getX() < 0) {
			getTransform().setTranslationX(0);
		}
		if (getX() > world.getWorldWidth() - getWidth()) {
			getTransform().setTranslationX(world.getWorldWidth() - getWidth());
		}
		
		// Jump
		if (input.isKeyPressed(BenGame.KEY_JUMP) && jumpAllowed) {
			jump();
		}
		
		// Activate jump if player hits the ground
		if (isInContact(world.getFloor())) {
			jumpAllowed = true;
		} else {
			state = JUMPING;
			jumpAllowed = false;
		}
		
		// Update the individual player
		updatePlayer(world, gc, game, delta);
	}
	
	public int getState() {
		return state;
	}

	protected abstract void updatePlayer(GameWorld world, GameContainer gc, StateBasedGame game, int delta);
	
	public void jump() {
		applyForce(new Vector2(0, -JUMP_FORCE));
	}
}
