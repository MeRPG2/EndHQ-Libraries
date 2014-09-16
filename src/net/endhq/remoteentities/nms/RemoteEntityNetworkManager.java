package net.endhq.remoteentities.nms;

import java.io.IOException;
import java.net.SocketAddress;

import net.endhq.remoteentities.utilities.ReflectionUtil;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import net.minecraft.server.v1_7_R4.NetworkManager;

public class RemoteEntityNetworkManager extends NetworkManager
{

	public RemoteEntityNetworkManager(MinecraftServer server) throws IOException
	{
		super(false);

		this.assignReplacementNetworking();
	}

	private void assignReplacementNetworking()
	{
		ReflectionUtil.setNetworkChannel(this, new NullChannel(null));
		ReflectionUtil.setNetworkAddress(this, new SocketAddress() {
		});
	}
}