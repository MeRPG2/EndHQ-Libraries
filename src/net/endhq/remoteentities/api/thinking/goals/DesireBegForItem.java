package net.endhq.remoteentities.api.thinking.goals;

import net.endhq.remoteentities.api.RemoteEntity;
import net.endhq.remoteentities.api.thinking.DesireBase;
import net.endhq.remoteentities.api.thinking.DesireType;
import net.endhq.remoteentities.persistence.ParameterData;
import net.endhq.remoteentities.persistence.SerializeAs;
import net.endhq.remoteentities.utilities.NMSUtil;
import net.endhq.remoteentities.utilities.ReflectionUtil;
import net.minecraft.server.v1_7_R4.EntityHuman;
import net.minecraft.server.v1_7_R4.EntityWolf;

import org.bukkit.Material;

/**
 * Using this desire the entity will move towards a player and beg when he has or has one of the given item(s) in his hand.
 */
public class DesireBegForItem extends DesireBase
{
	@SerializeAs(pos = 2)
	protected final Material[] m_toBeg;
	protected EntityHuman m_nearestPlayer;
	@SerializeAs(pos = 1)
	protected float m_minDistance;
	protected float m_minDistanceSquared;
	private int m_ticks;

	@Deprecated
	public DesireBegForItem(RemoteEntity inEntity, Material... inMaterial)
	{
		this(inEntity, 5f, inMaterial);
	}

	@Deprecated
	public DesireBegForItem(RemoteEntity inEntity, float inMinDistance, Material... inMaterial)
	{
		this(inMinDistance, inMaterial);
		this.m_entity = inEntity;
	}

	public DesireBegForItem(Material... inMaterial)
	{
		this(5f, inMaterial);
	}

	public DesireBegForItem(float inMinDistance, Material... inMaterial)
	{
		super();
		this.m_toBeg = inMaterial;
		this.m_type = DesireType.HAPPINESS;
		this.m_minDistance = inMinDistance;
		this.m_minDistanceSquared = this.m_minDistance * this.m_minDistance;
	}

	@Override
	public void startExecuting()
	{
		if(this.getEntityHandle() instanceof EntityWolf)
			((EntityWolf)this.getEntityHandle()).m(true);

		this.m_ticks = 40 + this.getEntityHandle().aI().nextInt(40);
	}

	@Override
	public void stopExecuting()
	{
		this.m_nearestPlayer = null;
		if(this.getEntityHandle() instanceof EntityWolf)
			((EntityWolf)this.getEntityHandle()).m(false);
	}

	@Override
	public boolean update()
	{
		NMSUtil.getControllerLook(this.getEntityHandle()).a(this.m_nearestPlayer.locX, this.m_nearestPlayer.locY + this.m_nearestPlayer.getHeadHeight(), this.m_nearestPlayer.locZ, 10F, NMSUtil.getMaxHeadRotation(this.getEntityHandle()));
		this.m_ticks--;
		return true;
	}

	@Override
	public boolean shouldExecute()
	{
		if(this.getEntityHandle() == null)
			return false;

		this.m_nearestPlayer = this.getEntityHandle().world.findNearbyPlayer(this.getEntityHandle(), this.m_minDistance);
		return this.m_nearestPlayer != null && this.hasItemInHand(this.m_nearestPlayer);
	}

	@Override
	public boolean canContinue()
	{
		if(!this.m_nearestPlayer.isAlive())
			return false;

		if(this.getEntityHandle().e(this.m_nearestPlayer) > this.m_minDistanceSquared)
			return false;

		return this.m_ticks > 0 && this.hasItemInHand(this.m_nearestPlayer);
	}

	protected boolean hasItemInHand(EntityHuman inPlayer)
	{
		if(inPlayer.getBukkitEntity().getItemInHand() == null)
			return false;

		Material inHand = inPlayer.getBukkitEntity().getItemInHand().getType();
		for(Material m : this.m_toBeg)
		{
			if(m == inHand)
				return true;
		}
		return false;
	}

	@Override
	public ParameterData[] getSerializableData()
	{
		return ReflectionUtil.getParameterDataForClass(this).toArray(new ParameterData[0]);
	}
}
