package com.whiuk.philip.jrogue.objects;

import com.whiuk.philip.jrogue.creatures.Creature;

/**
 * 
 * @author Philip Whitehouse
 *
 */
public class Effect {
	/**
	 * 
	 */
	protected int duration;
	/**
	 * 
	 * @param d
	 */
	public Effect(final int d) {
		this.duration = d;
	}
	/**
	 * 
	 * @param other
	 */
	public Effect(final Effect other) {
		this.duration = other.duration; 
	}
	/**
	 * 
	 * @return
	 */
	public final boolean isDone() {
		return duration < 1;
	}

	/**
	 * 
	 * @param creature
	 */
	public final void update(final Creature creature) {
		duration--;
		onUpdate(creature);
	}
	
	/**
	 * 
	 * @param creature
	 */
	public void onUpdate(final Creature creature) {
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 * @param creature
	 */
	public final void start(final Creature creature) {
		onStart(creature);
		
	}
	
	/**
	 * 
	 * @param creature
	 */
	public void onStart(final Creature creature) {
	}

	/**
	 * 
	 * @param creature
	 */
	public final void end(final Creature creature) {
		onEnd(creature);
	}

	/**
	 * 
	 * @param creature
	 */
	public void onEnd(final Creature creature) {
		// TODO Auto-generated method stub
		
	}
}
