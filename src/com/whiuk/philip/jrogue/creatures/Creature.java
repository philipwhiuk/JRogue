package com.whiuk.philip.jrogue.creatures;

import java.util.logging.Logger;

import com.whiuk.philip.jrogue.location.Area;
import com.whiuk.philip.jrogue.location.AreaType;
import com.whiuk.philip.jrogue.location.CaveLevel;
import com.whiuk.philip.jrogue.location.Direction;
import com.whiuk.philip.jrogue.location.Location;
import com.whiuk.philip.jrogue.location.Cave;
import com.whiuk.philip.jrogue.location.Tile;
import com.whiuk.philip.jrogue.location.TileType;
import com.whiuk.philip.jrogue.location.World;
import com.whiuk.philip.jrogue.objects.GameObject;
import com.whiuk.philip.jrogue.objects.Item;
import com.whiuk.philip.jrogue.objects.Moveable;
import com.whiuk.philip.jrogue.objects.ObjectType;
import com.whiuk.philip.jrogue.objects.Updateable;

/**
 * The player, a mob, or other character which can interact with the game world.
 * @author Philip Whitehouse
 * 
 */
public abstract class Creature extends GameObject implements Moveable,
		Updateable {
	/**
	 * 
	 */
	private static final Logger LOGGER = Logger.getLogger(Creature.class
			.getName());
	/**
	 * 
	 */
	private CreatureAi ai;
	/**
	 * 
	 */
	private final Area area;
	/**
	 * 
	 */
	private Skills skills;
	/**
	 * 
	 */
	private Location location;
	/**
	 * 
	 */
	private Tile tile;
	/**
	 * 
	 */
	private int visionRadius;
	/**
	 * 
	 */
	private final World world;
	/**
	 * 
	 */
	private int xp;
	/**
	 * 
	 */
	private int level;
	/**
	 * 
	 */
	private Equipment equipment;
	/**
	 * 
	 */
	private Attributes attributes;
	/**
	 * 
	 */
	private Health health;
	/**
	 * 
	 */
	private int mana;
	/**
	 * 
	 */
	private int maxMana;

	/**
	 * 
	 * @param type
	 *            The object type for this character
	 * @param w World
	 * @param a Area
	 * @param f Floor
	 * @param maxHp 
	 * @param a2 
	 * @param s Statistics
	 */
	public Creature(final ObjectType type, final World w, final Area a,
			final Location f, final int maxHp,
			final Attributes a2, final Skills s) {
		super(type);
		world = w;
		area = a;
		location = f;
		health = new Health(maxHp);
		attributes = a2;
		skills = s;
		equipment = new Equipment();
	}

	/**
	 * 
	 */
	public final void ascend() {
		if(area.getType().equals(AreaType.CAVE)) {
			final CaveLevel newLevel =
					((Cave) area).getLevelAbove((CaveLevel) location);
			final Tile newTile = newLevel.getStairsDownTile();
			move(newLevel, newTile);
		} else {
			throw new IllegalStateException(
					"Not expecting ascend in area type:" + area.getType());
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public final boolean canEnter(final int x, final int y) {
		return canEnter(location.getTile(x, y));
	}

	/**
	 * 
	 * @param t
	 * @return
	 */
	public final boolean canEnter(final Tile t) {
		if (t.hasCreature()) {
			return false;
		} else if (t.getType().equals(TileType.CAVE_WALL)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public final boolean canSee(final int x, final int y) {
		return canSee(location.getTile(x, y));
	}

	/**
	 * 
	 * @return
	 */
	public final boolean canSee(final GameObject o) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public final boolean canSee(final Tile t) {
		// TODO Auto-generated method stub
		return false;
	}

	// TODO What's the point of this?
	/**
	 * 
	 */
	public final Creature creature(final int nx, final int ny) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public final void descend() {
		if(area.getType().equals(AreaType.CAVE)) {
			final CaveLevel newLevel = ((Cave) area).getLevelBelow(
					(CaveLevel) location);
			final Tile newTile = newLevel.getStairsUpTile();
			System.out.println(newTile);
			move(newLevel, newTile);
		} else {
			throw new IllegalStateException();
		}
	}

	/**
	 * 
	 * @return
	 */
	public final void doAction(final Item item, final String string) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param message
	 */
	public final void doAction(final String message) {
		// TODO I think this just displays '<<NAME>> string' somewhere
	}

	/**
	 * 
	 * @return
	 */
	public final void equip(final Item randomWeapon) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return location
	 */
	public final Location getLocation() {
		return location;
	}

	/**
	 * 
	 * @return area
	 */
	public final Area getArea() {
		return area;
	}


	/**
	 * 
	 * @return tile
	 */
	public final Tile getTile() {
		return tile;
	}

	/**
	 * Move to a given floor.
	 * 
	 * @param l
	 *            level
	 */
	public final void goToFloor(final int l) {
		if(area.getType().equals(AreaType.CAVE)) {
			final CaveLevel newFloor = ((Cave) area).getLevel(l);
			Tile newTile;
			CaveLevel currentLevel = (CaveLevel) getLocation();
			
			if (currentLevel.getLevel() < l) {
				newTile = newFloor.getStairsUpTile();
			} else if (currentLevel.getLevel() < l) {
				newTile = newFloor.getStairsDownTile();
			} else {
				return;
			}
			move(newFloor, newTile);
		} else {
			throw new IllegalStateException(
					"Unexpected goToFloor() when in area of type:"
							+ area.getType());
		}
	}

	/**
	 * 
	 * @return
	 */
	public final Inventory inventory() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param tile2
	 * @return
	 */
	public final Item item(final Tile tile2) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public final int level() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @param target
	 */
	private void meleeAttack(final Creature target) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param i Value to alter by
	 */
	public final void modifyDetectCreatures(final int i) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param i Value to alter by
	 */
	private void modifyFood(final int i) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param i Value to alter by
	 */
	public final void modifyHp(final int i, final String string) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return
	 */
	public final void modifyMana(final int i) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return
	 */
	public void modifyMaxHp(final int i) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return
	 */
	public void modifyMaxMana(final int i) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return
	 */
	public final void modifyRegenHpPer1000(final int i) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	public void modifyRegenManaPer1000(final int i) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param i
	 *            Value to adjust vision radius by.
	 */
	public final void modifyVisionRadius(final int i) {
		visionRadius += i;

	}

	/**
	 * 
	 * @param i
	 *            Value to adjust xp by.
	 */
	public final void modifyXp(final int i) {
		xp += i;
		if (xp < Level.getXPforLevel(level)) {
			levelDrop();
		} else if (xp < Level.getXPforLevel(level + 1)) {
			levelGain();
		}
	}
	/**
	 * 
	 */
	private void levelDrop() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 */
	private void levelGain() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Handle generic movement calls

	}

	@Override
	public final void move(final Direction d) {
		LOGGER.info("move(Direction)");
		final Tile t = getLocation().getTileInDirectionFromTile(d, getTile());
		if (!move(t)) {
			LOGGER.info("Unable to move");
		}
	}

	/**
	 * 
	 * @param f
	 * @param t
	 * @return
	 */
	public final boolean move(final Location f, final Tile t) {
		LOGGER.info("move(Floor, Tile)");
		if (!canEnter(t)) {
			return false;
		}
		if (getLocation() != null) {
			getLocation().removeCreature(this);
		}
		if (getTile() != null) {
			getTile().removeCreature(this);
		}
		f.addCreature(this);
		t.setCreature(this);
		setLocation(f);
		setTile(t);
		return true;
	}

	@Override
	public final boolean move(final Tile t) {
		LOGGER.info("move(Tile)");
		if (!canEnter(t)) {
			return false;
		}
		if (getTile() != null) {
			getTile().removeCreature(this);
		}
		t.setCreature(this);
		setTile(t);
		return true;
	}
	
	@Override
	public final boolean move(final int x, final int y) {
		LOGGER.info("move(x,y)");
		return move(location.getTile(x, y));
	}

	@Override
	public final void moveBy(final int mx, final int my) {
		if ((mx == 0) && (my == 0)) {
			return;
		}
		final Tile newTile = location.getTileFromTile(tile, mx, my);
		modifyFood(-1);

		if (!newTile.hasCreature()) {
			ai.onEnter(tile.getX() + mx, tile.getY() + my, tile);
		} else {
			meleeAttack(newTile.getCreature());
		}
	}

	@Override
	public final void moveWithoutCheck(final Tile t) {
		if (getTile() != null) {
			getTile().removeCreature(this);
		}
		t.setCreature(this);
		setTile(t);
	}

	/**
	 * 
	 * @return name
	 */
	public final String name() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public final void pickup() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param c Creature
	 */
	public final void rangedWeaponAttack(final Creature c) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param a AI
	 */
	public final void setCreatureAi(final CreatureAi a) {
		this.ai = a;
	}

	/**
	 * 
	 * @param f
	 *            floor
	 */
	public final void setLocation(final Location f) {
		location = f;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public final void setTile(final int x, final int y) {
		tile = location.getTile(x, y);
	}

	/**
	 * 
	 * @param t
	 *            tile
	 */
	public final void setTile(final Tile t) {
		tile = t;
	}

	/**
	 * 
	 * @return
	 */
	public final void summon(final Creature bat) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param tile2
	 */
	public final void throwItem(final Tile tile2) {
		// TODO Auto-generated method stub

	}

	@Override
	public final void update() {
		ai.onUpdate();
	}

	/**
	 * 
	 * @return
	 */
	public final int visionRadius() {
		return visionRadius;
	}

	public final Equipment getEquipment() {
		return equipment;
	}

	public final void equip(final Item item, final Slot s) {
		// TODO Auto-generated method stub
		
	}

	public final int hp() {
		return health.getHp();
	}

	public final int maxHp() {
		return health.getMaxHp();
	}

	public final int mana() {
		// TODO Auto-generated method stub
		return mana;
	}

	public final int maxMana() {
		// TODO Auto-generated method stub
		return maxMana;
	}

	public final void modifyAttackValue(final int i) {
		// TODO Auto-generated method stub
		
	}

	public final void modifyDefenseValue(final int i) {
		// TODO Auto-generated method stub
		
	}
}