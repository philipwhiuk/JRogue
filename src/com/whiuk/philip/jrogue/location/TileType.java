package com.whiuk.philip.jrogue.location;

import java.awt.Color;
import java.awt.Graphics2D;

import com.whiuk.philip.jrogue.GraphicsUtil;

/**
 * The basics of a {@link Tile}.
 * @author Philip Whitehouse
 *
 */
public enum TileType {
	/**
	 * 
	 */
	CAVE_FLOOR(GraphicsUtil.BLACK, true),
	/**
	 * 
	 */
	CAVE_WALL(GraphicsUtil.BROWN, false),
	/**
	 * 
	 */
	UNKNOWN(GraphicsUtil.BLACK, false);
	/**
	 * 
	 */
	private final Color color;
	/**
	 * 
	 */
	private final boolean canEnter;
	/**
	 * 
	 * @param c Color
	 * @param enter Can enter?
	 */
	TileType(final Color c, final boolean enter) {
		this.color = c;
		this.canEnter = enter;
	}
	/**
	 * @return the canEnter
	 */
	public boolean canEnter() {
		return canEnter;
	}
	/**
	 * 
	 * @param g
	 * @param width
	 * @param height
	 */
	public void render(final Graphics2D g, final int width, final int height) {
		GraphicsUtil.drawObject(g, color, width, height);
	}
	
}