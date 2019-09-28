package com.whiuk.philip.jrogue.location;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Philip Whitehouse
 *
 */
public final class World {
	/**
	 * 
	 */
	private List<Area> areas;
	/**
	 * 
	 */
	private Location startLocation;
	/**
	 * 
	 */
	//TODO: Different start positions
	private int startX = 20;
	/**
	 * 
	 */
	private int startY = 10;
	/**
	 * 
	 */
	public World() {
		areas = new ArrayList<>();
		Area startArea = new GeneratedCave(this);
		areas.add(startArea);
		startLocation = CaveLevelBuilder.getGroundFloor(startArea);
	}
	/**
	 * 
	 * @return
	 */
	public Location getStartLocation() {
		return startLocation;
	}
	/**
	 * 
	 * @return
	 */
	public int getStartX() {
		return startX;
	}
	/**
	 * 
	 * @return
	 */
	public int getStartY() {
		return startY;
	}
}