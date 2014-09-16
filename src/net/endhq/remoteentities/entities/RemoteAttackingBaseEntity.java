package net.endhq.remoteentities.entities;

import net.endhq.remoteentities.EntityManager;
import net.endhq.remoteentities.api.Fightable;
import net.endhq.remoteentities.api.RemoteEntityType;
import net.endhq.remoteentities.utilities.NMSUtil;
import net.endhq.remoteentities.utilities.WorldUtilities;
import net.minecraft.server.v1_7_R4.EntityLiving;

import org.bukkit.entity.LivingEntity;

public abstract class RemoteAttackingBaseEntity<T extends LivingEntity> extends RemoteBaseEntity<T> implements Fightable
{
	public RemoteAttackingBaseEntity(int inID, RemoteEntityType inType, EntityManager inManager)
	{
		super(inID, inType, inManager);
	}

	@Override
	public void attack(LivingEntity inTarget)
	{
		if(this.m_entity == null)
			return;

		NMSUtil.setGoalTarget(this.m_entity, WorldUtilities.getNMSEntity(inTarget));
	}

	@Override
	public void loseTarget()
	{
		if(this.m_entity == null)
			return;

		NMSUtil.setGoalTarget(this.m_entity, null);
	}

	@Override
	public LivingEntity getTarget()
	{
		if(this.m_entity == null)
			return null;

		EntityLiving target = NMSUtil.getGoalTarget(this.m_entity);
		if(target != null)
			return (LivingEntity)target.getBukkitEntity();

		return null;
	}
}