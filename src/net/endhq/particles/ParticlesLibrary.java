package net.endhq.particles;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_7_R4.MathHelper;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.avaje.ebean.text.StringParser;

public class ParticlesLibrary {
	public static Vector rotateAroundAxisX(Vector v, double angle) {
		double y, z, cos, sin;
		cos = Math.cos(angle);
		sin = Math.sin(angle);
		y = v.getY() * cos - v.getZ() * sin;
		z = v.getY() * sin + v.getZ() * cos;
		return v.setY(y).setZ(z);
	}

	public static Vector rotateAroundAxisY(Vector v, double angle) {
		double x, z, cos, sin;
		cos = Math.cos(angle);
		sin = Math.sin(angle);
		x = v.getX() * cos + v.getZ() * sin;
		z = v.getX() * -sin + v.getZ() * cos;
		return v.setX(x).setZ(z);
	}

	public static Vector rotateAroundAxisZ(Vector v, double angle) {
		double x, y, cos, sin;
		cos = Math.cos(angle);
		sin = Math.sin(angle);
		x = v.getX() * cos - v.getY() * sin;
		y = v.getX() * sin + v.getY() * cos;
		return v.setX(x).setY(y);
	}
	public static void createGlobalParticleEffect(Location location, ParticlesType particleType, float speedOfParticles, int howManyParticles) throws Exception {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			particleType.sendToPlayer(p, location, 0, 0, 0, speedOfParticles, howManyParticles);
		}
	}

	public static void createGlobalParticleEffect(Location location, ParticlesType particleType, float offsetX, float offsetY, float offsetZ, float speedOfParticles, int howManyParticles) throws Exception {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			particleType.sendToPlayer(p, location, offsetX, offsetY, offsetZ, speedOfParticles, howManyParticles);
		}
	}

	public static void showPlayerParticleEffect(Player player, Location location, ParticlesType particleType, float speedOfParticles, int howManyParticles) throws Exception {
		particleType.sendToPlayer(player, location, 0, 0, 0, speedOfParticles, howManyParticles);
	}

	public static void showPlayerParticleEffect(Player player, Location location, ParticlesType particleType, float offsetX, float offsetY, float offsetZ, float speedOfParticles, int howManyParticles) throws Exception {
		particleType.sendToPlayer(player, location, offsetX, offsetY, offsetZ, speedOfParticles, howManyParticles);
	}
	
	public static void showParticleText(Player player, ParticlesType particleType, String text) throws Exception {
		Location location = player.getLocation();
		location = location.add(0D, 2D, 0D);
		int distance = 3;
		location = new Location(location.getWorld(),
        		distance*Double.parseDouble(Float.toString(MathHelper.sin(-location.getYaw() * 0.017453292F - (float)Math.PI)))*Double.parseDouble(Float.toString(-MathHelper.cos(-location.getPitch() * 0.017453292F)))+location.getX(),
        		distance*Double.parseDouble(Float.toString(MathHelper.sin(-location.getPitch() * 0.017453292F)))+location.getY(),
        		distance*Double.parseDouble(Float.toString(MathHelper.cos(-location.getYaw() * 0.017453292F - (float)Math.PI)))*Double.parseDouble(Float.toString(-MathHelper.cos(-location.getPitch() * 0.017453292F)))+location.getZ(),
        		location.getYaw(), location.getPitch());
		Font font = new Font("Tohoma", Font.PLAIN, 12);
		BufferedImage image = stringToBufferedImage(font, text);
		int c=0;
		try {
			for (int y = 0; y < image.getHeight(); y += 1) {
				for (int x = 0; x < image.getWidth(); x += 1) {
					c = image.getRGB(x, y);
					if (Color.black.getRGB() != c) continue;
					Vector v = new Vector((float) image.getWidth() / 2 - x, (float) image.getHeight() / 2 - y, 0).multiply((float)1/7);
					rotateAroundAxisY(v, -location.getYaw() * 3.1415927 / 180);
					ParticlesLibrary.showPlayerParticleEffect(player, location.add(v), particleType, 0F, 1);
					location.subtract(v);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void createSwirlEffect(Location l, ParticlesType particleType, int helixes, int circles, int radius) throws Exception {
		Location location = new Location(Bukkit.getWorld("world"), -430D, 150D, 1505D);
		int step = 0;
        for (int x = 0; x < circles; x++) {
            for (int i = 0; i < helixes; i++) {
                double angle = step * Math.PI/16 + (2 * Math.PI * i / 6);
                Vector v = new Vector(Math.cos(angle) * radius, step * .1F, Math.sin(angle) * radius);
                rotateAroundAxisX(v, (location.getPitch() + 90)  * 3.1415927 / 180);
                rotateAroundAxisY(v, -location.getYaw()  * 3.1415927 / 180);

                location.add(v);
                createGlobalParticleEffect(location, particleType, 0F, 5);
                location.subtract(v);
            }
            step++;
        }
	}
	
	private static BufferedImage stringToBufferedImage(Font font, String s) {
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = img.getGraphics();
		g.setFont(font);
		FontRenderContext frc = g.getFontMetrics().getFontRenderContext();
		Rectangle2D rect = font.getStringBounds(s, frc);
		g.dispose();
		img = new BufferedImage((int) Math.ceil(rect.getWidth()), (int) Math.ceil(rect.getHeight()), BufferedImage.TYPE_4BYTE_ABGR);
		g = img.getGraphics();
		g.setColor(Color.black);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		int x = 0;
		int y = fm.getAscent();
		g.drawString(s, x, y);
		g.dispose();
		return img;
	}
	
	
	
	
	
	
	
	
	

}
