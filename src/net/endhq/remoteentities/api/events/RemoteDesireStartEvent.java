package net.endhq.remoteentities.api.events;

import net.endhq.remoteentities.api.thinking.Desire;
import net.endhq.remoteentities.api.thinking.DesireItem;

import org.bukkit.event.HandlerList;

public class RemoteDesireStartEvent extends RemoteEvent
{
	private static final HandlerList handlers = new HandlerList();
	private DesireItem m_desire;

	public RemoteDesireStartEvent(DesireItem inDesire)
	{
		super(inDesire.getDesire().getRemoteEntity());
		this.m_desire = inDesire;
	}

	/**
	 * Gets the desire is about to start.
	 *
	 * @return Starting desire
	 */
	public Desire getDesire()
	{
		return this.m_desire.getDesire();
	}

	/**
	 * Gets the priority the desire is at.
	 *
	 * @return Desire priority
	 */
	public int getPriority()
	{
		return this.m_desire.getPriority();
	}

	/**
	 * Sets the desire that should be started instead.
	 *
	 * @param inDesire Desire to start.
	 */
	public void setDesire(DesireItem inDesire)
	{
		this.m_desire = inDesire;
	}

	public DesireItem getDesireItem()
	{
		return this.m_desire;
	}

	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}

	public static HandlerList getHandlerList()
	{
		return handlers;
	}
}