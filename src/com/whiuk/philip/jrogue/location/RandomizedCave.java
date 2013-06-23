package com.whiuk.philip.jrogue.location;

/**
 * A {@Cave} system whose levels changed on entering and exiting.
 * @author Philip Whitehouse
 *
 */
public class RandomizedCave extends Cave {
	/**
	 * 
	 * @param w
	 */
	RandomizedCave(final World w) {
		super(w);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public final CaveLevel getLevelBelow(final CaveLevel f) {
		int newLvl = f.getLevel() + 1;
		return CaveLevelBuilder.generateRandomFloor(this, newLvl);
	}
	@Override
	public final CaveLevel getLevelAbove(final CaveLevel f) {
		int newLvl = f.getLevel() - 1;
		return CaveLevelBuilder.generateRandomFloor(this, newLvl);
	}
	
	@Override
	public final CaveLevel getLevel(final int l) {
		return CaveLevelBuilder.generateRandomFloor(this, l);
	}
}
