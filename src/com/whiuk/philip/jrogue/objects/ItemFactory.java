package com.whiuk.philip.jrogue.objects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.whiuk.philip.jrogue.GraphicsUtil;
import com.whiuk.philip.jrogue.JRogue;
import com.whiuk.philip.jrogue.creatures.Creature;
import com.whiuk.philip.jrogue.location.CaveLevel;


/**
 * 
 * @author Philip Whitehouse
 *
 */
public class ItemFactory {
	/**
	 * 
	 */
	private static final int BREAD_FOOD_VALUE = 400;
	/**
	 * 
	 */
	private static final int FRUIT_FOOD_VALUE = 100;
	/**
	 * 
	 */
	private static final int BAGUETTE_FOOD_VALUE = 100;
	/**
	 * 
	 */
	protected static final int INNER_STRENGTH_REGEN = 10;
	/**
	 * 
	 */
	private static final int INNER_STRENGTH_ATTACK_BOOST = 2;
	/**
	 * 
	 */
	private static final int DETECT_CREATURES_DURATION = 75;
	/**
	 * 
	 */
	private static final int INNER_STRENGTH_DEFENSE_BOOST = 2;
	/**
	 * 
	 */
	private static final int SLOW_HEALTH_DURATION = 100;
	/**
	 * 
	 */
    private CaveLevel floor;
    /**
     * 
     */
	private Map<String, Color> potionColors;
	/**
	 * 
	 */
	private List<String> potionAppearances;
    /**
     * 
     * @param f
     */
	public ItemFactory(final CaveLevel f) {
    	this.floor = f;
		setUpPotionAppearances();
	}
	
	/**
	 * 
	 */
	private void setUpPotionAppearances() {
		potionColors = new HashMap<String, Color>();
		potionColors.put("red potion", GraphicsUtil.RED);
		potionColors.put("yellow potion", GraphicsUtil.YELLOW);
		potionColors.put("green potion", GraphicsUtil.GREEN);
		potionColors.put("cyan potion", GraphicsUtil.CYAN);
		potionColors.put("blue potion", GraphicsUtil.BLUE);
		potionColors.put("magenta potion", GraphicsUtil.MAGENTA);
		potionColors.put("dark potion", GraphicsUtil.DARK_GRAY);
		potionColors.put("grey potion", GraphicsUtil.GRAY);
		potionColors.put("light potion", GraphicsUtil.WHITE);

		potionAppearances = new ArrayList<String>(potionColors.keySet());
		Collections.shuffle(potionAppearances);
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newRock() {
		Item rock = new Item(',', GraphicsUtil.DARK_YELLOW, "rock", null);
		rock.modifyThrownAttackValue(5);
		floor.addAtEmptyLocation(rock);
		return rock;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newVictoryItem() {
		Item item = new Item('*', GraphicsUtil.WHITE, "teddy bear", null);
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newBread() {
		Item item = new Item('%', GraphicsUtil.DARK_YELLOW, "bread", null);
		item.modifyFoodValue(BREAD_FOOD_VALUE);
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newFruit() {
		Item item = new Item('%', GraphicsUtil.RED, "apple", null);
		item.modifyFoodValue(FRUIT_FOOD_VALUE);
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newDagger() {
		Item item = new Item(')', GraphicsUtil.LIGHT_GRAY, "dagger", null);
		item.modifyAttackValue(5);
		item.modifyThrownAttackValue(5);
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newSword() {
		Item item = new Item(')', GraphicsUtil.WHITE, "sword", null);
		item.modifyAttackValue(10);
		item.modifyThrownAttackValue(3);
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newStaff() {
		Item item = new Item(')', GraphicsUtil.DARK_YELLOW, "staff", null);
		item.modifyAttackValue(5);
		item.modifyDefenseValue(3);
		item.modifyThrownAttackValue(3);
		floor.addAtEmptyLocation(item);
		return item;
	}

	/**
	 * 
	 * @return
	 */
	public final Item newBow() {
		Item item = new Item(')', GraphicsUtil.DARK_YELLOW, "bow", null);
		item.modifyAttackValue(1);
		item.modifyRangedAttackValue(5);
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newEdibleWeapon() {
		Item item = new Item(')', GraphicsUtil.DARK_YELLOW, "baguette", null);
		item.modifyAttackValue(3);
		item.modifyFoodValue(BAGUETTE_FOOD_VALUE);
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newLightArmor() {
		Item item = new Item('[', GraphicsUtil.GREEN, "tunic", null);
		item.modifyDefenseValue(2);
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newMediumArmor() {
		Item item = new Item('[', GraphicsUtil.WHITE, "chainmail", null);
		item.modifyDefenseValue(4);
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newHeavyArmor() {
		Item item = new Item('[', GraphicsUtil.WHITE, "platemail", null);
		item.modifyDefenseValue(6);
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item randomWeapon() {
		switch (JRogue.RANDOM.nextInt(3)) {
		case 0: return newDagger();
		case 1: return newSword();
		case 2: return newBow();
		default: return newStaff();
		}
	}

	/**
	 * 
	 * @return
	 */
	public final Item randomArmor() {
		switch (JRogue.RANDOM.nextInt(2)) {
		case 0: return newLightArmor();
		case 1: return newMediumArmor();
		default: return newHeavyArmor();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newPotionOfHealth() {
		String appearance = potionAppearances.get(0);
		final Item item = new Item('!', potionColors.get(appearance),
				"health potion", appearance);
		item.setQuaffEffect(new Effect(1) {
			public void onStart(final Creature creature) {
				if (creature.hp() == creature.maxHp()) {
					return;
				}
				
				creature.modifyHp(15, "Killed by a health potion?");
				creature.doAction(item, "look healthier");
			}
		});
		
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newPotionOfMana() {
		String appearance = potionAppearances.get(1);
		final Item item = new Item('!', potionColors.get(appearance),
				"mana potion", appearance);
		item.setQuaffEffect(new Effect(1) {
			public void onStart(final Creature creature) {
				if (creature.mana() == creature.maxMana()) {
					return;
				}
				
				creature.modifyMana(10);
				creature.doAction(item, "look restored");
			}
		});
		
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newPotionOfSlowHealth() {
		String appearance = potionAppearances.get(2);
		final Item item = new Item('!', potionColors.get(appearance),
				"slow health potion", appearance);
		item.setQuaffEffect(new Effect(SLOW_HEALTH_DURATION) {
			public void onStart(final Creature creature) {
				creature.doAction(item, "look a little better");
			}
			
			public void onUpdate(final Creature creature) {
				super.update(creature);
				creature.modifyHp(1, "Killed by a slow health potion?");
			}
		});
		
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newPotionOfPoison() {
		String appearance = potionAppearances.get(3);
		final Item item = new Item('!', potionColors.get(appearance),
				"poison potion", appearance);
		item.setQuaffEffect(new Effect(20) {
			public void onStart(final Creature creature) {
				creature.doAction(item, "look sick");
			}
			
			public void onUpdate(final Creature creature) {
				super.update(creature);
				creature.modifyHp(-1, "Died of poison.");
			}
		});
		
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newPotionOfWarrior() {
		String appearance = potionAppearances.get(4);
		final Item item = new Item('!', potionColors.get(appearance),
				"warrior's potion", appearance);
		item.setQuaffEffect(new Effect(20) {
			public void onStart(final Creature creature) {
				creature.modifyAttackValue(5);
				creature.modifyDefenseValue(5);
				creature.doAction(item, "look stronger");
			}
			public void onEnd(final Creature creature) {
				creature.modifyAttackValue(-5);
				creature.modifyDefenseValue(-5);
				creature.doAction("look less strong");
			}
		});
		
		floor.addAtEmptyLocation(item);
		return item;
	}

	/**
	 * 
	 * @return
	 */
	public final Item newPotionOfArcher() {
		String appearance = potionAppearances.get(5);
		final Item item = new Item('!', potionColors.get(appearance),
				"archers potion", appearance);
		item.setQuaffEffect(new Effect(20) {
			public void onStart(final Creature creature) {
				creature.modifyVisionRadius(3);
				creature.doAction(item, "look more alert");
			}
			public void onEnd(final Creature creature) {
				creature.modifyVisionRadius(-3);
				creature.doAction("look less alert");
			}
		});
		
		floor.addAtEmptyLocation(item);
		return item;
	}

	/**
	 * 
	 * @return
	 */
	public final Item newPotionOfExperience() {
		String appearance = potionAppearances.get(6);
		final Item item = new Item('!', potionColors.get(appearance),
				"experience potion", appearance);
		item.setQuaffEffect(new Effect(20) {
			public void onStart(final Creature creature) {
				creature.doAction(item, "look more experienced");
				creature.modifyXp(creature.level() * 5);
			}
		});
		
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item randomPotion() {
		switch (JRogue.RANDOM.nextInt(9)) {
		case 0: return newPotionOfHealth();
		case 1: return newPotionOfHealth();
		case 2: return newPotionOfMana();
		case 3: return newPotionOfMana();
		case 4: return newPotionOfSlowHealth();
		case 5: return newPotionOfPoison();
		case 6: return newPotionOfWarrior();
		case 7: return newPotionOfArcher();
		default: return newPotionOfExperience();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newWhiteMagesSpellbook() {
		Item item = new Item('+', GraphicsUtil.WHITE,
				"white mage's spellbook", null);
		item.addWrittenSpell(new Spell("minor heal", 4, new Effect(1) {
			public void onStart(final Creature creature) {
				if (creature.hp() == creature.maxHp()) {
					return;
				}
				
				creature.modifyHp(20, "Killed by a minor heal spell?");
				creature.doAction("look healthier");
			}
		}));
		
		item.addWrittenSpell(new Spell("major heal", 8, new Effect(1) {
			public void onStart(final Creature creature) {
				if (creature.hp() == creature.maxHp()) {
					return;
				}
				
				creature.modifyHp(50, "Killed by a major heal spell?");
				creature.doAction("look healthier");
			}
		}));
		
		item.addWrittenSpell(new Spell("slow heal", 12, new Effect(50) {
			public void onUpdate(final Creature creature) {
				super.update(creature);
				creature.modifyHp(2, "Killed by a slow heal spell?");
			}
		}));

		item.addWrittenSpell(new Spell("inner strength", 16, new Effect(50) {
			public void onStart(final Creature creature) {
				creature.modifyAttackValue(INNER_STRENGTH_ATTACK_BOOST);
				creature.modifyDefenseValue(INNER_STRENGTH_DEFENSE_BOOST);
				creature.modifyVisionRadius(1);
				creature.modifyRegenHpPer1000(INNER_STRENGTH_REGEN);
				creature.doAction("seem to glow with inner strength");
			}
			public void onUpdate(final Creature creature) {
				super.update(creature);
				if (Math.random() < 0.25) {
					creature.modifyHp(1, "Killed by inner strength spell?");
				}
			}
			public void onEnd(final Creature creature) {
				creature.modifyAttackValue(-INNER_STRENGTH_ATTACK_BOOST);
				creature.modifyDefenseValue(-INNER_STRENGTH_DEFENSE_BOOST);
				creature.modifyVisionRadius(-1);
				creature.modifyRegenHpPer1000(-INNER_STRENGTH_REGEN);
			}
		}));
		
		floor.addAtEmptyLocation(item);
		return item;
	}
	
	/**
	 * 
	 * @return
	 */
	public final Item newBlueMagesSpellbook() {
		Item item = new Item('+', GraphicsUtil.BLUE,
				"blue mage's spellbook", null);

		item.addWrittenSpell(new Spell("blood to mana", 1, new Effect(1) {
			public void onStart(final Creature creature) {
				int amount = Math.min(creature.hp() - 1,
						creature.maxMana() - creature.mana());
				creature.modifyHp(-amount, "Killed by a blood to mana spell.");
				creature.modifyMana(amount);
			}
		}));
		
		item.addWrittenSpell(new Spell("blink", 6, new Effect(1) {
			public void onStart(final Creature creature) {
				creature.doAction("fade out");
				
				int mx = 0;
				int my = 0;
				
				do {
					mx = (int) (JRogue.RANDOM.nextInt(11)) - 5;
					my = (int) (JRogue.RANDOM.nextInt(11)) - 5;
				}
				while (!creature.canEnter(creature.getTile().getX() + mx,
							creature.getTile().getY() + my)
						&& creature.canSee(creature.getTile().getX() + mx,
								creature.getTile().getY() + my));
				
				creature.moveBy(mx, my);
				
				creature.doAction("fade in");
			}
		}));
		
		item.addWrittenSpell(new Spell("summon bats", 11, new Effect(1) {
			@Override
			public void onStart(final Creature creature) {
				for (int ox = -1; ox < 2; ox++) {
					for (int oy = -1; oy < 2; oy++) {
						int nx = creature.getTile().getX() + ox;
						int ny = creature.getTile().getY() + oy;
						if (ox == 0 && oy == 0 
								|| creature.creature(nx, ny) != null) {
							continue;
						}
						
						Creature bat = floor.getCreatureFactory().newBat();
						
						if (!bat.canEnter(nx, ny)) {
							floor.removeCreature(bat);
							continue;
						}
						
						bat.setTile(nx, ny);
						
						creature.summon(bat);
					}
				}
			}
		}));
		
		item.addWrittenSpell(new Spell("detect creatures", 16,
				new Effect(DETECT_CREATURES_DURATION) {
			public void onStart(final Creature creature) {
				creature.doAction("look far off into the distance");
				creature.modifyDetectCreatures(1);
			}
			public void onEnd(final Creature creature) {
				creature.modifyDetectCreatures(-1);
			}
		}));
		floor.addAtEmptyLocation(item);
		return item;
	}
	/**
	 * 
	 * @return
	 */
	public final Item randomSpellBook() {
		switch (JRogue.RANDOM.nextInt(2)) {
		case 0: return newWhiteMagesSpellbook();
		default: return newBlueMagesSpellbook();
		}
	}

	public Item newSandstone() {
		// TODO Auto-generated method stub
		return null;
	}

	public Item newGranite() {
		// TODO Auto-generated method stub
		return null;
	}

	public Item newLimestone() {
		// TODO Auto-generated method stub
		return null;
	}

	public Item newSlate() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
