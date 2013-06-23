package com.whiuk.philip.jrogue.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.whiuk.philip.jrogue.GraphicsUtil;
import com.whiuk.philip.jrogue.Renderable;


/**
 * 
 * @author Philip Whitehouse
 *
 */
public enum ObjectType implements Renderable {
	/**
	 * 
	 */
	MONSTER(Color.red),
	/**
	 * 
	 */
	PLAYER(Color.cyan),
	/**
	 * 
	 */
	GOLD(Color.yellow),
	/**
	 * 
	 */
	ITEM(Color.green),
	/**
	 * 
	 */
	DOOR(Color.orange),
	/**
	 * 
	 */
	STAIRS_UP(Color.green),
	/**
	 * 
	 */
	STAIRS_DOWN(Color.green);
	/**
	 * 
	 */
	private final Color color;

	/**
	 * 
	 * @param c Colour
	 */
	ObjectType(final Color c) {
		this.color = c;
	}
	@Override
	public void render(final Graphics2D g, final int x, final int y) {
		GraphicsUtil.drawObject(g, color, x, y);
	}
}