package net.endhq.particles;

import java.lang.reflect.Field;

import org.bukkit.Location;
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
	ICONCRACK("iconcrack_"),
	TILECRACK("tilecrack_");
	private String particleName;
	
	private ParticlesType(String particleName) {
		this.particleName = particleName;
	}
	
	public void sendToPlayer(Player player, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) throws Exception {
		Class packetClass=null;
		for(int v = 1; v<9; v++) {
			for(int r = 1; r<10; r++) {
				try {
					packetClass = Class.forName("net.minecraft.server.v1_"+v+"_R"+r+".PacketPlayOutWorldParticles");
					break;
				} catch(Exception ex) {
					continue;
				}
			}
		}
		Class playerClass = null;
		for(int v = 1; v<9; v++) {
			for(int r = 1; r<10; r++) {
				try {
					playerClass = Class.forName("net.minecraft.server.v1_"+v+"_R"+r+".entity.CraftPlayer");
					break;
				} catch(Exception ex) {
					continue;
				}
			}
		}
		if(packetClass==null) {
			throw new ClassNotFoundException("Could not find packet class.");
		}
		if(playerClass==null) {
			throw new ClassNotFoundException("Could not find player class.");
		}
		Object packet = Class.forName("net.minecraft.server").newInstance();
	    ReflectionUtil.setValue(packet, "a", this.particleName);
	    ReflectionUtil.setValue(packet, "b", Float.valueOf((float)location.getX()));
	    ReflectionUtil.setValue(packet, "c", Float.valueOf((float)location.getY()));
	    ReflectionUtil.setValue(packet, "d", Float.valueOf((float)location.getZ()));
	    ReflectionUtil.setValue(packet, "e", Float.valueOf(offsetX));
	    ReflectionUtil.setValue(packet, "f", Float.valueOf(offsetY));
	    ReflectionUtil.setValue(packet, "g", Float.valueOf(offsetZ));
	    ReflectionUtil.setValue(packet, "h", Float.valueOf(speed));
	    ReflectionUtil.setValue(packet, "i", Integer.valueOf(count));
	    Object cPlayer = playerClass.cast(player);
	    Field pCon = cPlayer.getClass().getMethod("getHandle", null).invoke(cPlayer, null).getClass().getField("playerConnection");
	    pCon.get(cPlayer).getClass().getMethod("sendPacket", packet.getClass()).invoke(pCon, packet);//.getHandle().playerConnection.sendPacket(packet);
	    
	}
}
