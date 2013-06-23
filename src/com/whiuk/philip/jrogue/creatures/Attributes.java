package com.whiuk.philip.jrogue.creatures;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides the vital statistics of a {@link Creature}.
 * @author Philip Whitehouse
 *
 */
public class Attributes {
	public enum Attribute {
		/**
		 * 
		 */
		STRENGTH("Strength", "Str"),
		/**
		 * 
		 */
		DEXTERITY("Dexterity", "Dex"),
		/**
		 * 
		 */
		INTELLIGENCE("Intelligence", "Int"),
		/**
		 * 
		 */
		CONSTITUTION("Constitution", "Con"),
		/**
		 * 
		 */
		WISDOM("Wisdom", "Wis"),
		/**
		 * 
		 */
		CHARISMA("Charisma", "Chr");
		
		/**
		 * 
		 */
		private String longName;
		/**
		 * 
		 */
		private String shortName;
		/**
		 * 
		 * @param l
		 * @param s
		 */
		Attribute(final String l, final String s) {
			longName = l;
			shortName = s;
		}
		/**
		 * 
		 * @return
		 */
		public String getShortName() {
			return shortName;
		}
		/**
		 * 
		 * @return
		 */
		public String toString() {
			return longName;
		}
	}
	
	/**
	 * 
	 */
	private Map<Attribute, Integer> attributes;
	
	/**
	 * 
	 */
	public Attributes() {
		attributes = new HashMap<Attribute, Integer>();
	}
	
	/**
	 * @param a Attribute
	 * @return Value
	 */
	public final Integer getAttributeValue(final Attribute a) {
		return attributes.get(a);
	}
	
}