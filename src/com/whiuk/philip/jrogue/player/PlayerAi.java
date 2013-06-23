package com.whiuk.philip.jrogue.player;

import com.whiuk.philip.jrogue.creatures.CreatureAi;


/**
 * Provides artificial intelligence to control element of
 * the player's character.
 * @author Philip Whitehouse
 *
 */
public class PlayerAi extends CreatureAi {
	/**
	 * 
	 * @param player
	 */
    public PlayerAi(final Player player) {
    	super(player);
    }
}
