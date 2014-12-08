package gamedata.gamecomponents;

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
	
	@Override
	public abstract String toString();
}
