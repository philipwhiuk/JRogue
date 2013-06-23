package com.whiuk.philip.jrogue.objects;

import com.whiuk.philip.jrogue.location.Direction;
import com.whiuk.philip.jrogue.location.Tile;


/**
 * Indicates the object is capable of moving.
 * @author Philip Whitehouse
 *
 */
public interface Moveable {
	/**
	 * Move the object using AI behaviour.
	 */
	void move();
	/**
	 * Move the object in the given direction.
	 * @param d
	 */
	void move(Direction d);
	/**
	 * Move the object to the specified tile without checking if it's empty.
	 * @param t
	 */
	void moveWithoutCheck(Tile t);
	/**
	 * Move the object to the specified tile.
	 * @param t
	 * @return true if the object was moved.
	 */
	boolean move(Tile t);
	/**
	 * Move the object to the tile at the given coordinates.
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 * @return true if the object was moved.
	 */
	boolean move(int x, int y);
	/**
	 * Move the object by the given values relative to it's current position.
	 * @param mx Tiles in the x-direction
	 * @param my Tiles in the y-direction
	 */
	void moveBy(int mx, int my);
}