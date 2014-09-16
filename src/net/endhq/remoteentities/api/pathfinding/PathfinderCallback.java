package net.endhq.remoteentities.api.pathfinding;

public interface PathfinderCallback
{
	public void onPathfindEnd(Pathfinder inFinder, PathResult inResult);
}