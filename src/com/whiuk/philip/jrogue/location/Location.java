package com.whiuk.philip.jrogue.location;

import java.awt.Graphics2D;

import com.whiuk.philip.jrogue.Renderable;
import com.whiuk.philip.jrogue.creatures.Creature;
import com.whiuk.philip.jrogue.creatures.CreatureFactory;
import com.whiuk.philip.jrogue.objects.GameObject;
import com.whiuk.philip.jrogue.objects.ItemFactory;
import com.whiuk.philip.jrogue.objects.Updateable;

public interface Location extends Renderable, Updateable {
	/**
	 * 
	 * @return
	 */
	Area getArea();
	/**
	 * 
	 * @param c Creature
	 */
	void addCreature(final Creature c);
	/**
	 * 
	 * @param c Creature
	 */
	void removeCreature(final Creature c);
	/**
	 * 
	 * @return
	 */
	ItemFactory getItemFactory();

	/**
	 * 
	 * @param tile
	 * @param mx
	 * @param my
	 * @return
	 */
	Tile getTileFromTile(final Tile tile, final int mx, final int my);

	/**
	 * 
	 * @param g
	 * @param w
	 * @param h
	 * @param c Creature
	 */
	void renderCentredOnCreature(final Graphics2D g,
			final int w, final int h, final Creature c);
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	Tile getTile(final int x, final int y);
	
	/**
	 * 
	 * @param o
	 */
	void addAtEmptyLocation(final GameObject o);
	
	/**
	 * 
	 * @return
	 */
	int getHeight();
	
	/**
	 * 
	 * @return
	 */
	int getWidth();
	
	/**
	 * 
	 * @return
	 */
	CreatureFactory getCreatureFactory();
	
	/**
	 * 
	 * @param d
	 * @param tile
	 * @return
	 */
	Tile getTileInDirectionFromTile(final Direction d, final Tile tile);
	/**
	 * 
	 * @param cf
	 */
	void setCreatureFactory(CreatureFactory cf);
}
