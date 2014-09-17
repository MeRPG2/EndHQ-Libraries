package net.endhq.util.nms;

import org.bukkit.Bukkit;

public class Reflection {
	public static String revision = "x";
	public static String getRevision() {
		if(revision.equalsIgnoreCase("x")) {
			Class sC = Bukkit.getServer().getClass();
			revision = sC.getPackage().getName().replace("org.bukkit.craftbukkit.", "").split("\\.")[0];
			return revision;
		}
		return revision;
	}
	public static Class<?> getNMSClass(String name) {
		if(revision.equalsIgnoreCase("x")) getRevision();
		try {
			return Class.forName("net.minecraft.server."+revision+"."+name);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
