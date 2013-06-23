package com.whiuk.philip.jrogue.creatures;

import com.whiuk.philip.jrogue.creatures.Attributes.Attribute;


/**
 * Represents a buff or nerf to one of the attributes of a {@link Creature}.
 * @author Philip Whitehouse
 *
 */
public class Modifier {
	/**
	 * 
	 */
	private Attribute attribute;
	/**
	 * 
	 */
	private int value;

	/**
	 * 
	 * @param a Attribute
	 * @param v Value
	 */
	public Modifier(final Attribute a, final int v) {
		attribute = a;
		value = v;
	}
	@Override
	public final String toString() {
		return attribute.getShortName() + ": " + value;
	}
	/**
	 * 
	 * @return
	 */
	public final Attribute getAttribute() {
		return attribute;
	}
	/**
	 * 
	 * @return
	 */
	public final int getValue() {
		return value;
	}
}