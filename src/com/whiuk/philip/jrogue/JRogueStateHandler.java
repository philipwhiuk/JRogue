package com.whiuk.philip.jrogue;

import com.whiuk.philip.jrogue.player.Player;

/**
 * 
 * @author Philip Whitehouse
 *
 */
public interface JRogueStateHandler {
	/**
	 * Start a new game.
	 */
	void newGame();
	/**
	 * Open an existing game.
	 */
	void openGame();
	/**
	 * Save the current game.
	 */
	void saveGame();
	/**
	 * End the current game.
	 */
	void endGame(Player player);
	/**
	 * Exit.
	 */
	void exit();
}
