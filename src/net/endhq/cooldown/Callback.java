package net.endhq.cooldown;

import net.endhq.bukkitplugin.BukkitPlugin;

/**
 * Created by J on 10/19/2014.
 */
public class Callback implements Runnable {
    public Object[] args;

    /**
     * Creates a new callback for use with the Cooldown class. Instantiate a new object array with "new Object[] {element1, element2, ...}". DO NOT STORE DESTROYABLE OBJECTS SUCH AS PLAYERS IN THE OBJECT ARRAY!
     * @param args
     */
    public Callback(Object[] args) {
        this.args=args;
    }

    /**
     * Creates a new callback for use with the Cooldown class.
     */
    public Callback() {

    }

    /**
     * Override this method to actually make your callback. You can access "args" as a local variable. YOU MUST OVERRIDE THIS FOR YOUR CALLBACK TO HAVE ANY EFFECT!
     */
    @Override
    public void run() {
        BukkitPlugin.getInst().getLogger().info("A callback just called with nothing in it. Fix your code.");
    }
}
