package net.endhq.particles.effects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.endhq.particles.ParticlesType;

public class SingleParticle {
	private Location loc;
	private ParticlesType type;
	public float speed = 0F;
	public int count = 10;
	public float offsetX = 0F;
	public float offsetY = 0F;
	public float offsetZ = 0F;
	public SingleParticle(Location loc, ParticlesType type) {
		this.loc=loc;
		this.type=type;
	}
	public void runE(Player p) throws Exception {//Runs with Exception
		type.sendToPlayer(p, loc, offsetX, offsetY, offsetZ, speed, count);
	}
	public void run(Player p) {//Runs without Exception
		try {
			type.sendToPlayer(p,  loc, offsetX, offsetY, offsetZ, speed, count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void runE() throws Exception {
		for(Player p : Bukkit.getOnlinePlayers()) {
			type.sendToPlayer(p,  loc, offsetX, offsetY, offsetZ, speed, count);
		}
	}
	public void run() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			try {
				type.sendToPlayer(p,  loc, offsetX, offsetY, offsetZ, speed, count);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
