package com.whiuk.philip.jrogue.screens;

import java.awt.Color;
import java.awt.Graphics2D;

import com.whiuk.philip.jrogue.GraphicsUtil;
import com.whiuk.philip.jrogue.JRogue;
import com.whiuk.philip.jrogue.Screen;

/**
 * 
 * @author Philip Whitehouse
 *
 */
public class StartScreen extends Screen {
	/**
	 * 
	 */
	private static final int TITLE_X = 200;
	/**
	 * 
	 */
	private static final int TITLE_Y = 130;
	/**
	 * 
	 */
	private static final int VERSION_X = 280;
	/**
	 * 
	 */
	private static final int VERSION_Y = 160;
	/**
	 * 
	 */
	private static final int AUTHOR_X = 240;
	/**
	 * 
	 */
	private static final int AUTHOR_Y = 190;
	/**
	 * 
	 */
	private static final int START_X = 240;
	/**
	 * 
	 */
	private static final int START_Y = 250;
	/**
	 * 
	 */
	private JRogue app;
	/**
	 * 
	 * @param a app
	 */
	public StartScreen(final JRogue a) {
		this.app = a;
	}
	
	/**
	 * Render the start screen.
	 * @param g 2D graphics
	 * @param width X-extent
	 * @param height Y-extent
	 */
	public final void render(final Graphics2D g,
			final int width, final int height) {
		g.setColor(GraphicsUtil.BLACK);
		g.fillRect(0, 0, width, height);
		GraphicsUtil.drawText(g, JRogue.TITLE + " - " + JRogue.DESCRIPTION,
				TITLE_X, TITLE_Y);
		GraphicsUtil.drawText(g, "v" + JRogue.VERSION,
				VERSION_X, VERSION_Y);
		GraphicsUtil.drawText(g, "By " + JRogue.AUTHOR,
				AUTHOR_X, AUTHOR_Y);
		GraphicsUtil.drawText(g, "Press SPACE TO START",
				START_X, START_Y);
	}
	@Override
	public final void handleKeyTyped(final char keyChar) {
		if (keyChar == ' ') {
			app.newGame();
		}
	}
}
