package de.amonbenson.ben.res;

import java.io.File;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class Resources {

	// Default file path
	public static final String RES_PATH = "de/amonbenson/ben/res/";

	// Images
	public static Image IMG_PLAYER_BEN;
	public static Image IMG_FLOOR;

	// Sounds

	// Music

	// Fonts

	// Cursors

	private static boolean initialized = false;
	private static long initTime = 0;
	private static Music currentMusic = null;
	private static Image currentCursor = null;

	public static void init() {
		// Don't initialize a second time
		if (initialized)
			return;

		long startTime = System.currentTimeMillis();

		// Load all
		IMG_PLAYER_BEN = loadImage("player_ben.png");
		IMG_FLOOR = loadImage("floor.png");

		// Finish initialization
		initialized = true;
		initTime = System.currentTimeMillis() - startTime;
	}

	public void reloadAll() {
		// Reset initalization and call init methode
		initialized = false;
		init();
	}

	private static Image loadImage(String name) {
		Image image = null;
		try {
			image = new Image(RES_PATH + name);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return image;
	}

	private static Image loadImage(String name, int xTiles, int yTiles,
			int x, int y) {
		Image image = loadImage(name);
		float tileWidth = image.getWidth() / xTiles;
		float tileHeight = image.getHeight() / yTiles;
		Image subimage = image.getSubImage((int) (x * tileWidth),
				(int) (y * tileHeight), (int) (tileWidth), (int) (tileHeight));
		return subimage;
	}

	private static Sound loadSound(String name) {
		Sound sound = null;
		try {
			sound = new Sound(RES_PATH + name);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sound;
	}

	private static Music loadMusic(String name) {
		Music music = null;
		try {
			music = new Music(RES_PATH + name);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return music;
	}

	private static UnicodeFont loadFont(String name, int size, boolean bold,
			boolean italic) {
		UnicodeFont font = null;
		try {
			font = new UnicodeFont(RES_PATH + name, size, bold, italic);
			font.getEffects().add(new ColorEffect(java.awt.Color.white));
			font.addAsciiGlyphs();
			font.loadGlyphs();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return font;
	}

	public static void changeMusic(Music newMusic) {
		// Don't change anything if the music is already playing or if the new music is null.
		if (newMusic == null)
			return;
		if (newMusic == currentMusic)
			return;

		// If the current music isn't null, stop it
		if (currentMusic != null)
			currentMusic.stop();

		// Set the current music
		currentMusic = newMusic;

		// Play in loop
		currentMusic.loop();
	}

	public static void changeCursor(Image newCursor) {
		// Set current cursor
		currentCursor = newCursor;
	}

	public static void drawCursor(int x, int y) {
		// Draw the cursor if not null
		if (currentCursor != null) {
			currentCursor.draw(x, y);
		}
	}

	public static boolean isIntialized() {
		return initialized;
	}

	public static long getInitTime() {
		return initTime;
	}

	public static File getSavesPath() {
		String home = System.getenv("APPDATA");
		if (home.isEmpty()) {
			home = System.getProperty("user.home");
		}
		File file = new File(home, ".kiato");
		file.mkdirs();
		return file;
	}

	public static File getSaveFile() {
		File saveFile = new File(getSavesPath(), ".save");
		return saveFile;
	}
}
