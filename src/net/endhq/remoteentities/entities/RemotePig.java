package net.endhq.remoteentities.entities;

import net.endhq.remoteentities.EntityManager;
import net.endhq.remoteentities.api.EntitySound;
import net.endhq.remoteentities.api.RemoteEntityType;

public class RemotePig extends RemoteBaseEntity
{
	public RemotePig(int inID, EntityManager inManager)
	{
		this(inID, null, inManager);
	}

	public RemotePig(int inID, RemotePigEntity inEntity, EntityManager inManager)
	{
		super(inID, RemoteEntityType.Pig, inManager);
		this.m_entity = inEntity;
	}

	@Override
	public String getNativeEntityName()
	{
		return "Pig";
	}

	@Override
	protected void setupSounds()
	{
		this.setSound(EntitySound.RANDOM, "mob.pig.say");
		this.setSound(EntitySound.HURT, "mob.pig.say");
		this.setSound(EntitySound.DEATH, "mob.pig.death");
		this.setSound(EntitySound.STEP, "mob.pig.step");
	}
}