package com.whiuk.philip.jrogue.screens;

import java.awt.Graphics2D;

import com.whiuk.philip.jrogue.Renderable;

/**
 * 
 * @author Philip Whitehouse
 *
 */
public class Map implements Renderable {
	/**
	 * 
	 */
	private GameScreen game;
	/**
	 * 
	 * @param g
	 */
	public Map(final GameScreen g) {
		game = g;
	}

	@Override
	public final void render(final Graphics2D g, final int w, final int h) {
		game.getPlayer().getLocation()
			.renderCentredOnCreature(g, w, h, game.getPlayer());
	}
	

}
