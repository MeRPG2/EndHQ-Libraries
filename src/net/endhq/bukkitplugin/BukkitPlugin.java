package net.endhq.bukkitplugin;


import net.endhq.remoteentities.RemoteEntities;

import org.bukkit.plugin.java.JavaPlugin;

public class BukkitPlugin extends JavaPlugin {
	//Testing
	private static BukkitPlugin inst;
	RemoteEntities reme;
	@Override
	public void onEnable() {
		this.getLogger().info("Woo! EndHQ's Libraries have loaded! :D");
		inst=this;
		reme=new RemoteEntities();
	}
	@Override
	public void onDisable() {
		reme.onDisable();
	}
	public static BukkitPlugin getInst() {
		return inst;
	}
}