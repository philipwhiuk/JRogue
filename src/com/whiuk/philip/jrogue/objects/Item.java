package com.whiuk.philip.jrogue.objects;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import com.whiuk.philip.jrogue.creatures.Creature;
import com.whiuk.philip.jrogue.creatures.Slot;


/**
 * 
 * @author Philip Whitehouse
 *
 */
public class Item extends GameObject {
	/**
	 * 
	 */
	private ItemType type;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String unidentifiedName;
	/**
	 * 
	 */
	private String appearance;
	/**
	 * 
	 */
	private int foodValue;
	/**
	 * 
	 */
	private int attackValue;
	/**
	 * 
	 */
	private int defenseValue;
	/**
	 * 
	 */
	private int thrownAttackValue;
	/**
	 * 
	 */
	private int thrownDefenseValue;
	/**
	 * 
	 */
	private int rangedAttackValue;
	/**
	 * 
	 */
	private int rangedDefenseValue;
	/**
	 * 
	 */
	private int magicalAttackValue;
	/**
	 * 
	 */
	private int magicalDefenseValue;
	/**
	 * 
	 */
	private Effect quaffEffect;
	/**
	 * 
	 */
	private Effect useEffect;	
	/**
	 * 
	 */
	private boolean canUse;
	/**
	 * 
	 */
	private boolean hasLimitedUses;
	/**
	 * 
	 */
	private int uses;
	/**
	 * 
	 */
	private List<Spell> writtenSpells;
	/**
	 * 
	 */
	private List<Slot> slots;
	/**
	 * 
	 * @return
	 */
	public final List<Spell> writtenSpells() {
		return writtenSpells;
	}

	/**
	 * 
	 * @param c
	 * @param co
	 * @param string
	 * @param a appearance
	 */
	public Item(final char glyph, final Color co,
			final String string, final String a) {
		super(ObjectType.ITEM);
		writtenSpells = new ArrayList<Spell>();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param player
	 */
	final void use(final Creature creature) {
		if (canUse) {
			useEffect.start(creature);
		}
	}

	/**
	 * 
	 * @param i
	 */
	public final void modifyAttackValue(final int i) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @param i
	 */
	public final void modifyDefenseValue(final int i) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @param effect
	 */
	public final void setQuaffEffect(final Effect effect) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @param effect
	 */
	public final void setUseEffect(final Effect effect) {
		useEffect = effect;
	}
	
	/**
	 * 
	 * @param i
	 */
	public final void modifyFoodValue(final int i) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @param i
	 */
	public final void modifyRangedAttackValue(final int i) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @param i
	 */
	public final void modifyThrownAttackValue(final int i) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * @param spell
	 */
	public final void addWrittenSpell(final Spell spell) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @return
	 */
	public final int rangedAttackValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public final int defenseValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public final int attackValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public final int thrownAttackValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public final boolean improves(final Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	public final boolean canEquipInSlot(final Slot s) {
		return slots.contains(s);
	}
}