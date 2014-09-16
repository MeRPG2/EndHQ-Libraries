package net.endhq.remoteentities.api.thinking.goals;

import net.endhq.remoteentities.api.RemoteEntity;
import net.endhq.remoteentities.api.thinking.DesireBase;
import net.endhq.remoteentities.api.thinking.DesireType;
import net.endhq.remoteentities.nms.RandomPositionGenerator;
import net.endhq.remoteentities.persistence.ParameterData;
import net.endhq.remoteentities.persistence.SerializeAs;
import net.endhq.remoteentities.utilities.ReflectionUtil;
import net.minecraft.server.v1_7_R4.Vec3D;

import org.bukkit.Location;

/**
 * Using this desire the entity will move around in panic when it gets damaged.
 */
public class DesirePanic extends DesireBase
{
	protected double m_x;
	protected double m_y;
	protected double m_z;
	@SerializeAs(pos = 1)
	protected double m_speed;

	@Deprecated
	public DesirePanic(RemoteEntity inEntity)
	{
		super(inEntity);
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	public DesirePanic()
	{
		this(-1);
	}

	public DesirePanic(double inSpeed)
	{
		super();
		this.m_speed = inSpeed;
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	@Override
	public boolean shouldExecute()
	{
		if(this.getEntityHandle() == null || (this.getEntityHandle().getLastDamager() == null && !this.getEntityHandle().isBurning()))
			return false;
		else
		{
			Vec3D vec = RandomPositionGenerator.a(this.getEntityHandle(), 5, 4);
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
	public void startExecuting()
	{
		this.getRemoteEntity().move(new Location(this.getRemoteEntity().getBukkitEntity().getWorld(), this.m_x, this.m_y, this.m_z), (this.m_speed == -1 ? this.getRemoteEntity().getSpeed() : this.m_speed));
	}

	@Override
	public boolean canContinue()
	{
		return !this.getNavigation().g();
	}

	@Override
	public ParameterData[] getSerializableData()
	{
		return ReflectionUtil.getParameterDataForClass(this).toArray(new ParameterData[0]);
	}
}