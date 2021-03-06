package net.endhq.remoteentities.entities;

import java.util.HashMap;
import java.util.Map;

import net.endhq.remoteentities.EntityManager;
import net.endhq.remoteentities.api.EntitySound;
import net.endhq.remoteentities.api.RemoteEntityType;

import org.bukkit.entity.MagmaCube;

public class RemoteLavaSlime extends RemoteAttackingBaseEntity<MagmaCube>
{
	public RemoteLavaSlime(int inID, EntityManager inManager)
	{
		this(inID, null, inManager);
	}

	public RemoteLavaSlime(int inID, RemoteLavaSlimeEntity inEntity, EntityManager inManager)
	{
		super(inID, RemoteEntityType.LavaSlime, inManager);
		this.m_entity = inEntity;
	}

	@Override
	public String getNativeEntityName()
	{
		return "LavaSlime";
	}

	@Override
	protected void setupSounds()
	{
		Map<String, String> hurt = new HashMap<String, String>();
		hurt.put("big", "mob.slime.big");
		hurt.put("small", "mob.slime.small");
		this.setSounds(EntitySound.HURT, hurt);
		this.setSounds(EntitySound.DEATH, new HashMap<String, String>(hurt));
		this.setSound(EntitySound.ATTACK, "mob.attack");
		this.setSounds(EntitySound.STEP, new HashMap<String, String>(hurt));
	}
}