package com.whiuk.philip.jrogue.creatures;

import java.util.Map;
/**
 * Provides support for a craft or discipline that can be learned in the game.
 * @author Philip Whitehouse
 *
 */
public class Skills {
	/**
	 * 
	 * @author Philip Whitehouse
	 *
	 */
	private static class Data {
		private int max;
		private int current;
		private int currentXP;

		public Data(final int i) {
			max = i;
			current = i;
			currentXP = Level.getXPforLevel(i);
		}

		/**
		 * 
		 * @return
		 */
		public final int max() {
			return max;
		}

		/**
		 * 
		 * @return
		 */
		public final int current() {
			return current;
		}

		/**
		 * 
		 * @param exp Experience to add/remove.
		 * @return
		 */
		public final void modifyExperience(final int exp) {
		}

		/**
		 * 
		 * @param l
		 * @return
		 */
		public final void modifyCurrent(final int l) {
			// TODO Auto-generated method stub
		}

	}

	/**
	 * 
	 * @author Philip Whitehouse
	 *
	 */
	public enum Skill {
		/**
		 * 
		 */
		ATTACK("Attack"),
		/**
		 * 
		 */
		DEFENCE("Defence"),
		/**
		 * 
		 */
		MANA("Mana");
		
		/**
		 * 
		 */
		private String name;

		/**
		 * 
		 * @param s
		 */
		Skill(final String s) {
			name = s;
		}
	}

	/**
	 * 
	 */
	private Map<Skill, Data> stats;
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public final int getMaxStat(final Skill s) {
		return stats.get(s).max();
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public final int getCurrentStat(final Skill s) {
		return stats.get(s).current();
	}
	
	/**
	 * 
	 * @param s
	 * @param exp
	 * @return
	 */
	public final void modifyExperience(final Skill s, final int exp) {
		stats.get(s).modifyExperience(exp);
	}
	
	/**
	 * 
	 * @param s
	 * @param l
	 * @return
	 */
	public final void modifyCurrent(final Skill s, final int l) {
		stats.get(s).modifyCurrent(l);
	}

	public final void setSkill(final Skill s, final int i) {
		stats.put(s, new Data(i));
	}
}
