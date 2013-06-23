package com.whiuk.philip.jrogue.location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A connected list of {@link Point}s.
 * @author Philip Whitehouse
 *
 */
public class Line implements Iterable<Point> {
	/**
	 * 
	 */
	private List<Point> points;
	/**
	 * 
	 * @return
	 */
	public final List<Point> getPoints() {
		return points;
	}
	
	/**
	 * 
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 */
	public Line(final int x0, final int y0, final int x1, final int y1) {
		points = new ArrayList<Point>();
		
		int dx = Math.abs(x1 - x0);
		int dy = Math.abs(y1 - y0);
		
		int sx;
		if (x0 < x1) {
			sx = 1;
		} else {
			sx = -1;
		}
		int sy;
		if (y0 < y1) {
			sy = 1;
		} else {
			sy = -1;
		}
		
		int err = dx - dy;
		
		int xn = x0;
		int yn = y0;
		
		while (true) {
			points.add(new Point(x0, y0));
			
			if (xn == x1 && yn == y1) {
				break;
			}
			
			int e2 = err * 2;
			if (e2 > -dx) {
				err -= dy;
				xn += sx;
			}
			if (e2 < dx) {
				err += dx;
				yn += sy;
			}
		}
	}

	@Override
	public final Iterator<Point> iterator() {
		return points.iterator();
	}
}
