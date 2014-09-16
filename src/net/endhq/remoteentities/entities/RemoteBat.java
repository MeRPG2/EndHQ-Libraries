package net.endhq.remoteentities.entities;

import net.endhq.remoteentities.EntityManager;
import net.endhq.remoteentities.api.EntitySound;
import net.endhq.remoteentities.api.RemoteEntityType;
import net.minecraft.server.v1_7_R4.EntityBat;

import org.bukkit.entity.Bat;

public class RemoteBat extends RemoteAttackingBaseEntity<Bat>
{
	public RemoteBat(int inID, EntityManager inManager)
	{
		super(inID, RemoteEntityType.Bat, inManager);
	}

	public RemoteBat(int inID, RemoteBatEntity inEntity, EntityManager inManager)
	{
		this(inID, inManager);
		this.m_entity = inEntity;
	}

	@Override
	public String getNativeEntityName()
	{
		return "Bat";
	}

	public boolean isHanging()
	{
		return ((EntityBat)this.m_entity).bN();
	}

	public void setHanging(boolean inHanging)
	{
		((EntityBat)this.m_entity).setAsleep(inHanging);
	}

	@Override
	protected void setupSounds()
	{
		this.setSound(EntitySound.SLEEPING, "mob.bat.idle");
		this.setSound(EntitySound.HURT, "mob.bat.hurt");
		this.setSound(EntitySound.DEATH, "mob.bat.death");
	}
}