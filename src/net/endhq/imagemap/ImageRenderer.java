package net.endhq.imagemap;

import java.awt.image.BufferedImage;
import java.lang.reflect.Method;

import net.endhq.bukkitplugin.BukkitPlugin;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;



public class ImageRenderer extends MapRenderer {
	private BufferedImage img;
	private Method m;
	public ImageRenderer(BufferedImage img) {
		this.img=img;
	}
	public ImageRenderer(BufferedImage img, Method m) {
		this.img=img;
		this.m=m;
	}
	@Override
	public void render(MapView map, MapCanvas canvas, Player player) {
		canvas.drawImage(0, 0, img);
		if(m!=null) {
			if(m.getParameterTypes().length==2) {
				if(m.getParameterTypes()[0] == MapCanvas.class && m.getParameterTypes()[1] == Player.class) {
					if(m.getReturnType().equals(Void.TYPE)) {
						m.setAccessible(true);
						try {
							m.invoke(null, canvas, player);
						} catch (Exception e) {
							//e.printStackTrace();
							BukkitPlugin.getInst().getLogger().info("Failed to write to map.");
						}
					}
				}
			}
		}
	}
	public void applyToMap(MapView map) {
		for (MapRenderer renderer : map.getRenderers()) {
			map.removeRenderer(renderer);
		}
		map.addRenderer(this);
	}
}
