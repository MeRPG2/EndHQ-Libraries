package net.endhq.customsounds;

import net.minecraft.server.v1_7_R4.PacketPlayOutNamedSoundEffect;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Sound {
	private String name;
	public Sound(String name) {
		this.name=name;
	}
	
	public void playSound(Player p, Location l) {
		((CraftPlayer) p).getHandle().playerConnection
			.sendPacket(new PacketPlayOutNamedSoundEffect(
					name, l.getX(), l.getY(), l.getZ(),
					100000.0F, 1.0F));
	}
}
