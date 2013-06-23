package com.whiuk.philip.jrogue;

/**
 * Provides the current state of the application.
 * @author Philip Whitehouse
 *
 */
enum ApplicationState {
	/**
	 * Indicates the application hasn't got a game in progress.
	 */
	START,
	/**
	 * Indicates a game is currently in progress
	 */
	GAME,
	/**
	 * Indicates that a game has finished.
	 */
	FINISH
}