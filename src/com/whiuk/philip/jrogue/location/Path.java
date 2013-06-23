package com.whiuk.philip.jrogue.location;

import java.util.List;

import com.whiuk.philip.jrogue.creatures.Creature;


/**
 * 
 * @author Philip Whitehouse
 *
 */
public class Path {
	/**
	 * 
	 */
	private static final int MAX_TRIES = 300;

	/**
	 * 
	 */
	private static PathFinder pf = new PathFinder();
	
	/**
	 * 
	 */
	private List<Point> points;
	/**
	 * 
	 * @return
	 */
	public final List<Point> points() {
		return points;
	}
	
	/**
	 * 
	 * @param creature
	 * @param t
	 */
	public Path(final Creature creature, final Tile t) {
		points = pf.findPath(creature,
				new Point(creature.getTile()), new Point(t), MAX_TRIES);
	}
}
