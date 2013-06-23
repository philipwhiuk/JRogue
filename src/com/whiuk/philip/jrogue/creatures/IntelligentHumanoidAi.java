package com.whiuk.philip.jrogue.creatures;

/**
 * The artificial intelligence covering humanoid
 * creatures of roughly human intelligence.
 * @author Philip Whitehouse
 *
 */
public class IntelligentHumanoidAi extends HumanoidAi {
	/**
	 * 
	 * @param creature
	 * @param factory
	 */
	public IntelligentHumanoidAi(final Creature creature) {
        super(creature);
	}
	@Override
	public final void onUpdate() {
		if (canUseBetterEquipment()) {
			useBetterEquipment();
		} else if (canRangedWeaponAttack(target)) {
			creature.rangedWeaponAttack(target);
		} else if (canThrowAt(target)) {
			creature.throwItem(target.getTile());
		} else if (creature.canSee(target.getTile())) {
			hunt(target);
		} else if (canPickup()) {
			creature.pickup();
		} else {
			wander();
		}
    }
}
