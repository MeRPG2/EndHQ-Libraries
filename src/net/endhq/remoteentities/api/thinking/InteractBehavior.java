package net.endhq.remoteentities.api.thinking;

import net.endhq.remoteentities.api.RemoteEntity;

import org.bukkit.entity.Player;

public abstract class InteractBehavior extends BaseBehavior
{
	public InteractBehavior(RemoteEntity inEntity)
	{
		super(inEntity);
		this.m_name = "Interact";
	}

	/**
	 * Called when a player interacts with the entity
	 *
	 * @param inPlayer player
	 */
	public abstract void onInteract(Player inPlayer);
}