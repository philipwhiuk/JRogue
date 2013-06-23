package com.whiuk.philip.jrogue.creatures;

import java.awt.Color;

import com.whiuk.philip.jrogue.location.Area;
import com.whiuk.philip.jrogue.location.CaveLevel;
import com.whiuk.philip.jrogue.location.Location;
import com.whiuk.philip.jrogue.location.World;
import com.whiuk.philip.jrogue.objects.ObjectType;


/**
 * Represents a creature not controlled by the player.
 * @author Philip Whitehouse
 *
 */
class Monster extends Creature {
	/**
	 * 
	 */
	private Color color;

	/**
	 * Simplified constructor.
	 * @param n name
	 * @param f floor
	 * @param c colour
	 * @param maxHp
	 * @param attrs attributes
	 * @param skills skills
	 */
	public Monster(final String n, final Location f, final Color c,
			final int maxHp,
			final Attributes attrs,
			final Skills skills) {
		this(n, f.getArea().getWorld(), f.getArea(),
				f, c, maxHp, attrs, skills);
	}
	
	/**
	 * Constructor.
	 * @param n Name
	 * @param w World
	 * @param a Area
	 * @param f Location
	 * @param c Color
	 * @param maxHp Maximum HP
	 * @param attrs Attributes
	 * @param skills Skills
	 */
	public Monster(final String n,
			final World w, final Area a, final Location f,
			final Color c, final int maxHp,
			final Attributes attrs,
			final Skills skills) {
		super(ObjectType.MONSTER, w, a, f, maxHp, attrs, skills);
		this.color = c;
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param n Name
	 * @param f Location
	 * @param c Colour
	 * @param i Maximum HP
	 */
	public Monster(final String n, final Location f,
			final Color c, final int i) {
		this(n, f.getArea().getWorld(), f.getArea(),
				f, c, i, new Attributes(), new Skills());
	}
}