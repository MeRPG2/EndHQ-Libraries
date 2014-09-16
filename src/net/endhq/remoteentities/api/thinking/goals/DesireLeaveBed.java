package net.endhq.remoteentities.api.thinking.goals;

import net.endhq.remoteentities.api.thinking.DesireBase;
import net.endhq.remoteentities.api.thinking.DesireType;
import net.endhq.remoteentities.entities.RemotePlayer;

/**
 * Using this desire the player will leave the bed once the sun comes out.
 */
public class DesireLeaveBed extends DesireBase
{
	@Deprecated
	public DesireLeaveBed(RemotePlayer inEntity)
	{
		super(inEntity);
		this.m_isContinuous = false;
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	public DesireLeaveBed()
	{
		super();
		this.m_isContinuous = false;
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	@Override
	public boolean shouldExecute()
	{
		return this.getEntityHandle().world.w() && ((RemotePlayer)this.m_entity).isSleeping();
	}

	@Override
	public void startExecuting()
	{
		((RemotePlayer)this.m_entity).leaveBed();
	}
}