package net.endhq.remoteentities.exceptions;

@SuppressWarnings("serial")
public class NotACreeperException extends RuntimeException
{
	public NotACreeperException()
	{
		super("Entity is not a creeper.");
	}
}