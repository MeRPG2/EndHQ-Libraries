package net.endhq.cooldownapi;

import java.util.Date;

public class Cooldown {
	private String owner;
	private String name;
	private long ticksLeft;
	public Cooldown(String owner, String name, long ticksLeft) {
		this.owner=owner;
		this.name=name;
		this.ticksLeft=ticksLeft;
	}
	public boolean tick() {
		ticksLeft--;
		if(ticksLeft < 1) {
			return true;
		} else {
			return false;
		}
	}
	public String name() {
		return name;
	}
	public String owner() {
		return owner;
	}
	public long left() {
		return ticksLeft;
	}
	//Serialization
	public String serialize() {
		return owner+":-_-{}:"+name+":-_-{}:"+ticksLeft+":-_-{}:"+((new Date()).getTime());
	}
	public static Cooldown parse(String in) {
		String[] data = in.split(":-_-{}:");//50 mscs to tick
		long ticksBfStp = Long.parseLong(data[2]);
		long ticksPassed = ((new Date()).getTime())-(Long.parseLong(data[3]))/50;
		long left = ticksBfStp-ticksPassed;
		Cooldown cd = new Cooldown(data[0], data[1], left);
		return cd;
	}
}
