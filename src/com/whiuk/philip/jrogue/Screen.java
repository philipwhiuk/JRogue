package com.whiuk.philip.jrogue;

/**
 * Provides the core rendered component displayed in the game.
 * @author Philip Whitehouse
 *
 */
public abstract class Screen implements Renderable {

	/**
	 * 
	 * @param keyChar
	 */
	public abstract void handleKeyTyped(char keyChar);

}
