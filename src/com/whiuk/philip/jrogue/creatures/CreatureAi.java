package com.whiuk.philip.jrogue.creatures;

import java.util.List;

import com.whiuk.philip.jrogue.JRogue;
import com.whiuk.philip.jrogue.ai.Agent;
import com.whiuk.philip.jrogue.ai.Behaviour;
import com.whiuk.philip.jrogue.ai.Goal;
import com.whiuk.philip.jrogue.location.Line;
import com.whiuk.philip.jrogue.location.Path;
import com.whiuk.philip.jrogue.location.Point;
import com.whiuk.philip.jrogue.location.Tile;
import com.whiuk.philip.jrogue.location.TileType;
import com.whiuk.philip.jrogue.objects.Item;

/**
 * The base artificial intelligence for all {@link Creature}s
 * including the player.
 * @author Philip Whitehouse
 *
 */
public class CreatureAi implements Agent {
	/**
	 * 
	 */
	private static final int WANDER_DISTANCE = 3;
	/**
	 * 
	 */
    protected Creature creature;
    /**
     * 
     * @param c Creature
     */
    public CreatureAi(final Creature c) {
        this.creature = c;
        this.creature.setCreatureAi(this);
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
     * @param x
     * @param y
     * @param tile
     */
    public void onEnter(final int x, final int y, final Tile tile) {
    	
    }
    
    /**
     * 
     */
	public void onUpdate() {
	}
	
	/**
	 * 
	 * @param message
	 */
	public void onNotify(final String message) {
	}

	/**
	 * 
	 * @param wx
	 * @param wy
	 * @return
	 */
	public final boolean canSee(final int wx, final int wy) {
		if ((creature.getTile().getX() - wx) * (creature.getTile().getX() - wx)
				+ (creature.getTile().getY() - wy)
					* (creature.getTile().getY() - wy)
				> creature.visionRadius() * creature.visionRadius()) {
			return false;
		}
		
		for (Point p : new Line(creature.getTile().getX(),
				creature.getTile().getY(), wx, wy)) {
			//TODO: Re-work
//			if (creature.realTile(p.x, p.y).isGround()
//					|| p.x == wx && p.y == wy) {
//				continue;
//			}
			
			return false;
		}
		
		return true;
	}
	/**
	 * 
	 * @param creature
	 */
	public final void hunt(final Creature target) {
		List<Point> points = new Path(creature, target.getTile()).points();
		
		int mx = points.get(0).x - creature.getTile().getX();
		int my = points.get(0).y - creature.getTile().getY();
		
		creature.moveBy(mx, my);
	}
	/**
	 * 
	 */
	public final void wander() {

	}

	/**
	 * 
	 */
	public final void onGainLevel() {
		new LevelUpController().autoLevelUp(creature);
	}

	/**
	 * 
	 * @param wx
	 * @param wy
	 * @return
	 */
	public final TileType rememberedTile(final int wx, final int wy) {
		return TileType.UNKNOWN;
	}

	/**
	 * 
	 * @param other
	 * @return
	 */
	protected final boolean canThrowAt(final Creature other) {
		return creature.canSee(other.getTile().getX(), other.getTile().getY())
			&& creature.getEquipment().hasThrownWeapon()
			&& creature.getEquipment().getThrownAttackValue() > 0;
	}

	/**
	 * 
	 * @param other
	 * @return
	 */
	protected final boolean canRangedWeaponAttack(final Creature other) {
		return creature.getEquipment().hasRangedWeapon()
		    && creature.getEquipment().getRangedAttackValue() > 0
		    && creature.canSee(other.getTile());
	}

	/**
	 * 
	 * @return
	 */
	protected final boolean canPickup() {
		return creature.item(creature.getTile()) != null
			&& !creature.inventory().isFull();
	}

	/**
	 * 
	 * @return
	 */
	protected final boolean canUseBetterEquipment() {
		for (Item item : creature.inventory().getItems()) {
			if (item == null) {
				continue;
			}
			for (Slot s : Slot.values()) {
				if (item.canEquipInSlot(s)) {
					if (creature.getEquipment().getItem(s) == null) {
						return true;
					} else if (item.improves(
							creature.getEquipment().getItem(s))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 */
	protected final void useBetterEquipment() {
		//First pass, fill slots
		for (Item item : creature.inventory().getItems()) {
			if (item == null) {
				continue;
			}
			for (Slot s : Slot.values()) {
				if (item.canEquipInSlot(s)) {
					if (creature.getEquipment().getItem(s) == null) {
						creature.equip(item, s);
					}
				}
			}
		}
		//Second pass, replace worse items
		for (Item item : creature.inventory().getItems()) {
			if (item == null) {
				continue;
			}
			for (Slot s : Slot.values()) {
				if (item.canEquipInSlot(s)) {
					if (item.improves(
							creature.getEquipment().getItem(s))) {
						creature.equip(item, s);
					}
				}
			}
		}
	}
	@Override
	public void addBehaviour(Behaviour b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void plan() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addGoal(Goal g) {
		// TODO Auto-generated method stub
		
	}
}
