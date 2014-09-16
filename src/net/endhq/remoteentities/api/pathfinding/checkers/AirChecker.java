package net.endhq.remoteentities.api.pathfinding.checkers;

import net.endhq.remoteentities.api.pathfinding.MoveData;
import net.endhq.remoteentities.api.pathfinding.Pathfinder;

public class AirChecker implements MoveChecker
{
	@Override
	public void checkMove(MoveData inData)
	{
		if(inData.getYDiff() >= 0)
		{
			if(!Pathfinder.isTransparent(inData.getBlock()) && !Pathfinder.isLiquid(inData.getBlock()))
				inData.setValid(false);
		}
	}
}