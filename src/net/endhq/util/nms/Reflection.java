package net.endhq.util.nms;

import java.lang.reflect.Field;

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
	public static void setValue(Object instance, String fieldName, Object value) throws Exception {
		Field field = instance.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(instance, value);
	}
			  
	public static Object getValue(Object instance, String fieldName) throws Exception {
		Field field = instance.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(instance);
	}
}
