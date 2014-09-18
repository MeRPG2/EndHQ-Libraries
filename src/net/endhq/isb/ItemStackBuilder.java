package net.endhq.isb;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackBuilder {
	private ItemStack stack = new ItemStack(Material.STONE, 1, (short)0);
	
	public ItemStackBuilder(Material type, int amount, short data) {
		stack.setType(type);
		stack.setAmount(amount);
		stack.setDurability(data);
	}
	
	public ItemStackBuilder(Material type, int amount) {
		stack.setType(type);
		stack.setAmount(amount);
	}
	
	public ItemStackBuilder(Material type) {
		stack.setType(type);
	}
	
	public ItemStackBuilder(Material type, short data) {
		stack.setType(type);
		stack.setDurability(data);
	}
	public ItemStackBuilder setType(Material type) {
		stack.setType(type);
		return this;
	}
	public ItemStackBuilder setAmount(int amount) {
		stack.setAmount(amount);
		return this;
	}
	public ItemStackBuilder setData(short data) {
		stack.setDurability(data);
		return this;
	}
	public ItemStackBuilder addEnch(Enchantment ench, int level) {//Normalizes. No more level 0 turning out to be level 1. 0 removes the enchantment. Always adds unsafe, makes code easier.
		if(level-1<0)
			stack.removeEnchantment(ench);
		if(level-1>=0)
			stack.addUnsafeEnchantment(ench, level-1);
		return this;
	}
	public ItemStackBuilder setName(String name) {
		ItemMeta im = stack.getItemMeta();
		im.setDisplayName(name);
		stack.setItemMeta(im);
		return this;
	}
	public ItemStackBuilder addLore(String lore) {
		ItemMeta im = stack.getItemMeta();
		List<String> lores = im.getLore();
		lores.add(lore);
		im.setLore(lores);
		stack.setItemMeta(im);
		return this;
	}
	public ItemStackBuilder clearLores() {
		ItemMeta im = stack.getItemMeta();
		List<String> lores = im.getLore();
		lores.clear();
		im.setLore(lores);
		stack.setItemMeta(im);
		return this;
	}
	public ItemStackBuilder setLoreLine(int line, String text) {
		ItemMeta im = stack.getItemMeta();
		List<String> lores = im.getLore();
		lores.set(line, text);
		im.setLore(lores);
		stack.setItemMeta(im);
		return this;
	}
}































