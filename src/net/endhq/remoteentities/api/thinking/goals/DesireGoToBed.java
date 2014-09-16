package net.endhq.remoteentities.api.thinking.goals;

import net.endhq.remoteentities.api.RemoteEntity;
import net.endhq.remoteentities.api.thinking.DesireType;
import net.endhq.remoteentities.entities.RemotePlayer;
import net.minecraft.server.v1_7_R4.Block;
import net.minecraft.server.v1_7_R4.Blocks;

import org.bukkit.Location;

/**
 * Using this desire the player will try to find next bed and will go to sleep at night.
 */
public class DesireGoToBed extends DesireFindBlockBase
{
	@Deprecated
	public DesireGoToBed(RemotePlayer inEntity)
	{
		this(inEntity, 32);
	}

	@Deprecated
	public DesireGoToBed(RemotePlayer inEntity, int inRange)
	{
		super(inEntity, Block.getId(Blocks.BED), inRange);
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	@Deprecated
	public DesireGoToBed(RemoteEntity inEntity, int inBlockID, int inRange)
	{
		super(inEntity, inBlockID, inRange);
	}

	public DesireGoToBed()
	{
		this(32);
	}

	public DesireGoToBed(int inRange)
	{
		super(Block.getId(Blocks.BED), inRange);
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	@Deprecated
	public DesireGoToBed(int inBlockID, int inRange)
	{
		super(inBlockID, inRange);
	}

	@Override
	public boolean shouldExecute()
	{
		if(this.m_entity.getHandle().world.w() || ((RemotePlayer)this.m_entity).isSleeping())
			return false;

		return this.findNearest();
	}

	@Override
	public void startExecuting()
	{
		this.m_entity.move(new Location(this.m_entity.getBukkitEntity().getWorld(), this.m_locX, this.m_locY, this.m_locZ));
	}

	@Override
	public boolean update()
	{
		if(this.getNavigation().g())
		{
			((RemotePlayer)this.m_entity).enterBed(new Location(this.m_entity.getBukkitEntity().getWorld(), this.m_locX, this.m_locY, this.m_locZ));
			return false;
		}

		return true;
	}
}