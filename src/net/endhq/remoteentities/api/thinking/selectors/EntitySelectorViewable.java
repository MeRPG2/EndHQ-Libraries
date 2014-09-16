package net.endhq.remoteentities.api.thinking.selectors;

import net.endhq.remoteentities.utilities.NMSUtil;
import net.minecraft.server.v1_7_R4.*;

public class EntitySelectorViewable implements IEntitySelector
{
	private final EntityLiving m_entity;

	public EntitySelectorViewable(EntityLiving inEntity)
	{
		this.m_entity = inEntity;
	}

	@Override
	public boolean a(Entity inEntity)
	{
		return NMSUtil.getEntitySenses(this.m_entity).canSee(inEntity);
	}
}