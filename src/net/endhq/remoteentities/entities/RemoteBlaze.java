package net.endhq.remoteentities.entities;

import net.endhq.remoteentities.EntityManager;
import net.endhq.remoteentities.api.EntitySound;
import net.endhq.remoteentities.api.RemoteEntityType;

import org.bukkit.entity.Blaze;

public class RemoteBlaze extends RemoteAttackingBaseEntity<Blaze>
{
	public RemoteBlaze(int inID, EntityManager inManager)
	{
		this(inID, null, inManager);
	}

	public RemoteBlaze(int inID, RemoteBlazeEntity inEntity, EntityManager inManager)
	{
		super(inID, RemoteEntityType.Blaze, inManager);
		this.m_entity = inEntity;
	}

	@Override
	public String getNativeEntityName()
	{
		return "Blaze";
	}

	@Override
	protected void setupSounds()
	{
		this.setSound(EntitySound.RANDOM, "mob.blaze.breathe");
		this.setSound(EntitySound.HURT, "mob.blaze.hit");
		this.setSound(EntitySound.DEATH, "mob.blaze.death");
	}
}