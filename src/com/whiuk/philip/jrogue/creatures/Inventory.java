package com.whiuk.philip.jrogue.creatures;

import com.whiuk.philip.jrogue.objects.Item;

/**
 * The items a creature is currently carrying.
 * @author Philip Whitehouse
 *
 */
public class Inventory {

	/**
	 * 
	 */
	private Item[] items;
	/**
	 * 
	 * @return
	 */
	public final Item[] getItems() {
		return items.clone();
	}
	/**
	 * 
	 * @param i
	 * @return
	 */
	public final Item get(final int i) {
		return items[i];
	}
	/**
	 * 
	 * @param max
	 */
	public Inventory(final int max) {
		items = new Item[max];
	}
	/**
	 * 
	 * @param item
	 */
	public final void add(final Item item) {
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				items[i] = item;
				break;
			}
		}
	}
	/**
	 * 
	 * @param item
	 */
	public final void remove(final Item item) {
		for (int i = 0; i < items.length; i++) {
			if (items[i] == item) {
				items[i] = null;
				return;
			}
		}
	}
	/**
	 * 
	 * @return
	 */
	public final boolean isFull() {
		int size = 0;
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null) {
				size++;
			}
		}
		return size == items.length;
	}
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	public final boolean contains(final Item item) {
		for (Item i : items) {
			if (i == item) {
				return true;
			}
		}
		return false;
	}
}
