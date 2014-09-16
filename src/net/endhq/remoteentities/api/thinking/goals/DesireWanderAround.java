package net.endhq.remoteentities.api.thinking.goals;

import net.endhq.remoteentities.api.RemoteEntity;
import net.endhq.remoteentities.api.thinking.DesireBase;
import net.endhq.remoteentities.api.thinking.DesireType;
import net.endhq.remoteentities.nms.RandomPositionGenerator;
import net.endhq.remoteentities.persistence.SerializeAs;
import net.minecraft.server.v1_7_R4.EntityTameableAnimal;
import net.minecraft.server.v1_7_R4.Vec3D;

import org.bukkit.Location;

/**
 * Using this desire the entity will move around randomly.
 */
public class DesireWanderAround extends DesireBase
{
	protected double m_xPos;
	protected double m_yPos;
	protected double m_zPos;
	@SerializeAs(pos = 0)
	protected float m_speed = -1;

	@Deprecated
	public DesireWanderAround(RemoteEntity inEntity)
	{
		super(inEntity);
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	public DesireWanderAround()
	{
		super();
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	public DesireWanderAround(float inSpeed)
	{
		this();
		this.m_speed = inSpeed;
	}

	@Override
	public boolean shouldExecute()
	{
		if(this.getEntityHandle() == null)
			return false;

		if(this.getEntityHandle().aM() >= 100)
			return false;
		else if(this.getEntityHandle().aI().nextInt(120) != 0)
			return false;
		else if(this.getEntityHandle() instanceof EntityTameableAnimal && ((EntityTameableAnimal)this.getEntityHandle()).isSitting())
			return false;
		else
		{
			Vec3D vector = RandomPositionGenerator.a(this.getEntityHandle(), 10, 7);
			if(vector == null)
				return false;
			else
			{
				this.m_xPos = vector.a;
				this.m_yPos = vector.b;
				this.m_zPos = vector.c;
				return true;
			}
		}
	}

	@Override
	public boolean canContinue()
	{
		return !this.getNavigation().g();
	}

	@Override
	public void startExecuting()
	{
		this.getRemoteEntity().move(new Location(this.getRemoteEntity().getBukkitEntity().getWorld(), this.m_xPos, this.m_yPos, this.m_zPos), (this.m_speed == -1 ? this.getRemoteEntity().getSpeed() : this.m_speed));
	}
}