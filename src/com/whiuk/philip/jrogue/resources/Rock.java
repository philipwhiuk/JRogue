package com.whiuk.philip.jrogue.resources;

import com.whiuk.philip.jrogue.location.Tile;
import com.whiuk.philip.jrogue.objects.Item;
import com.whiuk.philip.jrogue.objects.ItemFactory;

public class Rock implements Resource {
	public enum Type {
		SANDSTONE, GRANITE, LIMESTONE, SLATE
	}

	Type type;
	Vein vein;
	private ItemFactory itemFactory;
	private int resourcesLeft;
	private Tile tile;
	
	Item[] mine() {
		Item[] items;
		Item rock;
		switch(type) {
		case SLATE:
			rock = itemFactory.newSlate();
			break;
		case SANDSTONE:
			rock = itemFactory.newSandstone();
			break;
		case LIMESTONE:
			rock = itemFactory.newLimestone();
			break;
		default:
		case GRANITE:
			rock = itemFactory.newGranite();
			break;
		}
		
		if (vein != null) {
			items = new Item[]{rock,vein.getOre()};
		} else {
			items = new Item[]{rock};
		}
		resourcesLeft--;
		if(resourcesLeft <= 0) {
			tile.removeResource(this);
		}
		return items;
		
	}
}
