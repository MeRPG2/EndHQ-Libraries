package net.endhq.particles;

import net.minecraft.server.v1_7_R4.PacketPlayOutWorldParticles;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
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
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles();
	    ReflectionUtil.setValue(packet, "a", this.particleName);
	    ReflectionUtil.setValue(packet, "b", Float.valueOf((float)location.getX()));
	    ReflectionUtil.setValue(packet, "c", Float.valueOf((float)location.getY()));
	    ReflectionUtil.setValue(packet, "d", Float.valueOf((float)location.getZ()));
	    ReflectionUtil.setValue(packet, "e", Float.valueOf(offsetX));
	    ReflectionUtil.setValue(packet, "f", Float.valueOf(offsetY));
	    ReflectionUtil.setValue(packet, "g", Float.valueOf(offsetZ));
	    ReflectionUtil.setValue(packet, "h", Float.valueOf(speed));
	    ReflectionUtil.setValue(packet, "i", Integer.valueOf(count));
	    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	}
}
