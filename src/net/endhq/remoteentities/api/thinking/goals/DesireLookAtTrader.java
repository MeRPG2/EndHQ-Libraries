package net.endhq.remoteentities.api.thinking.goals;

import net.endhq.remoteentities.api.RemoteEntity;
import net.endhq.remoteentities.api.features.TradingFeature;
import net.endhq.remoteentities.utilities.NMSClassMap;
import net.minecraft.server.v1_7_R4.*;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftLivingEntity;

/**
 * Using this desire the villager will look at the player which is trading with it.
 */
public class DesireLookAtTrader extends DesireLookAtNearest
{
	@Deprecated
	public DesireLookAtTrader(RemoteEntity inEntity, float inMinDistance)
	{
		super(inEntity, EntityHuman.class, inMinDistance);
	}

	@SuppressWarnings("unchecked")
	@Deprecated
	public DesireLookAtTrader(RemoteEntity inEntity, Class<?> inTarget, float inMinDistance)
	{
		this(inEntity, inMinDistance);
		if(Entity.class.isAssignableFrom(inTarget))
			this.m_toLookAt = (Class<? extends Entity>)inTarget;
		else
			this.m_toLookAt = (Class<? extends Entity>)NMSClassMap.getNMSClass(inTarget);
	}

	public DesireLookAtTrader(float inMinDistance)
	{
		super(EntityHuman.class, inMinDistance);
	}

	@SuppressWarnings("unchecked")
	@Deprecated
	public DesireLookAtTrader(Class<?> inTarget, float inMinDistance)
	{
		this(inMinDistance);
		if(Entity.class.isAssignableFrom(inTarget))
			this.m_toLookAt = (Class<? extends Entity>)inTarget;
		else
			this.m_toLookAt = (Class<? extends Entity>)NMSClassMap.getNMSClass(inTarget);
	}

	@Override
	public boolean shouldExecute()
	{
		EntityLiving entity = this.getEntityHandle();
		if(!(entity instanceof EntityVillager))
		{
			if(this.getRemoteEntity().getFeatures().hasFeature(TradingFeature.class))
			{
				TradingFeature feature = this.getRemoteEntity().getFeatures().getFeature(TradingFeature.class);
				if(feature.getTradingPlayers().size() != 0)
				{
					this.m_target = ((CraftLivingEntity)feature.getTradingPlayers().get(feature.getTradingPlayers().size() - 1)).getHandle();
					return true;
				}
			}

			return false;
		}
		else
		{
			EntityVillager villager = (EntityVillager)entity;
			if(villager.bS())
			{
				this.m_target = villager.b();
				return true;
			}

			return false;
		}
	}
}