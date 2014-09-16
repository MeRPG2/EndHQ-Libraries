package net.endhq.remoteentities.api.pathfinding.checkers;

import net.endhq.remoteentities.api.pathfinding.MoveData;
import net.endhq.remoteentities.api.pathfinding.Pathfinder;

public class AvoidLiquidChecker implements MoveChecker
{
	@Override
	public void checkMove(MoveData inData)
	{
		if(!inData.isValid())
			return;

		if(Pathfinder.isLiquid(inData.getBlock()) || Pathfinder.isLiquid(inData.getAboveBlock()))
			inData.setValid(false);
	}
}