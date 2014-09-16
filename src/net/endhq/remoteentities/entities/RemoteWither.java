package net.endhq.remoteentities.entities;

import net.endhq.remoteentities.EntityManager;
import net.endhq.remoteentities.api.EntitySound;
import net.endhq.remoteentities.api.RemoteEntityType;

import org.bukkit.entity.Wither;

public class RemoteWither extends RemoteAttackingBaseEntity<Wither>
{
	public RemoteWither(int inID, EntityManager inManager)
	{
		super(inID, RemoteEntityType.Wither, inManager);
	}

	@Override
	public String getNativeEntityName()
	{
		return "WitherBoss";
	}

	@Override
	protected void setupSounds()
	{
		this.setSound(EntitySound.RANDOM, "mob.wither.idle");
		this.setSound(EntitySound.HURT, "mob.wither.hurt");
		this.setSound(EntitySound.DEATH, "mob.wither.death");
	}
}