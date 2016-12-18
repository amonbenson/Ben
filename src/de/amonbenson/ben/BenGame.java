package de.amonbenson.ben;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.lwjgl.LWJGLException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import de.amonbenson.ben.game.MainGame;
import de.amonbenson.ben.res.Resources;

public class BenGame extends StateBasedGame {
	
	public static final int STATE_MAIN_GAME = 0;
	
	public static final int GAME_WIDTH = 3840, GAME_HEIGHT = 2160, FRAME_RATE = 120;
	public static final String GAME_NAME = "Bén";
	
	private static AppGameContainer appgc;
	
	public BenGame() {
		super(GAME_NAME);
		
		
	}

	public static void main(String[] args) {
		// Disable log verbose
		Log.setVerbose(false);

		try {
			
			// Create game and container
			appgc = new AppGameContainer(new ScalableGame(new BenGame(), GAME_WIDTH, GAME_HEIGHT));
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			appgc.setDisplayMode(screen.width, screen.height, true);
			//appgc.setMouseGrabbed(true);
			appgc.setTargetFrameRate(FRAME_RATE);
			appgc.setShowFPS(true);
			appgc.start();

		} catch (Exception ex) {
			BenGame.handleException(ex);
		}
	}
	
	public static void handleException(Exception ex) {
		System.err.println("Oh no! Game crash:");
		ex.printStackTrace();
		System.exit(1);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		
		// Init resources
		Resources.init();
		
		// Start with main state
		// TODO: Change to title or splash state later on.
		addState(new MainGame());
		
	}
}
