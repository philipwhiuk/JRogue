package com.whiuk.philip.jrogue.creatures;

import com.whiuk.philip.jrogue.location.Tile;

/**
 * The artificial intelligence governing human-esque creatures. 
 * @author Philip Whitehouse
 *
 */
public class HumanoidAi extends CreatureAi {
	/**
	 * 
	 */
	//TODO: Targeting AI
	protected Creature target;
	/**
	 * 
	 * @param creature
	 * @param player
	 */
	public HumanoidAi(final Creature creature) {
        super(creature);
	}
	
	//Rationale Overridden in {@link IntelligentHumanoidAi}
	//CSIGNORE: DesignForExtension
	@Override
	public void onUpdate() {
		if (Math.random() < 0.2) {
			return;
		}
		if (creature.canSee(getEnemy())) {
			hunt(target);
		} else {
			wander();
		}
    }

	private Tile getEnemy() {
		// TODO Auto-generated method stub
		return null;
	}
}
