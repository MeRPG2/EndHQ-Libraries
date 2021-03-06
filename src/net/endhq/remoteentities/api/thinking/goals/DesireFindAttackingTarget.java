package net.endhq.remoteentities.api.thinking.goals;

import java.util.Iterator;
import java.util.List;

import net.endhq.remoteentities.api.RemoteEntity;
import net.endhq.remoteentities.api.thinking.DesireType;
import net.endhq.remoteentities.persistence.ParameterData;
import net.endhq.remoteentities.persistence.SerializeAs;
import net.endhq.remoteentities.utilities.NMSUtil;
import net.endhq.remoteentities.utilities.ReflectionUtil;
import net.minecraft.server.v1_7_R4.*;

/**
 * This desire searches for nearest entity which has this entity as a target and sets it as the target of this entity.
 */
public class DesireFindAttackingTarget extends DesireTargetBase
{
	@SerializeAs(pos = 4)
	protected boolean m_attackNearest;
	protected EntityLiving m_target;
	protected int m_lastAttackedTick;

	@Deprecated
	public DesireFindAttackingTarget(RemoteEntity inEntity, float inDistance, boolean inShouldCheckSight, boolean inAttackNearest)
	{
		super(inEntity, inDistance, inShouldCheckSight);
		this.m_attackNearest = inAttackNearest;
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	@Deprecated
	public DesireFindAttackingTarget(RemoteEntity inEntity, float inDistance, boolean inShouldCheckSight, boolean inShouldMelee, boolean inAttackNearest)
	{
		super(inEntity, inDistance, inShouldCheckSight, inShouldMelee);
		this.m_attackNearest = inAttackNearest;
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	public DesireFindAttackingTarget(float inDistance, boolean inShouldCheckSight, boolean inAttackNearest)
	{
		super(inDistance, inShouldCheckSight);
		this.m_attackNearest = inAttackNearest;
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	public DesireFindAttackingTarget(float inDistance, boolean inShouldCheckSight, boolean inShouldMelee, boolean inAttackNearest)
	{
		super(inDistance, inShouldCheckSight, inShouldMelee);
		this.m_attackNearest = inAttackNearest;
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	@Override
	public boolean shouldExecute()
	{
		if(this.getEntityHandle() == null)
			return false;

		EntityLiving handle = this.getEntityHandle();
		int lastAttackedTick = handle.aK();
		EntityLiving lastAttacker = handle.getLastDamager();
		return lastAttackedTick != this.m_lastAttackedTick && lastAttacker != null && this.isSuitableTarget(handle.getLastDamager(), true);
	}

	@Override
	public boolean canContinue()
	{
		EntityLiving entityTarget = this.getEntityHandle().getLastDamager();
		return entityTarget != null && entityTarget != this.m_target;
	}

	@Override
	public void startExecuting()
	{
		EntityLiving entity = this.getEntityHandle();
		NMSUtil.setGoalTarget(entity, entity.getLastDamager());
		this.m_target = entity.getLastDamager();
		this.m_lastAttackedTick = this.getEntityHandle().aK();

		if(this.m_attackNearest)
		{
			@SuppressWarnings("unchecked")
			List<EntityLiving> list = entity.world.a(this.m_target.getClass(), AxisAlignedBB.a(entity.locX, entity.locY, entity.locZ, entity.locX + 1, entity.locY + 1, entity.locZ + 1).grow(this.m_distance, 4, this.m_distance));
			Iterator<EntityLiving> it = list.iterator();

			while(it.hasNext())
			{
				EntityLiving target = it.next();

				if(this.getEntityHandle() != target && NMSUtil.getGoalTarget(target) == null && !target.c(this.getEntityHandle().getLastDamager()))
					NMSUtil.setGoalTarget(target, entity.getLastDamager());
			}
		}
		super.startExecuting();
	}

	@Override
	public void stopExecuting()
	{
		if(NMSUtil.getGoalTarget(this.getEntityHandle()) != null && NMSUtil.getGoalTarget(this.getEntityHandle()) instanceof EntityHuman && ((EntityHuman)NMSUtil.getGoalTarget(this.getEntityHandle())).abilities.isInvulnerable)
			super.stopExecuting();
	}

	@Override
	public ParameterData[] getSerializableData()
	{
		return ReflectionUtil.getParameterDataForClass(this).toArray(new ParameterData[0]);
	}
}