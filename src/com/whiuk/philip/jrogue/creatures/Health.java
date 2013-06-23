package com.whiuk.philip.jrogue.creatures;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents the physical well-being of a {@link Creature}.
 * Provides support for health-points and injuries.
 * @author Philip Whitehouse
 *
 */
public class Health {
	/**
	 * 
	 * @author Philip Whitehouse
	 *
	 */
	public enum Area {
		/**
		 * 
		 */
		HEAD,
		/**
		 * 
		 */
		BODY,
		/**
		 * 
		 */
		LEGS,
		/**
		 * 
		 */
		HANDS,
		/**
		 * 
		 */
		ARMS
	};
	/**
	 * 
	 * @author Philip Whitehouse
	 *
	 */
	public enum Severity {
		/**
		 * 
		 */
		MINOR,
		/**
		 * 
		 */
		MAJOR,
		/**
		 * 
		 */
		SEVERE
	};
	/**
	 * 
	 * @author Philip Whitehouse
	 *
	 */
	public enum DamageType {
		/**
		 * 
		 */
		CUT,
		/**
		 * 
		 */
		BRUISED
	};
	/**
	 * 
	 * @author Philip Whitehouse
	 *
	 */
	public enum Status {
		/**
		 * 
		 */
		BLEEDING,
		/**
		 * 
		 */
		BANDAGED,
		/**
		 * 
		 */
		STABLE
	};
	/**
	 * 
	 * @author Philip Whitehouse
	 *
	 */
	public enum HealthStatus {
		/**
		 * 
		 */
		HEALED,
		/**
		 * 
		 */
		INJURED,
		/**
		 * 
		 */
		DEAD
	};
	
	/**
	 * 
	 * @author Philip Whitehouse
	 *
	 */
	private static class Injury {
		/**
		 * 
		 */
		private Severity severity;
		/**
		 * 
		 */
		private DamageType type;
		/**
		 * 
		 */
		private Status status;
		/**
		 * 
		 */
		private boolean isPoisoned;
		/**
		 * 
		 */
		private boolean isBurnt;
		/**
		 * 
		 */
		private int turns = 0;
		
		/**
		 * 
		 * @param s Severity?
		 * @param t Type?
		 * @param s2 Status?
		 * @param p Poisoned?
		 * @param b Burned?
		 */
		Injury(final Severity s, final DamageType t, final Status s2,
				final boolean p, final boolean b) {
			this.severity = s;
			this.type = t;
			this.status = s2;
			this.isPoisoned = p;
			this.isBurnt = b;
		}
		
		/**
		 * 
		 * @return Status
		 */
		public HealthStatus update() {
			if (severity.equals(Severity.SEVERE) 
					&& (status.equals(Status.BLEEDING) 
					|| isPoisoned)) {
				return HealthStatus.DEAD;
			}
			if (severity.equals(Severity.MINOR) 
					&& (status.equals(Status.STABLE) 
							|| status.equals(Status.BANDAGED))
					&& turns-- <= 0
					&& !isPoisoned
					&& !isBurnt) {
				return HealthStatus.HEALED;
			}
			//TODO: Injury change over time
			return HealthStatus.INJURED;
		}
		
		/**
		 * 
		 * @return true if the injury can be improved by bandaging.
		 */
		public boolean canBandage() {
			if(type.equals(DamageType.CUT) && status.equals(Status.BLEEDING)) {
				return true;
			}
			return false;
		}
		
		/**
		 * 
		 * @return true if the injury can be improved by curing poison
		 */
		public boolean canCurePoison() {
			return isPoisoned;
		}
		
		/**
		 * 
		 * @return true if the injury can be improved by curing burns
		 */
		public boolean canCureBurn() {
			return isBurnt;
		}
	}
	
	/**
	 * 
	 */
	private Map<Area, Injury> injuries;
	/**
	 * 
	 */
	private int currentHp;
	/**
	 * 
	 */
	private int maxHp;
	
	/**
	 * 
	 * @param i
	 */
	public Health(final int i) {
		maxHp = i;
		currentHp = i;
		injuries = new HashMap<Area,Injury>();
		
	}

	/**
	 * 
	 * @param a Area
	 * @return
	 */
	public final boolean canBandage(final Area a) {
		if (injuries.containsKey(a)) {
			return injuries.get(a).canBandage();
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public final boolean canBandage() {
		for (Entry<Area, Injury> injury : injuries.entrySet()) {
			if (injury.getValue().canBandage()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param a Area
	 * @return
	 */
	public final boolean canCureBurn(final Area a) {
		if ( injuries.containsKey(a)) {
			return injuries.get(a).canCureBurn();
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public final boolean canCureBurn() {
		for (Entry<Area, Injury> injury : injuries.entrySet()) {
			if (injury.getValue().canCureBurn()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param a Area
	 * @return
	 */
	public final boolean canCurePoison(final Area a) {
		if ( injuries.containsKey(a)) {
			return injuries.get(a).canCurePoison();
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public final boolean canCurePoison() {
		for (Entry<Area, Injury> injury : injuries.entrySet()) {
			if (injury.getValue().canCurePoison()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 */
	public final void update() {
		for (Entry<Area, Injury> injury : injuries.entrySet()) {
			if (injury.getValue().update().equals(HealthStatus.HEALED)) {
				injuries.remove(injury.getKey());
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public final int getHp() {
		// TODO Auto-generated method stub
		return currentHp;
	}

	/**
	 * 
	 * @return
	 */
	public final int getMaxHp() {
		// TODO Auto-generated method stub
		return maxHp;
	}
}
