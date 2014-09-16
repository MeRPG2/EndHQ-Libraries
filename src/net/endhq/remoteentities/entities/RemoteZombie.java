package net.endhq.remoteentities.entities;

import net.endhq.remoteentities.EntityManager;
import net.endhq.remoteentities.api.EntitySound;
import net.endhq.remoteentities.api.RemoteEntityType;

import org.bukkit.entity.Zombie;

public class RemoteZombie extends RemoteAttackingBaseEntity<Zombie>
{
	public RemoteZombie(int inID, EntityManager inManager)
	{
		this(inID, null, inManager);
	}

	public RemoteZombie(int inID, RemoteZombieEntity inEntity, EntityManager inManager)
	{
		super(inID, RemoteEntityType.Zombie, inManager);
		this.m_entity = inEntity;
	}

	@Override
	public String getNativeEntityName()
	{
		return "Zombie";
	}

	@Override
	protected void setupSounds()
	{
		this.setSound(EntitySound.RANDOM, "mob.zombie.say");
		this.setSound(EntitySound.HURT, "mob.zombie.hurt");
		this.setSound(EntitySound.DEATH, "mob.zombie.death");
		this.setSound(EntitySound.STEP, "mob.zombie.step");
	}
}