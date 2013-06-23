package com.whiuk.philip.jrogue.location;

/**
 * An underground {@link Area} of passages connecting different levels.
 * @author Philip Whitehouse
 *
 */
public abstract class Cave extends Area {
	/**
	 * 
	 * @param w
	 */
	public Cave(final World w) {
		super(w, AreaType.CAVE);
		
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Returns the level reached by descending from the given level.
	 * @param tile
	 * @return 
	 */
	public abstract CaveLevel getLevelBelow(CaveLevel f);
	/**
	 * Returns the level reached by ascending from the given level.
	 * @param tile
	 * @return
	 */
	public abstract CaveLevel getLevelAbove(CaveLevel f);
	/**
	 * Returns the given level.
	 * @param l
	 * @return
	 */
	public abstract CaveLevel getLevel(int l);
}
