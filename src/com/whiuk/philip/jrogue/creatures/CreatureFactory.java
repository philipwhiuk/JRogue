package com.whiuk.philip.jrogue.creatures;

import com.whiuk.philip.jrogue.GraphicsUtil;
import com.whiuk.philip.jrogue.location.Location;

/**
 * Provides a method of generating creatures for an environment.
 * @author Philip Whitehouse
 *
 */

public class CreatureFactory {
	/**
	 * 
	 * @author Philip Whitehouse
	 *
	 */
	public static enum GenerationType {
		/**
		 * 
		 */
		POINTS,
		/**
		 * 
		 */
		STANDARD_ROLL
	}
	private static final int GOBLIN_HEALTH = 66;
	/**
	 * 
	 */
    private Location location;
    /**
     * 
     * @param f
     */
	public CreatureFactory(final Location f) {
    	this.location = f;
	}
	/**
	 * 
	 * @return
	 */
	public final Monster newFungus() {
		Monster fungus = new Monster("Fungus", location, GraphicsUtil.GREEN, 1);
	    location.addAtEmptyLocation(fungus);
	    new PlantAi(fungus, this);
	    return fungus;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Creature newBat() {
		Skills s = new Skills();
		s.setSkill(Skills.Skill.DEFENCE,5);
		Creature bat = new Monster("bat", location, GraphicsUtil.YELLOW, 15);
		location.addAtEmptyLocation(bat);
		new FlyingAi(bat);
		return bat;
	}
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	public final Creature newZombie(final Creature player) {
		Creature zombie = new Monster("zombie", location, GraphicsUtil.WHITE, 50);
		location.addAtEmptyLocation(zombie);
		new HumanoidAi(zombie);
		return zombie;
	}

	/**
	 * 
	 * @param depth
	 * @param player
	 * @return
	 */
	public final Creature newGoblin() {
		Creature goblin = new Monster("goblin", location,
				GraphicsUtil.GREEN, GOBLIN_HEALTH);
		new HumanoidAi(goblin);
		goblin.equip(location.getItemFactory().randomWeapon());
		goblin.equip(location.getItemFactory().randomArmor());
		location.addAtEmptyLocation(goblin);
		return goblin;
	}
}
