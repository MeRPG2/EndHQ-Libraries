package net.endhq.remoteentities.entities;

import net.endhq.remoteentities.EntityManager;
import net.endhq.remoteentities.api.EntitySound;
import net.endhq.remoteentities.api.RemoteEntityType;

import org.bukkit.entity.Silverfish;

public class RemoteSilverfish extends RemoteAttackingBaseEntity<Silverfish>
{
	public RemoteSilverfish(int inID, EntityManager inManager)
	{
		this(inID, null, inManager);
	}

	public RemoteSilverfish(int inID, RemoteSilverfishEntity inEntity, EntityManager inManager)
	{
		super(inID, RemoteEntityType.Silverfish, inManager);
		this.m_entity = inEntity;
	}

	@Override
	public String getNativeEntityName()
	{
		return "Silverfish";
	}

	@Override
	protected void setupSounds()
	{
		this.setSound(EntitySound.RANDOM, "mob.silverfish.say");
		this.setSound(EntitySound.HURT, "mob.silverfish.hit");
		this.setSound(EntitySound.DEATH, "mob.silverfish.kill");
		this.setSound(EntitySound.STEP, "mob.silverfish.step");
	}
}