package gamedata.gamecomponents;

import gamedata.stats.Stats;

import java.awt.geom.Point2D;

public class PlayerData implements IHasStats{

	private Stats myStats;
	
	@Override
	public Stats getStats() {
		return myStats;
	}

	/**
	 * In a sense, Player should not return getLoc()
	 */
	@Override
	public Point2D getLoc() {
		return null;
	}

}
