package com.whiuk.philip.jrogue.player;

import java.util.logging.Logger;

import com.whiuk.philip.jrogue.creatures.Attributes;
import com.whiuk.philip.jrogue.creatures.Class;
import com.whiuk.philip.jrogue.creatures.Creature;
import com.whiuk.philip.jrogue.creatures.Gender;
import com.whiuk.philip.jrogue.creatures.Skills;
import com.whiuk.philip.jrogue.location.Area;
import com.whiuk.philip.jrogue.location.Location;
import com.whiuk.philip.jrogue.location.World;
import com.whiuk.philip.jrogue.objects.ObjectType;

/**
 * Provides core data on the player's character.
 * @author Philip Whitehouse
 *
 */
public class Player extends Creature {
	public static Player newPlayer(final Location location) {
		Player player = new Player(
				location.getArea().getWorld(), location.getArea(), location,
				new PlayerData("Feorin", null,
						Gender.MALE, Race.HUMAN, Class.WARRIOR),
				10, new Attributes(), new Skills());
		new PlayerAi(player);
		
		return player;
	}
	
	
	/**
	 * 
	 */
	private static final Logger LOGGER =
			Logger.getLogger(Player.class.getName());
	/**
	 * 
	 */
	private final PlayerData data;
	/**
	 * 
	 */
	private int level;
	
	/**
	 * 
	 * @param w
	 * @param a Area
	 * @param f Location
	 * @param n
	 * @param g
	 * @param r
	 * @param c
	 * @param s
	 * @param l
	 * @param m
	 * @param a2 Attributes
	 * @param d2
	 */
	public Player(final World w, final Area a, final Location f,
			final PlayerData d,
			final int maxHp, final Attributes a2,
			final Skills s) {
		super(ObjectType.PLAYER, w, a, f, maxHp, a2, s);
		data = d;
	}

	/**
	 * 
	 * @return
	 */
	public final PlayerData getData() {
		return data;
	}
	
}