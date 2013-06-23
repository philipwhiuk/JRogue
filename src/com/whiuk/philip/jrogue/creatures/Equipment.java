package com.whiuk.philip.jrogue.creatures;

import java.util.HashMap;
import java.util.Map;

import com.whiuk.philip.jrogue.objects.Item;

/**
 * The items a {@link Creature} is currently using.
 * @author Philip Whitehouse
 *
 */
public class Equipment {
	/**
	 * 
	 */
	private Map<Slot, Item> items;

	/**
	 * 
	 */
	public Equipment() {
		items = new HashMap<Slot, Item>();
	}
	
	public final Item getItem(final Slot s) {
		return items.get(s);
	}

	public final boolean hasRangedWeapon() {
		// TODO Auto-generated method stub
		return false;
	}

	public final int getRangedAttackValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public final boolean hasThrownWeapon() {
		// TODO Auto-generated method stub
		return false;
	}

	public final int getThrownAttackValue() {
		// TODO Auto-generated method stub
		return 0;
	}
}
