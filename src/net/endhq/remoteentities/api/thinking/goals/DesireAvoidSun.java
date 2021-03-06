package net.endhq.remoteentities.api.thinking.goals;

import java.util.Random;

import net.endhq.remoteentities.api.RemoteEntity;
import net.endhq.remoteentities.api.thinking.DesireBase;
import net.endhq.remoteentities.api.thinking.DesireType;
import net.minecraft.server.v1_7_R4.*;

import org.bukkit.Location;

/**
 * Using this desire the entity will try to find a dark place to be instead of remaining at a sunny spot.
 * This is primarily seen and used by zombies and skeletons.
 */
public class DesireAvoidSun extends DesireBase
{
	protected double m_x;
	protected double m_y;
	protected double m_z;

	@Deprecated
	public DesireAvoidSun(RemoteEntity inEntity)
	{
		super(inEntity);
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	public DesireAvoidSun()
	{
		super();
		this.m_type = DesireType.PRIMAL_INSTINCT;
	}

	@Override
	public void startExecuting()
	{
		this.getRemoteEntity().move(new Location(this.getRemoteEntity().getBukkitEntity().getWorld(), this.m_x, this.m_y, this.m_z));
	}

	@Override
	public boolean shouldExecute()
	{
		EntityLiving entity = this.getEntityHandle();
		if(entity == null)
			return false;

		if(!entity.world.w())
			return false;
		else if(!entity.isBurning())
			return false;
		else if(!entity.world.i(MathHelper.floor(entity.locX), (int)entity.boundingBox.b, MathHelper.floor(entity.locZ)))
			return false;
		else
		{
			Vec3D vec = this.getShadowPlace();

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
		return !this.getNavigation().g();
	}

	protected Vec3D getShadowPlace()
	{
		EntityLiving entity = this.getEntityHandle();
		Random r = entity.aI();

		for(int i = 0; i < 10; i++)
		{
			int x = MathHelper.floor(entity.locX + r.nextInt(20) - 10);
			int y = MathHelper.floor(entity.boundingBox.b + r.nextInt(6) - 3);
			int z = MathHelper.floor(entity.locZ + r.nextInt(20) - 10);

			if(entity instanceof EntityCreature)
			{
				if(!entity.world.i(x, y, z) && ((EntityCreature)entity).a(x, y, z) < 0.0F)
					return Vec3D.a(x, y, z);
			}
			else
			{
				if(!entity.world.i(x, y, z) && (0.5F - entity.world.n(x, y, z)) < 0.0F)
					return Vec3D.a(x, y, z);
			}
		}
		return null;
	}
}
