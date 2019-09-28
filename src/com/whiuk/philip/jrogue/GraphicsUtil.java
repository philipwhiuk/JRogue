package com.whiuk.philip.jrogue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * Provides a set of graphics functionality to implement
 * writing text, colour selection and fonts.
 * @author Philip Whitehouse
 */
public final class GraphicsUtil {
	/**
	 * 
	 */
	public static final Color WHITE = Color.white;
	/**
	 * 
	 */
	public static final Color RED = Color.red;
	/**
	 * 
	 */
	public static final Color MAGENTA = Color.MAGENTA;
	/**
	 * 
	 */
	public static final Color GREEN = Color.green;
	/**
	 * 
	 */
	public static final Color YELLOW = Color.yellow;
	/**
	 * 
	 */
	public static final Color BLUE = Color.BLUE;
	/**
	 * 
	 */
	public static final Color BROWN = new Color(51, 51, 0);
	/**
	 * 
	 */
	public static final Color GRAY = Color.GRAY;
	/**
	 * 
	 */
	public static final Color BLACK = new Color(25, 25, 25);
	/**
	 * 
	 */
	public static final Color DARK_RED = new Color(128, 0, 0);
	/**
	 * 
	 */
	public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;
	/**
	 * 
	 */
	public static final Color DARK_BLUE = new Color(0, 0, 128);
	/**
	 * 
	 */
	public static final Color CYAN = new Color(0, 255, 255);
	/**
	 * 
	 */
	public static final Color DARK_GREEN = new Color(0, 128, 0);
	/**
	 * 
	 */
	public static final Color DARK_MAGENTA = new Color(128, 0, 128);
	/**
	 * 
	 */
	public static final Color DARK_YELLOW = new Color(128, 128, 0);
	/**
	 * 
	 */
	public static final Color DARK_CYAN = new Color(0, 128, 128);
	/**
	 * 
	 */
	public static final Color DARK_GRAY = new Color(128, 128, 128);
	
	/**
	 * 
	 */
	private GraphicsUtil() {
		
	}

	/**
	 * Text font.
	 */
	private static final Font TEXT_FONT =
			new Font("Courier New", Font.PLAIN, 14);
	
	/**
	 * Draw some text on the screen at the given coordinates.
	 * @param g The 2D graphics context
	 * @param text Text to draw
	 * @param x Starting position (X)
	 * @param y Starting position (Y)
	 */
	public static void drawText(final Graphics2D g, final String text,
			final int x, final int y) {
		g.setColor(Color.white);
		g.setFont(TEXT_FONT);
		g.drawString(text, x, y);
	}
	/**
	 * Draw a map object.
	 * @param g 2D graphics
 	 * @param c Colour to use
	 * @param width Starting position (X)
	 * @param height Starting position (Y)
	 */
	public static void drawObject(final Graphics2D g, 
			final Color c, final int width, final int height) {
		g.setColor(c);
		g.fillRect(0, 0, width, height);
	}
}
