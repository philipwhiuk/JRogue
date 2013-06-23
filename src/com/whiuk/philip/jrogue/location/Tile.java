package com.whiuk.philip.jrogue.location;

import java.awt.Graphics2D;

import com.whiuk.philip.jrogue.Renderable;
import com.whiuk.philip.jrogue.creatures.Creature;
import com.whiuk.philip.jrogue.objects.GameObject;
import com.whiuk.philip.jrogue.objects.ObjectType;
import com.whiuk.philip.jrogue.resources.Resource;



/**
 * A single point in a {@link Location}.
 * @author Philip Whitehouse
 *
 */
public class Tile implements Renderable {
	/**
	 * 
	 */
	private volatile GameObject object;
	/**
	 * 
	 */
	private volatile Creature creature;
	/**
	 * 
	 */
	private final int x;
	/**
	 * 
	 */
	private final int y;
	/**
	 * 
	 */
	private TileType type;
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Tile(final TileType t, final int xPos, final int yPos) {
		this.type = t;
		this.x = xPos;
		this.y = yPos;
	}
	/**
	 * 
	 * @param tile
	 */
	public Tile(final Tile tile) {
		this.type = tile.type;
		this.x = tile.x;
		this.y = tile.y;
		this.object = tile.object;
		this.creature = tile.creature;
	}

	/**
	 * @return true if the tile contains an object
	 */
	public final boolean hasGameObject() {
		return (object != null);
	}

	/**
	 * @return true if the tile contains a character
	 */
	public final boolean hasCreature() {
		return (creature != null);
	}
	/**
	 * @param o the object to place on this tile.
	 */
	public final void setGameObject(final GameObject o) {
		object = o;
	}
	/**
	 * @param c character
	 */
	public final void setCreature(final Creature c) {
		this.creature = c;
	}
	
	/**
	 * @return true if the tile contains the player
	 */
	final boolean hasPlayer() {
		return creature != null
				&& creature.getType().equals(ObjectType.PLAYER);
	}
	
	/**
	 * @return true if the tile contains a monster
	 */
	final boolean hasMonster() {
		return creature != null
				&& creature.getType().equals(ObjectType.MONSTER);
	}
	
	/**
	 * @return true if the tile is currently lit
	 */
	final boolean isLit() {
		//TODO: Lighting
		return true;
	}
	@Override
	public final void render(final Graphics2D g,
			final int width, final int height) {
		if (!type.canEnter()) {
			type.render(g, width, height);
		} else if (hasPlayer()) {
			System.out.println("Has player");
			creature.render(g, width, height);
		} else if (isLit()) {
			if (object != null) {
				object.render(g, width, height);
			} else {
				type.render(g, width, height);
			}
		}
	}
	/**
	 * 
	 * @param c Creature
	 */
	public final void removeCreature(final Creature c) {
		if (this.creature == c) {
			this.creature = null;
		} else {
			throw new IllegalStateException();
		}
	}
	/**
	 * 
	 * @return
	 */
	public final int getX() {
		return x;
	}
	/**
	 * 
	 * @return
	 */
	public final int getY() {
		return y;
	}
	/**
	 * 
	 * @return
	 */
	//TODO: Multiple objects on a tile.
	public final GameObject getGameObject() {
		return object;
	}

	/**
	 * 
	 * @return
	 */
	public final TileType getType() {
		return type;
	}
	/**
	 * 
	 * @param floor
	 */
	public final void setType(final TileType t) {
		type = t;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Creature getCreature() {
		return creature;
	}
	/**
	 * 
	 * @param resource
	 */
	public void removeResource(Resource resource) {
		// TODO Auto-generated method stub
		
	}
}