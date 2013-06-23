package com.whiuk.philip.jrogue.ai;

public interface Agent {
	void addBehaviour(Behaviour b);
	void plan();
	void addGoal(Goal g);
}
