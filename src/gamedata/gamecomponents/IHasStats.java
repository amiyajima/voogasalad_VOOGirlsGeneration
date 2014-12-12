package gamedata.gamecomponents;

import java.awt.geom.Point2D;

import gamedata.stats.Stats;

/**
 * Marker Interface used to group together gamecomponents that have statistics 
 * (Player, Piece, Patch) stored in a Stats object, to enable abstraction when
 * implementing Events.
 * 
 * @author Mike Zhu
 *
 */
public interface IHasStats {
		
	public abstract Stats getStats();
	
	public abstract Point2D getLoc();
	
	@Override
	public abstract String toString();
}
