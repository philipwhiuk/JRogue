package com.whiuk.philip.jrogue.location;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.whiuk.philip.jrogue.creatures.Creature;
import com.whiuk.philip.jrogue.creatures.CreatureFactory;
import com.whiuk.philip.jrogue.objects.GameObject;
import com.whiuk.philip.jrogue.objects.ItemFactory;



/**
 * A {@link Location} within a cave that has links to those above and below.
 * @author Philip Whitehouse
 *
 */
public final class CaveLevel implements Location {
	/**
	 *
	 */
	private static final int TILE_SIZE = 10;
	/**
	 * 
	 */
	private static final Logger LOGGER =
			Logger.getLogger(CaveLevel.class.getName());
	/**
	 * Floor.
	 * tiles[x][y];
	 * 
	 * tiles[x] = column
	 */
	private final Tile[][] tiles;
	/**
	 * 
	 */
	private final Tile stairsUpTile;
	/**
	 * 
	 */
	private final Tile stairsDownTile;
	/**
	 * 
	 */
	private int level;
	/**
	 * 
	 */
	private int width;
	/**
	 * 
	 */
	private int height;
	/**
	 * 
	 */
	private CreatureFactory creatureFactory;
	/**
	 * 
	 */
	private List<Creature> creatures;
	/**
	 * 
	 */
	private Area area;
	/**
	 * 
	 */
	private ItemFactory itemFactory;
	
	/**
	 * 
	 * @param a Area
	 * @param l Floor level
	 * @param t Tile array
	 * @param up
	 * @param down
	 */
	CaveLevel(final Area a, final int l, final Tile[][] t,
			final Tile up, final Tile down) {
		this.area = a;
		this.level = l;
		this.tiles = t;
		this.width = tiles.length;
		this.height = tiles[0].length;
		this.stairsUpTile = up;
		this.stairsDownTile = down;
		this.itemFactory = new ItemFactory(this);
		this.creatureFactory = new CreatureFactory(this);
		this.creatures = new ArrayList<Creature>();
	}

	@Override
	public Tile getTileInDirectionFromTile(
			final Direction d, final Tile tile) {
		LOGGER.info("X:" + tile.getX() + ", Y:" + tile.getY());
		switch (d) {
			case NORTH:
				if (tile.getY() <= 0) {
					return tiles[tile.getX()][tile.getY()];
				}
				return tiles[tile.getX()][tile.getY() - 1];
			case EAST:
				if (tile.getX() >= tiles.length - 1) {
					return tiles[tile.getX()][tile.getY()];
				}
				return tiles[tile.getX() + 1][tile.getY()];
			case WEST:
				if (tile.getX() <= 0) {
					return tiles[tile.getX()][tile.getY()];
				}
				return tiles[tile.getX() - 1][tile.getY()];
			case SOUTH:
				if (tile.getY() >= tiles[tile.getX()].length - 1) {
					return tiles[tile.getX()][tile.getY()];
				}
				return tiles[tile.getX()][tile.getY() + 1];
			default:
				return null;
		}
	}

	@Override
	public void render(final Graphics2D g, final int w, final int h) {
		LOGGER.info("Rendering floor");
		int x2 = 0;
		for (Tile[] column : tiles) {
			if (x2 + TILE_SIZE <= w) {
				int y2 = 0;
				for (Tile tile: column) {
					if (y2 + TILE_SIZE <= h) {
						tile.render(g, TILE_SIZE, TILE_SIZE);
					}
					y2 += TILE_SIZE;
					g.translate(0, TILE_SIZE);
				}
				g.translate(0, -(column.length * TILE_SIZE));
			}
			x2 += TILE_SIZE;
			g.translate(TILE_SIZE, 0);
		}
		g.translate(-(tiles.length * TILE_SIZE), 0);
	}

	@Override
	public void renderCentredOnCreature(final Graphics2D g,
			final int w, final int h, final Creature c) {
		if (!creatures.contains(c)) {
			LOGGER.warning(
					"Attempted to render centred on a creature not on floor");
			render(g, w, h);
			return;
		} else {
			int x = c.getTile().getX();
			int y = c.getTile().getY();
			
			int tx = (w / TILE_SIZE);
			int ty = (h / TILE_SIZE);
			
			int sx = 0, fx = width;
			int sy = 0, fy = height;
			
			if (width >= tx) {
				sx = x - (tx / 2);
				if (sx < 0) {
					sx = 0;
					fx = sx + tx;
				} else {
					fx = x + (tx / 2);
				}
				if (fx >= width) {
					fx = width;
				}
			}
			
			if (height >= ty) {
				sy = y - (ty / 2);
				if (sy < 0) {
					sy = 0;
					fy = ty;
				} else {
					fy = y + ty / 2;
				}
				if (fy >= height) {
					fy = height;
				}
			}
			System.out.println("W: " + w + ", H: " + h);
			System.out.println("X: " + x + ", Y: " + y);
			System.out.println("SX: " + sx + ", SY: " + sy);
			System.out.println("FX: " + fx + ", FY: " + fy);
			
			for (int x2 = sx; x2 < fx; x2++) {
				for (int y2 = sy; y2 < fy; y2++) {					
					tiles[x2][y2].render(g, TILE_SIZE, TILE_SIZE);
					g.translate(0, TILE_SIZE);
				}
				g.translate(0, -((fy - sy) * TILE_SIZE));
				g.translate(TILE_SIZE, 0);
			}
			g.translate(-((fx - sx) * TILE_SIZE), 0);
		}
	}
	/**
	 * 
	 * @return level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * 
	 * @return tile with stairs leading up
	 */
	public Tile getStairsUpTile() {
		return stairsUpTile;
	}
	/**
	 * 
	 * @return tile with stairs leading down
	 */
	public Tile getStairsDownTile() {
		return stairsDownTile;
	}
	/**
	 * 
	 * @param x X
	 * @param y Y
	 * @return tile at given coordinates.
	 */
	public Tile getTile(final int x, final int y) {
		return tiles[x][y];
	}
	/**
	 * 
	 * @return
	 */
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}
	/**
	 * 
	 * @return
	 */
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}
	/**
	 * 
	 * @return
	 */
	public CreatureFactory getCreatureFactory() {
		// TODO Auto-generated method stub
		return creatureFactory;
	}
	/**
	 * 
	 * @param o
	 */
	public void addAtEmptyLocation(final GameObject o) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update() {
	    for (Creature creature : creatures) {
	        creature.update();
	    }
	}
	@Override
	public Area getArea() {
		return area;
	}
	/**
	 * 
	 * @param c Creature
	 */
	public void addCreature(final Creature c) {
		creatures.add(c);
	}
	
	@Override
	public void removeCreature(final Creature c) {
		creatures.remove(c);
	}
	@Override
	public ItemFactory getItemFactory() {
		return itemFactory;
	}
	@Override
	public Tile getTileFromTile(final Tile tile, final int mx, final int my) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreatureFactory(CreatureFactory cf) {
		this.creatureFactory = cf;
	}
}