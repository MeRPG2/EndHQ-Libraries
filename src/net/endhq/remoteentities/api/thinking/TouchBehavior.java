package net.endhq.remoteentities.api.thinking;

import net.endhq.remoteentities.api.RemoteEntity;

import org.bukkit.entity.Player;

public abstract class TouchBehavior extends BaseBehavior
{
	public TouchBehavior(RemoteEntity inEntity)
	{
		super(inEntity);
		this.m_name = "Touch";
	}

	/**
	 * Gets called when the entity gets touched by a player
	 *
	 * @param inPlayer player
	 */
	public abstract void onTouch(Player inPlayer);
}