package net.endhq.bukkitplugin;


import org.bukkit.plugin.java.JavaPlugin;

public class BukkitPlugin extends JavaPlugin {
	private static BukkitPlugin inst;
	@Override
	public void onEnable() {
		this.getLogger().info("Woo! EndHQ's Libraries have loaded! :D");
		inst=this;
	}
	public static BukkitPlugin getInst() {
		return inst;
	}
}