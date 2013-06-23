package com.whiuk.philip.jrogue.location;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Philip Whitehouse
 *
 */
public class GeneratedCave extends Cave {
	/**
	 * 
	 */
	private Map<Integer, CaveLevel> floors;
	/**
	 * @param w world
	 */
	public GeneratedCave(final World w) {
		super(w);
		floors = new HashMap<Integer, CaveLevel>();
	}
	
	@Override
	public final CaveLevel getLevelBelow(final CaveLevel f) {
		int newLvl = f.getLevel() + 1;
		if (!floors.containsKey(newLvl)) {
			floors.put(newLvl, CaveLevelBuilder.generateRandomFloor(this, newLvl));
		}
		return floors.get(newLvl);
	}
	@Override
	public final CaveLevel getLevelAbove(final CaveLevel f) {
		int newLvl = f.getLevel() - 1;
		if (!floors.containsKey(newLvl)) {
			floors.put(newLvl, CaveLevelBuilder.generateRandomFloor(this, newLvl));
		}
		return floors.get(newLvl);
	}
	
	@Override
	public final CaveLevel getLevel(final int l) {
		if (!floors.containsKey(l)) {
			floors.put(l, CaveLevelBuilder.generateRandomFloor(this, l));
		}
		return floors.get(l);
	}
	
}
