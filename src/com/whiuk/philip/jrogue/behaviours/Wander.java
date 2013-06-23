package com.whiuk.philip.jrogue.behaviours;

import com.whiuk.philip.jrogue.JRogue;
import com.whiuk.philip.jrogue.ai.Behaviour;
import com.whiuk.philip.jrogue.objects.Moveable;

public class Wander implements Behaviour {

	private int moveDistance;
	private Moveable moveable;

	public Wander(Moveable m, int d) {
		this.moveable = m;
		this.moveDistance = d;
	}
	
	@Override
	public void perform() {
		int mx = JRogue.RANDOM.nextInt(moveDistance) - 1;
		int my = JRogue.RANDOM.nextInt(moveDistance) - 1;
		
		moveable.moveBy(mx, my);
	}
}
