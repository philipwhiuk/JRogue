package com.whiuk.philip.jrogue.screens;

import java.awt.Graphics2D;

import com.whiuk.philip.jrogue.GraphicsUtil;
import com.whiuk.philip.jrogue.Renderable;
import com.whiuk.philip.jrogue.location.AreaType;
import com.whiuk.philip.jrogue.location.CaveLevel;

/**
 * 
 * @author Philip Whitehouse
 *
 */
public class PlayerInfo implements Renderable {
	/**
	 * 
	 */
	private static final int MARGIN = 40;
	/**
	 * 
	 */
	private static final int NAME_Y = 50;
	/**
	 * 
	 */
	private static final int RACE_Y = NAME_Y + 30;
	/**
	 * 
	 */
	private static final int FLOOR_Y = RACE_Y + 30;
	/**
	 * 
	 */
	private static final int X_Y = FLOOR_Y + 30;
	/**
	 * 
	 */
	private static final int Y_Y = X_Y + 30;
	/**
	 * 
	 */
	private GameScreen game;
	/**
	 * 
	 * @param g
	 */
	public PlayerInfo(final GameScreen g) {
		game = g;
	}

	@Override
	public final void render(final Graphics2D g, final int w, final int h) {
		GraphicsUtil.drawText(g,
				"Name: " + game.getPlayer().getData().getName(),
				MARGIN, NAME_Y);
		GraphicsUtil.drawText(g,
				"Race: "  + game.getPlayer().getData().getRace(),
				MARGIN, RACE_Y);
		if(game.getPlayer().getArea().getType().equals(AreaType.CAVE)) {
			GraphicsUtil.drawText(g,
					"Cave Level: "
						+ ((CaveLevel) game.getPlayer().getLocation()),
					MARGIN, FLOOR_Y);
		}
		GraphicsUtil.drawText(g,
				"X: " + game.getPlayer().getTile().getX(),
				MARGIN, X_Y);
		GraphicsUtil.drawText(g,
				"Y: " + game.getPlayer().getTile().getY(),
				MARGIN, Y_Y);
	}
	

}
