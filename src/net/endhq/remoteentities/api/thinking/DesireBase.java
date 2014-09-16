package net.endhq.remoteentities.api.thinking;

import net.endhq.remoteentities.api.RemoteEntity;
import net.endhq.remoteentities.entities.RemoteBaseEntity;
import net.endhq.remoteentities.persistence.ParameterData;
import net.endhq.remoteentities.utilities.NMSUtil;
import net.endhq.remoteentities.utilities.ReflectionUtil;
import net.minecraft.server.v1_7_R4.*;

public abstract class DesireBase implements Desire
{
	protected RemoteEntity m_entity;
	protected DesireType m_type = DesireType.SUBCONSCIOUS;
	protected boolean m_isContinuous = true;

	public DesireBase()
	{
	}

	@Deprecated
	public DesireBase(RemoteEntity inEntity)
	{
		this.m_entity = inEntity;
	}

	@Override
	public RemoteEntity getRemoteEntity()
	{
		return this.m_entity;
	}

	@Override
	public void onAdd(RemoteEntity inEntity)
	{
		this.m_entity = inEntity;
	}

	public EntityLiving getEntityHandle()
	{
		if(this.m_entity == null)
			return null;

		return this.getRemoteEntity().getHandle();
	}

	@Override
	public DesireType getType()
	{
		return this.m_type;
	}

	public boolean update()
	{
		return true;
	}

	public boolean isContinuous()
	{
		return this.m_isContinuous;
	}

	@Override
	public void stopExecuting()
	{
	}

	@Override
	public void startExecuting()
	{
	}

	@Override
	public boolean canContinue()
	{
		return this.shouldExecute();
	}

	@Override
	public void setType(DesireType inType)
	{
		this.m_type = inType;
	}

	public void movePath(PathEntity inPath, double inSpeed)
	{
		if(this.getRemoteEntity() instanceof RemoteBaseEntity)
			((RemoteBaseEntity)this.getRemoteEntity()).moveWithPath(inPath, inSpeed);
		else
			this.getNavigation().a(inPath, inSpeed);
	}

	public ParameterData[] getSerializableData()
	{
		return ReflectionUtil.getParameterDataForClass(this).toArray(new ParameterData[0]);
	}

	public Navigation getNavigation()
	{
		return NMSUtil.getNavigation(this.getEntityHandle());
	}
}