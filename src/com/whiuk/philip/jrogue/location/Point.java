package com.whiuk.philip.jrogue.location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An X,Y position.
 * @author Philip Whitehouse
 *
 */
public class Point {
	/**
	 * 
	 */
	public int x;
	/**
	 * 
	 */
	public int y;

	/**
	 * 
	 * @param t
	 */
	public Point(final Tile t) {
		this.x = t.getX();
		this.y = t.getY();
	}
	
	/**
	 * 
	 * @param xPos
	 * @param yPos
	 */
	public Point(final int xPos, final int yPos) {
		this.x = xPos;
		this.y = yPos;
	}
	
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Point)) {
			return false;
		}
		Point other = (Point) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public final List<Point> neighbors8() {
		List<Point> points = new ArrayList<Point>();
		
		for (int ox = -1; ox < 2; ox++) {
			for (int oy = -1; oy < 2; oy++) {
				if (ox == 0 && oy == 0) {
					continue;
				}
				
				points.add(new Point(x + ox, y + oy));
			}
		}

		Collections.shuffle(points);
		return points;
	}
}
