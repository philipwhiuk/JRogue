package com.whiuk.philip.jrogue.location;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.logging.Logger;

import com.whiuk.philip.jrogue.creatures.CreatureFactory;
import com.whiuk.philip.jrogue.objects.GameObject;
import com.whiuk.philip.jrogue.objects.ObjectType;


/**
 * A factory for constructing randomly generated {@link CaveLevel}s.
 * @author Philip Whitehouse
 *
 */
final class CaveLevelBuilder {
	/**
	 * 
	 */
	private static final Logger LOGGER =
			Logger.getLogger(CaveLevelBuilder.class.getName());
	/**
	 * 
	 */
	private static final int RANDOM_FLOOR_X = 150;
	/**
	 * 
	 */
	private static final int RANDOM_FLOOR_Y = 150;
	private static final int GROUND_FLOOR_WIDTH = 50;
	private static final int GROUND_FLOOR_HEIGHT = 25;
	/**
	 * 
	 */
	private static CaveLevel groundFloor;
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
	private Tile[][] tiles;
	/**
	 * 
	 */
	private Tile stairsUpTile;
	/**
	 * 
	 */
	private Tile stairsDownTile;
	/**
	 * 
	 */
	private int level;
	/**
	 * 
	 */
	private Area area;
	/**
	 * 
	 * @param a Area
	 * @param w
	 * @param h
	 */
	public CaveLevelBuilder(final Area a, final int w, final int h, final int l) {
		this.area = a;
        this.width = w;
        this.height = h;
        this.tiles = new Tile[w][h];
        this.level = l;
    }
	/**
	 * 
	 */
	public CaveLevelBuilder() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @return
	 */
	private CaveLevelBuilder randomizeTiles() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	if (Math.random() < 0.5) {
            		tiles[x][y] = new Tile(TileType.CAVE_FLOOR, x, y);
            	} else {
            		tiles[x][y] = new Tile(TileType.CAVE_WALL, x, y);
            	}
            }
        }
        return this;
    }
	/**
	 * Smooth the floor multiple times.
	 * @param times The number of times to apply the smoothing algorithm
	 * @return FloorBuilder
	 */
	/**
	 * 
	 * @param times
	 * @return
	 */
	private CaveLevelBuilder smooth(final int times) {
        for (int time = 0; time < times; time++) {
        	tiles = smoothTiles(tiles);
        }
        return this;
    }
	/**
	 * Smooth a set of tiles.
	 * @param t
	 * @return
	 */
	private Tile[][] smoothTiles(final Tile[][] t) {
        Tile[][] tiles2 = new Tile[width][height];
        for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles2[x][y] = (Tile) new Tile(t[x][y]);
				tiles2[x][y].setType(processSmoothingTile(t, x, y));
			}
		}
        return tiles2;
	}
	/**
	 * Smooth a tile based on it's neighbours.
	 * @param tiles
	 * @param x
	 * @param y
	 * @return
	 */
	private TileType processSmoothingTile(
			final Tile[][] t, final int x, final int y) {
		int floors = 0;
		int rocks = 0;
		for (int ox = -1; ox < 2; ox++) {
			for (int oy = -1; oy < 2; oy++) {
				if (x + ox < 0 || x + ox >= width || y + oy < 0
						|| y + oy >= height) {
					continue;
				}
				if (t[x + ox][y + oy].getType().equals(
						TileType.CAVE_FLOOR)) {
					floors++;
				} else {
					rocks++;
				}
			}
		}
		if (floors >= rocks) {
			return TileType.CAVE_FLOOR;
		} else {
			return TileType.CAVE_WALL;
		}

	}
	/**
	 * @param a Area
	 * @return floor
	 */
	public static CaveLevel getGroundFloor(final Area a) {
		//TODO GroundFloor;		
		if (groundFloor == null) {
			CaveLevelBuilder groundFloorBuilder = new CaveLevelBuilder();
			int w = GROUND_FLOOR_WIDTH;
			int h = GROUND_FLOOR_HEIGHT;
			groundFloorBuilder.area = a;
			groundFloorBuilder.width = w;
			groundFloorBuilder.height = h;
			groundFloorBuilder.tiles =
					new Tile[w][h];
			for (int x = 0; x < groundFloorBuilder.tiles.length; x++) {
				for (int y = 0; y < groundFloorBuilder.tiles[x].length; y++) {
					groundFloorBuilder.tiles[x][y] =
							new Tile(TileType.CAVE_FLOOR, x, y);
				}
			}
			//BORDER WALLS
			for (Tile t : groundFloorBuilder.tiles[0]) {
				t.setType(TileType.CAVE_WALL);
			}
			for (Tile t : groundFloorBuilder
					.tiles[groundFloorBuilder.tiles.length - 1]) {
				t.setType(TileType.CAVE_WALL);
			}
			int y = 0;
			for (int x = 1; x < groundFloorBuilder.tiles.length; x++) {
				groundFloorBuilder.tiles[x][y].setType(TileType.CAVE_WALL);
			}
			y = groundFloorBuilder.tiles[0].length - 1;
			for (int x = 1; x < groundFloorBuilder.tiles.length; x++) {
				groundFloorBuilder.tiles[x][y].setType(TileType.CAVE_WALL);
			}
			//DOWN
			groundFloorBuilder.tiles[10][10].setGameObject(
					new GameObject(ObjectType.STAIRS_DOWN));
			groundFloorBuilder.stairsDownTile =
					groundFloorBuilder.tiles[10][10];
			groundFloor = groundFloorBuilder.build();			
			CreatureFactory cf = new CreatureFactory(groundFloor);
			groundFloor.setCreatureFactory(cf);
			groundFloor.getCreatureFactory().newGoblin();
		}
		return groundFloor;
	}
	/**
	 * @param a Area
	 * @param level Dungeon level to generate
	 * @return floor
	 */
	public static CaveLevel generateRandomFloor(final Area a, final int level) {
		if (level == 0) {
			return getGroundFloor(a);
		}
		return new CaveLevelBuilder(a, RANDOM_FLOOR_X, RANDOM_FLOOR_Y, level)
			.randomizeTiles()
			.smooth(12).populateFloor().addStairs().build();
	}
	/**
	 * 
	 * @return
	 */
	public CaveLevelBuilder addStairs() {
		do {
			LOGGER.info("Adding stairs");
			if (stairsDownTile != null
					&& stairsDownTile.getGameObject().getType()
					.equals(ObjectType.STAIRS_DOWN)) {
				stairsDownTile.setGameObject(null);
			}
			if (stairsUpTile != null
					&& stairsUpTile.getGameObject().getType()
					.equals(ObjectType.STAIRS_UP)) {
				stairsUpTile.setGameObject(null);
			}
			
			stairsDownTile = addAtEmptyTile(
					new GameObject(ObjectType.STAIRS_DOWN));
			stairsUpTile = addAtEmptyTile(
					new GameObject(ObjectType.STAIRS_UP));
		}
		while(routeCheck(stairsUpTile, stairsDownTile) < 10);
		return this;
	}
	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	//TODO: Re-factor this.
	private int routeCheck(final Tile start, final Tile end) {
		int[][] flood = new int[width][height];
		LOGGER.info("Start - X:" + start.getX() + ", Y:" + start.getY());
		LOGGER.info("End - X:" + end.getX() + ", Y:" + end.getY());
		
		flood[start.getX()][start.getY()] = 1;
		Queue<Tile> toExpand = new ArrayDeque<Tile>();
		toExpand.add(start);
		do {
			LOGGER.info("To Expand: " + toExpand.size());
			Tile t = toExpand.poll();
			if (t.getX() > 0) {
				if (flood[t.getX() - 1][t.getY()] == 0
						&& t.getType().canEnter()) {
					toExpand.add(tiles[t.getX() - 1][t.getY()]);
					flood[t.getX() - 1][t.getY()] =
							flood[t.getX()][t.getY()] + 1;
				}
			}
			if (t.getY() > 0) {
				if (flood[t.getX()][t.getY() - 1] == 0
						&& t.getType().canEnter()) {
					toExpand.add(tiles[t.getX()][t.getY() - 1]);
					flood[t.getX()][t.getY() - 1] =
							flood[t.getX()][t.getY()] + 1;
				}
			}
			if (t.getX() < width - 1) {
				if (flood[t.getX() + 1][t.getY()] == 0
						&& t.getType().canEnter()) {
					toExpand.add(tiles[t.getX() + 1][t.getY()]);
					flood[t.getX() + 1][t.getY()] =
							flood[t.getX()][t.getY()] + 1;
				}
			}
			if (t.getY() < height - 1) {
				if (flood[t.getX()][t.getY() + 1] == 0
						&& t.getType().canEnter()) {
					toExpand.add(tiles[t.getX()][t.getY() + 1]);
					flood[t.getX()][t.getY() + 1] =
							flood[t.getX()][t.getY()] + 1;
				}
			}
		} while(!toExpand.isEmpty() && flood[end.getX()][end.getY()] == 0);
		LOGGER.info("Status:" + flood[end.getX()][end.getY()]);
		return flood[end.getX()][end.getY()];
	}
	/**
	 * 
	 * @param gameObject
	 * @return
	 */
	public Tile addAtEmptyTile(final GameObject object) {
	    int x;
	    int y;
	   
	    do {
	        x = (int) (Math.random() * width);
	        y = (int) (Math.random() * height);
	    } 
	    while (!tiles[x][y].getType().equals(TileType.CAVE_FLOOR)
	    		|| tiles[x][y].hasCreature()
	    		|| tiles[x][y].hasGameObject());
	    tiles[x][y].setGameObject(object);
	    return tiles[x][y];
	}
	/**
	 * 
	 * @param a Area
	 * @return
	 */
	public CaveLevelBuilder setArea(final Area a) {
		area = a;
		return this;
	}
	/**
	 * 
	 * @param l
	 * @return
	 */
	public CaveLevelBuilder populateFloor() {
		// TODO Auto-generated method stub
		return this;
	}
	/**
	 * 
	 * @return
	 */
	public CaveLevel build() {
		// TODO Auto-generated method stub
		return new CaveLevel(area, level, tiles, stairsUpTile, stairsDownTile);
	}
}