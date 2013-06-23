package com.whiuk.philip.jrogue.objects;

import java.awt.Graphics2D;

import com.whiuk.philip.jrogue.Renderable;


/**
 * 
 * @author Philip Whitehouse
 *
 */
public class GameObject implements Renderable {
	/**
	 * 
	 */
	private final ObjectType type;
	/**
	 * 
	 * @param t type
	 */
	public GameObject(final ObjectType t) {
		this.type = t;
	}
	@Override
	public final void render(final Graphics2D g, final int x, final int y) {
		type.render(g, x, y);
	}
	/**
	 * 
	 * @return type
	 */
	public final ObjectType getType() {
		return type;
	}
	
}