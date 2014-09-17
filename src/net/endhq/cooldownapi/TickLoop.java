package net.endhq.cooldownapi;

import java.util.ArrayList;

import org.bukkit.Bukkit;

public class TickLoop implements Runnable {

	@Override
	public void run() {
		ArrayList<Cooldown> cds = CooldownAPI.doNotCallThisMethodItIsVeryDangerous();
		for(Cooldown c : cds) {
			if(c.tick()) {
				CooldownAPI.doNotCallThisMethodItIsSuperDangerous(c);
				Bukkit.getPluginManager().callEvent(new CooldownOverEvent(c));
			}
		}
		
	}
	
}
