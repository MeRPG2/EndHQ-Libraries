package net.endhq.cooldown;

import net.endhq.bukkitplugin.BukkitPlugin;
import net.endhq.util.Meta;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by J on 10/19/2014.
 */
public class Cooldown implements Runnable {

    private String storageKey;
    private UUID id;
    private Runnable callback = null;

    /**
     * Create a basic cooldown
     * @param p player to attatch the cooldown to
     * @param key key for the cooldown.
     * @param ticks how many ticks to wait on the cooldown.
     */
    public Cooldown(Player p, String key, long ticks) {
        storageKey = "cooldown."+key;
        Meta.setMetadata(p, storageKey, "true", BukkitPlugin.getInst());
        this.id=p.getUniqueId();
        Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitPlugin.getInst(), this, ticks);
    }


    /**
     * Create a cooldown with a callback
     * @param p player to attatch the cooldown to
     * @param key key for the cooldown.
     * @param ticks how many ticks to wait on the cooldown.
     * @param callback callback runnable is called after the cooldown is over and cleared.
     */
    public Cooldown(Player p, String key, long ticks, Callback callback) {
    	storageKey = "cooldown."+key;
        Meta.setMetadata(p, storageKey, "true", BukkitPlugin.getInst());
        this.id=p.getUniqueId();
        this.callback=callback;
        Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitPlugin.getInst(), this, ticks);
    }

    /**
     * Do not override this. Example usage is as follows:
     * <p>
     *      new Cooldown(player, "basicCooldown", 5 * 20L, new Callback(new Object[] {player.getUniqueId()}) {
                @Override
                public void run() {
                    Bukkit.getPlayer((UUID)args[0]).sendMessage("This is the callback.");
                }
            });
     * </p>
     */
    @Override
    public void run() {
        Player p = Bukkit.getPlayer(id);
        if(p!=null && p.isOnline()) {
            Meta.setMetadata(p, storageKey, "false", BukkitPlugin.getInst());
            if(callback!=null) {
                Bukkit.getScheduler().runTask(BukkitPlugin.getInst(), callback);
            }
        }
    }

    /**
     * Gets if a player is on a specified cooldown.
     * @param p player to check for
     * @param key key of the cooldown
     * @return true if the player is on the cooldown, otherwise false.
     */
    public static boolean isOnCooldown(Player p, String key) {
        if(((String)Meta.getMetadata(p, "cooldown."+key, BukkitPlugin.getInst())).equalsIgnoreCase("true"))
            return true;
        return false;
    }


}
