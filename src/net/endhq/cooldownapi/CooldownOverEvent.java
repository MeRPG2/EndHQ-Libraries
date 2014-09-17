package net.endhq.cooldownapi;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CooldownOverEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private Cooldown cooldown;
	public CooldownOverEvent(Cooldown cooldown) {
		this.cooldown=cooldown;
	}
	public String getCooldownName() {
		return cooldown.name();
	}
	public String getCooldownOwner() {
		return cooldown.owner();
	}
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
