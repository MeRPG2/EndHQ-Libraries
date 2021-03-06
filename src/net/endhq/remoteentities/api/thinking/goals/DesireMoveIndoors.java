package net.endhq.remoteentities.api.thinking.goals;

import net.endhq.remoteentities.api.RemoteEntity;
import net.endhq.remoteentities.api.thinking.DesireBase;
import net.endhq.remoteentities.api.thinking.DesireType;
import net.endhq.remoteentities.nms.RandomPositionGenerator;
import net.minecraft.server.v1_7_R4.*;

import org.bukkit.Location;

/**
 * Using this desire the entity will move into the nearest village and into a house to take shelter at night.
 */
public class DesireMoveIndoors extends DesireBase
{
	protected VillageDoor m_targetDoor;
	protected int m_x = -1;
	protected int m_z = -1;

	@Deprecated
	public DesireMoveIndoors(RemoteEntity inEntity)
	{
		super(inEntity);
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	public DesireMoveIndoors()
	{
		super();
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	@Override
	public boolean shouldExecute()
	{
		EntityLiving entity = this.getEntityHandle();
		if(entity == null)
			return false;

		if((!entity.world.w() || entity.world.P()) && !entity.world.worldProvider.g)
		{
			if(entity.aI().nextInt(50) != 0)
				return false;
			else if(this.m_x != -1 && entity.e(this.m_x, entity.locY, this.m_z) < 4)
				return false;
			else
			{
				Village nearestVillage = entity.world.villages.getClosestVillage(MathHelper.floor(entity.locX), MathHelper.floor(entity.locY), MathHelper.floor(entity.locZ), 14);

				if(nearestVillage == null)
					return false;
				else
				{
					this.m_targetDoor = nearestVillage.c(MathHelper.floor(entity.locX), MathHelper.floor(entity.locY), MathHelper.floor(entity.locZ));
					return this.m_targetDoor != null;
				}
			}
		}
		return false;
	}

	@Override
	public boolean canContinue()
	{
		return !this.getNavigation().g();
	}

	@Override
	public void startExecuting()
	{
		this.m_x = -1;
		EntityLiving entity = this.getEntityHandle();
		if(entity.e(this.m_targetDoor.getIndoorsX(), entity.locY, this.m_targetDoor.getIndoorsZ()) > 256)
		{
			Vec3D vec = RandomPositionGenerator.a(entity, 14, 3, Vec3D.a(this.m_targetDoor.getIndoorsX() + 0.5, this.m_targetDoor.getIndoorsY(), this.m_targetDoor.getIndoorsZ() + 0.5));
			if(vec != null)
				this.getRemoteEntity().move(new Location(entity.getBukkitEntity().getWorld(), vec.a, vec.b, vec.c));
		}
		else
			this.getRemoteEntity().move(new Location(entity.getBukkitEntity().getWorld(), this.m_targetDoor.getIndoorsX() + 0.5, this.m_targetDoor.getIndoorsY(), this.m_targetDoor.getIndoorsZ() + 0.5));
	}

	@Override
	public void stopExecuting()
	{
		this.m_x = this.m_targetDoor.getIndoorsX();
		this.m_z = this.m_targetDoor.getIndoorsZ();
		this.m_targetDoor = null;
	}
}