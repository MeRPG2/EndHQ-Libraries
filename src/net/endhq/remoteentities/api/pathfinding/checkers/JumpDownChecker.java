package net.endhq.remoteentities.api.pathfinding.checkers;

import net.endhq.remoteentities.api.pathfinding.MoveData;
import net.endhq.remoteentities.api.pathfinding.Pathfinder;

import org.bukkit.block.BlockFace;

public class JumpDownChecker implements MoveChecker
{
	@Override
	public void checkMove(MoveData inData)
	{
		if(!inData.isValid())
			return;

		if(inData.getYDiff() == -1)
		{
			if((!Pathfinder.isTransparent(inData.getAboveBlock()) && !Pathfinder.isLiquid(inData.getAboveBlock())) || (!Pathfinder.isTransparent(inData.getHeadBlock()) && !Pathfinder.isLiquid(inData.getHeadBlock())) || (!Pathfinder.isTransparent(inData.getHeadBlock().getRelative(BlockFace.UP)) && !Pathfinder.isLiquid(inData.getHeadBlock().getRelative(BlockFace.UP))))
				inData.setValid(false);
		}
	}
}