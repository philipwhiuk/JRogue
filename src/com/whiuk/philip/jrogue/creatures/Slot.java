package com.whiuk.philip.jrogue.creatures;

/**
 * Provides a representation of an area of the body
 * on which a creature can equip an item.
 * @author Philip Whitehouse
 *
 */
public enum Slot {
	/**
	 * 
	 */
	HEAD("Head"),
	/**
	 * 
	 */
	BODY("Body"),
	/**
	 * 
	 */
	LEGS("Legs"),
	/**
	 * 
	 */
	HANDS("Hands"),
	/**
	 * 
	 */
	MAIN_HAND("Main-Hand"),
	/**
	 * 
	 */
	OFF_HAND("Off-Hand"),
	/**
	 * 
	 */
	AMMO("Ammo");
	
	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 * @param s name
	 */
	Slot(final String s) {
		this.name = s;
	}
	
}
