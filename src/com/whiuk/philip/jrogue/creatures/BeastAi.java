package com.whiuk.philip.jrogue.creatures;

import com.whiuk.philip.jrogue.JRogue;

/**
 * Provides the artificial intelligence for
 * a beast-like mob (sub-human intelligence).
 * @author Philip Whitehouse
 *
 */
public class BeastAi extends CreatureAi {
	/**
	 * 
	 */
    private CreatureFactory factory;
    /**
     * 
     */
    private int spreadcount;

	/**
	 * 
	 * @param creature
	 * @param factory
	 */
	public BeastAi(final Creature creature, final CreatureFactory f) {
        super(creature);
        this.factory = f;
	}
	@Override
	public final void onUpdate() {
        if (spreadcount < 5 && Math.random() < 0.02) {
            spread();
        }
    }
	/**
	 * 
	 */
    private void spread() {
    	
        int x = getCreature().getTile().getX()
        		+ (int) (JRogue.RANDOM.nextInt(11)) - 5;
        int y = getCreature().getTile().getY()
        		+ (int) (JRogue.RANDOM.nextInt(11)) - 5;
   
        if (!getCreature().canEnter(x, y)) {
            return;
        }
        Creature child = factory.newFungus();
        child.setTile(x, y);
        spreadcount++;
    }
}
