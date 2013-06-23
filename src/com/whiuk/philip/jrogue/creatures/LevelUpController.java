package com.whiuk.philip.jrogue.creatures;

import java.util.ArrayList;
import java.util.List;



/**
 * Provides mechanisms to control levelling.
 * @author Philip Whitehouse
 *
 */
public class LevelUpController {
	/**
	 * 
	 */
	private static final LevelUpOption[] OPTIONS = new LevelUpOption[] {
			new LevelUpOption("Increased hit points") {
				public void invoke(final Creature creature) {
					creature.modifyMaxHp(10);
					creature.modifyHp(10,
							"Died from increased hp level-up bonus?");
					creature.doAction("look a lot healthier");
				}
			}, new LevelUpOption("Increased mana") {
				public void invoke(final Creature creature) {
					creature.modifyMaxMana(5);
					creature.modifyMana(5);
					creature.doAction("look more magical");
				}
			}, new LevelUpOption("Increased attack value") {
				public void invoke(final Creature creature) {
					creature.modifyAttackValue(2);
					creature.doAction("look stronger");
				}
			}, new LevelUpOption("Increased defense value") {
				public void invoke(final Creature creature) {
					creature.modifyDefenseValue(1);
					creature.doAction("look a little tougher");
				}
			}, new LevelUpOption("Increased vision") {
				public void invoke(final Creature creature) {
					creature.modifyVisionRadius(1);
					creature.doAction("look a little more aware");
				}
			}, new LevelUpOption("Increased hp regeneration") {
				public void invoke(final Creature creature) {
					creature.modifyRegenHpPer1000(10);
					creature.doAction("look a little less bruised");
				}
			}, new LevelUpOption("Increased mana regeneration") {
				public void invoke(final Creature creature) {
					creature.modifyRegenManaPer1000(10);
					creature.doAction("look a little less tired");
				}
			}
	};

	/**
	 * 
	 * @param creature
	 */
	public final void autoLevelUp(final Creature creature) {
		OPTIONS[(int) (Math.random() * OPTIONS.length)].invoke(creature);
	}

	/**
	 * 
	 * @return
	 */
	public final List<String> getLevelUpOptions() {
		List<String> names = new ArrayList<String>();
		for (LevelUpOption option : OPTIONS) {
			names.add(option.name());
		}
		return names;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public final LevelUpOption getLevelUpOption(final String name) {
		for (LevelUpOption option : OPTIONS) {
			if (option.name().equals(name)) {
				return option;
			}
		}
		return null;
	}
}
