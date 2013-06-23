package com.whiuk.philip.jrogue;

import java.awt.Graphics2D;

/**
 * Indicates the object can be rendered.
 * @author Philip Whitehouse
 *
 */
public interface Renderable {
	/**
	 * Render the object.
	 * @param g 2D Graphics object to render with
	 * @param x X-extent
	 * @param y Y-extent
	 */
	void render(Graphics2D g, int x, int y);
}