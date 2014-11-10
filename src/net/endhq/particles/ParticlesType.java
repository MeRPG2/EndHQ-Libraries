package net.endhq.particles;

import java.lang.reflect.Field;

import net.endhq.util.nms.Reflection;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public enum ParticlesType {
	HUGE_EXPLOSION("hugeexplosion"),
	LARGE_EXPLODE("largeexplode"),
	FIREWORKS_SPARK("fireworksSpark"),
	BUBBLE("bubble"),
	SUSPEND("suspend"),
	DEPTH_SUSPEND("depthSuspend"),
	TOWN_AURA("townaura"),
	CRIT("crit"),
	MAGIC_CRIT("magicCrit"),
	MOB_SPELL("mobSpell"),
	MOB_SPELL_AMBIENT("mobSpellAmbient"),
	SPELL("spell"),
	INSTANT_SPELL("instantSpell"),
	WITCH_MAGIC("witchMagic"),
	NOTE("note"),
	PORTAL("portal"),
	ENCHANTMENT_TABLE("enchantmenttable"),
	EXPLODE("explode"),
	FLAME("flame"),
	LAVA("lava"),
	FOOTSTEP("footstep"),
	SPLASH("splash"),
	LARGE_SMOKE("largesmoke"),
	CLOUD("cloud"),
	RED_DUST("reddust"),
	SNOWBALL_POOF("snowballpoof"),
	DRIP_WATER("dripWater"),
	DRIP_LAVA("dripLava"),
	SNOW_SHOVEL("snowshovel"),
	SLIME("slime"),
	HEART("heart"),
	ANGRY_VILLAGER("angryVillager"),
	HAPPY_VILLAGER("happerVillager"),
	ICONCRACK("iconcrack_<id>"),
	TILECRACK("tilecrack_<id>_<data>");
	private String particleName;
	
	private ParticlesType(String particleName) {
		this.particleName = particleName;
	}
	
	public ParticlesType setType(Material m) {
		particleName = particleName.replaceAll("<id>", String.valueOf(m.getId()));
		return this;
	}
	
	public ParticlesType setData(short data) {
		particleName = particleName.replaceAll("<data>", String.valueOf(data));
		return this;
	}
	
	public void sendToPlayer(Player player, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) throws Exception {//NEW SYSTEM UNTESTED!
		if(particleName.contains("crack")) {
			throw new Exception();
		}
		Class packetClass = Reflection.getNMSClass("net.minecraft.server."+Reflection.revision+".PacketPlayOutWorldParticles");
		Class playerClass = Reflection.getNMSClass("net.minecraft.server"+Reflection.revision+".entity.CraftPlayer");
		if(packetClass==null) {
			throw new ClassNotFoundException("Could not find packet class.");
		}
		if(playerClass==null) {
			throw new ClassNotFoundException("Could not find player class.");
		}
		Object packet = Class.forName("net.minecraft.server").newInstance();
	    Reflection.setValue(packet, "a", this.particleName);
	    Reflection.setValue(packet, "b", Float.valueOf((float)location.getX()));
	    Reflection.setValue(packet, "c", Float.valueOf((float)location.getY()));
	    Reflection.setValue(packet, "d", Float.valueOf((float)location.getZ()));
	    Reflection.setValue(packet, "e", Float.valueOf(offsetX));
	    Reflection.setValue(packet, "f", Float.valueOf(offsetY));
	    Reflection.setValue(packet, "g", Float.valueOf(offsetZ));
	    Reflection.setValue(packet, "h", Float.valueOf(speed));
	    Reflection.setValue(packet, "i", Integer.valueOf(count));
	    Object cPlayer = playerClass.cast(player);
	    Field pCon = cPlayer.getClass().getMethod("getHandle", null).invoke(cPlayer, null).getClass().getField("playerConnection");
	    pCon.get(cPlayer).getClass().getMethod("sendPacket", packet.getClass()).invoke(pCon, packet);
	}
}