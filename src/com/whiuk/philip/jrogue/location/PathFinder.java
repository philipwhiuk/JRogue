package com.whiuk.philip.jrogue.location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.whiuk.philip.jrogue.creatures.Creature;


/**
 * 
 * @author Philip Whitehouse
 *
 */
public class PathFinder {
	/**
	 * 
	 */
    private ArrayList<Point> open;
	/**
	 * 
	 */
    private ArrayList<Point> closed;
	/**
	 * 
	 */
    private HashMap<Point, Point> parents;
	/**
	 * 
	 */
	private HashMap<Point, Integer> totalCost;
    
	/**
	 * 
	 */
    public PathFinder() {
    	this.open = new ArrayList<Point>();
        this.closed = new ArrayList<Point>();
        this.parents = new HashMap<Point, Point>();
        this.totalCost = new HashMap<Point, Integer>();
    }
    
    /**
     * 
     * @param from
     * @param to
     * @return
     */
    private int heuristicCost(final Point from, final Point to) {
        return Math.max(Math.abs(from.x - to.x), Math.abs(from.y - to.y));
    }

    /**
     * 
     * @param from
     * @return
     */
    private int costToGetTo(final Point from) {
    	if(parents.get(from) == null) {
    		return 0;
    	} else {
    		return 1 + costToGetTo(parents.get(from));
    	}
    }
    
    /**
     * 
     * @param from
     * @param to
     * @return
     */
    private int totalCost(final Point from, final Point to) {
        if (totalCost.containsKey(from)) {
			return totalCost.get(from);
		}
        
        int cost = costToGetTo(from) + heuristicCost(from, to);
        totalCost.put(from, cost);
        return cost;
    }

    /**
     * 
     * @param child
     * @param parent
     */
    private void reParent(final Point child, final Point parent) {
        parents.put(child, parent);
        totalCost.remove(child);
    }

    /**
     * 
     * @param creature
     * @param start
     * @param end
     * @param maxTries
     * @return
     */
    public final ArrayList<Point> findPath(final Creature creature,
    		final Point start, final Point end, final int maxTries) {
        open.clear();
        closed.clear();
        parents.clear();
        totalCost.clear();
    	
        open.add(start);
        
        for (int tries = 0; tries < maxTries && open.size() > 0; tries++) {
            Point closest = getClosestPoint(end);
            
            open.remove(closest);
            closed.add(closest);

            if (closest.equals(end)) {
				return createPath(start, closest);
			} else {
				checkNeighbors(creature, end, closest);
			}
        }
        return null;
    }

    /**
     * 
     * @param end
     * @return
     */
	private Point getClosestPoint(final Point end) {
		Point closest = open.get(0);
		for (Point other : open) {
		    if (totalCost(other, end) < totalCost(closest, end)) {
				closest = other;
			}
		}
		return closest;
	}

	/**
	 * 
	 * @param creature
	 * @param end
	 * @param closest
	 */
	private void checkNeighbors(final Creature creature,
			final Point end, final Point closest) {
		for (Point neighbor : closest.neighbors8()) {
		    if (closed.contains(neighbor)
		    		|| !creature.canEnter(neighbor.x, neighbor.y)
		    		&& !neighbor.equals(end)) {
				continue;
			}
			
		    if (open.contains(neighbor)) {
				reParentNeighborIfNecessary(closest, neighbor);
			} else {
				reParentNeighbor(closest, neighbor);
			}
		}
	}

	/**
	 * 
	 * @param closest
	 * @param neighbor
	 */
	private void reParentNeighbor(final Point closest, final Point neighbor) {
		reParent(neighbor, closest);
		open.add(neighbor);
	}

	/**
	 * 
	 * @param closest
	 * @param neighbor
	 */
	private void reParentNeighborIfNecessary(final Point closest,
			final Point neighbor) {
		Point originalParent = parents.get(neighbor);
		double currentCost = costToGetTo(neighbor);
		reParent(neighbor, closest);
		double reparentCost = costToGetTo(neighbor);
		
		if (reparentCost < currentCost) {
			open.remove(neighbor);
		} else {
			reParent(neighbor, originalParent);
		}
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private ArrayList<Point> createPath(final Point start,
			final Point end) {
		ArrayList<Point> path = new ArrayList<Point>();
		Point nextPoint = end;
		while (!nextPoint.equals(start)) {
		    path.add(nextPoint);
		    nextPoint = parents.get(nextPoint);
		}

		Collections.reverse(path);
		return path;
	}
}
