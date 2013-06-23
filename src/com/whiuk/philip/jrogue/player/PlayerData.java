package com.whiuk.philip.jrogue.player;

import com.whiuk.philip.jrogue.creatures.Class;
import com.whiuk.philip.jrogue.creatures.Gender;


/**
 * Provides un-changing elements of the player's background.
 * @author Philip Whitehouse
 *
 */
public class PlayerData {
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String description;
	/**
	 * 
	 */
	private Gender gender;
	/**
	 * 
	 */
	private Race race;
	/**
	 * 
	 */
	private Class clazz;
	
	/**
	 * 
	 * @param n name
	 * @param d description
	 * @param g gender
	 * @param r race
	 * @param c class
	 */
	PlayerData(final String n, final String d, final Gender g,
			final Race r, final Class c) {
		setName(n);
		setDescription(d);
		setGender(g);
		setRace(r);
		setClazz(c);
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param n the name to set
	 */
	public final void setName(final String n) {
		this.name = n;
	}

	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @param d the description to set
	 */
	public final void setDescription(final String d) {
		this.description = d;
	}

	/**
	 * @return the gender
	 */
	public final Gender getGender() {
		return gender;
	}

	/**
	 * @param g the gender to set
	 */
	public final void setGender(final Gender g) {
		this.gender = g;
	}

	/**
	 * @return the race
	 */
	public final Race getRace() {
		return race;
	}

	/**
	 * @param r the race to set
	 */
	public final void setRace(final Race r) {
		this.race = r;
	}

	/**
	 * @return the clazz
	 */
	public final Class getClazz() {
		return clazz;
	}

	/**
	 * @param c the clazz to set
	 */
	public final void setClazz(final Class c) {
		this.clazz = c;
	}
}
