package net.endhq.remoteentities.api.thinking.goals;

import net.endhq.remoteentities.api.RemoteEntity;
import net.endhq.remoteentities.api.features.TamingFeature;
import net.endhq.remoteentities.api.thinking.DesireType;
import net.endhq.remoteentities.exceptions.NotTameableException;
import net.endhq.remoteentities.utilities.NMSUtil;
import net.minecraft.server.v1_7_R4.EntityLiving;
import net.minecraft.server.v1_7_R4.EntityTameableAnimal;

/**
 * Using this desire the entity will help attacking the target of the tamer.
 */
public class DesireHelpAttacking extends DesireTamedBase
{
	protected EntityLiving m_ownerTarget;
	protected int m_lastAttackTick;

	@Deprecated
	public DesireHelpAttacking(RemoteEntity inEntity, float inDistance, boolean inShouldCheckSight)
	{
		super(inEntity, inDistance, inShouldCheckSight);
		if(!(this.getEntityHandle() instanceof EntityTameableAnimal) && !this.getRemoteEntity().getFeatures().hasFeature(TamingFeature.class))
			throw new NotTameableException();

		this.m_animal = this.getEntityHandle();
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	public DesireHelpAttacking(float inDistance, boolean inShouldCheckSight)
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
		if(this.m_animal == null)
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
				this.m_ownerTarget = owner.aL();
				int lastAttackTick = owner.aM();
				return lastAttackTick != this.m_lastAttackTick && this.isSuitableTarget(this.m_ownerTarget, false);
			}
		}
	}

	@Override
	public void startExecuting()
	{
		NMSUtil.setGoalTarget(this.getEntityHandle(), this.m_ownerTarget);
		this.m_lastAttackTick = this.getTamer().aM();
		super.startExecuting();
	}
}