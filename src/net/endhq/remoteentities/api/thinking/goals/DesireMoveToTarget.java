package net.endhq.remoteentities.api.thinking.goals;

import net.endhq.remoteentities.api.RemoteEntity;
import net.endhq.remoteentities.api.thinking.DesireBase;
import net.endhq.remoteentities.api.thinking.DesireType;
import net.endhq.remoteentities.nms.RandomPositionGenerator;
import net.endhq.remoteentities.persistence.ParameterData;
import net.endhq.remoteentities.persistence.SerializeAs;
import net.endhq.remoteentities.utilities.NMSUtil;
import net.endhq.remoteentities.utilities.ReflectionUtil;
import net.minecraft.server.v1_7_R4.EntityLiving;
import net.minecraft.server.v1_7_R4.Vec3D;

import org.bukkit.Location;

/**
 * Using this desire the entity will move towards its target but won't do any attacking or else.
 */
public class DesireMoveToTarget extends DesireBase
{
	@SerializeAs(pos = 1)
	protected float m_minDistance;
	protected float m_minDistanceSquared;
	protected EntityLiving m_target;
	protected double m_x;
	protected double m_y;
	protected double m_z;

	@Deprecated
	public DesireMoveToTarget(RemoteEntity inEntity, float inMinDistance)
	{
		super(inEntity);
		this.m_minDistance = inMinDistance;
		this.m_minDistanceSquared = this.m_minDistance * this.m_minDistance;
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	public DesireMoveToTarget(float inMinDistance)
	{
		super();
		this.m_minDistance = inMinDistance;
		this.m_minDistanceSquared = this.m_minDistance * this.m_minDistance;
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	@Override
	public boolean shouldExecute()
	{
		if(this.getEntityHandle() == null)
			return false;

		this.m_target = NMSUtil.getGoalTarget(this.getEntityHandle());
		if(this.m_target == null)
			return false;
		else if(this.m_target.e(this.getEntityHandle()) > this.m_minDistanceSquared)
			return false;
		else
		{
			Vec3D vec = RandomPositionGenerator.a(this.getEntityHandle(), 16, 7, Vec3D.a(this.m_target.locX, this.m_target.locY, this.m_target.locZ));

			if(vec == null)
				return false;
			else
			{
				this.m_x = vec.a;
				this.m_y = vec.b;
				this.m_z = vec.c;
				return true;
			}
		}
	}

	@Override
	public boolean canContinue()
	{
		return !this.getNavigation().g() && this.m_target.isAlive() && this.m_target.e(this.getEntityHandle()) < this.m_minDistanceSquared;
	}

	@Override
	public void stopExecuting()
	{
		this.m_target = null;
	}

	@Override
	public void startExecuting()
	{
		this.getRemoteEntity().move(new Location(this.getRemoteEntity().getBukkitEntity().getWorld(), this.m_x, this.m_y, this.m_z));
	}

	@Override
	public ParameterData[] getSerializableData()
	{
		return ReflectionUtil.getParameterDataForClass(this).toArray(new ParameterData[0]);
	}
}