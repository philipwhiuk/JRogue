package com.whiuk.philip.jrogue.location;

/**
 * An area of the game above ground.
 * @author Philip Whitehouse
 *
 */
//TODO: Surface areas
public class Surface extends Area {
	/**
	 * 
	 * @param w world
	 */
	Surface(final World w) {
		super(w, AreaType.SURFACE);
	}
}
