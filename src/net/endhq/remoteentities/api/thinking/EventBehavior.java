package net.endhq.remoteentities.api.thinking;

import net.endhq.remoteentities.api.RemoteEntity;

import org.bukkit.Bukkit;

public abstract class EventBehavior extends BaseBehavior
{
	public EventBehavior(RemoteEntity inEntity)
	{
		super(inEntity);
	}

	@Override
	public void onAdd()
	{
		Bukkit.getPluginManager().registerEvents(this, this.getRemoteEntity().getManager().getPlugin());
	}
}