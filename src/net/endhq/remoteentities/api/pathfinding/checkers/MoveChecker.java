package net.endhq.remoteentities.api.pathfinding.checkers;

import net.endhq.remoteentities.api.pathfinding.MoveData;

public interface MoveChecker
{
	public void checkMove(MoveData inData);
}