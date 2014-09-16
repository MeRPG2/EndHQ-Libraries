package net.endhq.remoteentities.api.thinking.selectors;

import net.minecraft.server.v1_7_R4.*;

public class EntitySelectorMonster implements IEntitySelector
{
	@Override
	public boolean a(Entity inEntity)
	{
		return inEntity instanceof IMonster;
	}
}