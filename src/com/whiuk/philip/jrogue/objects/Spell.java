package com.whiuk.philip.jrogue.objects;

/**
 * 
 * @author Philip Whitehouse
 *
 */
public class Spell {

	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private int manaCost;
	/**
	 * 
	 */
	private Effect effect;
	
	/**
	 * 
	 * @param n
	 * @param m
	 * @param e
	 */
	public Spell(final String n, final int m, final Effect e) {
		this.name = n;
		this.manaCost = m;
		this.effect = e;
	}
	/**
	 * 
	 * @return
	 */
	public final String name() {
		return name;
	}

	/**
	 * 
	 * @return
	 */
	public final int manaCost() {
		return manaCost;
	}

	/**
	 * 
	 * @return
	 */
	public final Effect effect() {
		return new Effect(effect);
	}

	/**
	 * 
	 * @return
	 */
	public final boolean requiresTarget() {
		return true;
	}

}
