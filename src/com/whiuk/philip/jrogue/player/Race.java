package com.whiuk.philip.jrogue.player;

import com.whiuk.philip.jrogue.creatures.Modifier;
import com.whiuk.philip.jrogue.creatures.Attributes.Attribute;

/**
 * Provides information on a race of beings.
 * @author Philip Whitehouse
 *
 */
public enum Race {
	/**
	 * 
	 */
	HUMAN("Human", new Modifier[]{}),
	/**
	 * 
	 */
	HALFELF("Half-Elf", new Modifier[]{
			new Modifier(Attribute.DEXTERITY, 1),
			new Modifier(Attribute.INTELLIGENCE, 1),
			new Modifier(Attribute.CONSTITUTION, -1),
			new Modifier(Attribute.WISDOM, -1),
			new Modifier(Attribute.CHARISMA, 1)}),
	/**
	 * 
	 */
	ELF("Elf", new Modifier[]{}),
	/**
	 * 
	 */
	HOBBIT("Hobbit", new Modifier[]{}),
	/**
	 * 
	 */
	GNOME("Gnome", new Modifier[]{}),
	/**
	 * 
	 */
	DWARF("Dwarf", new Modifier[]{}),
	/**
	 * 
	 */
	HALFORC("Half-Orc", new Modifier[]{}),
	/**
	 * 
	 */
	HALFTROLL("Half-Troll", new Modifier[]{}),
	/**
	 * 
	 */
	DUNADAN("Dunadan", new Modifier[]{}),
	/**
	 * 
	 */
	HIGHELF("High-Elf", new Modifier[]{}),
	/**
	 * 
	 */
	KOBOLD("Kobold", new Modifier[]{});
	/**
	 * 
	 */
	private final String title;
	/**
	 * 
	 */
	private final Modifier[] modifiers;
	/**
	 * 
	 * @param title
	 * @param mods
	 */
	private Race(final String t, final Modifier[] mods) {
		this.title = t;
		this.modifiers = mods;
	}
	
	@Override
	public final String toString() {
		return title;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Modifier[] getAttributeModifiers() {
		return modifiers;
	}
}