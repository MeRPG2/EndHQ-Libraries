package net.endhq.remoteentities.api.features;

import net.endhq.remoteentities.api.RemoteEntity;
import net.endhq.remoteentities.persistence.ParameterData;
import net.endhq.remoteentities.persistence.SerializeAs;
import net.endhq.remoteentities.utilities.ReflectionUtil;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RemoteTamingFeature extends RemoteFeature implements TamingFeature
{
	@SerializeAs(pos = 1)
	protected String m_tamer;

	public RemoteTamingFeature()
	{
		this((String)null);
	}

	public RemoteTamingFeature(String inTamer)
	{
		super("TAMING");
		this.m_tamer = inTamer;
	}

	public RemoteTamingFeature(Player inTamer)
	{
		this(inTamer.getName());
	}

	@Deprecated
	public RemoteTamingFeature(RemoteEntity inEntity)
	{
		this(inEntity, (String)null);
	}

	@Deprecated
	public RemoteTamingFeature(RemoteEntity inEntity, String inTamer)
	{
		super("TAMING", inEntity);
		this.m_tamer = inTamer;
	}

	@Deprecated
	public RemoteTamingFeature(RemoteEntity inEntity, Player inTamer)
	{
		this(inEntity, inTamer.getName());
	}

	@Override
	public boolean isTamed()
	{
		return this.m_tamer != null;
	}

	@Override
	public void untame()
	{
		this.m_tamer = null;
	}

	@Override
	public void tame(Player inPlayer)
	{
		this.m_tamer = inPlayer.getName();
	}

	@Override
	public Player getTamer()
	{
		return Bukkit.getPlayerExact(this.m_tamer);
	}

	@Override
	public ParameterData[] getSerializableData()
	{
		return ReflectionUtil.getParameterDataForClass(this).toArray(new ParameterData[0]);
	}
}