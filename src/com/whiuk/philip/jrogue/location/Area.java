package com.whiuk.philip.jrogue.location;

/**
 * A region of the game world comprising of multiple connected sub-areas.
 * @author Philip Whitehouse
 *
 */
public abstract class Area {
	/**
	 *
	 */
	private final World world;
	/**
	 * 
	 */
	private AreaType type;

	/**
	 * 
	 * @param w
	 * @param t type
	 */
	Area(final World w, final AreaType t) {
		world = w;
		type = t;
	}
	/**
	 * 
	 * @return
	 */
	public final World getWorld() {
		return world;
	}	
	/**
	 * 
	 * @return
	 */
	public final AreaType getType() {
		return type;
	}
}
