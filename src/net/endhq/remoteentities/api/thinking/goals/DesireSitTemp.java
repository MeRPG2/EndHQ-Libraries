package net.endhq.remoteentities.api.thinking.goals;

import net.endhq.remoteentities.api.RemoteEntity;
import net.minecraft.server.v1_7_R4.PathfinderGoalSit;

public class DesireSitTemp extends PathfinderGoalSit
{
	private final RemoteEntity m_entity;

	public DesireSitTemp(RemoteEntity arg0)
	{
		super(null);
		this.m_entity = arg0;
	}

	@Override
	public void setSitting(boolean flag)
	{
		if(this.m_entity.getMind().getMovementDesire(DesireSit.class) == null)
			return;

		this.m_entity.getMind().getMovementDesire(DesireSit.class).canSit(flag);
	}
}