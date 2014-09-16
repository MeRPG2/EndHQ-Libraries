package net.endhq.remoteentities.api.features;

import net.endhq.remoteentities.api.RemoteEntity;
import net.endhq.remoteentities.persistence.ParameterData;
import net.endhq.remoteentities.utilities.ReflectionUtil;

public class RemoteFeature implements Feature
{
	protected final String NAME;
	protected RemoteEntity m_entity;

	@Deprecated
	public RemoteFeature(String inName, RemoteEntity inEntity)
	{
		this(inName);
		this.m_entity = inEntity;
	}

	public RemoteFeature(String inName)
	{
		this.NAME = inName;
	}

	@Override
	public String getName()
	{
		return this.NAME;
	}

	@Override
	public RemoteEntity getEntity()
	{
		return this.m_entity;
	}

	@Override
	public void onAdd()
	{
	}

	@Override
	public void onAdd(RemoteEntity inEntity)
	{
		this.m_entity = inEntity;
		this.onAdd();
	}

	@Override
	public void onRemove()
	{
	}

	public ParameterData[] getSerializableData()
	{
		return ReflectionUtil.getParameterDataForClass(this).toArray(new ParameterData[0]);
	}
}