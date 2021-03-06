package net.endhq.remoteentities.api.events;

import net.endhq.remoteentities.api.RemoteEntity;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

/**
 * Event that gets fired when an entity got touched by another
 */
public class RemoteEntityTouchEvent extends RemoteEvent
{
	protected final Entity m_touchingEntity;
	private static final HandlerList handlers = new HandlerList();

	public RemoteEntityTouchEvent(RemoteEntity inEntity, Entity inTouchingEntity)
	{
		super(inEntity);
		this.m_touchingEntity = inTouchingEntity;
	}

	/**
	 * Entity that's touching the RemoteEntity
	 *
	 * @return entity
	 */
	public Entity getTouchingEntity()
	{
		return this.m_touchingEntity;
	}

	public static HandlerList getHandlerList()
	{
		return handlers;
	}

	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}
}