package net.endhq.remoteentities.exceptions;

@SuppressWarnings("serial")
public class NotAnOcelotException extends RuntimeException
{
	public NotAnOcelotException()
	{
		super("Entity is not an ocelot.");
	}
}