package net.endhq.remoteentities.entities;

import net.endhq.remoteentities.EntityManager;
import net.endhq.remoteentities.api.EntitySound;
import net.endhq.remoteentities.api.RemoteEntityType;

import org.bukkit.entity.IronGolem;

public class RemoteIronGolem extends RemoteAttackingBaseEntity<IronGolem>
{
	public RemoteIronGolem(int inID, EntityManager inManager)
	{
		this(inID, null, inManager);
	}

	public RemoteIronGolem(int inID, RemoteIronGolemEntity inEntity, EntityManager inManager)
	{
		super(inID, RemoteEntityType.IronGolem, inManager);
		this.m_entity = inEntity;
	}

	@Override
	public String getNativeEntityName()
	{
		return "VillagerGolem";
	}

	@Override
	protected void setupSounds()
	{
		this.setSound(EntitySound.RANDOM, "none");
		this.setSound(EntitySound.HURT, "mob.irongolem.hit");
		this.setSound(EntitySound.DEATH, "mob.irongolem.death");
		this.setSound(EntitySound.STEP, "mob.irongolem.walk");
	}
}