package net.endhq.cooldownapi;

import java.util.ArrayList;

import net.endhq.bukkitplugin.BukkitPlugin;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class CooldownAPI {
	private static ArrayList<Cooldown> cooldowns = new ArrayList<Cooldown>();
	public void onEnable() {
		String g = BukkitPlugin.getInst().getConfig().getString("this");
		String[] data = g.split(":({_-_}):");
		for(int i = 1; i==data.length; i++) {
			cooldowns.add(Cooldown.parse(data[i]));
		}
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitPlugin.getInst(), new TickLoop(), 1, 1);
	}
	public void onDisable() {
		String str = "Serialization_DO_NOT_TOUCH";
		for(Cooldown c : cooldowns) {
			str+=":({_-_}):";
			str+=c.serialize();
		}
		BukkitPlugin.getInst().getConfig().set("this", str);
	}
	public static boolean registerCooldown(String owner, String name, long ticks) {
		for(Cooldown c : cooldowns) {
			if((c.owner()).equalsIgnoreCase(owner) && (c.name().equalsIgnoreCase(name))) {
				return false;
			}
		}
		Cooldown cool = new Cooldown(owner, name, ticks);
		cooldowns.add(cool);
		return true;
	}
	public static boolean cancelCooldown(String owner, String name) {
		for(Cooldown c : cooldowns) {
			if((c.owner()).equalsIgnoreCase(owner) && (c.name().equalsIgnoreCase(name))) {
				cooldowns.remove(c);
				return true;
			}
		}
		return false;
	}
	public static ArrayList<Cooldown> doNotCallThisMethodItIsVeryDangerous() {
		return cooldowns;
	}
	public static void doNotCallThisMethodItIsSuperDangerous(Cooldown c) {
		cooldowns.remove(c);
	}
}
