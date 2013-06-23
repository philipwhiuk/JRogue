package com.whiuk.philip.jrogue.creatures;


/**
 * Provides an option that can be used when increasing in level.
 * @author Philip Whitehouse
 *
 */
public abstract class LevelUpOption {
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 * @return
	 */
	public final String name() {
		return name;
	}
	/**
	 * 
	 * @param n
	 */
	public LevelUpOption(final String n) {
		this.name = n;
	}
	
	/**
	 * 
	 * @param creature
	 */
	public abstract void invoke(final Creature creature);
}
