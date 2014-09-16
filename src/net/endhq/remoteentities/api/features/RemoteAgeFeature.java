package net.endhq.remoteentities.api.features;

import net.endhq.remoteentities.api.RemoteEntity;
import net.endhq.remoteentities.persistence.ParameterData;
import net.endhq.remoteentities.persistence.SerializeAs;
import net.endhq.remoteentities.utilities.ReflectionUtil;

public class RemoteAgeFeature extends RemoteFeature implements AgeFeature
{
	@SerializeAs(pos = 1)
	protected int m_age;

	public RemoteAgeFeature()
	{
		this(0);
	}

	public RemoteAgeFeature(int inAge)
	{
		super("AGE");
		this.m_age = inAge;
	}

	@Deprecated
	public RemoteAgeFeature(RemoteEntity inEntity)
	{
		this(inEntity, 0);
	}

	@Deprecated
	public RemoteAgeFeature(RemoteEntity inEntity, int inAge)
	{
		super("AGE", inEntity);
		this.m_age = inAge;
	}

	@Override
	public int getAge()
	{
		return this.m_age;
	}

	@Override
	public void setAge(int inAge)
	{
		this.m_age = inAge;
	}

	@Override
	public ParameterData[] getSerializableData()
	{
		return ReflectionUtil.getParameterDataForClass(this).toArray(new ParameterData[0]);
	}
}