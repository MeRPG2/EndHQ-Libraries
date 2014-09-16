package net.endhq.remoteentities.api.thinking.goals;

import net.endhq.remoteentities.api.RemoteEntity;
import net.endhq.remoteentities.api.features.TamingFeature;
import net.endhq.remoteentities.api.thinking.DesireType;
import net.endhq.remoteentities.exceptions.NotTameableException;
import net.endhq.remoteentities.utilities.NMSUtil;
import net.minecraft.server.v1_7_R4.EntityLiving;
import net.minecraft.server.v1_7_R4.EntityTameableAnimal;

/**
 * Using this desire the entity will target the entity which is attacking the tamer of this entity.
 * However, this desire will not attack the target directly.
 */
public class DesireProtectOwner extends DesireTamedBase
{
	protected EntityLiving m_ownerAttacker;
	protected int m_lastAttackedTick;

	@Deprecated
	public DesireProtectOwner(RemoteEntity inEntity, float inDistance, boolean inShouldCheckSight)
	{
		super(inEntity, inDistance, inShouldCheckSight);
		if(!(this.getEntityHandle() instanceof EntityTameableAnimal) && !this.getRemoteEntity().getFeatures().hasFeature(TamingFeature.class))
			throw new NotTameableException();

		this.m_animal = this.getEntityHandle();
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	public DesireProtectOwner(float inDistance, boolean inShouldCheckSight)
	{
		super(inDistance, inShouldCheckSight);
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	@Override
	public void onAdd(RemoteEntity inEntity)
	{
		super.onAdd(inEntity);
		if(!(this.getEntityHandle() instanceof EntityTameableAnimal) && !this.getRemoteEntity().getFeatures().hasFeature(TamingFeature.class))
			throw new NotTameableException();

		this.m_animal = this.getEntityHandle();
	}

	@Override
	public boolean shouldExecute()
	{
		if(this.getEntityHandle() == null)
			return false;

		if(!this.isTamed())
			return false;
		else
		{
			EntityLiving owner = this.getTamer();
			if(owner == null)
				return false;
			else
			{
				this.m_ownerAttacker = owner.getLastDamager();
				int lastAttackedTick = owner.aK();
				return lastAttackedTick != this.m_lastAttackedTick && this.isSuitableTarget(this.m_ownerAttacker, false);
			}
		}
	}

	@Override
	public void startExecuting()
	{
		NMSUtil.setGoalTarget(this.getEntityHandle(), this.m_ownerAttacker);
		this.m_lastAttackedTick = this.getTamer().aK();
		super.startExecuting();
	}
}